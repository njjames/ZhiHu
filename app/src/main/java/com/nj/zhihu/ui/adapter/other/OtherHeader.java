package com.nj.zhihu.ui.adapter.other;

import com.nj.zhihu.bean.IBaseItem;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherHeader implements IBaseItem {
    private String image;

    public OtherHeader(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
