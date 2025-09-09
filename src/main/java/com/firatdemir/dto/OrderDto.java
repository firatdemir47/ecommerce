package com.firatdemir.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

	private Long id;

	private LocalDateTime orderDate;

	private Long userId;

	private List<OrderItemDto> items;

}
