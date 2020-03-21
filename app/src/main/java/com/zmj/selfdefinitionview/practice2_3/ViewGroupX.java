package com.zmj.selfdefinitionview.practice2_3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/19
 * Description :
 */
public class ViewGroupX extends ViewGroup {
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;


    public ViewGroupX(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
          * getPaddingLeft()   当前容器的paddingLeft
          * widthMeasureSpec.size 当前容器的宽度(parentWidth - parentPadding - this.margin)
          * view.getMeasuredHeight() (widthMeasureSpec.size - this.padding - viewMargin)
          */
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();

        int desireWidth = 0, desireHeight = 0;
        int childCount = getChildCount(); // 直接子元素的个数
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != GONE) {
                LayoutParams params = (LayoutParams) view.getLayoutParams();
                measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, 0);
                desireWidth = Math.max(desireWidth, view.getMeasuredWidth());
                desireHeight += view.getMeasuredHeight() + (params.bottomMargin + params.topMargin);
            }
        }
        // count with padding
        desireWidth += mPaddingLeft + mPaddingRight;
        desireHeight += mPaddingTop + mPaddingBottom;

        // see if the size is big enough
        desireWidth = Math.max(desireWidth, getSuggestedMinimumWidth());
        desireHeight = Math.max(desireHeight, getSuggestedMinimumHeight());

        setMeasuredDimension(resolveSize(desireWidth, widthMeasureSpec), resolveSize(desireHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**
          * l this(相对父布局)left
          * r this(相对父布局)right
          */
        int top = mPaddingTop;
        int childCount = getChildCount(); // 直接子元素的个数
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != GONE) {
                LayoutParams params = (LayoutParams) view.getLayoutParams();

                int viewLeft = mPaddingLeft + params.leftMargin;
                int viewTop = top + params.topMargin;

                view.layout(viewLeft, viewTop, viewLeft + view.getMeasuredWidth(), viewTop + view.getMeasuredHeight());
                top += params.topMargin + view.getMeasuredHeight() + params.bottomMargin;
            }
        }
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams( android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
