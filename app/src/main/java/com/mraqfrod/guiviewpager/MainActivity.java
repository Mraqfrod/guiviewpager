package com.mraqfrod.guiviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private GuideFragment fragment_0;
    private GuideFragment fragment_1;
    private GuideFragment fragment_2;
    private GuideFragment fragment_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
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


}
