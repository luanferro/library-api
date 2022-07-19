package com.luanferro.libraryapi.service;

import com.luanferro.libraryapi.api.dto.LoanFilterDTO;
import com.luanferro.libraryapi.model.entity.Loan;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LoanService {
    Loan save(Loan loan);

    Optional<Loan> getById(Long id);

    Loan update(Loan loan);

    Page<Loan> find(LoanFilterDTO filter, Pageable pageable);
}
