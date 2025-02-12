package org.example.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//ovo je superklasa za sve ostale entitete - Koristi se kada želiš da deljene atribute definišeš u jednoj klasi, a da ih druge Entity klase naslede.
@MappedSuperclass
public class Entity {
    @Id  //oznaka za priamrni kljuc (id)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //automatski generise vrednost id u bp
    )
    private int id; //ovim smo definisali primarni kljuc u bp
    private boolean deleted;

    public Entity(){

    }

    public Entity(int id, boolean deleted){
        this.id = id;
        this.deleted = deleted;
    }

    public int getId() { return id; }

    public void setId(int id){ this.id = id;}

    public boolean getDeleted() { return  deleted;}

    public void setDeleted() { this.deleted = deleted;}
}
