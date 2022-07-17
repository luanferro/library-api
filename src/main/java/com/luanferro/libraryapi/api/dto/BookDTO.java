package com.luanferro.libraryapi.api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;


}
