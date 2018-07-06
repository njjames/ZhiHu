package com.nj.zhihu.ui.adapter.home;

import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.TopStories;

import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesHeader implements IBaseItem {
    private List<TopStories> mTopStories;

    public StoriesHeader(List<TopStories> topStories) {
        mTopStories = topStories;
    }

    public List<TopStories> getTopStories() {
        return mTopStories;
    }
}
