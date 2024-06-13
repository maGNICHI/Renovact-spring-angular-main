package tn.esprit.foyeruniversiteeya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.foyeruniversiteeya.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {
}
