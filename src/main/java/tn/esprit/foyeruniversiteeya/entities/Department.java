package tn.esprit.foyeruniversiteeya.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idDepartment;

    String nomDepartment;

    int nombreClasses;

    int nombreEtage;

    @OneToMany(mappedBy="department")
    @JsonIgnore
    private Set<Classe> classeSet;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Universite universite;


}
