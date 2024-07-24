package com.example.weeklyquiz4.service;

import com.example.weeklyquiz4.dto.CustomerDto;
import com.example.weeklyquiz4.entity.CustomerEntity;
import com.example.weeklyquiz4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto addCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerDto.toEntity();
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return CustomerDto.fromEntity(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerDto::fromEntity);
    }

    @Transactional
    public Optional<CustomerDto> updateBook(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customerDto.getName());
                    existingCustomer.setAddress(customerDto.getAddress());
                    existingCustomer.setPhone(customerDto.getPhone());
                    return CustomerDto.fromEntity(customerRepository.save(existingCustomer));
                });
    }

    @Transactional
    public boolean deleteBook(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return true;
                })
                .orElse(false);
    }
}
