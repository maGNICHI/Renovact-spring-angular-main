package tn.esprit.foyeruniversiteeya.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;
    String nomUniversite;
    String adresse;
    String mail;
    String numero;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Foyer foyer;
    @ManyToOne
    Ville ville;

 /*   @OneToMany(mappedBy="universite")
    @JsonIgnore
    private Set<Department> departements;*/

   /* @OneToMany( fetch = FetchType.EAGER)
    private List<Images> images;*/
   @OneToOne( cascade = CascadeType.ALL)
   private Images logo;
}
