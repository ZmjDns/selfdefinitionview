package com.zmj.selfdefinitionview.practice2_3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zmj.selfdefinitionview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/18
 * Description :
 */
public class TagLayout extends ViewGroup {
    private List<Rect> mchildRect=new ArrayList<>();

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //这里可以根据需求改
    public void setData(List<String> data){
        for (final String datum : data) {
            View view = inflate(getContext(),R.layout.tag,null);
            TextView textView = view.findViewById(R.id.text);
            textView.setText(datum);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClicktag.OnClickTag(datum);
                }
            });
            addView(view);
        }
    }

    private onClickTag  mClicktag;

    public void setmClicktag(onClickTag mClicktag) {
        this.mClicktag = mClicktag;
    }

    public interface onClickTag{
        void OnClickTag(String tag);
    }

    /*
     *  首先是测量子View的高 在测量整个TagLayout 高度  宽比较好获得
     *  测量  子View  的宽高
     *  当childLeft左边距 + 自身宽度 > TagLayout  自身的宽度时     换行
     *  每一行的childView高度相加  =  TagLayout 高度
     *  最后在onLayout  里面进行摆放
     */


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mchildRect.clear();
        //获得TagLayout  的宽度
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        //TagLayout 的高度
        int layoutHeight=0;
        //获取子View总数
        int childCount=getChildCount();
        //childView  距离父布局的左边距离
        int childLeft=getPaddingLeft();
        //childView 距离父布局的顶部距离
        int childTop=getPaddingTop();
        //获取每个childView情况
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            //由子View自身指定了 自己的宽高
            childView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
            int childWidth = childView.getMeasuredWidth();
            int childHeight=childView.getMeasuredHeight();
            //当 childView  距离父布局的左边距离  +  childView  自身的距离 > TagLayout  的宽度时换行
            //这里面不仅完成了 换行操作 还计算了 TagLayout的高度
            //应该说换行操作 = TagLayout的高度
            if (childLeft+childWidth>layoutWidth){
                childLeft=0;  //换行后将childLeft 置为0  重新开始计算宽度
                childTop=childTop+childHeight+20;  //加20 加15是希望childView之间不要太靠近也可以用marginLayoutParams
            }
            //左上右下
            mchildRect.add(new Rect(childLeft,childTop,childLeft+childWidth,childTop+childHeight));
            //这里计算childLeft   确定每一行的子View 位置
            childLeft+=childWidth+15;
            //当是最后一个childView时  自身高度 + childTop = layoutHeight
            if (i==childCount-1){
                layoutHeight=childTop+childHeight;
            }
        }
        //最后不要忘了这里设置Taglayout 自身的宽度和高度
        setMeasuredDimension(layoutWidth,layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childView= getChildAt(i);
            Rect lcation = mchildRect.get(i);
            childView.layout(lcation.left,lcation.top,lcation.right,lcation.bottom);
        }
    }

}

