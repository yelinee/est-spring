package com.example.weeklyquiz4.dto;

import com.example.weeklyquiz4.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long customerId;
    private String name;
    private String address;
    private String phone;

    // Entity -> DTO
    public static CustomerDto fromEntity(CustomerEntity customerEntity){
        return CustomerDto.builder()
                .customerId(customerEntity.getCustomerId())
                .name(customerEntity.getName())
                .address(customerEntity.getName())
                .phone(customerEntity.getPhone())
                .build();
    }

    // DTO -> Entity
    public CustomerEntity toEntity() {
        return new CustomerEntity(
                this.customerId,
                this.name,
                this.address,
                this.phone
        );
    }

}
