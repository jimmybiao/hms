package com.jimmy.pojo;

public class Investment {

	private Integer id;
	private String investCategory;
	private String investSubCategory;
	private double amount;
	private String remark;
	private String investDate;
	private String updatedDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInvestCategory() {
		return investCategory;
	}
	public void setInvestCategory(String investCategory) {
		this.investCategory = investCategory;
	}
	public String getInvestSubCategory() {
		return investSubCategory;
	}
	public void setInvestSubCategory(String investSubCategory) {
		this.investSubCategory = investSubCategory;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInvestDate() {
		return investDate;
	}
	public void setInvestDate(String investDate) {
		this.investDate = investDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Override
	public String toString() {
		return "Investment [id=" + id + ", investCategory=" + investCategory + ", investSubCategory="
				+ investSubCategory + ", amount=" + amount + ", remark=" + remark + ", investDate=" + investDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}
