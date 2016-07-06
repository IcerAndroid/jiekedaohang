package com.yxr.jiekedaohang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icer on 16/6/7.
 */
public class DataProvider {
    public static DataProvider getInstance() {
        return SingletonHolder.instance;
    }

    public static class SingletonHolder {
        private static DataProvider instance = new DataProvider();
    }

    private static List<Section> mSectionList = new ArrayList<>();


    public static void putData(List list) {
        if (list == null) return;
        mSectionList.clear();
        mSectionList.addAll(list);
    }

    public  List<Section> getData() {

        return mSectionList;
    }

    /**
     * 获取模块中的列表信息
     *
     * @param list
     * @param type
     * @return
     */
    public  List<Section> getListByType(List<Section> list, int type) {
        ArrayList<Section> sections = new ArrayList<>();
        if (list == null || list.size() == 0) return sections;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            int itemtype = list.get(i).getType();
            if (itemtype == type) {
                sections.add(list.get(i));
            }
        }
        return sections;
    }


}
