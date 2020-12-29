package com.oes.constant.consist;

/**
 * @author : JQK
 * @date : 2020-07-22 8:50
 * @description : redis存储的key
 */
public class RedisKeyConstants {

    /** 课程目录树key */
    public static final String PRE_COURSE_CATEGORY = "course_category:";


    /** 注册用户account的占位，在用户注册时，先要判断account是否可用，
     * 如果可用把该account放入redis起到占位的作用，防止因为多线程造成account重复
     * */
    public static final String PRE_REGISTER_ACCOUNT_LIST = "register:account";

}
