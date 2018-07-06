package com.nj.zhihu.ui.adapter.drawer;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.ThemesOther;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 定义一个item的类，这个类实现了ItemViewDelegate接口，泛型表示数据类型的父类
 * 每一种item的类都要实现了ItemViewDelegate接口，并且数据类型是同一个父类
 * Created by Administrator on 2018-07-02.
 */

public class ThemeItemDelegate implements ItemViewDelegate<IBaseItem> {
    //返回这种item的布局
    @Override
    public int getItemViewLayoutId() {
        return R.layout.drawer_list_item;
    }

    //判断数据是否数据这种item
    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof ThemesOther;
    }

    //完成数据和事件的绑定
    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        ThemesOther themesOther = (ThemesOther) iBaseItem;
        holder.setText(R.id.tv_theme_title, themesOther.getName());
    }
}
