package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.DetailProjet;


import java.util.List;

public interface IDetailProjetService {
    DetailProjet addDetailProjet(DetailProjet detailprojet);

    List<DetailProjet> getAllDetailProjet();

    DetailProjet getDetailProjet(Long idDetail);

    void deleteDetailProjet(Long idDetail);

    DetailProjet updateDetailProjet(DetailProjet detailprojet);

    List<DetailProjet> filterDetailProjets(String descriptionDetail, String technologie);
}