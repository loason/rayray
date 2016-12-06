package com.hh.android.stock.testkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hh.android.stock.testkit.R;

/**
 * 公用标题栏
 * Created by litj on 2016/8/3.
 */
public class CommonTitleView extends RelativeLayout{

    private TextView tvTitle;
    private TextView tvSubTitle;
    private TextView tvRight;
    private ImageView ivRight;
    private ImageView ivLeft;
    private TextView tvLeft;
    private LinearLayout llRightTxt;
    private LinearLayout llRightImg;
    private LinearLayout llLeftTxt;
    private LinearLayout llLeftImg;

    public CommonTitleView(Context context) {
        super(context);
        init(context, null);
    }

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.common_title_view_layout, this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        llRightTxt = (LinearLayout) findViewById(R.id.ll_right_txt);
        llRightImg = (LinearLayout) findViewById(R.id.ll_right_img);
        llLeftTxt = (LinearLayout) findViewById(R.id.ll_left_txt);
        llLeftImg = (LinearLayout) findViewById(R.id.ll_left_img);
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleView);
        String title = attrsArray.getString(R.styleable.CommonTitleView_mainTitle);
        String subTitle = attrsArray.getString(R.styleable.CommonTitleView_subTitle);
        String rightTxt = attrsArray.getString(R.styleable.CommonTitleView_rightTxt);
        int rightSrc = attrsArray.getResourceId(R.styleable.CommonTitleView_rightSrc, -1);
        String leftTxt = attrsArray.getString(R.styleable.CommonTitleView_leftTxt);
        int leftSrc = attrsArray.getResourceId(R.styleable.CommonTitleView_leftSrc, -1);
        attrsArray.recycle();
        if(!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(subTitle)){
            tvSubTitle.setText(subTitle);
            tvSubTitle.setVisibility(VISIBLE);
        }
        if(!TextUtils.isEmpty(rightTxt)){
            tvRight.setText(rightTxt);
            llRightImg.setVisibility(GONE);
            llRightTxt.setVisibility(VISIBLE);
        }
        else if(rightSrc != -1){
            ivRight.setImageResource(rightSrc);
            llRightImg.setVisibility(VISIBLE);
            llRightTxt.setVisibility(GONE);
        }
        if(!TextUtils.isEmpty(leftTxt)){
            tvLeft.setText(leftTxt);
            llLeftImg.setVisibility(GONE);
            llLeftTxt.setVisibility(VISIBLE);
        }
        else if(leftSrc != -1){
            ivLeft.setImageResource(leftSrc);
            llLeftImg.setVisibility(VISIBLE);
            llLeftTxt.setVisibility(GONE);
        }


    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setSubTitle(String subTitle){
        tvSubTitle.setText(subTitle);
        tvSubTitle.setVisibility(VISIBLE);
    }

    public void setTvRight(String right){
        tvRight.setText(right);
    }

    public void setOnLeftTxtListener(OnClickListener listener){
        llLeftTxt.setOnClickListener(listener);
    }

    public void setOnLeftImgListener(OnClickListener listener){
        llLeftImg.setOnClickListener(listener);
    }

    public void setOnRightTxtListener(OnClickListener listener){
        llRightTxt.setOnClickListener(listener);
    }

    public void setOnRightImgListener(OnClickListener listener){
        llRightImg.setOnClickListener(listener);
    }

    public void setRllRightTxtEnable(boolean enable){
        llRightTxt.setEnabled(enable);
    }

    public void setRllRightImgEnable(boolean enable){
        llRightImg.setEnabled(enable);
        ivRight.setVisibility(enable ? VISIBLE : GONE);
    }

    public void setTvRightColor(int color){
        tvRight.setTextColor(color);
    }

    public void setBackgroundTransparent(){
        findViewById(R.id.content).setBackgroundResource(android.R.color.transparent);
        findViewById(R.id.v_bottom_line).setVisibility(GONE);
    }

}
