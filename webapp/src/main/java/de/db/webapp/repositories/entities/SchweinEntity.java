package de.db.webapp.repositories.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString

@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name="tbl_schweine")
@NamedQuery(name = "SchweinEntity.findAllAsList", query = "select s from SchweinEntity s")
public class SchweinEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(nullable = false)
    private int gewicht;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SchweinEntity that = (SchweinEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}