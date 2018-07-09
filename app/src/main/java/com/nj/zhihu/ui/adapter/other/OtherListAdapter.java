package com.nj.zhihu.ui.adapter.other;

import android.content.Context;

import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherListAdapter extends MultiItemTypeAdapter<IBaseItem> {
    public OtherListAdapter(Context context, List<IBaseItem> datas) {
        super(context, datas);
        addItemViewDelegate(new OtherHeaderDelegate());
        addItemViewDelegate(new OtherSectionDelegate());
        addItemViewDelegate(new OtherItemDelegte());
    }
}
