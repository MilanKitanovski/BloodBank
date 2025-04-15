package org.example.model;

import lombok.*;
import org.hibernate.Hibernate;
import com.sun.istack.NotNull;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@javax.persistence.Entity
public class Reservation extends Entity{
    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @OneToOne
    private Appointment appointment;

    //Metoda equals služi za ispravno poređenje objekata u Javi, posebno u kolekcijama (Set, List, Map).
    //Bez prilagođenog equals, dva Hibernate entiteta sa istim ID-om ne bi bila jednaka, što može dovesti do grešaka prilikom rada sa bazom.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;
        return getId() != 0 && Objects.equals(getId(), that.getId());
    }

    //Metoda hashCode vraća celobrojnu vrednost (hash kod) koja predstavlja objekat u memoriji.
    //Koristi se u hash-baziranim kolekcijama, poput HashMap, HashSet, i HashTable.
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
