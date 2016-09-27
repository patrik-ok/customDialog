package com.rlk.feedback.util;

import com.rlk.feedback.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DialogUtils {
	public DialogUtils(Context mContext) {
		// 1. View Object
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View layout = inflater.inflate(R.layout.notice_dialog_layout, null);
		setLayout(layout);
		// 2. setCustomView
		Dialog dialog_buider = new Dialog(mContext);
		dialog_buider.setContentView(layout);
		setDialog(dialog_buider);
	}

	public Dialog createNoticeDialog() {

		// 3. Tittle
		TextView dialog_title = (TextView) getLayout().findViewById(R.id.dialog_title);
		dialog_title.setVisibility(mTitle == null ? View.GONE : View.VISIBLE);
		dialog_title.setText(dialog_title.getVisibility() == View.GONE ? "" : mTitle);

		// 4. Message
		TextView dialog_msg = (TextView) getLayout().findViewById(R.id.dialog_msg);
		dialog_msg.setVisibility(mMessage == null ? View.GONE : View.VISIBLE);
		dialog_msg.setText(dialog_msg.getVisibility() == View.GONE ? "" : mMessage);

		// 5. PositiveButton
		TextView btn_ok = (TextView) getLayout().findViewById(R.id.btn_ok);
		btn_ok.setVisibility(mPositiveButtonString == null ? View.GONE : View.VISIBLE);
		btn_ok.setText(btn_ok.getVisibility() == View.GONE ? "" : mPositiveButtonString);
		btn_ok.setOnClickListener(mOnPositiveClick);

		// 6. NegativeButton
		TextView btn_cancel = (TextView) getLayout().findViewById(R.id.btn_cancel);
		btn_cancel.setVisibility(mNegativeButtonString == null ? View.GONE : View.VISIBLE);
		btn_cancel.setText(btn_cancel.getVisibility() == View.GONE ? "" : mNegativeButtonString);
		btn_cancel.setOnClickListener(mOnNegativeClick);

		return getDialog();
	}

	private String mTitle;
	private String mMessage;
	private String mPositiveButtonString;
	private String mNegativeButtonString;
	private OnCreateNoticeDialog mOnPositiveClick;
	private OnCreateNoticeDialog mOnNegativeClick;
	private View layout;
	private Dialog mDialog;

	private View getLayout() {
		return layout;
	}

	private void setLayout(View layout) {
		this.layout = layout;
	}

	private void setDialog(Dialog dialog) {
		mDialog = dialog;
	}

	private Dialog getDialog() {
		return mDialog;
	}

	public abstract class OnCreateNoticeDialog implements OnClickListener {
		Dialog dialog;
		boolean isNull = true;

		public OnCreateNoticeDialog() {
			dialog = getDialog();
		}

		@Override
		public void onClick(View arg0) {
			if (dialog != null) {
				isNull = false;
				dialog.dismiss();
			}
			onNoticeClick(isNull + "");
		}

		public abstract void onNoticeClick(String iString);
	}

	public DialogUtils setTitle(String title) {
		mTitle = title;
		return this;
	}

	public DialogUtils setMessage(String msg) {
		mMessage = msg;
		return this;
	}

	public DialogUtils setonPositiveClick(String mPositiveBtnStr, OnCreateNoticeDialog onPositiveClick) {
		mPositiveButtonString = mPositiveBtnStr;
		mOnPositiveClick = onPositiveClick;
		return this;
	}

	public DialogUtils setonNegativeClick(String mNegativeBtnStr, OnCreateNoticeDialog onNegativeClick) {
		mNegativeButtonString = mNegativeBtnStr;
		mOnNegativeClick = onNegativeClick;
		return this;
	}

	// public static AlertDialog createNoticeDialog(Context mContext, final
	// OnCreateNoticeDialog onNotice) {
	// if(onNotice == null){
	// return null ;
	// }
	//
	// // 1. View Object
	// LayoutInflater inflater = LayoutInflater.from(mContext);
	// View layout =inflater.inflate(R.layout.notice_dialog_layout, null);
	//
	// // 2. setCustomView
	// AlertDialog.Builder dialog_buider = new AlertDialog.Builder(mContext);
	// dialog_buider.setView(layout);
	// final AlertDialog dialog = dialog_buider.create();
	// // 3. Tittle
	// TextView dialog_title = (TextView)
	// layout.findViewById(R.id.dialog_title);
	// dialog_title.setText(onNotice.getTitle() == null ? "" :
	// onNotice.getTitle());
	//
	// // 4. Message
	// TextView dialog_msg = (TextView) layout.findViewById(R.id.dialog_msg);
	// dialog_msg.setText(onNotice.getMessage() == null ? "" :
	// onNotice.getMessage());
	//
	// // 5. PositiveButton
	// TextView btn_ok = (TextView) layout.findViewById(R.id.btn_ok);
	// btn_ok.setText(onNotice.getPositiveButtonString() == null ? "" :
	// onNotice.getPositiveButtonString());
	// btn_ok.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// onNotice.excPositiveAction();
	// dialog.dismiss();
	// }
	// });
	//
	// // 6. NegativeButton
	// TextView btn_cancel = (TextView) layout.findViewById(R.id.btn_cancel);
	// btn_cancel.setText(onNotice.getNegativeButtonString() == null ? "" :
	// onNotice.getNegativeButtonString());
	// btn_cancel.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// onNotice.excNegativeAction();
	// dialog.dismiss();
	// }
	// });
	//
	// return dialog;
	// }
	// public interface OnCreateNoticeDialog {
	// String getTitle();
	//
	// String getMessage();
	//
	// String getPositiveButtonString();
	//
	// String getNegativeButtonString();
	//
	// void excPositiveAction();
	//
	// void excNegativeAction();
	// }
	// OnCreateNoticeDialog OnNotice;
	//
	// private void createNoticeDialog(){
	// DialogUtils.createNoticeDialog(this, new OnCreateNoticeDialog() {
	//
	// @Override
	// public String getTitle() {
	// // TODO Auto-generated method stub
	// return getString(R.string.back_dialog_title);
	// }
	//
	// @Override
	// public String getPositiveButtonString() {
	// // TODO Auto-generated method stub
	// return getString(R.string.yes);
	// }
	//
	// @Override
	// public String getNegativeButtonString() {
	// // TODO Auto-generated method stub
	// return getString(R.string.no);
	// }
	//
	// @Override
	// public String getMessage() {
	// // TODO Auto-generated method stub
	// return getString(R.string.back_dialog_msg);
	// }
	//
	// @Override
	// public void excPositiveAction() {
	// mPreferencesHelper.putValue(Configure.KEY_PREFERENCE_IS_REFRESH, false);
	// disMiss();
	// FeedBackEditActivity.this.finish();
	// pointToHide();
	// }
	//
	// @Override
	// public void excNegativeAction() {
	// // TODO Auto-generated method stub
	//
	// }
	// }).show();
	// }
}
