package com.nj.zhihu.ui.adapter.home;

import android.content.Context;

import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesListAdapter extends MultiItemTypeAdapter<IBaseItem> {

    public StoriesListAdapter(Context context, List<IBaseItem> datas) {
        super(context, datas);
        addItemViewDelegate(new StoriesHeaderDelegate());
        addItemViewDelegate(new StoriesSectionDelegate());
        addItemViewDelegate(new StoriesItemDelegate());
    }
}
