package com.luanferro.libraryapi.model.repository;

import com.luanferro.libraryapi.model.entity.Book;
import com.luanferro.libraryapi.model.entity.Loan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static com.luanferro.libraryapi.model.repository.BookRepositoryTest.createNewBook;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepositoy repositoy;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("deve verificar se existe emprestimo nao devolvido para o livro")
    public void existsByBookAndNotReturnedTest() {
        //cenario
        Book book = createNewBook("123");
        entityManager.persist(book);

        Loan loan = Loan.builder().book(book).customer("Fulano").loanDate(LocalDate.now()).build();
        entityManager.persist(loan);

        boolean exists = repositoy.existsByBookAndNotReturned(book);

        assertThat(exists).isTrue();
    }
}
