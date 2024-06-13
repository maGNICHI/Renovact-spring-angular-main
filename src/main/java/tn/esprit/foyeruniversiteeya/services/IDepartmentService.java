package tn.esprit.foyeruniversiteeya.services;


import tn.esprit.foyeruniversiteeya.entities.Department;

import java.util.List;

public interface IDepartmentService {

    // CRUD
    Department addDepartment(Department department);

    Department getDepartment(long idDepartment);

    List<Department> getAllDepartments();

    void deleteDepartment(long idDepartment);

    Department updateDepartment(Department department);


    Department addDepartmentWithUni(Department department, long id);

    // Avancéé

     Department affecterClasseADepartment(long idDepartement , long idClasse);

     List<Department> getDepartmentsByNom(String nom);

    Long statNumberofdepartments();

    List<Department> getDepartmentsOrderByEtage();

    List<Department> getDepartmentsOrderByClasses();

    List<Object> getDepartmentSummaries();





}
