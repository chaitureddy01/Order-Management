package com.flmfoods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {

	private int itemId;
	
	//private String itemName;
	
	private int quantity;
	
	//private double price;
}
