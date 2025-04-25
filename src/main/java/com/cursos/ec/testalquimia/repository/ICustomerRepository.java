package com.cursos.ec.testalquimia.repository;

import com.cursos.ec.testalquimia.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByIdentification(String identification);

    boolean existsByIdentificationAndIdNot(String identification, Long id);

    @Query("SELECT c FROM Customer c " +
            "WHERE c.user.username = :usernameUser " +
            "AND (:paramFilter = '' OR ( c.identification LIKE %:paramFilter% OR upper(c.fullName) LIKE %:paramFilter%))")
    List<Customer> findAllCustomerByUser(String usernameUser, String paramFilter);
}
