package com.tcl.john.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.tcl.john.dialog.R;
import com.tcl.john.dialog.databinding.DialogPasswordBinding;
import com.tcl.john.dialog.utils.KeyMap;

public class PasswordDialog extends Dialog {

    private static final int PASSWORD_DIGITS = 4;

    private DialogPasswordBinding mDialogPasswordBinding;

    private Context mContext;
    private View passwordView;
    private Dialog passwordDialog;
    private ImageView[] passView = new ImageView[4];

    private OtherKeyDown otherKeyDown;
    private CheckResultCallback checkResultCallback;

    private int passwordValue;
    private StringBuffer strBufferPassword;

    public PasswordDialog(Context context) {
        super(context);
        this.mContext = context;

        initView();
        initData();
        initDialog();
    }

    private void initView() {
        mDialogPasswordBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_password, null, false);
        passwordView = mDialogPasswordBinding.getRoot();
        passwordView.setFocusable(true);
        passView[0] = mDialogPasswordBinding.pass0;
        passView[1] = mDialogPasswordBinding.pass1;
        passView[2] = mDialogPasswordBinding.pass2;
        passView[3] = mDialogPasswordBinding.pass3;
    }

    private void initData() {
        strBufferPassword = new StringBuffer();
    }

    private void initDialog() {
        passwordDialog = new Dialog(mContext, R.style.CustomDialog);
        Window dialogWindow = passwordDialog.getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        passwordDialog.setCancelable(true);

        passwordDialog.setCanceledOnTouchOutside(false);
        passwordDialog.setOnKeyListener(inputPwdListener);
        passwordDialog.addContentView(passwordView, params);
    }

    private void inputIncorrect() {
        passView[0].setImageResource(R.mipmap.pin_icon_lock);
        passView[1].setImageResource(R.mipmap.pin_icon_lock);
        passView[2].setImageResource(R.mipmap.pin_icon_lock);
        passView[3].setImageResource(R.mipmap.pin_icon_lock);
        strBufferPassword.setLength(0);
        passwordValue = 0;
    }

    @Override
    public void dismiss() {
        if (passwordDialog != null && passwordDialog.isShowing()) {
            passwordValue = 0;
            strBufferPassword.setLength(0);
            passwordDialog.dismiss();
        }
    }

    public void show() {
        passwordDialog.show();
        passwordView.requestFocus();
    }

    private OnKeyListener inputPwdListener = new OnKeyListener() {

        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                int iNumber;
                switch (keyCode) {
                    case KeyMap.KEY_0:
                        iNumber = 0;
                        break;
                    case KeyMap.KEY_1:
                        iNumber = 1;
                        break;
                    case KeyMap.KEY_2:
                        iNumber = 2;
                        break;
                    case KeyMap.KEY_3:
                        iNumber = 3;
                        break;
                    case KeyMap.KEY_4:
                        iNumber = 4;
                        break;
                    case KeyMap.KEY_5:
                        iNumber = 5;
                        break;
                    case KeyMap.KEY_6:
                        iNumber = 6;
                        break;
                    case KeyMap.KEY_7:
                        iNumber = 7;
                        break;
                    case KeyMap.KEY_8:
                        iNumber = 8;
                        break;
                    case KeyMap.KEY_9:
                        iNumber = 9;
                        break;
                    case KeyMap.KEY_EXIT:
                    case KeyMap.KEY_BACK:
                        if (otherKeyDown != null) {
                            otherKeyDown.onKeyDown(keyCode, event);
                            return true;
                        }
                        dismiss();
                        return true;
                    default:
                        iNumber = -1;
                        break;
                }
                if (iNumber != -1) {
                    updateView(passwordValue);

                    strBufferPassword.append(String.valueOf(iNumber));
                    passwordValue++;
                    if (PASSWORD_DIGITS <= passwordValue && checkResultCallback != null) {
                        if (checkResultCallback.isPasswordCorrect(strBufferPassword.toString())) {
                            dismiss();
                            checkResultCallback.correct();
                        } else {
                            inputIncorrect();
                            checkResultCallback.incorrect();
                        }
                    }
                }
            }
            return false;
        }
    };

    private void updateView(int position) {
        if (position < 0 || position > 4) {
            for (int i = 0; i < 4; i++) {
                passView[i].setImageResource(R.mipmap.pin_icon_lock);
            }
            return;
        }
        passView[position].setImageResource(R.mipmap.pin_icon_unlock);
        passwordView.invalidate();

    }

    public interface OtherKeyDown {
        boolean onKeyDown(int keyCode, KeyEvent event);
    }

    public void setOtherKeyDown(OtherKeyDown keyDown) {
        this.otherKeyDown = keyDown;
    }

    public interface CheckResultCallback {
        boolean isPasswordCorrect(String password);

        void correct();

        void incorrect();
    }

    public void setCheckResultCallback(CheckResultCallback checkResultCallback) {
        this.checkResultCallback = checkResultCallback;
    }
}
