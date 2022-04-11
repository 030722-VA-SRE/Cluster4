package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//entity specifies that the class is an entity and is mapped to a database table
@Entity
@Table(name = "brand")
public class Brand{
	
	//Id specifies the primary key of an entity
	@Id
	//GenerationType.Identity is the easiest to use but not the best one from a performance POV
	//This approach has a significant drawback if you use Hibernate
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//Column is used for adding the column the name in the table
	@Column(nullable = false)
	private String name;
	
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Brand(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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


	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return id == other.id && Objects.equals(name, other.name);
	}


	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}

	
	
	
	
	

}//end class
