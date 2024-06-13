package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.Chambre;
import tn.esprit.foyeruniversiteeya.entities.TypeChambre;

import java.util.List;

public interface IChambreService {
    Chambre addChambre(Chambre chambre);
    Chambre getChambre(Long idChambre);

    public List<Chambre> getAllChambre();
    void deleteChambre(long idChambre);

    Chambre updateChambre(Chambre chambre);
    List<Chambre> getChambresParNomUniversite (String nomUniversite) ;
    List<Chambre> getChambresParBlocEtType (Long idBloc, TypeChambre typeC) ;
    public Chambre affecterChambreABloc(Chambre chambre,Long idBloc);
}
