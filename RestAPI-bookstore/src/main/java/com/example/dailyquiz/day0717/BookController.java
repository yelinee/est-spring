package com.example.dailyquiz.day0717;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private List<Book> books = new ArrayList<Book>();
    private Long nextId = 1L;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> BookDtoList = books.stream().map(this::convertToBookDto).collect(Collectors.toList()); // 리스트의 각 Book 객체를 DTO로 변환 후 새로운 리스트 생성
        return ResponseEntity.ok(BookDtoList); // 결과값을 200 OK 코드와 함께 반환
    }

    // 아이디로 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        BookDto bookDto = books.stream().map(this::convertToBookDto)
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found")); // 존재하지 않는 도서일 경우의 예외처리

        return ResponseEntity.ok(bookDto); // 결과값을 200 OK 코드와 함께 반환
    }

    // 새로운 도서 추가
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = convertToBookEntity(bookDto); // DTO를 엔티티로 변환한 후 도서 리스트에 해당 도서 객체를 추가
        book.setId(nextId++);
        books.add(book);
        BookDto addedBookDto = convertToBookDto(book); // 다시 DTO로 변환
        return ResponseEntity.ok(addedBookDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        Book updatedbook = books.stream() // 해당 id의 도서가 있는지 조회
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found")); // 없을 시의 예외처리

        updatedbook.setTitle(bookDto.getTitle());
        updatedbook.setAuthor(bookDto.getAuthor());
        updatedbook.setPrice(bookDto.getPrice());
        updatedbook.setIsbn(bookDto.isIsbn());
        updatedbook.setPublishedYear(bookDto.getPublishedYear());

        BookDto updatedBookDto = convertToBookDto(updatedbook); // 수정된 도서 객체를 DTO로 변환

        return ResponseEntity.ok(updatedBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book deletedBook = books.stream() // 해당 id와 일치하는 도서 조회
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        books.remove(deletedBook);
        return ResponseEntity.noContent().build(); // 204 No Content 상태코드 반환

    }

    // Book Entity -> Book DTO
    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        bookDto.setIsbn(book.isIsbn());
        bookDto.setPublishedYear(book.getPublishedYear());

        return bookDto;
    }

    // Book DTO -> Book Entity
    private static Book convertToBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setIsbn(bookDto.isIsbn());
        book.setPublishedYear(bookDto.getPublishedYear());

        return book;
    }
}
