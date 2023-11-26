package ru.project.library_web.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private int id;
    private CopyBook copyBook;
    private Subscriber subscriber;
}
