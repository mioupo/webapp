package com.web.base.common;


import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Pages<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> content;
    private int pageNum;
    private int pageSize;
	private int totalPages;
	private int rowCount;
    private int begin;

    /**
	 * 初始化Page
	 * @param pageNum		页号
	 * @param rowCount		数据总条数
	 */
	public Pages(int pageNum, int pageSize, List<T> content,int rowCount){
        this.content= content;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.begin = pageSize*(pageNum - 1);
		this.rowCount = rowCount;
		this.totalPages = pageSize > 0 ? (int)Math.ceil(((double) rowCount)/pageSize) : 0;
	}
	public List<T> getContent() {
		// TODO Auto-generated method stub
		return content;
	}

    public int getNumber() {
        return pageNum;
    }

    public int getSize() {
        return pageSize;
    }
    public int getBegin() {
        return begin;
    }
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return content.size();
	}

	public long getTotalElements() {
		// TODO Auto-generated method stub
		return rowCount;
	}

	public int getTotalPages() {
		// TODO Auto-generated method stub
		return totalPages;
	}

	public boolean hasContent() {
		if(content != null){
			return true;
		}
		return false;
	}

	public boolean hasNextPage() {
		if(totalPages > 0 && totalPages-1 > pageNum){
			return true;
		}
		return false;
	}

	public boolean hasPreviousPage() {
		if(pageNum > 0){
			return true;
		}
		return false;
	}

	public boolean isFirstPage() {
		if(pageNum > 0){
			return false;
		}
		return true;
	}

	public boolean isLastPage() {
		if(totalPages > 0 && totalPages-1 == pageNum){
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}



}
