package tn.esprit.foyeruniversiteeya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.foyeruniversiteeya.entities.Bloc;
import tn.esprit.foyeruniversiteeya.entities.Ville;

public interface VilleRepository extends JpaRepository<Ville,Long> {
    @Query("SELECT count (f)FROM Universite u   join u.foyer f  WHERE   u.ville.idVille = :idvil")
    int countFoyerByVille(@Param("idvil") Long idvil);
}



