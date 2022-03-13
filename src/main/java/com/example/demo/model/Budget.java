package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@NamedQuery(name = "myFindBudget", query = "select sum(n.nowBudget) FROM budgets b")
@Table(name="budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id", nullable = false)
	private Long budgetId;
	
	@NotNull
	@Positive
	@Column(name = "now_budget")
	private int nowBudget;
}
