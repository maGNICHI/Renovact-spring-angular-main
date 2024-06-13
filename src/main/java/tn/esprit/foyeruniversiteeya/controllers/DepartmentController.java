package tn.esprit.foyeruniversiteeya.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Department;
import tn.esprit.foyeruniversiteeya.services.IDepartmentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Department")
public class DepartmentController {

    IDepartmentService departmentService;


    @PostMapping("/addDepartment")
    Department addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @GetMapping("/Department/{id}")
    Department retriveDepartment(@PathVariable long id){
        return departmentService.getDepartment(id);
    }

    @GetMapping("/department")
    List<Department> retriveDepartments(){
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/deleteDepartment/{id}")
    void deleteDepartment(@PathVariable long id){
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/updateDepartment")
    Department updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    @PostMapping("/addDepartmentWithUni/{id}")
    Department addDepartmentWithUni(@RequestBody Department department,@PathVariable long id){


        return departmentService.addDepartmentWithUni(department,id);
    }

    // Avancés
    @PutMapping("/affecterClasseADepartment/{idDepartement}/{idClasse}")
    public Department affecterClasseADepartment(@PathVariable long idDepartement, @PathVariable long idClasse) {
        return departmentService.affecterClasseADepartment(idDepartement,idClasse);
    }

    @GetMapping("/department/{nom}")
    List<Department> getDepartmentsByNom(@PathVariable String nom){
        return departmentService.getDepartmentsByNom(nom);
    }

    @GetMapping("/countdepartment")
    Long statNumberofdepartments(){
        return departmentService.statNumberofdepartments();
    }

    @GetMapping("/DepartmentOrderEtage")
    List<Department> getDepartmentsOrderByEtage(){
        return departmentService.getDepartmentsOrderByEtage();
    }

    @GetMapping("/DepartmentOrderClasses")
    List<Department> getDepartmentsOrderByClasses(){
        return departmentService.getDepartmentsOrderByClasses();
    }

    @GetMapping("/DepartmentsStatistiques")
    List<Object> DepartmentsStatistiques(){
        return departmentService.getDepartmentSummaries();
    }

}
