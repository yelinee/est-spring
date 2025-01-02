package com.example.weeklyquiz4.service;

import com.example.weeklyquiz4.dto.StoreDto;
import com.example.weeklyquiz4.entity.StoreEntity;
import com.example.weeklyquiz4.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public StoreDto createStore(StoreDto storeDto) {
        StoreEntity storeEntity = storeDto.toEntity();
        StoreEntity savedStore = storeRepository.save(storeEntity);
        return StoreDto.fromEntity(savedStore);
    }

    @Transactional(readOnly = true)
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<StoreDto> getStoreById(Long id) {
        return storeRepository.findById(id)
                .map(StoreDto::fromEntity);
    }

    @Transactional
    public Optional<StoreDto> updateStore(Long id, StoreDto storeDto) {
        return storeRepository.findById(id)
                .map(existingStore -> {
                    existingStore.setStoreName(storeDto.getStoreName());
                    existingStore.setStoreAddress(storeDto.getStoreAddress());
                    existingStore.setStorePhoneNumber(storeDto.getStorePhoneNumber());
                    return StoreDto.fromEntity(storeRepository.save(existingStore));
                });
    }

    @Transactional
    public boolean deleteStore(Long id) {
        return storeRepository.findById(id)
                .map(store -> {
                    storeRepository.delete(store);
                    return true;
                })
                .orElse(false);
    }

}
