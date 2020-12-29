package com.oes.constant.consist;

/**
 * 手机验证码相关常量
 */

public class VerifyCodeConstants {
    public static final String Netocr_KEY = "AH8uz2DPRqTsmzbHmEZBjC";
    public static final String Netocr_SECRET = "4925cbc915044b73bccabb1855f465c1";
    public static final String IDENTITY_CARD_OCR_DISTINGUISH_URL = "https://netocr.com/api/recogliu.do";
    public static final String IDENTITY_CARD_VALID_DISTINGUISH_URL = "https://netocr.com/verapi/veridenNoOrd.do";
    public static final String VERIFYCODEURL = "http://sdk.entinfo.cn:8061/mdgxsend.ashx?sn=SDK-AMD-010-01830&pwd=06D89145FAD45B7D06E59250CCBCB419&mobile=";
    public static final String VERIFYCODEEND = "【光大银行西安分行】";

    /*手机验证码的过期时间，2分钟*/
    //public static final int VERIFY_CODE_EXPIRE = 2 * 60*60;

    /*redis中存放的手机验证码的前缀*/
    public static final String PHONE_VERIFY_CODE_REDIS_KEY_PREFIX = "verifyCode:";

    /* 邮箱的正则表达式 */
    public static final String MAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /* 手机的正则表示式 */
    public static final String PHONE_REGEX = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    /* 密码的最短位数 */
    public static final int PWD_MIN_LENGTH = 8;

}
