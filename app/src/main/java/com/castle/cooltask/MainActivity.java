package com.castle.cooltask;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.Toast;

import com.castle.cooltask.adapter.CommonAdapter;
import com.castle.cooltask.adapter.ViewHolder;
import com.castle.cooltask.model.Point;
import com.castle.cooltask.model.Task;
import com.castle.cooltask.utils.PointEvaluator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.markushi.ui.RevealColorView;


public class MainActivity extends AppCompatActivity {

    private static final Task.State COMPLETED = null;
    private ListView lv_task;
    private List<Task> tasks;
    private FloatingActionButton fab_add;
    private FloatingActionButton fab_add_fake;
    private RevealColorView reveal;

    private Point centerPoint;

    private Point p1;
    private Point p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTasks();

        lv_task = (ListView) findViewById(R.id.lv_task);
        lv_task.setAdapter(new CommonAdapter<Task>(this, tasks, R.layout.item_menu) {

            @Override
            public void convert(ViewHolder helper, Task item, int position) {
                helper.setText(R.id.tv_label, item.getName());

                int backgroudRes = getBackgroundRes(item.getState());
                helper.setBackgroundRes(R.id.v_state, backgroudRes);

            }
        });

        reveal = (RevealColorView) findViewById(R.id.reveal);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add_fake = (FloatingActionButton) findViewById(R.id.fab_add_fake);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {



                p1 = new Point(fab_add.getX(),fab_add.getY());
                int l1[] = new int[2];
                fab_add.getLocationOnScreen(l1);

                int[] l2 = new int[2];
                fab_add_fake.getLocationOnScreen(l2);
//              Point p2 = new Point(location[0]+reveal.getWidth()/2-fab_add.getWidth()/2,location[1]+reveal.getHeight()/2-fab_add.getHeight());



                final int translationX = (int) ((l2[0]-l1[0]));
                final int translationY = (int) ((l2[1]-l1[1]));

                p2 = new Point(fab_add.getX()+translationX,fab_add.getY()+translationY);

                final float fakeStart = fab_add_fake.getX()-translationX;
                final float fakeEnd = fab_add_fake.getY() -translationY;

                ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), p1, p2);
               animator.addListener(new Animator.AnimatorListener() {
                   @Override
                   public void onAnimationStart(Animator animation) {
                       lv_task.animate().setDuration(300).alpha(0f).translationX(-130f);
                   }

                   @Override
                   public void onAnimationEnd(Animator animation) {
                       fab_add.setVisibility(View.GONE);
                       FragmentManager fragmentManager = getFragmentManager();
                       fragmentManager.beginTransaction().add(R.id.content,new AddFragment(),"ff").commit();

                   }

                   @Override
                   public void onAnimationCancel(Animator animation) {

                   }

                   @Override
                   public void onAnimationRepeat(Animator animation) {

                   }
               });

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float t = animation.getAnimatedFraction();
                        Point point = (Point) animation.getAnimatedValue();
                        fab_add.setX(point.getX());
                        fab_add.setY(point.getY());

                        fab_add_fake.setTranslationX(-(p2.getX() - point.getX()));
                        fab_add_fake.setTranslationY(-(p2.getY()-point.getY()));

//                        fab_add_fake.set(fab_add_fake.getX()+p2.getX()-point.getX());
//                        fab_add_fake.setY(fab_add_fake.getY()+p2.getY()-point.getY());

                        if(t>=0.5){
//                            fab_add_fake.setScaleX((float)(t*t-1.225*t+10.225f));
//                            fab_add_fake.setScaleY((float)(t*t-1.225*t+10.225f));
                            fab_add.setAlpha(0f);
                            fab_add_fake.setAlpha(1f);

//                            fab_add_fake.setScaleX(t*16-7);
//                            fab_add_fake.setScaleY(t*16-7);
//                            fab_add_fake.setScaleX((float) (10.667 * t * t - 1.667));
//                            fab_add_fake.setScaleY((float)(10.667*t*t-1.667));
                            fab_add_fake.setScaleX((float)(8.258*t*t*t*t*t+0.742));
                            fab_add_fake.setScaleY((float)(8.258*t*t*t*t*t+0.742));


                        }else{
                            fab_add_fake.setAlpha(0f);
                        }





                    }
                });
                animator.setDuration(300);
                animator.start();

//                Toast.makeText(MainActivity.this,""+p2.getX()+";"+p2.getY(), Toast.LENGTH_LONG).show();
//                centerPoint = p2;

//                fab_add.animate().translationX(translationX).translationY(translationY).setInterpolator(new LinearInterpolator()).setDuration(250).setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        fab_add_fake.setX(fab_add_fake.getX() - translationX / 1f);
//                        fab_add_fake.setY(fab_add_fake.getY() - translationY / 1f);
////                        fab_add_fake.animate().translationX(0f).translationY(0f).scaleX(5f).scaleY(5f);
////                        ObjectAnimator animatorTX = ObjectAnimator.ofFloat(fab_add_fake,"translationX",);
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });


