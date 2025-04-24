package com.cursos.ec.testalquimia.repository;

import com.cursos.ec.testalquimia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
