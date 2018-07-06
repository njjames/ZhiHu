package com.nj.zhihu.ui.adapter.home;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesSectionDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.story_section_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof StoriesSection;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        StoriesSection storiesSection = (StoriesSection) iBaseItem;
        if (position == 1) {
            holder.setText(R.id.tv_story_section, "今日热点");
        }else {
            holder.setText(R.id.tv_story_section, ((StoriesSection) iBaseItem).getDate());
        }
    }
}
