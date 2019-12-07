package com.androidlmy.makecolor.net.api;


import com.androidlmy.makecolor.bean.CodeMessage;
import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.bean.LogBean;
import com.androidlmy.makecolor.bean.UpDataBean;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * @功能:
 * @Creat 2019/10/22 14:57
 * @User Lmy
 * @Compony zaituvideo
 */
public interface LoginApi {
    /**
     * 登录
     */
    @FormUrlEncoded//@body格式不可以和FormUrlEncoded同时使用
    @POST("islogin")
    //这里不能为空 即使没有数据
    Call<LogBean> login(@FieldMap Map<String, String> paramsMap);

    /**
     * 注册
     */
//    @FormUrlEncoded//@body格式不可以和FormUrlEncoded同时使用
    @POST("register")
    @Multipart
    //这里不能为空 即使没有数据
    Call<CodeMessage> register(@QueryMap Map<String, String> paramsMap, @Part MultipartBody.Part multipartFile);

    /**
     * 修改密码
     */
    @FormUrlEncoded//@body格式不可以和FormUrlEncoded同时使用
    @POST("alterpassword")
    //这里不能为空 即使没有数据
    Call<CodeMessage> alterpassword(@FieldMap Map<String, String> paramsMap);

    /**
     * 更新信息 GET请求参数注解用QueryMap
     *
     * @QueryMap Map<String, String> paramsMap
     */
    @GET("appupdate")
    Call<UpDataBean> updataapp(@QueryMap Map<String, String> paramsMap);

    /**
     * 更新信息 GET请求参数注解用QueryMap
     *
     * @QueryMap Map<String, String> paramsMap
     */
    @GET("selectall")
    Call<FriendListBean> selectall();

    /**
     * 登录
     */
    @FormUrlEncoded//@body格式不可以和FormUrlEncoded同时使用
    @POST("addcategory")
    //这里不能为空 即使没有数据
    Call<CodeMessage> addclass(@FieldMap Map<String, String> paramsMap);


}
