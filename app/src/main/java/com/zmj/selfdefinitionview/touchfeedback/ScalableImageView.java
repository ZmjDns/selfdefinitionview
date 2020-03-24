package com.zmj.selfdefinitionview.touchfeedback;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.zmj.selfdefinitionview.R;

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/24
 * Description :
 */
public class ScalableImageView extends View implements Runnable {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    // 记录手指滑动距离
    float offsetX;
    float offsetY;

    float originalOffsetX;
    float originalOffsetY;

    float smallScale;
    float bigScale;
    // 判断是否是大图
    boolean big;

    // 动画
    float scaleFraction;  // 0 - 1f
    ObjectAnimator scaleAnimator;

    // 手势侦测器
    private GestureDetectorCompat detector;
    CustomGestureListener gestureListener = new CustomGestureListener();

    /*
    在onFling中使用，OverScroller算是一个计算器，需要在onFling中设置参数
     */
    private OverScroller overScroller;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int)(300 * getContext().getResources().getDisplayMetrics().density));
        detector = new GestureDetectorCompat(getContext(), gestureListener);
        overScroller = new OverScroller(getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originalOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

        if ((float) bitmap.getWidth() / (float) bitmap.getHeight() > (float) getWidth() / (float) getHeight()) {
            smallScale = (float) getWidth() / (float) bitmap.getWidth();
            bigScale = (float) getHeight() / (float) bitmap.getHeight();
        } else {
            smallScale = (float) getHeight() / (float) bitmap.getHeight();
            bigScale = (float) getWidth() / (float) bitmap.getHeight();
        }

        bigScale = bigScale * 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 给随手指偏移 乘以scaleFraction在缩小的时候自动居中
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);

        // float scale = big ? bigScale : smallScale;
        // 双击的时候给随这动画的执行scaleFraction会变化
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);

        // 图片居中
        canvas.drawBitmap(
                bitmap,
                originalOffsetX, originalOffsetY,
                mPaint
        );
    }

    // 使用GestureDetectorCompat
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @SuppressLint("NewApi")
    @Override
    public void run() {
        // 动画是否还在进行中，如果结束了直接return
        if (!overScroller.computeScrollOffset()) return;
        // 更新xy值，计算之后可获取最新的值（每次获取的时候都需要让scroller先计算一下）
        overScroller.computeScrollOffset();
        offsetX = overScroller.getCurrX();
        offsetY = overScroller.getCurrY();
        invalidate();
        postOnAnimation(this);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    // 动画使用，set方法里面要加invalidate()刷新
    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds为true，不返回bitmap，只返回这个bitmap的尺寸
        options.inJustDecodeBounds = true;
        // 从资源中读取(比较浪费资源，所以上面设置为true，只获取图片宽高)
        BitmapFactory.decodeResource(getResources(), R.drawable.batman, options);
        // 再设置为false，最后要返回bitmap
        options.inJustDecodeBounds = false;
        // 根据缩放比例重新计算宽高
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.batman, options);
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // 预按下事件（父view是滑动控件会出现预按下事件）
        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        /**
         * 图片放大后跟随手指滑动偏移
         *
         * @param downEvent 按下的事件
         * @param event     当前事件
         * @param distanceX 上一个点到当前点之间距离
         * @param distanceY 上一个点到当前点之间距离
         * @return 没用
         */
        @Override
        public boolean onScroll(MotionEvent downEvent, MotionEvent event, float distanceX, float distanceY) {

            // 大图的时候移动,设置边界
            if (big) {
                offsetX -= distanceX;
                // （图片宽度 - 屏幕宽度）/ 2
                offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
                offsetX = Math.max(offsetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);
                offsetY -= distanceY;
                offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
                offsetY = Math.max(offsetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
            }

            invalidate();
            return false;
        }

        // 长按事件
        @Override
        public void onLongPress(MotionEvent e) {
        }

        // 惯性滑动，实现fling效果  velocity(速度：抬手一瞬间的 距离/时间 )
        @SuppressLint("NewApi")
        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent event, float velocityX, float velocityY) {

            if (big) {
                // 不会自动刷新，只是设置好了overScroller,可通过overScroller计算和设置动画
                overScroller.fling(
                        // 初始位置(原点)
                        (int) offsetX, (int) offsetY,
                        // velocity(速度：抬手一瞬间的 距离/时间)
                        (int) velocityX, (int) velocityY,
                        // x最大最小范围
                        -(int) (bitmap.getWidth() * bigScale - getWidth()) / 2, (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        // y最大最小范围
                        -(int) (bitmap.getHeight() * bigScale - getHeight()) / 2, (int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        // 过渡滚动（类似IOS效果）
                        100, 100
                );

            /*
              我们需要一个循环，不停的修改offsetX，offsetY进行刷新页面
              post()在主线程立即执行，postOnAnimation()在下一帧去主线程执行
              需要做动画，需要一帧一帧执行的时候用postOnAnimation()
              postOnAnimation作用：下一帧的时候执行run()方法
            */
                postOnAnimation(ScalableImageView.this);

            }

            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        // 双击事件（两次触摸间隔<300ms）
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            // invalidate();
            if (big) {
                // 根据手指点击的位置进行偏移量计算，根据手指位置方法
                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2) * bigScale / smallScale;
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
            // 这里返回无所谓，只要onDown返回true即可
            return false;
        }

        // 双击不松手滑动触发
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

}
