package com.luanferro.libraryapi.service.impl;

import com.luanferro.libraryapi.api.dto.LoanFilterDTO;
import com.luanferro.libraryapi.exception.BusinessException;
import com.luanferro.libraryapi.model.entity.Loan;
import com.luanferro.libraryapi.model.repository.LoanRepositoy;
import com.luanferro.libraryapi.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class LoanServiceImpl implements LoanService {
    private LoanRepositoy repository;

    public LoanServiceImpl(LoanRepositoy repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        if(repository.existsByBookAndNotReturned(loan.getBook())) {
            throw new BusinessException("Book already loaned");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Loan update(Loan loan) {
        return repository.save(loan);
    }

    @Override
    public Page<Loan> find(LoanFilterDTO filter, Pageable pageable) {
        return repository.findByBookIsbnOrCustomer(filter.getIsbn(), filter.getCustomer(), pageable);
    }
}
