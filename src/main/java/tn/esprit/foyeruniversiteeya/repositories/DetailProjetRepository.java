package tn.esprit.foyeruniversiteeya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.foyeruniversiteeya.entities.Chambre;
import tn.esprit.foyeruniversiteeya.entities.DetailProjet;

public interface DetailProjetRepository extends JpaRepository<DetailProjet,Long> {
}
