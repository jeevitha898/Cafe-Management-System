package com.example.CafeManagementSystem.entity;

import java.util.HashMap;
import java.util.Map;



public enum Category {

	    ESPRESSO(0),
	    ICED_AND_FROZEN(1),
	    SMOOTHIES(2),
	    COFFEE(3),
	    TEA_AND_COCOA(4),
	    CHAI(5),
	    EXTRAS(6);
	    

	    private int value;
	    private static Map map = new HashMap<>();

	    private Category(int value) {
	        this.value = value;
	    }

	    static {
	        for (Category category : Category.values()) {
	            map.put(category.value, category);
	        }
	    }

	    public static Category valueOf(int category) {
	        return (Category) map.get(category);
	    }

	    public int getValue() {
	        return value;
	    }
	

}
