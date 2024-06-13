package tn.esprit.foyeruniversiteeya.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.DetailProjet;
//import tn.esprit.foyeruniversiteeya.entities.Projet;
import tn.esprit.foyeruniversiteeya.repositories.DetailProjetRepository;
import tn.esprit.foyeruniversiteeya.repositories.ProjetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailProjetServiceImpl implements IDetailProjetService  {

    private final DetailProjetRepository detailProjetRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public DetailProjetServiceImpl(DetailProjetRepository detailProjetRepository, ProjetRepository projetRepository) {
        this.detailProjetRepository = detailProjetRepository;
        this.projetRepository = projetRepository;
    }

    @Override
    public DetailProjet addDetailProjet(DetailProjet detailProjet) {
        return detailProjetRepository.save(detailProjet);
    }

    @Override
    public List<DetailProjet> getAllDetailProjet() {
        return detailProjetRepository.findAll();
    }

    @Override
    public DetailProjet getDetailProjet(Long idDetail) {
        return detailProjetRepository.findById(idDetail).orElse(null);
    }

    @Override
    public void deleteDetailProjet(Long idDetail) {
        detailProjetRepository.deleteById(idDetail);
    }

    @Override
    public DetailProjet updateDetailProjet(DetailProjet detailProjet) {
        return detailProjetRepository.save(detailProjet);
    }

    public List<DetailProjet> filterDetailProjets(String descriptionDetail, String technologie) {
        List<DetailProjet> allDetailProjets = detailProjetRepository.findAll();

        return allDetailProjets.stream()
                .filter(detailProjet ->
                        (StringUtils.isBlank(descriptionDetail) || StringUtils.containsIgnoreCase(detailProjet.getDescriptionDetail(), descriptionDetail))
                                && (StringUtils.isBlank(technologie) || StringUtils.containsIgnoreCase(detailProjet.getTechnologie(), technologie)))
                .collect(Collectors.toList());
    }

   /* public DetailProjet affecterProjet(DetailProjet detailProjet, long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        if (projet != null) {
            detailProjet.setProjet(projet);
            return detailProjetRepository.save(detailProjet);
        } else {
            // Gérer le cas où le projet avec l'ID spécifié n'est pas trouvé
            return null;
        }
    }*/
}
