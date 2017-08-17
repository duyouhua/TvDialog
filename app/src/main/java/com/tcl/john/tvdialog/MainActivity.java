package com.tcl.john.tvdialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tcl.john.dialog.utils.WidgetUtil;
import com.tcl.john.dialog.widget.EditDialog;
import com.tcl.john.dialog.widget.PasswordDialog;
import com.tcl.john.dialog.widget.ToastDialog;
import com.tcl.john.tvdialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int DELAY_TIME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setBind(this);
    }

    public void showRenameDialog(View view) {
        final EditDialog renameDialog = WidgetUtil.createEditDialog(this, R.style.CustomDialog,
                R.string.usb_name_edit_title, R.string.usb_name_edit_content,
                R.string.alert_yes, R.string.alert_no, false);

        renameDialog.setOnButtonListener(new EditDialog.OnButtonListener() {
            @Override
            public void confirm(String text) {
                // TODO: 2017/8/16
            }

            @Override
            public void cancel() {
                // TODO: 2017/8/16
            }
        });
        renameDialog.show();
    }

    public void showPinDialog(View view) {
        final PasswordDialog passwordDialog = new PasswordDialog(this);
        passwordDialog.setCheckResultCallback(new PasswordDialog.CheckResultCallback() {
            @Override
            public boolean isPasswordCorrect(String password) {
                // TODO: 2017/8/16
                return false;
            }

            @Override
            public void correct() {
                Log.d(TAG, "correct: ");
                // TODO: 2017/8/16
            }

            @Override
            public void incorrect() {
                Log.d(TAG, "incorrect: ");
                // TODO: 2017/8/16
            }
        });
        passwordDialog.show();
    }

    public void showToastDialog(View view) {
        ToastDialog toastDialog = new ToastDialog(this, R.string.tip_msg, 600, 300, false);
        toastDialog.setMaxLines(7);
        toastDialog.show(DELAY_TIME);
    }

}
