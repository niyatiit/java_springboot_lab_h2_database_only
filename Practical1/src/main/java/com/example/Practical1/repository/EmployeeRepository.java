package com.example.Practical1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Practical1.entity.*;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee  , Integer>{

}
