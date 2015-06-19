package com.web.base.common;

/**
 * Created by yi on 2015/3/11.
 */
public class BaseEntity {

    protected Integer nextPage = 0;

    protected Integer pageSize = 10;

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getBegin() {
        return nextPage* pageSize;
    }
}
