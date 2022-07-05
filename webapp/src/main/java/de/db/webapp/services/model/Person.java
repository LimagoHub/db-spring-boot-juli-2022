package de.db.webapp.services.model;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Person {

    @Setter(AccessLevel.PRIVATE)
    private String id;

    private String vorname;

    private String nachname;

}