package com.luanferro.libraryapi.service.impl;

import com.luanferro.libraryapi.api.dto.BookDTO;
import com.luanferro.libraryapi.exception.BusinessException;
import com.luanferro.libraryapi.model.entity.Book;
import com.luanferro.libraryapi.model.repository.BookRepository;
import com.luanferro.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooKServiceImpl implements BookService {
    private BookRepository repository;

    public BooKServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if(repository.existsByIsbn(book.getIsbn())) {
            throw new BusinessException("Isbn ja cadastrado");
        }

        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Book book) {
    }

    @Override
    public Book update(Book book) {
        return null;
    }
}
