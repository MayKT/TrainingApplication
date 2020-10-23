package com.mkttestprojects.trainingapplication.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantsoe on 24/08/2020.
 **/
public class PageableList<T extends Pageable> implements Pageable {

    private List<T> pageableList;
    private Class<T> tClass;

    public Class<T> getTClass() {
        return tClass;
    }

    public PageableList(List<T> pageableList, Class<T> tClass) {

        this.tClass = tClass;

        if (pageableList == null)
            this.pageableList = new ArrayList<T>();
         else this.pageableList = pageableList;

    }

    public List<T> getPageableList() {
        return pageableList;
    }

    public void setPageableList(List<T> pageableList) {
        this.pageableList = pageableList;
    }
}
