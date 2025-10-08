package com.firatdemir.service.ımpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firatdemir.dto.PaymentMethodDto;
import com.firatdemir.exception.NotFoundException;
import com.firatdemir.model.PaymentMethod;
import com.firatdemir.repository.PaymentMethodRepository;
import com.firatdemir.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	private final PaymentMethodRepository repository;

	public PaymentMethodServiceImpl(PaymentMethodRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public PaymentMethodDto create(PaymentMethodDto dto) {
		PaymentMethod method = toEntity(dto);
		PaymentMethod saved = repository.save(method);
		return toDto(saved);
	}

	@Override
	public PaymentMethodDto getById(Long id) {
		PaymentMethod method = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ödeme yöntemi bulunamadı, id: " + id));
		return toDto(method);
	}

	@Override
	public List<PaymentMethodDto> getAll() {
		return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PaymentMethodDto update(Long id, PaymentMethodDto dto) {
		PaymentMethod method = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ödeme yöntemi bulunamadı, id: " + id));
		method.setName(dto.getName());
		method.setType(dto.getType());
		method.setEnabled(dto.isEnabled());
		return toDto(repository.save(method));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		PaymentMethod method = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ödeme yöntemi bulunamadı, id: " + id));
		repository.delete(method);
	}

	private PaymentMethod toEntity(PaymentMethodDto dto) {
		PaymentMethod m = new PaymentMethod();
		m.setId(dto.getId());
		m.setName(dto.getName());
		m.setType(dto.getType());
		m.setEnabled(dto.isEnabled());
		return m;
	}

	private PaymentMethodDto toDto(PaymentMethod entity) {
		PaymentMethodDto dto = new PaymentMethodDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setEnabled(entity.isEnabled());
		return dto;
	}
}


