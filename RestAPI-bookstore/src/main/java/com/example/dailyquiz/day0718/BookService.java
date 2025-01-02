package com.example.dailyquiz.day0718;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    // 전체 조회 로직
    public List<BookDto> getAllBooks() {
        return books.stream().map(BookService::convertToBookDto).collect(Collectors.toList()); // 리스트의 각 Book 객체를 DTO로 변환 후 새로운 리스트 생성
    }

    // 특정 id로 조회
    public BookDto getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .map(BookService::convertToBookDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found")); // 존재하지 않는 도서일 경우의 예외처리
    }

    // 새로운 도서 추가
    public BookDto createBook(BookDto bookDto) {
        Book book = convertToBookEntity(bookDto); // DTO를 엔티티로 변환한 후 도서 리스트에 해당 도서 객체를 추가
        book.setId(nextId++);
        books.add(book);
        return convertToBookDto(book); // 다시 DTO로 변환
    }

    // 도서 정보 수정
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book updatedBook = FindBookById(id);
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setAuthor(bookDto.getAuthor());
        updatedBook.setPrice(bookDto.getPrice());
        updatedBook.setIsbn(bookDto.isIsbn());
        updatedBook.setPublishedYear(bookDto.getPublishedYear());
        return convertToBookDto(updatedBook);
    }

    // 도서 삭제
    public boolean deleteBook(Long id) {
        Book deletedBook = FindBookById(id);
        if (deletedBook != null) {
            books.remove(deletedBook);
            return true;
        }
        return false;
    }

    // id로 도서 찾기
    private Book FindBookById(Long id) {
        Book bookFoundById = books.stream() // 해당 id와 일치하는 도서 조회
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return bookFoundById;
    }

    // Book Entity -> Book DTO
    private static BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.isIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedYear(book.getPublishedYear());
        return bookDto;
    }

    // Book DTO -> Book Entity
    private static Book convertToBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(book.isIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishedYear(bookDto.getPublishedYear());
        return book;
    }
}
