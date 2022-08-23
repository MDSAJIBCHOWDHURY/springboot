package com.tahmid.springboot.repository;


import com.tahmid.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from employees ", nativeQuery = true)
    List<Employee> findAllEmployee();

    @Query(value = "select * from employees e where e.id = :id", nativeQuery = true)
    Employee findEmployee(@Param("id") Long id);


    @Query(value = "update employees e set e.first_name=:firstName,e.last_name=:lastName," +
            "e.email_id=:emailId where e.id= :id",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void updateEmployee(@Param("firstName") String firstName,@Param("lastName")String lastName,
                       @Param("emailId") String emailId,@Param("id") Long id);

    @Query(value = "delete from employees where id =:id",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteEmployee(@Param("id") Long id);
}
