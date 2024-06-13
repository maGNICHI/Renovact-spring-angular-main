package tn.esprit.foyeruniversiteeya.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.foyeruniversiteeya.entities.Department;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.entities.Ville;

import java.util.List;


@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {

    @Query("select u from Universite u where u.nomUniversite=:nomU") //JPQL  JPQL  JPQL  JPQL  JPQL JPQL  !+ PAS SQL
    Universite retrivebyNom(@Param("nomU") String nomUniversity);

    @Query(value ="select * from Universite u where u.nomUniversite=:nomU", nativeQuery = true) //JPQL  JPQL  JPQL  JPQL  JPQL JPQL  !+ PAS SQL
    Universite retrivebyNomSQL(@Param("nomU") String nomUniversity);

     Universite findByNomUniversite(String nom);

     Universite findByAdresse(String adresse);
    Universite findUniversiteByNomUniversite(String nomUniversite);

    @Query("SELECT u FROM Universite u WHERE u.idUniversite IN :ids")
    List<Universite> findByIds(@Param("ids") List<Long> ids);
    List<Universite> findByVille(Ville ville);
    @Query("SELECT d FROM Department d  JOIN d.universite u WHERE  u.idUniversite = :univId")
    List<Department> findDepartementByUniversite(@Param("univId") Long idUniversite);
    @Query("SELECT d FROM Department d  JOIN d.universite u WHERE  u.idUniversite in :univIds")
    List<Department> findDepartementByIdUniversite(@Param("univIds") List<Long> univIds);
    @Query("SELECT d FROM Department d  JOIN d.universite u WHERE    u.ville.idVille = :idvil")
    List<Department> findDepartementByUniversiteByVille(@Param("idvil") Long idVille);
}
