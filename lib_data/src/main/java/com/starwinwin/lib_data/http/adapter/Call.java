package com.starwinwin.lib_data.http.adapter;

import com.starwinwin.lib_data.http.callback.AbsCallback;
import com.starwinwin.lib_data.http.model.Response;
import com.starwinwin.lib_data.http.request.BaseRequest;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/9/11
 * 描    述：请求的包装类
 * 修订历史：
 * ================================================
 */
public interface Call<T> {
    /** 同步执行 */
    Response<T> execute() throws Exception;

    /** 异步回调执行 */
    void execute(AbsCallback<T> callback);

    /** 是否已经执行 */
    boolean isExecuted();

    /** 取消 */
    void cancel();

    /** 是否取消 */
    boolean isCanceled();

    Call<T> clone();

    BaseRequest getBaseRequest();
}