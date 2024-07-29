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
    private Long storeId;
    private String storeName;
    private String storeAddress;
    private String storePhoneNumber;

    // Entity -> DTO
    public static StoreDto fromEntity(StoreEntity storeEntity) {
        return StoreDto.builder()
                .storeId(storeEntity.getStoreId())
                .storeName(storeEntity.getStoreName())
                .storeAddress(storeEntity.getStoreAddress())
                .storePhoneNumber(storeEntity.getStorePhoneNumber())
                .build();
    }

    // DTO -> Entity
    public StoreEntity toEntity() {
        return new StoreEntity(
                this.storeId,
                this.storeName,
                this.storeAddress,
                this.storePhoneNumber);
    };
}