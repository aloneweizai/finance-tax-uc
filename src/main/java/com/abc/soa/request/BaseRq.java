package com.abc.soa.request;

public class BaseRq implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer page=1;
	private Integer size=15;
	private long totalItems;
	private int totalPage;      //总页数
    private int offset;         //每页起始
    
    public void calculate(){
    	if (this.page < 1)
        {
    		this.page    = 1;
        }
    	this.offset= (this.page - 1) * size;
    	this.totalPage   = (int) Math.ceil(this.totalItems / (double) this.size);
    	if (this.offset >= this.totalItems)
        {
    		this.page    = this.totalPage;
    		this.offset  = (this.page - 1) * size;
        }
        if (this.offset < 0)
        {
        	this.page    = 1;
        	this.offset  = 0;
        }
    }
    
    
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}


	
    
}
