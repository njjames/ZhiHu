package com.nj.zhihu.ui.adapter.other;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;
import com.nj.zhihu.widget.banner.Banner;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherHeaderDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.other_header_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof OtherHeader;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        OtherHeader otherHeader = (OtherHeader) iBaseItem;
        ImageView otherImage = holder.getView(R.id.other_image);
        Context context = holder.getConvertView().getContext();
        if ((boolean) SpUtils.get(context, "NO_IMAGE_MODE", false)
                && !NetWorkUtils.isWifiConnected(context)) {
            GlideApp.with(context)
                    .load(R.drawable.image_top_default)
                    .into(otherImage);
        }else {
            GlideApp.with(context)
                    .load(otherHeader.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(otherImage);
        }
    }
}
