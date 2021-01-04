package com.oes.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oes.config.Url;
import com.oes.util.MD5Utils;
import com.oes.consist.TransactionConstants;
import com.oes.query.BankDetail;
import com.oes.query.OrderDetail;
import com.oes.query.OrderPostData;
import com.oes.query.OrderResult;
import com.oes.util.EncryptionUtils;
import com.oes.vo.OrderItemVO;
import com.oes.vo.OrderListVO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20209:11 PM
 */
@Service
public class TransactionService {
    @Resource
    private RestTemplate restTemplate;

    /**
     * 生成订单json
     * @param orderId
     * @return
     */
    public OrderResult getOrderResult (Integer orderId) {
        //TODO test
        OrderListVO olv = restTemplate.getForObject(Url.SERVICE_SHOPPING+"/order/api/"+orderId, OrderListVO.class);
        if (olv == null) {
            return null;
        }
        OrderResult orderResult = new OrderResult();
        orderResult.setOrder_id(olv.getOrderId().toString());
        orderResult.setPay_mode(TransactionConstants.PAY_MODE);
        orderResult.setTranstype(TransactionConstants.TRANSTYPE);
        orderResult.setTotal_money(olv.getTotal().toString());
        orderResult.setNotifyurl(TransactionConstants.NOTIFY_URL);
        // orderResult.setFround_notifyurl(TransactionConstants.FROUND_NOTIFY_URL);
        orderResult.setFround_notifyurl("");
        orderResult.setOrder_ip(TransactionConstants.ORDER_ID);
        orderResult.setApplication_id(TransactionConstants.APPLICATION_ID);
        orderResult.setRemark1("");
        orderResult.setRemark2("");
        orderResult.setRemark3("");
        orderResult.setRemark4("");
        orderResult.setRemark5("");
        orderResult.setBank_detail(new ArrayList<>());
        BankDetail bankDetail = new BankDetail();
        bankDetail.setBank_number(TransactionConstants.BANK_NUMBER);
        bankDetail.setBank_number_name(TransactionConstants.BANK_NUMBER_NAME);
        bankDetail.setOrder_detail(new ArrayList<>());
        for (OrderItemVO oiv : olv.getOrderItemVOList()) {
            OrderDetail od = new OrderDetail();
            JSONObject json = oiv.getOrderItem().getJSONObject("object");
            od.setCategory(json.getString("merType"));
            od.setPrice(json.getBigDecimal("coursePrice").toString());
            od.setGood_id(json.getInteger("merId").toString());
            od.setGood_title(json.getString("courseName"));
            od.setShop_id(json.getInteger("orgId").toString());
            od.setShop_name(json.getString("orgName"));
            od.setRemark1("");
            od.setRemark2("");
            od.setRemark3("");
            od.setRemark4("");
            od.setRemark5("");
            bankDetail.getOrder_detail().add(od);
        }
        orderResult.getBank_detail().add(bankDetail);
        return orderResult;
    }

    /**
     * RSA加密订单数据，这里对长订单加密还有问题
     * @param orderId
     * @return
     */
    public String RSA(Integer orderId) {
        OrderResult orderResult = getOrderResult(orderId);
        if (orderResult == null) {
            return null;
        }
        String jsonStr = JSONArray.toJSON(orderResult).toString();
        // 加密后的订单信息需要替换特殊字符串
        return EncryptionUtils.replaceAfterEncryption(EncryptionUtils.RSABackendEncrypt(jsonStr));
    }

    public String RSA(OrderResult orderResult) {
        if (orderResult == null) {
            return null;
        }
        String jsonStr = JSONArray.toJSON(orderResult).toString();
        // 加密后的订单信息需要替换特殊字符串
        return EncryptionUtils.replaceAfterEncryption(EncryptionUtils.RSABackendEncrypt(jsonStr));
    }


    public String doPost(OrderPostData orderPostData) {
        String url = TransactionConstants.ORDER_POST_URL;
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(JSONObject.toJSONString(orderPostData), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, formEntity, String.class);
        return response.getBody();
    }

    /**
     * 生成订单加密数据
     * @param orderId
     * @return
     */
    public OrderPostData getPostData(Integer orderId) {
        OrderPostData postData = null;
        try {
            OrderResult orderResult = getOrderResult(orderId);
            String jsonStr = JSONArray.toJSON(orderResult).toString();
            // RSA加密
            String rsaStr = EncryptionUtils.RSABackendEncrypt(jsonStr);
            // 加密后替换特殊字符
            String rsaResult  = EncryptionUtils.replaceAfterEncryption(rsaStr);
            // 进行URL编码
            String urlEncodeResult = URLEncoder.encode(rsaResult, "UTF-8");
            // 生成SIGN，要小写
            String sign = MD5Utils.MD5(urlEncodeResult + TransactionConstants.KEY).toLowerCase();
            postData = new OrderPostData(sign, TransactionConstants.APPLICATION_ID, urlEncodeResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return postData;
    }

    /**
     * 生成订单加密数据
     * @param result
     * @return
     */
    public OrderPostData getPostData(String result) {
        OrderPostData postData = null;
        try {
            // 进行URL编码
            result = URLEncoder.encode(result, "UTF-8");
            // 生成SIGN
            String sign = MD5Utils.MD5(result + TransactionConstants.KEY).toLowerCase();
            postData = new OrderPostData(sign, TransactionConstants.APPLICATION_ID, result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return postData;
    }


    /**
     * 对支付地址进行加密
     * @param str
     * @return
     */
    public String encryptPayAddr(String str) {
        // 使用前台公钥对支付地址进行加密，
        String rsaStr = EncryptionUtils.RSAFrontendEncrypt(str);
        // 并将'/'替换为'_'
        rsaStr = rsaStr.replace("/", "_");
        // 替换特殊字符
        rsaStr = EncryptionUtils.replaceAfterReceive(rsaStr);
        String payLink = TransactionConstants.PAY_ADDR_POST_URL + rsaStr;
        System.out.println("加密后的支付地址：" + payLink);
        return payLink;
    }
}
