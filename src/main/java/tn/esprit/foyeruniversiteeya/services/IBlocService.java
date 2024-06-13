package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.Bloc;

import java.util.List;

public interface IBlocService {
    Bloc addBloc(Bloc bloc,long idFoyer);
    List<Bloc>getAllBlocs();
    Bloc getBloc(Long idBloc);
    void deleteBloc(Long idBloc);
    Bloc updateBloc(Bloc bloc);
    public Bloc affectationFoyerBloc(Long idFoyer, Long idBloc);
    public Bloc affectationFoyerBlocc(Long idFoyer, String nomBloc);
    public Bloc desaffecterFoyerABloc(long idBloc);
}
