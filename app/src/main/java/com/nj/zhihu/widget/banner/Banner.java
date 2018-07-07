package com.nj.zhihu.widget.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.App;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.TopStories;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class Banner extends RelativeLayout {
    private static final String TAG = "BannerLOG";

    private ViewPager mViewPager;
    private TextView mHeaderTitle;
    private LinearLayout mPointGroup;
    private List<TopStories> mDataList;
    private List<ImageView> mImageViewList = new ArrayList<>();
    private BannerAdapter mBannerAdapter;
    private Handler mHandler = new Handler();
    private boolean isAutoPaly = true;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int lastPosition = 0; //vp上一张的位置，默认是0
    private OnBannerClickListener mBannerClickListener;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner, this, true);
        mViewPager = view.findViewById(R.id.viewpager);
        mHeaderTitle = view.findViewById(R.id.tv_header_title);
        mPointGroup = view.findViewById(R.id.point_group);
    }

    public Banner setDataList(List<TopStories> dataList) {
        mDataList = dataList;
        return this;
    }

    public void start() {
        initIndicator();
        initImageList();
    }

    //初始化vp中的图片
    private void initImageList() {
        //mImageViewList是vp的数据，用来传递给Adapter
        mImageViewList.clear();
        for (int i = 0; i < mDataList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            final int id = mDataList.get(i).getId();
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBannerClickListener != null) {
                        mBannerClickListener.onBannerClick(id);
                    }
                }
            });
            mImageViewList.add(imageView);
            //如果当前是无图模式，并且Wifi网络不可用，那么就不显示图片
            if ((boolean) SpUtils.get(App.getContext(), "NO_IMAGE_MODE", false)
                    && !NetWorkUtils.isWifiConnected(App.getContext())) {
                GlideApp.with(getContext())
                        .load(R.drawable.image_top_default)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imageView);
            } else {
                GlideApp.with(getContext())
                        .load(mDataList.get(i).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imageView);
            }
        }
        //将mImageViewList设置到vp的adpter中
        setVPData();
    }

    //给vp设置图片，给title设置数据
    private void setVPData() {
        mHeaderTitle.setText(mDataList.get(0).getTitle());
        if (mBannerAdapter == null) {
            mBannerAdapter = new BannerAdapter(mImageViewList);
        }
        mViewPager.setAdapter(mBannerAdapter);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, 4000);
        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //页面切换的时候，更新点和标题
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                //这里的position就是0~~size()
                mPointGroup.getChildAt(position).setSelected(true);
                mPointGroup.getChildAt(lastPosition).setSelected(false);
                lastPosition = position;
                mHeaderTitle.setText(mDataList.get(position).getTitle());
            }

            //滚动状态改变的时候，设置是否自动滚动
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1: //当我们手动滑动的时候，就停止自动播放
                        isAutoPaly = false;
                        break;
                    case 2: //当正常滚动时，就自动播放
                        isAutoPaly = true;
                        break;
                    default:
                            break;
                }
            }
        };
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    //初始化导航点
    private void initIndicator() {
        //初始化之前先删除所有点，否则点会一直增加
        mPointGroup.removeAllViews();
        for (int i = 0; i < mDataList.size(); i++) {
            ImageView point = new ImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(20, 20);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            point.setImageResource(R.drawable.point_selector);
            if (i == 0) {
                point.setSelected(true);
            }else {
                point.setSelected(false);
            }
            point.setLayoutParams(layoutParams);
            mPointGroup.addView(point);
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (isAutoPaly) {
                int currentItem = mViewPager.getCurrentItem();
                if (currentItem == mViewPager.getAdapter().getCount() - 1) {
                    mViewPager.setCurrentItem(0);
                } else {
                    mViewPager.setCurrentItem(currentItem + 1);
                }
                mHandler.postDelayed(this, 3000);
            } else {
                mHandler.postDelayed(this, 3000);
            }
        }
    };

    public interface OnBannerClickListener {
        void onBannerClick(int id);
    }

    public void setOnBannerClickListener(OnBannerClickListener bannerClickListener) {
        mBannerClickListener = bannerClickListener;
    }

    public void stop() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }
}
