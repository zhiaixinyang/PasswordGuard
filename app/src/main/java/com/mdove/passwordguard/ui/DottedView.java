package com.mdove.passwordguard.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.utils.DensityUtil;

/**
 * Created by MDove on 2017/8/11.
 */

public class DottedView extends View {
    private Paint paint;
    private int dottedViewLineColor;
    private int dottedViewLineInterval;
    private int dottedViewLineHeight;
    private int dottedViewLineWidth;

    public DottedView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DottedView);
            dottedViewLineColor = typedArray.getColor(R.styleable.DottedView_dv_line_color, ContextCompat.getColor(context, R.color.black));
            dottedViewLineInterval = (int) typedArray.getDimension(R.styleable.DottedView_dv_line_interval, DensityUtil.dip2px(context, 6));
            dottedViewLineHeight = (int) typedArray.getDimension(R.styleable.DottedView_dv_line_height, DensityUtil.dip2px(context, 2));
            dottedViewLineWidth = (int) typedArray.getDimension(R.styleable.DottedView_dv_line_width, DensityUtil.dip2px(context, 12));
            typedArray.recycle();
        }
        paint = new Paint();
        paint.setColor(dottedViewLineColor);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(dottedViewLineHeight);
    }

    public DottedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DottedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int allWidth = getMeasuredWidth();
        int curWidth = 0;
        while (curWidth < allWidth) {
            canvas.drawLine(dottedViewLineInterval + curWidth, 0, curWidth + dottedViewLineWidth, 0, paint);
            curWidth = curWidth + dottedViewLineInterval + dottedViewLineWidth;
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = dottedViewLineHeight;
        }
        setMeasuredDimension(widthSize, heightSize);
    }
}
