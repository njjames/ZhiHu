package com.nj.zhihu.bean;

import java.util.List;

/**
 * Created by nj on 2018/6/30.
 */

public class Themes implements IBaseItem {
    /**
     * limit : 1000
     * subscribed : []
     * others : []
     */

    private int limit;
    private List<?> subscribed;
    private List<ThemesOther> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<ThemesOther> getOthers() {
        return others;
    }

    public void setOthers(List<ThemesOther> others) {
        this.others = others;
    }
}
