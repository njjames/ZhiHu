package com.nj.zhihu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018-07-09.
 */

public class BeforeThemeStories implements IBaseItem {

    private List<ThemesContentItem> stories;

    public List<ThemesContentItem> getStories() {
        return stories;
    }

    public void setStories(List<ThemesContentItem> stories) {
        this.stories = stories;
    }

}
