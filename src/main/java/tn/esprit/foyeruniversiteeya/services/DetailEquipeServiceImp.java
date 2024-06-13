package tn.esprit.foyeruniversiteeya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.DetailEquipe;
import tn.esprit.foyeruniversiteeya.entities.Equipe;
import tn.esprit.foyeruniversiteeya.repositories.DetailEquipeRepository;

import java.util.List;
@Service
public class DetailEquipeServiceImp implements IDetailEquipeService{

    DetailEquipeRepository detailEquipeRepository;

    @Autowired
    public DetailEquipeServiceImp(DetailEquipeRepository detailEquipeRepository){
        this.detailEquipeRepository = detailEquipeRepository;
    }

    @Override
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe) {
        return detailEquipeRepository.save(detailEquipe);
    }

    @Override
    public List<DetailEquipe> getAllDetailEquipes() {
        return detailEquipeRepository.findAll();
    }

    @Override
    public DetailEquipe getDetailEquipe(int idDetailEquipe) {
        return detailEquipeRepository.findById(idDetailEquipe).orElse(null);
    }

    @Override
    public void deleteDetailEquipe(int idDetailEquipe) {
        detailEquipeRepository.deleteById(idDetailEquipe);
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe) {
        DetailEquipe eq = detailEquipeRepository.findById(detailEquipe.getIdDetailEquipe()).orElse(null);
        if (eq != null){
            return detailEquipeRepository.save(detailEquipe);
        }else {
            return detailEquipe;
        }      }
}
