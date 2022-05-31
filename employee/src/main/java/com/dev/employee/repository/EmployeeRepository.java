package com.dev.employee.repository;

import com.dev.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmployeeId(Long employeeId);
}
    git init
    git add README.md
        git commit -m "first commit"
        git branch -M main
        git remote add origin https://github.com/sshaunn/springApp.git
        git push -u origin main