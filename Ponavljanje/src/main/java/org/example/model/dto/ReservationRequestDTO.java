package org.example.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;
//U glavnom projektu ovo nije DTO. Služi za slanje podataka između klijenta i servera prilikom rezervacije termina.

@Data //Anotacija @Data dolazi iz Lombok biblioteke i služi da automatski generiše: Getter i Setter metode; toString() metoda; equals() i hashCode() metode; Konstruktor bez argumenata
public class ReservationRequestDTO {
    @NotNull
    private String userEmail;
    @NotNull
    private int appointmentId;
}

