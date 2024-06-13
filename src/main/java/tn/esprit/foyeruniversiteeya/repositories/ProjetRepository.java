package tn.esprit.foyeruniversiteeya.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.foyeruniversiteeya.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
