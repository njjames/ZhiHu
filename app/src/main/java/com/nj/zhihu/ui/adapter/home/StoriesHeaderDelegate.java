package com.nj.zhihu.ui.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.TopStories;
import com.nj.zhihu.ui.activity.StoryContentActivity;
import com.nj.zhihu.widget.banner.Banner;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesHeaderDelegate implements ItemViewDelegate<IBaseItem> {

    private Context mContext;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.story_header_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof StoriesHeader;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        //这样得到上下文对象
        mContext = holder.getConvertView().getContext();
        StoriesHeader storiesHeader = (StoriesHeader) iBaseItem;
        Banner banner = holder.getView(R.id.banner);
        banner.setDataList(storiesHeader.getTopStories()).start();

        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void onBannerClick(int id) {
                Intent intent = new Intent(mContext, StoryContentActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
    }
}
