package tn.esprit.foyeruniversiteeya.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.foyeruniversiteeya.entities.Department;
import tn.esprit.foyeruniversiteeya.entities.Filtre;
import tn.esprit.foyeruniversiteeya.entities.Universite;

import java.util.List;
import java.util.TreeSet;

public interface IUniversiteService {
    Universite addUniversiteVille(Universite universite, Long idVille, MultipartFile file) throws Exception;
    List<Universite> getAllUniversites();
    Page<Universite> getAllUniversites(Pageable pageable);
    Universite getUniversite(Long idUniversite);
    List<Universite>  getUniversiteByVille(long idVille);
    /* List<Departement> getDepartementByUniversite(long idUniversite);*/
    void deleteUniversite(Long idUniversite)throws Exception;
    public Universite updateUniversite(Universite universite,Long idVille,MultipartFile file) throws Exception;
    //, List<MultipartFile> files
    Universite findUniversiteByName(String nomUniversite);
    List<Department> getDepartementsByUniversite(Long idUniversite);
    Long nbrDepartementsByUniversite(Long idUniversite);
    TreeSet<Universite> getUniversitiesSorted();
    void assignUniversiteToDepartement(Long idUniversite, Long idDepartement);

    void deleteUniversiteParLot(List<Long>ids) throws Exception;
    List<Filtre> getDataByFiltre(long idVille, long idUniversite, long idDepartement);




}
