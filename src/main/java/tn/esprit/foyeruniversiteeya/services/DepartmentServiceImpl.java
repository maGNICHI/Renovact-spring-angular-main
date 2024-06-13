package tn.esprit.foyeruniversiteeya.services;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.Classe;
import tn.esprit.foyeruniversiteeya.entities.Department;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.repositories.ClasseRepository;
import tn.esprit.foyeruniversiteeya.repositories.DepartmentRepository;
import tn.esprit.foyeruniversiteeya.repositories.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService{

    DepartmentRepository departmentRepository;
    UniversiteRepository universiteRepository;

    ClasseRepository classeRepository;
    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartment(long idDepartment) {
        return departmentRepository.findById(idDepartment).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartment(long idDepartment) {
             departmentRepository.deleteById(idDepartment);
    }

    @Override
    public Department updateDepartment(Department department) {
        Department dep = departmentRepository.findById(department.getIdDepartment()).orElse(null);
        Universite universite = universiteRepository.findById(dep.getUniversite().getIdUniversite()).orElse(null);
        if (dep != null){
            department.setUniversite(universite);
            return departmentRepository.save(department);
        }else {
            return department;
        }

    }

    @Override
    public Department addDepartmentWithUni(Department department, long id) {
      Universite universite = universiteRepository.findById(id).orElse(null);

      department.setUniversite(universite);

      return departmentRepository.save(department);
    }

    @Override
    public Department affecterClasseADepartment(long idDepartement, long idClasse) {
        Department department = departmentRepository.findById(idDepartement).orElse(null);
        Classe classe = classeRepository.findById(idClasse).orElse(null);
        classe.setDepartment(department);
        if(classeRepository.save(classe) == classe){
            Integer classeIncrement =department.getNombreClasses();
            department.setNombreClasses(classeIncrement+1);
            departmentRepository.save(department);
        }


        return department;
    }

    @Override
    public List<Department> getDepartmentsByNom(String nom) {
        return departmentRepository.findDepartmentsByNomDepartment(nom);
    }

    @Override
    public Long statNumberofdepartments() {
        return departmentRepository.count();
    }

    @Override
    public List<Department> getDepartmentsOrderByEtage() {
        return departmentRepository.orderDepartmentsetage();
    }

    @Override
    public List<Department> getDepartmentsOrderByClasses() {
        return departmentRepository.orderClasses();
    }

    @Override
    public List<Object> getDepartmentSummaries() {
        return departmentRepository.getDepartmentSummaries();
    }


}
