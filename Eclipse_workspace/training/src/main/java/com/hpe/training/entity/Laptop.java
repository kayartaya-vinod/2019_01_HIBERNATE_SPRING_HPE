package com.hpe.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
public class Laptop {

	@Id
	@Column(name = "sl_no")
	private String serialNumber;
	private String make;
	private String model;

	private Employee ownedBy;

	public Laptop() {
	}

	public Laptop(String serialNumber, String make, String model) {
		this.serialNumber = serialNumber;
		this.make = make;
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Employee getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Employee ownedBy) {
		this.ownedBy = ownedBy;
	}

}
