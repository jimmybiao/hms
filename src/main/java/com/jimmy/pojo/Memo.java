package com.jimmy.pojo;

public class Memo {

	private Integer id;
	private String title;
	private Double amount;
	private String remark;
	private String memoDate;
	
	public String getMemoDate() {
		return memoDate;
	}
	public void setMemoDate(String memoDate) {
		this.memoDate = memoDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Memo [id=" + id + ", title=" + title + ", amount=" + amount + ", remark=" + remark + ", memoDate="
				+ memoDate + "]";
	}
	
}
