package com.oes.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20208:34 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPostData {
    /**
     * 签名，生成规则为 订单加密后的字符串 拼接 上方提供的key 后再进行 MD5加密：sign=MD5(RSA(订单数据)+key)
     */
    private String sign;

    private String application_id;

    /**
     * result 为使用提供的我方后台公钥对订单数据进行加密后得字符串
     */
    private String result;
}
