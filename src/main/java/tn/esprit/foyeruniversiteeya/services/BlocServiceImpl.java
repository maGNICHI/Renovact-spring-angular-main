package tn.esprit.foyeruniversiteeya.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.Bloc;
import tn.esprit.foyeruniversiteeya.entities.Foyer;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.repositories.BlocRepository;
import tn.esprit.foyeruniversiteeya.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{
    //@NotNull
    BlocRepository blocRepository;
    FoyerRepository foyerRepository;

    @Override
    public Bloc addBloc(Bloc bloc, long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        bloc.setFoyer(foyer);
        return blocRepository.save(bloc);
    }
    @Override
    public List<Bloc> getAllBlocs() {
        return (List<Bloc>) blocRepository.findAll();
    }

    @Override
    public Bloc getBloc(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void deleteBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);

    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }
    @Override
    public Bloc affectationFoyerBloc(Long idFoyer, Long idBloc) {

        Bloc bloc=blocRepository.findById(idBloc).orElse(null);
        Foyer foyer=foyerRepository.findById(idFoyer).orElse(null);
        bloc.setFoyer(foyer);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affectationFoyerBlocc(Long idFoyer, String nomBloc) {
        Foyer foyer=foyerRepository.findById(idFoyer).orElse(null);
        Bloc bloc=blocRepository.findByNomBloc(nomBloc);
        bloc.setFoyer(foyer);
        return blocRepository.save(bloc);

    }

    @Override
    public Bloc desaffecterFoyerABloc(long idBloc) {
        Bloc bloc=blocRepository.findById(idBloc).orElse(null);
        bloc.setFoyer(null);
        return blocRepository.save(bloc);
    }
}
