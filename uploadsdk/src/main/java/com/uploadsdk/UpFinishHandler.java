package com.uploadsdk;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

/**
 * 定义数据或文件上传结束后的处理动作
 */

public abstract class UpFinishHandler implements UpCompletionHandler {
    private static final String TAG = "UpFinishHandler";

    @Override
    public void complete(String key, ResponseInfo info, JSONObject response) {
        // 打印上传完成返回日志信息
        Log.i(TAG, info.toString());
        if (info.isOK()) {
            //上传成功
            onUpSuccess(key, response);
        } else {
            //上传失败
            onUpFailure(info.statusCode, info.error);
        }
    }


    /**
     * 上传完成后处理动作必须实现的方法
     *
     * @param fileName 文件上传保存名称
     * @param response 上传完成的回复内容
     */
    public abstract void onUpSuccess(String fileName, JSONObject response);

    public abstract void onUpFailure(int code, String msg);


}
