package ru.project.library_web.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    private int id;
    private String lastName;
    private String name;
    private String midName;
    @Override
    public String toString() {
        return lastName+" "+name+" "+midName;
    }
}
