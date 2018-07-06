package com.nj.zhihu.widget.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mImageViewList;

    public BannerAdapter(List<ImageView> imageViewList) {
        mImageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mImageViewList.size();
        container.addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
