package tn.esprit.foyeruniversiteeya.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    int idEquipe;
    String nomEquipe;
    @Enumerated(EnumType.STRING)
    Niveau niveau;
    @OneToMany( mappedBy="equipe")
    private Set<DetailEquipe> DetailEquipes;
   // @ManyToOne
   // Projet projet;

}
