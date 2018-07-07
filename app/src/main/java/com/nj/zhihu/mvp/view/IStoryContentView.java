package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.StoryContent;
import com.nj.zhihu.bean.StoryContentExtra;

/**
 * Created by Administrator on 2018-07-07.
 */

public interface IStoryContentView extends IBaseView {
    void onRequestError(String msg);

    void loadStoryContent(StoryContent storyContent);

    void loadStoryContentExtra(StoryContentExtra storyContentExtra);
}
