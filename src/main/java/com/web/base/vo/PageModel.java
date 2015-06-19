package com.web.base.vo;

import java.util.Calendar;


public class PageModel {

    /** default time point for search */
    private Long checkAt = Calendar.getInstance().getTimeInMillis();

    /** default page size is 20 */
    private int pageSize = 20;

    /** default next page is 1 */
    private int gotoPage = 1;

    private Object objs;

    public PageModel() {
    }

    public PageModel(int pageSize, int gotoPage) {
        this.pageSize = pageSize;
        this.gotoPage = gotoPage;
    }

    public long getCheckAt() {
        return checkAt;
    }

    public void setCheckAt(Long checkat) {
        this.checkAt = checkat == null ? this.checkAt : checkat;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pagesize) {
        this.pageSize = pagesize;
    }

    public int getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(int gotopage) {
        this.gotoPage = gotopage;
    }

	public Object getObjs() {
		return objs;
	}

	public void setObjs(Object objs) {
		this.objs = objs;
	}

}
