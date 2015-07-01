package com.castle.cooltask.widget;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import com.castle.cooltask.model.Point;

import at.markushi.ui.RevealColorView;
import at.markushi.ui.util.BakedBezierInterpolator;
import at.markushi.ui.util.UiHelper;

/**
 * Created by apple on 15/6/27.
 */
public class MyRevealColorView extends ViewGroup{

    public static final int ANIMATION_REVEAL = 0;
    public static final int ANIMATION_HIDE = 2;
    private static final float SCALE = 8.0F;
    private View inkView;
    private int inkColor;
    private ShapeDrawable circle;
    private ViewPropertyAnimator animator;

    public MyRevealColorView(Context context) {
        this(context, (AttributeSet)null);
    }

    public MyRevealColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRevealColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!this.isInEditMode()) {
            this.inkView = new View(context);
            this.addView(this.inkView);
            this.circle = new ShapeDrawable(new OvalShape());
            UiHelper.setBackground(this.inkView, this.circle);
            this.inkView.setVisibility(View.INVISIBLE);
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        this.inkView.layout(left, top, left + this.inkView.getMeasuredWidth(), top + this.inkView.getMeasuredHeight());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
        float circleSize = (float)Math.sqrt((double)(width * width + height * height)) * 2.0F;
        int size = (int)(circleSize / 8.0F);
        int sizeSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        this.inkView.measure(sizeSpec, sizeSpec);
    }

    public void reveal(int x, int y, int color, Animator.AnimatorListener listener) {
        int duration = this.getResources().getInteger(at.markushi.ui.R.integer.rcv_animationDurationReveal);
        this.reveal(x, y, color, 0, (long) duration, listener);
    }

    public void reveal(int x, int y, final int color, Point startPoint,Point endPoint,int startRadius, long duration, final Animator.AnimatorListener listener) {
        if(color != this.inkColor) {
            this.inkColor = color;
            if(this.animator != null) {
                this.animator.cancel();
            }

            this.circle.getPaint().setColor(color);
            this.inkView.setVisibility(View.VISIBLE);
            float startScale = (float)startRadius * 2.0F / (float)this.inkView.getHeight();
            float finalScale = this.calculateScale(x, y) * 8.0F;
            this.prepareView(this.inkView, x, y, startScale);
            this.animator = this.inkView.animate().scaleX(finalScale).scaleY(finalScale).setDuration(duration).setListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationStart(animator);
                    }

                }

                public void onAnimationEnd(Animator animation) {
                    MyRevealColorView.this.setBackgroundColor(color);
                    MyRevealColorView.this.inkView.setVisibility(View.INVISIBLE);
                    if(listener != null) {
                        listener.onAnimationEnd(animation);
                    }

                }

                public void onAnimationCancel(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationCancel(animator);
                    }

                }

                public void onAnimationRepeat(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationRepeat(animator);
                    }

                }
            });
            this.prepareAnimator(this.animator, 0);
            this.animator.start();
        }
    }


    public void reveal(int x, int y, final int color, int startRadius, long duration, final Animator.AnimatorListener listener) {
        if(color != this.inkColor) {
            this.inkColor = color;
            if(this.animator != null) {
                this.animator.cancel();
            }

            this.circle.getPaint().setColor(color);
            this.inkView.setVisibility(View.VISIBLE);
            float startScale = (float)startRadius * 2.0F / (float)this.inkView.getHeight();
            float finalScale = this.calculateScale(x, y) * 8.0F;
            this.prepareView(this.inkView, x, y, startScale);
            this.animator = this.inkView.animate().scaleX(finalScale).scaleY(finalScale).setDuration(duration).setListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationStart(animator);
                    }

                }

                public void onAnimationEnd(Animator animation) {
                    MyRevealColorView.this.setBackgroundColor(color);
                    MyRevealColorView.this.inkView.setVisibility(View.INVISIBLE);
                    if(listener != null) {
                        listener.onAnimationEnd(animation);
                    }

                }

                public void onAnimationCancel(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationCancel(animator);
                    }

                }

                public void onAnimationRepeat(Animator animator) {
                    if(listener != null) {
                        listener.onAnimationRepeat(animator);
                    }

                }
            });
            this.prepareAnimator(this.animator, 0);
            this.animator.start();
        }
    }

    public void hide(int x, int y, int color, Animator.AnimatorListener listener) {
        int duration = this.getResources().getInteger(at.markushi.ui.R.integer.rcv_animationDurationHide);
        this.hide(x, y, color, 0, (long)duration, listener);
    }

    public void hide(int x, int y, int color, int endRadius, long duration, final Animator.AnimatorListener listener) {
        this.inkColor = 0;
        if(this.animator != null) {
            this.animator.cancel();
        }

        this.inkView.setVisibility(View.VISIBLE);
        this.setBackgroundColor(color);
        float startScale = this.calculateScale(x, y) * 8.0F;
        float finalScale = (float)endRadius * 8.0F / (float)this.inkView.getWidth();
        this.prepareView(this.inkView, x, y, startScale);
        this.animator = this.inkView.animate().scaleX(finalScale).scaleY(finalScale).setDuration(duration).setListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                if(listener != null) {
                    listener.onAnimationStart(animator);
                }

            }

            public void onAnimationEnd(Animator animation) {
                MyRevealColorView.this.inkView.setVisibility(View.INVISIBLE);
                if(listener != null) {
                    listener.onAnimationEnd(animation);
                }

            }

            public void onAnimationCancel(Animator animator) {
                if(listener != null) {
                    listener.onAnimationCancel(animator);
                }

            }

            public void onAnimationRepeat(Animator animator) {
                if(listener != null) {
                    listener.onAnimationRepeat(animator);
                }

            }
        });
        this.prepareAnimator(this.animator, 2);
        this.animator.start();
    }

    public ViewPropertyAnimator prepareAnimator(ViewPropertyAnimator animator, int type) {
        if(Build.VERSION.SDK_INT >= 16) {
            animator.withLayer();
        }

        animator.setInterpolator(BakedBezierInterpolator.getInstance());
        return animator;
    }

    private void prepareView(View view, int x, int y, float scale) {
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;
        view.setTranslationX((float)(x - centerX));
        view.setTranslationY((float)(y - centerY));
        view.setPivotX((float)centerX);
        view.setPivotY((float)centerY);
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    private float calculateScale(int x, int y) {
        float centerX = (float)this.getWidth() / 2.0F;
        float centerY = (float)this.getHeight() / 2.0F;
        float maxDistance = (float)Math.sqrt((double)(centerX * centerX + centerY * centerY));
        float deltaX = centerX - (float)x;
        float deltaY = centerY - (float)y;
        float distance = (float)Math.sqrt((double)(deltaX * deltaX + deltaY * deltaY));
        float scale = 0.5F + distance / maxDistance * 0.5F;
        return scale;
    }

}
