package com.luanferro.libraryapi.service.impl;

import com.luanferro.libraryapi.exception.BusinessException;
import com.luanferro.libraryapi.model.entity.Book;
import com.luanferro.libraryapi.model.repository.BookRepository;
import com.luanferro.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

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
}
