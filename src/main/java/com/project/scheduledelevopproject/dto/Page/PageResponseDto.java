package com.project.scheduledelevopproject.dto.Page;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto {

    @NotBlank
    @Size(max = 10)
    private String title;

    @NotBlank
    @Size(max = 100)
    private String contents;

    @NotBlank
    private Long replyCount;

    @NotBlank
    private LocalDate createdAt;

    @NotBlank
    private LocalDate updatedAt;

    @NotBlank
    private String userName;
}
