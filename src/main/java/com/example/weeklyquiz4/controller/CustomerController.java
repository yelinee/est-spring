package com.example.weeklyquiz4.controller;

import com.example.weeklyquiz4.dto.CustomerDto;
import com.example.weeklyquiz4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // 고객 추가
    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto addedCustomer = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
    }

    // 모든 고객 조회
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // 특정 고객 조회
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 고객 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        return customerService.updateBook(id, customerDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 고객 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        boolean deleted = customerService.deleteBook(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }







}
