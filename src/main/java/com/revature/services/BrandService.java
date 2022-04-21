package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BrandAlreadyExistException;
import com.revature.exceptions.BrandNotFoundException;
import com.revature.models.Brand;
import com.revature.repositories.BrandRepository;

//@Service is used with classes that provide some business functionalities
//It is used to mark the class as a service provider
@Service
public class BrandService {
	
	private BrandRepository br;
	private static Logger LOG =LoggerFactory.getLogger(BrandService.class);
	
	/*@Autowired annotation indicates that the constructor should be autowired when creating the bean
	 * It is used for dependency injection - providing the objects that an object needs
	 * instead of having it construct them itself
	*/
	@Autowired
	public BrandService(BrandRepository br) {
		super();
		this.br = br;
	}//end
	
	//return all of the brands
	public List<Brand> getAllBrands(){
		
		LOG.debug("Retrieving all brands");
		
		List<Brand> list = br.findAll();
		
		LOG.debug("Done retrieving all brands");
		
		return list;
	}//end
	
	//create and adds brand to db
	@Transactional
	public Brand createBrand(Brand newBrand){
		
		LOG.debug("Attempting to create new brand");
		
		Brand b = br.findBrandByName(newBrand.getName());
		
		if(b != null){
			throw new BrandAlreadyExistException(b.getName() + " already exist");
		}//end if
		
		//save returns the saved entity
		
		Brand nB = br.save(newBrand);
		
		LOG.debug("Done creating new brand");
		
		return nB;
	}//end
	
	public Brand getBrandById(int id){
		
		LOG.debug("Attempting to retreive brand by id");
		
		Brand brand = br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		
		LOG.debug("Done retrieving brand by id");
		LOG.info("Looked up " + brand.getName());
		return brand;
		
	}//end
	
	@Transactional
	public Brand updateBrand(int id, Brand brand) {
		//Can use the save method to update brand
		LOG.debug("Attempting to update brand");
		
		Brand b = br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		brand.setId(b.getId());
		
		Brand uB = br.save(brand);
		
		LOG.debug("Done updating brand");
		
		return uB; 
	}//end
	
	
	@Transactional
	public boolean deleteBrandById(int id){
		
		LOG.debug("Attempting to delete brand by id");
		
		br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		br.deleteById(id);
		
		LOG.debug("Done deleting brandby id");
		return true;
		
	}
}//end 
