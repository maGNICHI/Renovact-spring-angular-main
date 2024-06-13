package tn.esprit.foyeruniversiteeya.services;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.Projet;
import tn.esprit.foyeruniversiteeya.entities.Type;


import java.util.Date;
import java.util.List;

public interface IProjetService  {
    Projet addProjetDetailProjet(Projet projet, long idDetail);
    List<Projet> getAllProjet();
    Projet getProjet(Long idProjet);
    void deleteProjet(Long idProjet);
    Projet updateProjet(Projet projet);

}
