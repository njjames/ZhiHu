package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;

/**
 * Created by Administrator on 2018-07-10.
 */

public interface ICommectView extends IBaseView {
    void onRequestErr(String msg);

    void loadLongComments(StoryContentLongComment storyContentLongComment);

    void loadSgortComments(StoryContentShortComment storyContentShortComment);
}
