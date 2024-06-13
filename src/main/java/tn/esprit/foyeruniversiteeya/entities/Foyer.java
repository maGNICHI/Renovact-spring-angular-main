package tn.esprit.foyeruniversiteeya.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;
    String nomFoyer;
    long capaciteFoyer;
    @OneToOne(mappedBy = "foyer")
    @JsonBackReference
    @JsonIgnore
    private Universite universite;

    @OneToMany(mappedBy="foyer",cascade = CascadeType.ALL)
    private Set<Bloc> blocs;
    //@OneToOne( cascade = CascadeType.ALL)
   // private Images image;
}
