package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Brand;

/*indicates that the decorated class is a repository.
 * a repository is a mechanism for encapsulating storage, retrieval, and search behavior
 * which emulates a collection of objects 
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	public Brand findBrandByName(String name);
	
}//end interface
