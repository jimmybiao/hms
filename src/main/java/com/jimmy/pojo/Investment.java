package com.jimmy.pojo;

public class Investment extends BasePojo{

	@Override
	public String toString() {
		return "Investment [getId()=" + getId() + ", getCategory()=" + getCategory() + ", getSubcategory()="
				+ getSubcategory() + ", getCreatedTime()=" + getCreatedTime() + ", getUpdatedTime()=" + getUpdatedTime()
				+ ", getRemark()=" + getRemark() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
