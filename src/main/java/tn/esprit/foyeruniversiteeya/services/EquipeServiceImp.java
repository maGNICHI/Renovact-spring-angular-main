package tn.esprit.foyeruniversiteeya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.Department;
import tn.esprit.foyeruniversiteeya.entities.Equipe;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.repositories.EquipeRepository;

import java.util.List;

@Service
public class EquipeServiceImp  implements IEquipeService{
    EquipeRepository equipeRepository;

    @Autowired
    public EquipeServiceImp(EquipeRepository equipeRepository){
         this.equipeRepository=equipeRepository;
    }

    @Override
    public Equipe addEquipe(Equipe equipe) {
            return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipe(int idEquipe) {
        return equipeRepository.findById(idEquipe).orElse(null);
    }

    @Override
    public void deleteEquipe(int idEquipe) {
        equipeRepository.deleteById(idEquipe);

    }

    @Override
    public Equipe updateEquipe(Equipe equipe) {
        Equipe eq = equipeRepository.findById(equipe.getIdEquipe()).orElse(null);
        if (eq != null){
            return equipeRepository.save(equipe);
        }else {
            return equipe;
        }    }
}
