package com.mdove.passwordguard.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdove.passwordguard.R;

/**
 * Created by MDove on 2018/2/18.
 */

public class ItemGroupLayout extends RelativeLayout {
    private TextView ivCircleOff;
    private ImageView ivCircleOn;
    private TextView mTvTitle;
    private RelativeLayout mLayoutMain;
    private boolean mIsCheck = false;
    private Context mContext;

    public ItemGroupLayout(Context context) {
        this(context, null);
    }

    public ItemGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.layout_item_group, this);
        ivCircleOff = findViewById(R.id.iv_circle_off);
        ivCircleOn = findViewById(R.id.iv_circle_on);
        mTvTitle = findViewById(R.id.tv_title);
        mLayoutMain = findViewById(R.id.layout_main);
    }

    public void setCheck(boolean isCheck) {
        mIsCheck = isCheck;
        if (mIsCheck) {
            ivCircleOff.setVisibility(VISIBLE);
            ivCircleOn.setVisibility(VISIBLE);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.commonColor));
            mLayoutMain.setBackground(ContextCompat.getDrawable(mContext,
                    R.drawable.bg_layout_item_group_on));
        } else {
            ivCircleOn.setVisibility(GONE);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mLayoutMain.setBackground(ContextCompat.getDrawable(mContext,
                    R.drawable.bg_layout_item_group_off));
        }
    }

    public boolean getCheck() {
        return mIsCheck;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }
}
