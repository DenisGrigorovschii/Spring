package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", "/books"})
    public String list(Model model) {
        model.addAttribute("books", repository.findAll());
        return "books/list";
    }

    @GetMapping("/books/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/books")
    public String save(@ModelAttribute Book book, RedirectAttributes ra) {
        repository.save(book);
        ra.addFlashAttribute("success", "Книга сохранена");
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Book> b = repository.findById(id);
        if (b.isPresent()) {
            model.addAttribute("book", b.get());
            return "books/form";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repository.deleteById(id);
        ra.addFlashAttribute("success", "Книга удалена");
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Book> b = repository.findById(id);
        if (b.isPresent()) {
            model.addAttribute("book", b.get());
            return "books/view";
        } else {
            return "redirect:/books";
        }
    }
}
