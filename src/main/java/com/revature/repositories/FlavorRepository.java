package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Brand;
import com.revature.models.Flavor;

public interface FlavorRepository extends JpaRepository<Flavor, Integer>{
	public List<Flavor> findFlavorByBrand(Brand brand);
	
}//end
