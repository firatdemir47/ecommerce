package com.firatdemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

	private Long id;

	private Long orderId;

	private Long productID;

	private Integer quantity;

	private Double price;

}
