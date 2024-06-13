package tn.esprit.foyeruniversiteeya.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idVille;
    String nom;
    String addresse;
   /* @OneToMany(mappedBy = "ville")
    private Set<Universite> universites;*/
}
