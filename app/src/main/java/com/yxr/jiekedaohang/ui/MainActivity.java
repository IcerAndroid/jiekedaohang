package com.yxr.jiekedaohang.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.yxr.jiekedaohang.DataProvider;
import com.yxr.jiekedaohang.R;
import com.yxr.jiekedaohang.Section;
import com.yxr.jiekedaohang.utils.FileUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;

    String[] navigation_titile;

    private List<Section> mSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        navigation_titile = getResources().getStringArray(R.array.navigations);
        try {
            InputStream open = getAssets().open("json");
            String json = FileUtils.readTextFromIs(open);
            mSections = JSON.parseArray(json, Section.class);
            DataProvider.getInstance().putData(mSections);//将数据加载到内存中
        } catch (Exception e) {
            e.printStackTrace();
        }


        mViewpager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    /**
     * 获取模块中的列表信息
     *
     * @param list
     * @param type
     * @return
     */
    private List<Section> getListByType(List<Section> list, int type) {
        ArrayList<Section> sections = new ArrayList<>();
        if (list == null || list.size() == 0) return sections;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            int itemtype = list.get(i).getType();
            if (itemtype == type) {
                sections.add(list.get(i));
            }
        }
        return sections;
    }


    public class TabPagerAdapter extends FragmentPagerAdapter {


        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return navigation_titile.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return navigation_titile[position];
        }

        @Override
        public Fragment getItem(int position) {
            return SectionFragment.getInstance(position + "");
        }

    }
}
