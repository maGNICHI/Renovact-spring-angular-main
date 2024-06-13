package tn.esprit.foyeruniversiteeya.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.Bloc;
import tn.esprit.foyeruniversiteeya.entities.Chambre;
import tn.esprit.foyeruniversiteeya.entities.TypeChambre;
import tn.esprit.foyeruniversiteeya.repositories.BlocRepository;
import tn.esprit.foyeruniversiteeya.repositories.ChambreRepository;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService{
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
    @Override
    public Chambre affecterChambreABloc(Chambre chambre, Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        chambre.setBloc(bloc);
        return chambreRepository.save(chambre);
    }
    @Override
    public Chambre getChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }


    @Override
    public List<Chambre> getAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public void deleteChambre(long idChambre) {
        chambreRepository.deleteById(idChambre);
    }


    @Override
    public Chambre updateChambre(Chambre chambre) {
        Chambre ch = chambreRepository.findById(chambre.getIdChambre()).orElse(null) ;
        if (ch != null)
            chambreRepository.save(chambre);
        return  ch;


    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {

        return null;
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC) {
        return chambreRepository.getChambresParBlocEtType(idBloc, typeC);
    }




}
