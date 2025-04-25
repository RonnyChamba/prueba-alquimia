package com.cursos.ec.testalquimia.repository;

import com.cursos.ec.testalquimia.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByIdentification(String identification);
}
