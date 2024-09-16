package com.example.CafeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.entity.Category;
import com.example.CafeManagementSystem.entity.Menu;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.MenuRepository;


@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepository;
	
	public MenuServiceImpl(MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}

	@Override
	public Menu insertMenu(Menu menu) {

		System.out.println("Item added successfully "+menu);
		menu.setItemName(menu.getItemName());
		menu.setDescription(menu.getDescription());
		menu.setPrice(menu.getPrice());
		
		return menuRepository.save(menu);
	}

	@Override
	public List<Menu> getAllMenuRecord() {
	
		return menuRepository.findAll();
	}

	@Override
	public Menu getMenuById(int itemId) throws CustomerNotFoundException{

		Optional<Menu> result = menuRepository.findById(itemId);
		Menu menu;
		if(result.isPresent())
		{
			menu = result.get();
		}
		else
		{
			throw new CustomerNotFoundException("Menu Item Id not found "+itemId);
		}
		return menu;
	}

	@Override
	public Menu updateMenuById(Menu menu, int itemId) throws CustomerNotFoundException {

		Optional<Menu> result = menuRepository.findById(itemId);
		Menu exitMenuItem;
		
		if(result.isPresent())
		{
			exitMenuItem = result.get();
		}
		else {
			throw new CustomerNotFoundException("Menu Item Id not found "+itemId);
		}
		
		exitMenuItem.setItemName(menu.getItemName());
		exitMenuItem.setDescription(menu.getDescription());
		exitMenuItem.setPrice(menu.getPrice());
		exitMenuItem.setItemImage(menu.getItemImage());
		exitMenuItem.setCategory(menu.getCategory());
		
		menuRepository.save(exitMenuItem);
		return exitMenuItem;
	}

	@Override
	public void deleteMenuById(int itemId) throws CustomerNotFoundException {

		if(menuRepository.existsById(itemId))
		{
			menuRepository.deleteById(itemId);
		}
		else
		{
			throw new CustomerNotFoundException("Menu item id not found "+itemId);
		}
	
	}

	@Override
	public List<Menu> findByPrice(float price) {

		return menuRepository.findByPrice(price);
	}

	@Override
	public Menu findByItemName(String itemName) throws CustomerNotFoundException
	{

		List<Menu> exitsNames = getAllMenuRecord();
		
		for(Menu menu : exitsNames)
		{
			if(menu.getItemName().equalsIgnoreCase(itemName))
			{
				return menu;
			}
			else if(menu.getItemName().contains(itemName.toLowerCase())){
				return menu;
			}
			else {
				throw new CustomerNotFoundException("Item name not found with "+itemName);
			}
		}
		return null;
	}
	
	
	@Override
	public List<Menu> findByCategory(Category category) {
		return menuRepository.findByCategory(category);
	}

}
