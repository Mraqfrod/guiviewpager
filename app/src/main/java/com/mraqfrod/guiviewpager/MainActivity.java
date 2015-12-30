package com.mraqfrod.guiviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private GuideFragment fragment_0;
    private GuideFragment fragment_1;
    private GuideFragment fragment_2;
    private GuideFragment fragment_3;
    private float currentPosition;
    private ImageView iv_initial_phone;
    private ImageView iv_device;
    private LinearLayout ll_rows;
    private LinearLayout ll_comments;
    private ImageView iv_final_photo;
    private TextView tv_avatar_you;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        iv_initial_phone = (ImageView) findViewById(R.id.iv_initial_phone);
        iv_device = (ImageView) findViewById(R.id.iv_device);
        ll_rows = (LinearLayout)findViewById(R.id.ll_rows);
        ll_comments =(LinearLayout) findViewById(R.id.ll_comments);
        iv_final_photo =(ImageView)findViewById(R.id.iv_final_photo);
        tv_avatar_you = (TextView)findViewById(R.id.tv_avatar_you);
        tv_register =(TextView)findViewById(R.id.tv_register);
        initview();
    }

    private void initview() {
       fragment_0 =new GuideFragment();
       fragment_1 =new GuideFragment();
       fragment_2 =new GuideFragment();
       fragment_3 =new GuideFragment();

        fragmentList.add(fragment_0);
        fragmentList.add(fragment_1);
        fragmentList.add(fragment_2);
        fragmentList.add(fragment_3);

        GuideAdapter adapter = new GuideAdapter (getSupportFragmentManager());
        //3页缓存
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, new MyTransformer());

    }

    class GuideAdapter extends FragmentPagerAdapter{

        public GuideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    class MyTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            Log.i("aaa"," posiotm ="+position);
                if(fragment_0.getView() ==page ){

                    currentPosition = position;

                //    Log.i("aaa", " currentPosition = " + currentPosition);
                }
            if(position <-1 ){

            }else if(position <=0){

            }else  if(position<=1) {
                float p = Math.abs(position);// 右增大 左减小
                float d = (1 - p);
            //    Log.i("aaa"," d = "+d +" positiom = "+position +"  p = "+ p );

                iv_final_photo.setPivotY(0f);
                iv_final_photo.setPivotX(iv_final_photo.getWidth() / 2);


                if (-1 < currentPosition && currentPosition <= 0) {

                    iv_initial_phone.setTranslationY(-800 * d);
                    iv_initial_phone.setScaleX(0.5f * p + 0.5f);
                    iv_initial_phone.setScaleY(0.5f * p + 0.5f);
                    iv_device.setScaleX(1 + 2 * d);

                    if (p > 0.5 && p <= 1) {
                        iv_device.setAlpha(2 * p - 1);
                    } else {
                        iv_device.setAlpha(0f);
                    }
                    ll_comments.setTranslationY(800 * p);
                    ll_comments.setAlpha(d);
                    ll_comments.setScaleX(2 - d);
                    ll_comments.setScaleY(2 - d);

                    ll_rows.setTranslationY(-1000 - 500 * p);
                    ll_rows.setAlpha(0.5f);
                    iv_final_photo.setTranslationY(-1000 - 500 * p);
                    iv_final_photo.setAlpha(0.5f);

                    tv_avatar_you.setTranslationY(-300);

                    tv_register.setTranslationY(300);

                } else if (-2 < currentPosition && currentPosition <= -1) {

                    iv_initial_phone.setTranslationY(-800 + -300 * d);

                    ll_comments.setAlpha(p);

                    ll_rows.setTranslationY(-1000 * p);
                    ll_rows.setAlpha(0.5f + 0.5f * d);

                    iv_final_photo.setTranslationY(-1000 * p);
                    iv_final_photo.setAlpha(0.5f + 0.5f * d);

                    tv_avatar_you.setTranslationY(-300);
                    tv_register.setTranslationY(300);
                } else if (-3 < currentPosition && currentPosition <= -2) {

                    iv_final_photo.setScaleX(1 + 3 * d);
                    iv_final_photo.setScaleY(1 + 3 * d);

                    for (int i = 0; i < ll_rows.getChildCount(); i++) {
                        View child = ll_rows.getChildAt(i);
                        child.setAlpha(p);
                        if (i % 2 == 0) {
                            child.setTranslationX(100 * d);
                        } else {
                            child.setTranslationX(-100 * d);
                        }
                    }
                    tv_avatar_you.setTranslationY(-300 + 300 * d);

                    tv_register.setTranslationY(300 - 300 * d);
                }
            } else {
            }
        }
    }
}