//                ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), p1, p2);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Point point = (Point) animation.getAnimatedValue();
//
//                       // fab_add.setX(point.getX());
//                        //fab_add.setY(point.getY());
//
//                    }
//                });
//                animator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        lv_task.animate().alpha(0f).translationX(-100).setDuration(500);
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                        float centerX = reveal.getWidth() / 2;
//                        float centerY = reveal.getHeight() / 2;
//                        reveal.reveal((int) centerX, (int) centerY, getResources().getColor(R.color.material_green_A200), 280, 300, new Animator.AnimatorListener() {
//                            @Override
//                            public void onAnimationStart(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
////                                ObjectAnimator alpha = ObjectAnimator.ofFloat(reveal, "alpha", 1f, 0f);
////                                alpha.setDuration(200);
////                                alpha.start();
//                            }
//
//                            @Override
//                            public void onAnimationCancel(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animator animation) {
//
//                            }
//                        });
//
//                        fab_add.setVisibility(View.GONE);
//                        int[] location = new int[2];
//                        fab_add.getLocationOnScreen(location);
////                        Toast.makeText(MainActivity.this, location[0] + ";" + location[1], Toast.LENGTH_LONG).show();
//
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                animator.setDuration(300);



//                ObjectAnimator alphaAnim = ObjectAnimator.ofInt(fab_add, "imageAlpha", 100, 0);
//                ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(fab_add, "scaleX", 1f, 5f);
//                ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(fab_add, "scaleY", 1f, 5f);

//                animator.start();

//                AnimatorSet set = new AnimatorSet();
//                set.play(animator).with(alphaAnim).with(scaleXAnim).with(scaleYAnim);
//                set.setInterpolator(new AccelerateInterpolator());
//                set.setDuration(300);
//                set.start();
            }
        });

    }
    protected int getBackgroundRes(Task.State state) {
        if (state == Task.State.COMPLETED) {
            return R.drawable.oval_completed;
        } else if (state == Task.State.SNOOZED) {
            return R.drawable.oval_snoozed;
        } else {
            return R.drawable.oval_overdue;

        }
    }

    private void getTasks() {
        tasks = new ArrayList<Task>();
        tasks.add(new Task("完成IPTV开发", "。。。", new Date(2015, 6, 2, 15, 6, 3), new Date(2015, 6, 2, 15, 6, 3), Task.State.COMPLETED));
        tasks.add(new Task("完成财富锦囊记账部分重构", "。。。", new Date(2015, 6, 2, 15, 6, 3), new Date(2015, 6, 2, 15, 6, 3), Task.State.COMPLETED));
        tasks.add(new Task("修改客户端联网机制", "。。。", new Date(2015, 6, 2, 15, 6, 3), new Date(2015, 6, 2, 15, 6, 3), Task.State.COMPLETED));
        tasks.add(new Task("学习Material Design", "。。。", new Date(2015, 6, 2, 15, 6, 3), new Date(2015, 6, 2, 15, 6, 3), Task.State.COMPLETED));
        tasks.add(new Task("研究Android属性动画", "。。。", new Date(2015, 6, 2, 15, 6, 3), new Date(2015, 6, 2, 15, 6, 3), Task.State.COMPLETED));

    }

    public void rollBack() {
        Fragment fragment = getFragmentManager().findFragmentByTag("ff");
        getFragmentManager().beginTransaction().remove(fragment).commit();
        
        startBackAnimator();


    }

    private void startBackAnimator() {
        fab_add.setVisibility(View.VISIBLE);
//        fab_add.setAlpha(1f);


        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(true), p2, p1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float t = 1f-animation.getAnimatedFraction();
                Point point = (Point) animation.getAnimatedValue();
                fab_add.setX(point.getX());
                fab_add.setY(point.getY());

                fab_add_fake.setTranslationX(-(p2.getX() - point.getX()));
                fab_add_fake.setTranslationY(-(p2.getY()-point.getY()));

//
                if(t>=0.5){
//                            fab_add_fake.setScaleX((float)(t*t-1.225*t+10.225f));
//                            fab_add_fake.setScaleY((float)(t*t-1.225*t+10.225f));
                    fab_add.setAlpha(0f);
                    fab_add_fake.setAlpha(1f);

//                            fab_add_fake.setScaleX(t*16-7);
//                            fab_add_fake.setScaleY(t*16-7);
//                            fab_add_fake.setScaleX((float) (10.667 * t * t - 1.667));
//                            fab_add_fake.setScaleY((float)(10.667*t*t-1.667));
                    fab_add_fake.setScaleX((float)(8.258*t*t*t*t*t+0.742));
                    fab_add_fake.setScaleY((float)(8.258*t*t*t*t*t+0.742));


                }else{
                    fab_add_fake.setAlpha(0f);
                    fab_add.setAlpha(1f);
                }

            }
        });
        animator.setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                lv_task.animate().setDuration(300).alpha(1f).translationX(0f);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fab_add_fake.setTranslationY(0f);
                fab_add_fake.setTranslationX(0f);
                fab_add_fake.setScaleY(1f);
                fab_add_fake.setScaleX(1f);



            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();


    }
}
