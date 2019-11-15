package com.jimmy.pojo;

public class Expense extends BasePojo{

	@Override
	public String toString() {
		return "Expense [getId()=" + getId() + ", getCategory()=" + getCategory() + ", getSubcategory()="
				+ getSubcategory() + ", getCreatedTime()=" + getCreatedTime() + ", getUpdatedTime()=" + getUpdatedTime()
				+ ", getRemark()=" + getRemark() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
