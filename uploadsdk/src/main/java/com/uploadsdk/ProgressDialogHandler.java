package com.uploadsdk;
import android.app.ProgressDialog;
import android.content.Context;

import com.qiniu.android.storage.UpProgressHandler;

/**
 * 自定义上传进度处理
 */

public class ProgressDialogHandler implements UpProgressHandler {
    private Context context;
    private boolean cancelable;
    private ProgressDialog pd;

    public ProgressDialogHandler(Context context, boolean cancelable) {
        this.context = context;
        this.cancelable = cancelable;
        pd = new ProgressDialog(context);
        pd.setCancelable(cancelable);
    }


    @Override
    public void progress(String key, double percent) {
        int progress = (int) (percent * 1000);
        if (progress != 1000) {
            if (!pd.isShowing()) {
                pd.show();
            }
        } else {
            if (pd != null) {
                pd.dismiss();
                pd = null;
            }
        }
    }
}
