package com.example.weeklyquiz4.dto;

import com.example.weeklyquiz4.entity.StoreEntity;
import lombok.*;

/**
 * DTO for {@link StoreEntity}
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    int storeId;
    String storeName;
    String storeAddress;
    String storePhoneNumber;
}