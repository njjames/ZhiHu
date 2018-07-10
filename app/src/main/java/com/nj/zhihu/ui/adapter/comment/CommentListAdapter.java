package com.nj.zhihu.ui.adapter.comment;

import android.content.Context;

import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentListAdapter extends MultiItemTypeAdapter<IBaseItem> {

    public CommentListAdapter(Context context, List<IBaseItem> datas) {
        super(context, datas);
        addItemViewDelegate(new CommentLongHeaderDelegate());
    }
}
