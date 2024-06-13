package tn.esprit.foyeruniversiteeya.services;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.foyeruniversiteeya.entities.Classe;

import java.util.List;

public interface IClasseService {

    // CRUD
    Classe addClasse(Classe classe);

    Classe getClasse(long idClasse);

    List<Classe> getAllClasse();

    void deleteClasse(long idClasse);

    Classe updateClasse(Classe classe);


    // Avancés

    public List<Object[]> getAllClassesWithDepartments();

    List<Classe> getClassesByNom(String nom);

    Long statNumberofClasses();

    List<Classe> getClasseOrderByCapacity();

    Long statNumberofClassesDispo(boolean etat);

    List<Classe> getClassesByAvailability(boolean etat);

    Classe addDepartmentClasse( Classe classe , Long idDepartment);

    public double getPercentageAvailableClassrooms();

    public double getPercentageUnavailableClassrooms() ;









}
