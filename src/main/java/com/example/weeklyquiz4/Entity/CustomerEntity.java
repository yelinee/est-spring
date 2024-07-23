package com.example.weeklyquiz4.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    private int customerId;
    private String customerName;
    private String address;
    private String phone;

}
