package tn.esprit.foyeruniversiteeya.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.foyeruniversiteeya.entities.Classe;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {

    @Query("SELECT c, d FROM Classe c JOIN FETCH c.department d")
    List<Object[]> findAllWithDepartments();

    @Query("SELECT c FROM Classe c where c.classeName=:name")
    List<Classe> getbyname(String name);

    long count();

    @Query("SELECT c FROM Classe c ORDER BY c.capacity")
    List<Classe> orderCap();

    long countByDisponibility(boolean disponibility);

    List<Classe> findByDisponibility(boolean disponibility);

    @Query("SELECT c, d, u FROM Classe c JOIN c.department d JOIN d.universite u")
    List<Classe> retrieveDepartment();


    @Query("SELECT c FROM Classe c WHERE c.disponibility = true")
    List<Classe> findAvailableClassrooms();

}
