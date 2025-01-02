package com.example.estspring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository이 자동 bean 등록시켜줌.(@Repository 없이도)

    //jpa puppy 이용하면 쉽게 만들 수 있음
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Book> findByStockGreaterThanEqual(Integer minStock);

    List<Book> findByTitleContaining(String keyword);

}