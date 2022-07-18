package com.luanferro.libraryapi.service;


import com.luanferro.libraryapi.exception.BusinessException;
import com.luanferro.libraryapi.model.entity.Book;
import com.luanferro.libraryapi.model.entity.Loan;
import com.luanferro.libraryapi.model.repository.LoanRepositoy;
import com.luanferro.libraryapi.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LoanServiceTest {

    @MockBean
    private LoanRepositoy repository;
    private LoanService service;

    @BeforeEach
    public void setUp() {
        this.service = new LoanServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um emprestimo")
    public void saveLoanTest() {
        Book book = Book.builder().id(1l).build();
        String customer = "Fulano";
        Loan savingLoan = Loan.builder()
                .book(book)
                .customer(customer)
                .loanDate(LocalDate.now())
                .build();

        Loan savedLoan = Loan.builder()
                .id(1l)
                .book(book)
                .loanDate(LocalDate.now())
                .customer(customer)
                .build();

        Mockito.when(repository.existsByBookAndNotReturned(book)).thenReturn(false);
        Mockito.when(repository.save(savingLoan)).thenReturn(savedLoan);

        Loan loan = service.save(savingLoan);

        assertThat(loan.getId()).isEqualTo(savedLoan.getId());
        assertThat(loan.getBook().getId()).isEqualTo(savedLoan.getBook().getId());
        assertThat(loan.getCustomer()).isEqualTo(savedLoan.getCustomer());
        assertThat(loan.getLoanDate()).isEqualTo(savedLoan.getLoanDate());
    }

    @Test
    @DisplayName("Deve lancar erro de negocio ao salvar um emprestimo com livro ja emprestado")
    public void loanedBookSaveTest() {
        Book book = Book.builder().id(1l).build();
        String customer = "Fulano";
        Loan savingLoan = Loan.builder()
                .book(book)
                .customer(customer)
                .loanDate(LocalDate.now())
                .build();

        Mockito.when(repository.existsByBookAndNotReturned(book)).thenReturn(true);

        Throwable exception = catchThrowable(() -> service.save(savingLoan));

        assertThat(exception).isInstanceOf(BusinessException.class).hasMessage("Book already loaned");

        Mockito.verify(repository, Mockito.never()).save(savingLoan);

    }
}
