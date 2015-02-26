package com.yibu.kuaibu.ui;

import java.util.List;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapterly.Cpxiangqingpopgridlist;
import com.yibu.kuaibu.adapterly.PopUpGridOfCPXQAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class ChooseSeKuailPopup extends PopupWindow implements android.view.View.OnClickListener{

	private OnDialogListener listener;
	private View mPopView;
	
	public ChooseSeKuailPopup(Context context, OnDialogListener listener,boolean isShowMd) {  
        super(context);  
        this.listener=listener;
        LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPopView= inflater.inflate(R.layout.dialog_get_sekuai, null);
        this.setContentView(mPopView);
        this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		ImageView btn_close=(ImageView)mPopView.findViewById(R.id.close_dia);
//        Button btn_take_photo=(Button)mPopView.findViewById(R.id.btn_take_photo);
//        Button btn_model=(Button)mPopView.findViewById(R.id.btn_model);
//        Button btn_cancel=(Button)mPopView.findViewById(R.id.btn_cancel);
//        if(!isShowMd) {
//        	btn_model.setVisibility(View.GONE);
//        }
        btn_close.setOnClickListener(this);

        GridView CuXiaoChanPinGard = (GridView) mPopView.findViewById(R.id.shangcCXChanPinGard);
        
        
        
        
        
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);//0xb0000000
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);//半透明颜色
    } 
	public ChooseSeKuailPopup(Context context, OnDialogListener listener,boolean isShowMd,
			List<Cpxiangqingpopgridlist> adapterpopdata)
	{
		// TODO Auto-generated constructor stub
		super(context);  
        this.listener=listener;
        LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPopView= inflater.inflate(R.layout.dialog_get_sekuai, null);
        this.setContentView(mPopView);
        this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		ImageView btn_close=(ImageView)mPopView.findViewById(R.id.close_dia);
//        Button btn_take_photo=(Button)mPopView.findViewById(R.id.btn_take_photo);
//        Button btn_model=(Button)mPopView.findViewById(R.id.btn_model);
//        Button btn_cancel=(Button)mPopView.findViewById(R.id.btn_cancel);
//        if(!isShowMd) {
//        	btn_model.setVisibility(View.GONE);
//        }
        btn_close.setOnClickListener(this);

        GridView CuXiaoChanPinGard = (GridView) mPopView.findViewById(R.id.shangcCXChanPinGard);
        
        Log.e("set","adapter");
        CuXiaoChanPinGard.setAdapter(new PopUpGridOfCPXQAdapter(
        		context, adapterpopdata, CuXiaoChanPinGard));
        
    
        
        
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
	}
	/**
	 * Dialog按钮回调接口
	 *
	 */
	public interface OnDialogListener {

		public void onClosePop();//选择本地照片
		
		public void onTakePhoto();//照相
		
		public void onModel();//照相

		

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.close_dia:
			listener.onClosePop();
			break;
		case R.id.btn_take_photo:
			listener.onTakePhoto();
			break;
		case R.id.btn_model:
			listener.onModel();
			break;
		
		}
		dismiss();
	}
}
