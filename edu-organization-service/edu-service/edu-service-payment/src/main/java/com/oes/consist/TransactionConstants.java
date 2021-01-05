package com.oes.consist;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20208:21 PM
 */
public class TransactionConstants {
    // public static String TEST_URL = "101.37.22.133";

    public static String TEST_URL = "192.168.1.132";

    public static String HOST_IP = "192.168.1.122";

    public static String HTTP = "http://";

    public static String HTTPS = "https://";

    public static String ORDER_POST_URL = HTTP + TEST_URL + ":8001/v1/orders/";

    public static String PAY_ADDR_POST_URL = HTTPS + TEST_URL + ":3000/#/payPage/";

    public static String NOTIFY_URL = "http://" + HOST_IP + "/offline-education-system/transaction/notifyUrl/";

    public static String FROUND_NOTIFY_URL = "http://localhost/offline-education-system/transaction/notifyUrl/";

    public static String ORDER_ID = "192.168.3.132";


    /**
     * 支付方式
     */
    public static String PAY_MODE = "alipay";
    /**
     * 支付通道
     */
    public static String TRANSTYPE = "0000";

    public static String APPLICATION_ID = "1319228416209600512";


    public static String KEY = "bbda32de628b08af4a480a517f5cf666";

    /**
     * 后台公钥现在试
     */

    public static String BACKEND_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoFJUxSqK5CDmWE/P724uBBym/lRo/GIxPns6CgCPPuRtBhutAdb+tShdXfYSagXFYOw2K2BmmlWqPX1mJMjkLLNJHNXxS12AfkvkBWETDZtH6LENc3zPdNzR5h8p/ZRdO7EJIUOyizm9i/18xHzYclV5aLDLJWJU9w/TLbvccnwIDAQAB";
    /**
     * 前台公钥
     */
    public static String FRONTEND_PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeSqVk4qfz5LbZlCy4UR89bL5Q" +
            "fUV8MLPGnvVEscepuqNBa/lE62/q5Ji1SBrDPsD789RL3A7k0gmudMKZ8Ufe/QzT" +
            "mOGQPdQlfgDvebtbwiAXB5AcjPB/ff1kCvE1B9twyIzzylFJ5BTUqxFm3cTbzk9l" +
            "tX9NagxsF9keZbmBOwIDAQAB";

    /**
     * 推送数据使用的私钥
     */
    public static String RSA_PRIVATE_KEY =
                "MIICXQIBAAKBgQCoFJUxSqK5CDmWE_P724uBBym_lRo_GIxPns6CgCPPuRtBhutAdb-tShdXfYSagXFYOw2K2BmmlWqPX1mJMjkLLNJHNXxS12AfkvkBWETDZtH6LENc3zPdNzR5h8p_ZRdO7EJIUOyizm9i_18xHzYclV5aLDLJWJU9w_TLbvccnwIDAQABAoGBAI-0sW5ocsIi3yGKw8MljZvu5OnxcHRb57M3gzcemf_DMyOOWFtPkWd2_ogGQDbdz2vxaX25kAzWmTol-uaLcyrQ0_Lt3wr1IhvhSEIxwdZae_Q8_8zd1CIIkf4T3tLopaOUab0p2UjpjOIvkk3pb0gx0bjCAebVdaI1rBzZB4dxAkEA1-1wNMr3UUxkTLYxCjdHz7c1r-zNkRcb3Fz8fF7CjQG3v8oEQ_TRW7F8K2M0Ic6x7v4U3dvU9yr7UcRPuANV6QJBAMdF9pGlLZRorZEDlt8Ja7CIYcTr5OSY7M84luP9EMwJ5L3Rb4-Eg3OVWKDuWAJpEU7MX5-IQIMmA2aR0od1YUcCQQCsWDroGFxgin2_8OehhNAPjecPHQ5S0UQOljV8u1HDpGxaa08OQpjb_ATkyJCyrzy8ShazoiQQiEECWZNbh2YJAkAgehWSbSI9ksXe4X_gP8_OSJvulSeL3iSSLT3iUGdBacde7FhLQqSN3ORB7jZnxins9sAK5xDkjvh06gUu0IGXAkBLq63EHBtfZD0ES3Gkhsd4bIqNWVtGH5cRvLSzpMDg4QemRuGl_IsWUALIkTc_NgyRhkAIeI-C-K34hKQAbu3L";

    public static String BANK_NUMBER = "13011301";

    public static String BANK_NUMBER_NAME = "学而思";
}
