package com.hames.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class ExpenseCategory extends Audit{
	
	@Id
	private String categoryId;
	@Indexed(unique=true)
	private String categoryName;
	private String categoryDescription;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "ExpenseCategory [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryDescription=" + categoryDescription
				+ "]";
	}

}
