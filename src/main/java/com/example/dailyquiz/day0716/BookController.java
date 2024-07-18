package com.example.dailyquiz.day0716;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    // 기존 메서드들은 그대로 유지

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book book) {
        updateBook(book);
        return "redirect:/books";
    }

    // 삭제 처리 (GET 요청으로 변경)
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return "redirect:/books";
    }

    private Book findBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                break;
            }
        }
    }
}
