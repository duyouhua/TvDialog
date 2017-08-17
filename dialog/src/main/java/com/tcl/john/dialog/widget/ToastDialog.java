package com.tcl.john.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

import com.tcl.john.dialog.R;
import com.tcl.john.dialog.databinding.DialogToastBinding;
import com.tcl.john.dialog.utils.KeyMap;

public class ToastDialog extends Dialog {
    private static final String TAG = ToastDialog.class.getSimpleName();
    private static final int DISMISS_MESSAGE = 0;

    private Context mContext;

    private TextView tvRemind;

    private Handler uiHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case DISMISS_MESSAGE:
                    dismiss();
                    break;
            }
        }

        ;
    };

    public ToastDialog(Context context) {
        this(context, null, 0, 0, false);
    }

    public ToastDialog(Context context, String text) {
        this(context, text, 0, 0, false);
    }

    public ToastDialog(Context context, int iResID) {
        this(context, iResID, 0, 0, false);
    }

    public ToastDialog(Context context, int iResID, int iWidth, int iHeight, boolean systemAlert) {
        this(context, context.getString(iResID), iWidth, iHeight, systemAlert);
    }

    public ToastDialog(Context context, String text, int iWidth, int iHeight, boolean systemAlert) {
        super(context, R.style.CustomDialog);
        mContext = context;
        buildView(context, iWidth, iHeight, systemAlert);
        if (null != text) {
            setMessage(text);
        }
    }

    private void buildView(Context context, int width, int height, boolean systemAlert) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        DialogToastBinding dialogToastBinding = DataBindingUtil.inflate(mInflater, R.layout.dialog_toast, null, false);
        setContentView(dialogToastBinding.getRoot());

        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        if (systemAlert) {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(params);

        tvRemind = dialogToastBinding.remindText;
        if (width > 0 && height > 0) {
            LayoutParams lp = tvRemind.getLayoutParams();
            lp.width = width;
            lp.height = height;
            tvRemind.setLayoutParams(lp);
        }
    }

    public void setMessage(CharSequence text) {
        tvRemind.setText(text);
    }

    public void setMessage(int iId) {
        tvRemind.setText(iId);
    }

    public void setMaxLines(int iLines) {
        tvRemind.setMaxLines(iLines);
    }

    public void setIcon(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvRemind.setCompoundDrawablePadding(10);
        tvRemind.setCompoundDrawables(drawable, null, null, null);
    }

    public void setIcon(int iId) {
        setIcon(ContextCompat.getDrawable(mContext, iId));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyMap.KEY_BACK || keyCode == KeyMap.KEY_EXIT) {
            dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void show() {
        show(0);
    }

    public void show(int iTimeOut) {
        super.show();
        if (iTimeOut > 0) {
            uiHandler.sendEmptyMessageDelayed(100, iTimeOut);
        }
    }


    public void dismiss() {
        if (isShowing()) {
            uiHandler.removeMessages(DISMISS_MESSAGE);
            super.dismiss();
        }
    }

}
