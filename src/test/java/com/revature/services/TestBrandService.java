package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.BrandAlreadyExistException;
import com.revature.exceptions.BrandNotFoundException;
import com.revature.models.Brand;
import com.revature.repositories.BrandRepository;

@ExtendWith(MockitoExtension.class)
class TestBrandService {
	
	@Mock
	private BrandRepository br;
	
	@InjectMocks
	BrandService bs;
	
	@Test
	void getAllBrandsTest(){
		
		List<Brand> brandList = new ArrayList<>();
		Brand b1 = new Brand(1, "Doritos");
		Brand b2 = new Brand(2, "Cheetos");
		Brand b3 = new Brand(3, "Lays");
		
		brandList.add(b1);
		brandList.add(b2);
		brandList.add(b3);
		
		Mockito.when(br.findAll()).thenReturn(brandList);
		
		assertEquals(bs.getAllBrands(), brandList);
		
	}//end
	
	@Test
	void createBrandTest(){
		Brand newBrand = new Brand(1, "Doritos");
		
		Mockito.when(br.save(newBrand)).thenReturn(newBrand);
		
		assertEquals(bs.createBrand(newBrand), newBrand);
		
	}//end
	
	@Test
	void createBrandFailTest(){
		
		Brand newBrand = new Brand(1, "Doritos");
		
		Mockito.when(br.findBrandByName(newBrand.getName())).thenReturn(newBrand);
		
		boolean thrown = false;
		
		try {
			bs.createBrand(newBrand);
		}catch(BrandAlreadyExistException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
	}//end
	
	
	@Test
	void getBrandByIdTest(){
		Brand newBrand = new Brand(1, "Doritos");
		
		Mockito.when(br.findById(1)).thenReturn(Optional.of(newBrand));
		
		assertEquals(bs.getBrandById(1), newBrand);
		
	}//end
	
	@Test
	void getBrandByIdFailTest(){
		
		Assertions.assertThrows(BrandNotFoundException.class, () -> bs.getBrandById(1));
		
	}//end
	
	
	@Test
	void updateBrandTest(){
		
		Brand b = new Brand(1, "Test");
		
		Mockito.when(br.findById(1)).thenReturn(Optional.of(b));
		Mockito.when(br.save(b)).thenReturn(b);
		
		assertEquals(bs.updateBrand(1, b), b);
		
	}//end
	
	@Test
	void updateBrandFailTest() {
		
		Brand b = new Brand(1, "Test");
		
		Assertions.assertThrows(BrandNotFoundException.class, () -> bs.updateBrand(1, b));
	}//end
	
	@Test
	void deleteBrandTest() {
		
		Brand b = new Brand(1, "Test");
		Mockito.when(br.findById(1)).thenReturn(Optional.of(b));
		assertTrue(bs.deleteBrandById(1));
	}//end
	
	@Test
	void deleteBrandFailTest() {
		assertThrows(BrandNotFoundException.class, () -> bs.deleteBrandById(1));
	}
}//end
