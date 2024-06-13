package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.Equipe;

import java.util.List;

public interface IEquipeService {

    Equipe addEquipe(Equipe equipe);
    List<Equipe> getAllEquipes();
    Equipe getEquipe(int idEquipe);
    void deleteEquipe(int idEquipe);
    Equipe updateEquipe(Equipe equipe);

}
