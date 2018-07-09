package com.nj.zhihu.ui.adapter.other;

import com.nj.zhihu.bean.Editors;
import com.nj.zhihu.bean.IBaseItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherSection implements IBaseItem {
    private ArrayList<Editors> editors;

    public OtherSection(ArrayList<Editors> editors) {
        this.editors = editors;
    }

    public ArrayList<Editors> getEditors() {
        return editors;
    }
}
