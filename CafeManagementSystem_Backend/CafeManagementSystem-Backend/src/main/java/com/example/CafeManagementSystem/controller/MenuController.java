package com.example.CafeManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.entity.Category;
import com.example.CafeManagementSystem.entity.Menu;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.service.MenuServiceImpl;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/menus")
public class MenuController {

	private MenuServiceImpl menuServiceImpl;

	public MenuController(MenuServiceImpl menuServiceImpl) {
		super();
		this.menuServiceImpl = menuServiceImpl;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Menu> insertMenuRecod(@Valid @RequestBody Menu menu)
	{
		return new ResponseEntity<Menu>(menuServiceImpl.insertMenu(menu), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Menu> getAllItemRecord()
	{
		return menuServiceImpl.getAllMenuRecord();
	}
	
	
	@GetMapping("/menu/{itemId}")
	public ResponseEntity<Menu> getmenuItemById(@PathVariable int itemId)
	{
		return new ResponseEntity<Menu>(menuServiceImpl.getMenuById(itemId), HttpStatus.OK);
	}
	
	
	@PutMapping("/edit/{itemId}")
	public ResponseEntity<Menu> updateMenuById(@Valid @PathVariable int itemId, @RequestBody Menu menu) {

		return new ResponseEntity<Menu>(menuServiceImpl.updateMenuById(menu, itemId), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/menu/{itemId}")
	public String deleteMenuById(@PathVariable int itemId) {

		menuServiceImpl.deleteMenuById(itemId);
		return "Deleted Successfully with id "+itemId;
	}
	
	
	@GetMapping("/price/{price}")
	public List<Menu> findByPrice(@PathVariable float price) {

		return menuServiceImpl.findByPrice(price);
	}
	
	@GetMapping("/name/{itemName}")
	public Menu findByItemName(@PathVariable String itemName) {

		return menuServiceImpl.findByItemName(itemName);
	}
	
	@GetMapping("/category/{categoryId}")
	public List<Menu> getAllBooksByCategory(@PathVariable int categoryId) {
		Category m = Category.valueOf(categoryId);
		return menuServiceImpl.findByCategory(m);
	}
}
	




