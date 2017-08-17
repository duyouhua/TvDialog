package com.tcl.john.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.tcl.john.dialog.R;
import com.tcl.john.dialog.databinding.DialogEditBinding;

public class EditDialog extends Dialog implements View.OnClickListener {

    public static final String TAG = EditDialog.class.getSimpleName();

    private Context mContext;

    private Value mValue;
    private DialogEditBinding mDialogEditBinding;

    private OnButtonListener mOnButtonListener;

    public class Value {
        public final ObservableField<String> mTitle = new ObservableField<>();
        public final ObservableField<String> mContent = new ObservableField<>();
        public final ObservableField<String> mPositiveName = new ObservableField<>();
        public final ObservableField<String> mNegativeName = new ObservableField<>();
    }

    public EditDialog(Context context) {
        super(context);
        mValue = new Value();
        this.mContext = context;
    }

    public EditDialog(Context context, int themeResId, String title, String content) {
        super(context, themeResId);
        this.mContext = context;
        mValue = new Value();
        mValue.mTitle.set(title);
        mValue.mContent.set(content);
    }

    protected EditDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        mValue = new Value();
    }

    public void setTitle(String title) {
        mValue.mTitle.set(title);
    }

    public void setContent(String content) {
        mValue.mContent.set(content);
    }

    public void setPositiveBtn(String name) {
        mValue.mPositiveName.set(name);
    }

    public void setNegativeBtn(String name) {
        mValue.mNegativeName.set(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "---onCreate---");
        mDialogEditBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_edit, null, false);
        mDialogEditBinding.setValue(mValue);
        setContentView(mDialogEditBinding.getRoot());
        setCanceledOnTouchOutside(false);
        setListener();
    }

    private void setListener() {
        mDialogEditBinding.confirmTv.setOnClickListener(this);
        mDialogEditBinding.cancelTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnButtonListener != null) {
            if (v == mDialogEditBinding.confirmTv) {
                mOnButtonListener.confirm(mDialogEditBinding.contentEt.getText().toString());

            } else if (v == mDialogEditBinding.cancelTv) {
                mOnButtonListener.cancel();

            }
        }
    }

    public interface OnButtonListener {
        void confirm(String text);

        void cancel();
    }

    public void setOnButtonListener(OnButtonListener onButtonListener) {
        mOnButtonListener = onButtonListener;
    }
}

