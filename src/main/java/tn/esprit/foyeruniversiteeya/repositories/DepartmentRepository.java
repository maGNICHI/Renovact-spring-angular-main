package tn.esprit.foyeruniversiteeya.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.foyeruniversiteeya.entities.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

  List<Department> findDepartmentsByNomDepartment(String nom);

    @Query("SELECT d FROM Department d order by d.nombreEtage")
    List<Department> orderDepartmentsetage();

    @Query("SELECT d FROM Department d order by d.nombreClasses")
    List<Department> orderClasses();

  @Query("SELECT d.nomDepartment as departmentName, COUNT(c.idClasse) as totalClasses, " +
          "SUM(c.capacity) as totalCapacity, " +
          "COUNT(CASE WHEN c.disponibility = true THEN 1 END) as totalAvailableClasses, " +
          "(COUNT(CASE WHEN c.disponibility = true THEN 1 END) / CAST(COUNT(c.idClasse) AS DOUBLE)) as avgAvailability " +
          "from Department d JOIN Classe c ON d.idDepartment = c.department.idDepartment " +
          "GROUP BY d.idDepartment, d.nomDepartment")
  List<Object> getDepartmentSummaries();


}
