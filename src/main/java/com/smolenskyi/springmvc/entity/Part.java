package com.smolenskyi.springmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="part")
public class Part {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="namePart")
	private String namePart;
	
	@Column(name="requirement")
	private boolean requirement;
	
	@Column(name="countInStock")
	private int countInStock;
	
	public Part() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamePart() {
		return namePart;
	}

	public void setNamePart(String namePart) {
		this.namePart = namePart;
	}

	public boolean isRequirement() {
		return requirement;
	}

	public void setRequirement(boolean requirement) {
		this.requirement = requirement;
	}

	public int getCountInStock() {
		return countInStock;
	}

	public void setCountInStock(int countInStock) {
		this.countInStock = countInStock;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", firstName=" + namePart + ", lastName=" + requirement + ", email=" + countInStock + "]";
	}
}





