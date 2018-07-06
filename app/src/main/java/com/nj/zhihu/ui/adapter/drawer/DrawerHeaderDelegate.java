package com.nj.zhihu.ui.adapter.drawer;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-02.
 */

public class DrawerHeaderDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.drawer_header_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof DrawerHeader;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {

    }
}
