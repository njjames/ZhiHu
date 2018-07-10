package com.nj.zhihu.ui.adapter.other;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.App;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.Editors;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.ui.activity.EditorActivity;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;
import com.nj.zhihu.widget.CircleTransform;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherSectionDelegate implements ItemViewDelegate<IBaseItem> {

    private Context mContext;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.other_section_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof OtherSection;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        final OtherSection otherSection = (OtherSection) iBaseItem;
        LinearLayout sectionContainer = holder.getView(R.id.other_section_container);
        mContext = holder.getConvertView().getContext();
        //如果没有添加过view，就添加，否则不重复添加
        if (sectionContainer.getChildCount() == 0) {
            for (Editors editors : otherSection.getEditors()) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(55, 55);
                layoutParams.setMargins(0, 0, 5, 0);
                imageView.setLayoutParams(layoutParams);
                if ((boolean) SpUtils.get(mContext, "NO_IMAGE_MODE", false)
                        && !NetWorkUtils.isWifiConnected(mContext)) {
                    GlideApp.with(mContext)
                            .load(R.drawable.editor_profile_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    //                        .transform(new CircleTransform())
                            .into(imageView);
                }else {
                    GlideApp.with(mContext)
                            .load(editors.getAvatar())
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    //                        .transform(new CircleTransform())
                            .into(imageView);
                }
                sectionContainer.addView(imageView);
            }
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditorActivity.class);
                intent.putParcelableArrayListExtra("editor_list", otherSection.getEditors());
                mContext.startActivity(intent);
            }
        });
    }
}
