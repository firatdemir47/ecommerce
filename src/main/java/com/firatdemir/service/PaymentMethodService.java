package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.PaymentMethodDto;

public interface PaymentMethodService {

	PaymentMethodDto create(PaymentMethodDto dto);

	PaymentMethodDto getById(Long id);

	List<PaymentMethodDto> getAll();

	PaymentMethodDto update(Long id, PaymentMethodDto dto);

	void delete(Long id);
}


