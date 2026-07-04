package com.demouser.expensetracker.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "cat_id")
	private Long catId;
	@Column(name = "cat_type",nullable = false)
	private String categoryType;

	@OneToMany(mappedBy = "category")
	private List<Expense> expenses = new ArrayList<>();
	
	public Category(){}

	public Category(Long catId, String categoryType, List<Expense> expenses){
	    this.catId = catId;
		this.categoryType = categoryType;
		this.expenses = expenses;
	}

	public Long getCatId(){
	    return catId;
	}
	
	public String getCategoryType(){
	    return categoryType;
	} 

	public void setCategoryType(String categoryType){
	    this.categoryType = categoryType; 
	}

	public List<Expense> getExpenses(){
	    return expenses;
	}

	public void addExpense(Expense expense) {
    expenses.add(expense);
    expense.setCategory(this);
}

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setCategory(null);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Category)) return false;
        Category category = (Category) o;
        return catId != null && catId.equals(category.catId);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(catId);
    }
    
    @Override
    public String toString(){
        return "Category id "+catId+" Category Type " + categoryType; 
    }
	
}
