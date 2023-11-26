package ru.project.library_web.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopyBook {
    private int id;
    private Book book;
    private Status status;
}
