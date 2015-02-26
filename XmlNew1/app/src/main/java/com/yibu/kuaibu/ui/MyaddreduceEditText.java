package com.yibu.kuaibu.ui;

/**************  ly    加减法     **************/


import com.yibu.kuaibu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyaddreduceEditText extends LinearLayout{

    private EditText mEditText;
    private ImageView bAdd;
    private ImageView bReduce;
    
    //这里的构造一定是两个参数。
    public MyaddreduceEditText(final Context ctxt, AttributeSet attrs) {
        super(ctxt,attrs);
    }


    protected void onFinishInflate() {
        super.onFinishInflate();
        
        LayoutInflater.from(getContext()).inflate(R.layout.yhaddreduceedittext_item, this);
        init_widget();
        addListener();
        
    }
    
    public void init_widget(){
        
        
        mEditText = (EditText)findViewById(R.id.et01);
        bAdd = (ImageView)findViewById(R.id.bt02);
        bReduce = (ImageView)findViewById(R.id.bt01);
        mEditText.setText("1");
    }
    
    public void addListener(){
        bAdd.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
                int num = Integer.valueOf(mEditText.getText().toString());
                num++;
                mEditText.setText(Integer.toString(num));
            }
        });
        
        bReduce.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int num = Integer.valueOf(mEditText.getText().toString());
                num--;
                mEditText.setText(Integer.toString(num));
            }
        });
    }
}