package com.example.CafeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CafeManagementSystem.entity.Category;
import com.example.CafeManagementSystem.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

	@Query("select m from Menu m where m.price = :price")
	public List<Menu> findByPrice(float price);
	
	@Query("select m from Menu m where m.itemName = :itemName")
	public List<Menu> findByItemName(String itemName);
	
	public List<Menu> findByCategory(Category category);
}
