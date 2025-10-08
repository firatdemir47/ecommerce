package com.firatdemir.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firatdemir.dto.PaymentMethodDto;
import com.firatdemir.service.PaymentMethodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

	private final PaymentMethodService service;

	public PaymentMethodController(PaymentMethodService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<PaymentMethodDto> create(@Valid @RequestBody PaymentMethodDto dto) {
		PaymentMethodDto created = service.create(dto);
		return ResponseEntity.created(java.net.URI.create("/api/payment-methods/" + created.getId())).body(created);
	}

	@GetMapping
	public ResponseEntity<List<PaymentMethodDto>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentMethodDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PaymentMethodDto> update(@PathVariable Long id, @Valid @RequestBody PaymentMethodDto dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}


