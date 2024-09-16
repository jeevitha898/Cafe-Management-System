package com.example.CafeManagementSystem.service;
import com.example.CafeManagementSystem.entity.Category;
import com.example.CafeManagementSystem.entity.Menu;
import java.util.List;

public interface MenuService {

	public Menu insertMenu(Menu menu);
	public List<Menu> getAllMenuRecord();
	public Menu getMenuById(int itemId);
	public Menu updateMenuById(Menu menu, int itemId);
	public void deleteMenuById(int itemId);
	public List<Menu> findByPrice(float price);
	public Menu findByItemName(String itemName);
	public List<Menu> findByCategory(Category category);
}
