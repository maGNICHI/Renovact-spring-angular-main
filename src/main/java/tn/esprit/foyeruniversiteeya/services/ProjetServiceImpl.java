package tn.esprit.foyeruniversiteeya.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.DetailProjet;
import tn.esprit.foyeruniversiteeya.entities.Foyer;
import tn.esprit.foyeruniversiteeya.entities.Projet;
import tn.esprit.foyeruniversiteeya.entities.Type;
import tn.esprit.foyeruniversiteeya.repositories.DetailProjetRepository;
import tn.esprit.foyeruniversiteeya.repositories.ProjetRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjetServiceImpl implements IProjetService  {
    ProjetRepository projetRepository;
    DetailProjetRepository detailProjetRepository;





    @Override
    public Projet addProjetDetailProjet(Projet  projet, long idDetail) {
        DetailProjet detailProjet=detailProjetRepository.findById(idDetail).orElse(null);
        projet.setDetailProjet(detailProjet);
        return projetRepository.save(projet);
    }


    @Override
    public List<Projet> getAllProjet() {
        return (List<Projet>) projetRepository.findAll();
    }

    @Override
    public Projet getProjet(Long idProjet) {
        return projetRepository.findById(idProjet).orElse(null);
    }

    @Override
    public void deleteProjet(Long idProjet) {
        projetRepository.deleteById(idProjet);
    }

    @Override
    public Projet updateProjet(Projet projet) {

        return projetRepository.save(projet);
    }




}
