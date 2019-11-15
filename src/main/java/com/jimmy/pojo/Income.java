package com.jimmy.pojo;

public class Income extends BasePojo{

	@Override
	public String toString() {
		return "Income [getId()=" + getId() + ", getCategory()=" + getCategory() + ", getSubcategory()="
				+ getSubcategory() + ", getCreatedTime()=" + getCreatedTime() + ", getUpdatedTime()=" + getUpdatedTime()
				+ ", getRemark()=" + getRemark() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
