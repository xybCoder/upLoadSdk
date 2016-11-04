package com.uploadsdk;

import android.content.Context;

import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.File;

/**
 * 上传下载管理类
 */

public class UpLoadUtil {

    private static UpLoadUtil mUpLoadUtil;
    private static UploadManager mUpLoadManager;
    private volatile boolean isCancelled = false;

    public static UpLoadUtil getInstance() {
        if (mUpLoadUtil == null) {
            mUpLoadUtil = new UpLoadUtil();
        }
        return mUpLoadUtil;
    }

    public static UploadManager getUpLoadManager() {
        if (mUpLoadManager == null) {
            mUpLoadManager = new UploadManager();
        }
        return mUpLoadManager;
    }

    /**
     * 上传文件
     *
     * @param data     上传的数据
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     */

    private void UpFile(Context context, byte[] data, String fileName, String token,
                        final UpFinishHandler handler) {
        getUpLoadManager().put(data, fileName, token, handler, new UploadOptions(null, null, false, new ProgressDialogHandler(context, true), new UpCancellationSignal() {

            @Override
            public boolean isCancelled() {
                return isCancelled;
            }
        }));
    }

    /**
     * 上传文件
     *
     * @param data     上传的数据
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     * @param options  上传数据的可选参数
     */
    private void UpFile(Context context, byte[] data, String fileName, String token,
                        final UpFinishHandler handler, UploadOptions options) {
        getUpLoadManager().put(data, fileName, token, handler, options);
    }


    /**
     * 上传文件
     *
     * @param context
     * @param filePath 上传的文件路径
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     */
    private void UpFile(Context context, String filePath, String fileName, String token, UpFinishHandler handler) {
        getUpLoadManager().put(new File(filePath), fileName, token, handler, new UploadOptions(null, null, false, new ProgressDialogHandler(context, true), new UpCancellationSignal() {
            @Override
            public boolean isCancelled() {
                return isCancelled;
            }
        }));
    }

    /**
     * 上传文件
     *
     * @param filePath 上传的文件路径
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     * @param options  上传数据的可选参数
     */
    private void UpFile(String filePath, String fileName, String token, final UpFinishHandler handler,
                        final UploadOptions options) {
        getUpLoadManager().put(new File(filePath), fileName, token, handler, options);
    }


    /**
     * 上传文件
     *
     * @param file     上传的文件对象
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     * @param options  上传数据的可选参数
     */
    private void UpFile(File file, final String fileName, String token, final UpFinishHandler handler,
                        final UploadOptions options) {
        getUpLoadManager().put(file, fileName, token, handler, options);
    }

    /**
     * 上传文件
     *
     * @param context
     * @param file     上传的文件对象
     * @param fileName 上传文件保存的文件名
     * @param token    上传凭证
     * @param handler  上传完成的后续处理动作
     */
    private void UpFile(Context context, File file, String fileName, String token, UpFinishHandler handler) {
        getUpLoadManager().put(file, fileName, token, handler, new UploadOptions(null, null, false, new ProgressDialogHandler(context, true), new UpCancellationSignal() {
            @Override
            public boolean isCancelled() {
                return isCancelled;
            }
        }));
    }


    private void cancleUp() {
        isCancelled = true;
    }


}
