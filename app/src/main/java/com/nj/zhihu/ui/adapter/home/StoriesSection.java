package com.nj.zhihu.ui.adapter.home;

import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.utils.DateUtils;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesSection implements IBaseItem {
    private String mDate;

    public StoriesSection(String date) {
        mDate = date;
    }

    public String getDate() {
        if (mDate == null) {
            return null;
        }
        return DateUtils.transform(mDate);
    }
}
