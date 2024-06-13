package tn.esprit.foyeruniversiteeya.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idProjet;
    String nomProjet;
    String dureProjet;
    @Enumerated(EnumType.STRING)
    Type typeProjet;

    Date dateDebutP;


    @ManyToOne
    DetailProjet DetailProjet;
   // @ManyToOne
    //Equipe equipe;
}
