package de.db.webapp.services.model;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Schwein {

    @Setter(AccessLevel.PRIVATE)
    private String id;
    @Setter(AccessLevel.PRIVATE)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    private int gewicht;

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }

    public void taufen(String name) {
        setName(name);
    }
}
