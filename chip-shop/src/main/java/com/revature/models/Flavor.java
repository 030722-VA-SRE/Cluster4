package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;


@Entity
@Table(name="flavor")
@Check(constraints  = "ounces > 0 and price > 0")
public class Flavor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int ounces;
	@Column(nullable = false)
	private float price;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "brand_id")
	private Brand brand;

	public Flavor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flavor(int id, String name, int ounces, float price, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.ounces = ounces;
		this.price = price;
		this.brand = brand;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOunces() {
		return ounces;
	}

	public void setOunces(int ounces) {
		this.ounces = ounces;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrandId(Brand brand) {
		this.brand = brand;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, name, ounces, price);
	}
	
	//This equals method compares everything but the id number
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flavor other = (Flavor) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(name, other.name)
				&& ounces == other.ounces && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}

	@Override
	public String toString() {
		return "Flavor [id=" + id + ", name=" + name + ", ounces=" + ounces + ", price=" + price + ", brandId="
				+ brand + "]";
	}
	
}//end 
