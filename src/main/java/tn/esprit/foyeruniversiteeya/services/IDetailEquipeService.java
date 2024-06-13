package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {

    DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);
    List<DetailEquipe> getAllDetailEquipes();
    DetailEquipe getDetailEquipe(int idDetailEquipe);
    void deleteDetailEquipe(int idDetailEquipe);
    DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe);

}
