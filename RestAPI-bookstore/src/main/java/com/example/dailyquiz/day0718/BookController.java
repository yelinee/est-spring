package com.example.dailyquiz.day0718;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 전체 조회(요청-응답)
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> BookDtoList = bookService.getAllBooks();
        return ResponseEntity.ok(BookDtoList); // 결과값을 200 OK 코드와 함께 반환
    }

    // 아이디로 조회(요청-응답)
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto); // 결과값을 200 OK 코드와 함께 반환
    }

    // 새로운 도서 추가(요청-응답)
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookDto addedBookDto = bookService.createBook(bookDto);
        return ResponseEntity.ok(addedBookDto);

    }

    // 도서 정보 수정(요청-응답)
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        BookDto updatedBookDto = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBookDto);
    }

    // 도서 삭제(요청-응답)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean completedDelete = bookService.deleteBook(id);
        if (!completedDelete) {
            log.info("Book deletion failed");
        }
        return ResponseEntity.noContent().build(); // 204 No Content 상태코드 반환 (정상적인 요청. 제공할 콘텐츠는 없음)
    }

}
