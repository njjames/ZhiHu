package com.nj.zhihu.ui.adapter.drawer;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.ThemesOther;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by nj on 2018/6/30.
 */

public class ThemesListAdapter extends MultiItemTypeAdapter<IBaseItem> {
    private static final String TAG = "ThemesListAdapterLOG";

    //默认是1，也就是默认选择首页
    private int mSelection = 1;
    private final Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //点击事件再调用我们定义的点击接口回调
            if (mOnItemClickListener!= null) {
                mOnItemClickListener.onDrawerHeaderClick(v);
            }
        }
    };

    //这里只要在构造方法中把itemview加进去，就可以在recyclerview中显示传递进来的集合了
    public ThemesListAdapter(Context context, List<IBaseItem> itemList) {
        super(context, itemList);
        mContext = context;
        addItemViewDelegate(new DrawerHeaderDelegate());
        addItemViewDelegate(new DrawerHomeDelegate());
        addItemViewDelegate(new ThemeItemDelegate());
    }

    public void setSelection(int selection) {
        mSelection = selection;
    }

    //绑定UI
    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        //如果当前的位置是选择的位置，那么显示按下的颜色
        if (position == mSelection) {
            holder.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.drawer_select));
        }else if (position > 0){//否则显示正常的颜色，这里需要有条件大于0，因为第一条索引为0的item是DrawerHeader
            holder.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.drawer_normal));

        }
    }

    //绑定事件
    //viewType就是item的类型，他就是addItemViewDelegate添加的序号，从0开始
    @Override
    protected void setListener(ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        super.setListener(parent, viewHolder, viewType);
        switch (viewType) {
            case 0:
                LinearLayout login = viewHolder.getView(R.id.login);
                Button collect = viewHolder.getView(R.id.collect);
                Button download = viewHolder.getView(R.id.download);
                //都调用点击事件
                login.setOnClickListener(mListener);
                collect.setOnClickListener(mListener);
                download.setOnClickListener(mListener);
                break;
            case 1:
                //只有在点击的时候才会调用，所以要设置点击事件监听
                viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: " + viewHolder.getAdapterPosition());
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemViewClick(viewHolder.getAdapterPosition());
                        }
                    }
                });
                //不能这样调用，否则没有点击就会执行，肯定报错
                //                if (mOnItemClickListener != null) {
                //                    mOnItemClickListener.onItemViewClick(viewHolder.getAdapterPosition());
                //                }
                break;
            case 2:
                viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: " + viewHolder.getAdapterPosition());
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemViewClick(viewHolder.getAdapterPosition());
                        }
                    }
                });
                //这样点击+就会响应后加的事件
                viewHolder.getView(R.id.iv_add_follow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onFollowClick();
                        }
                    }
                });
                break;
        }
    }

    //回调接口
    public interface OnItemClickListener {
        //DrawerHeader的点击回调
        void onDrawerHeaderClick(View view);

        //添加主题的回到
        void onFollowClick();

        //ThemeItem的点击回调
        void onItemViewClick(int position);
    }

    //设置回调的接口
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
