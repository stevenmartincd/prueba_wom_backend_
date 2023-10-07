package com.prueba.wom.repository;

import com.prueba.wom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para operaciones CRUD en la entidad User.
 * Este repositorio proporciona métodos para las operaciones más comunes de CRUD
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Busca un usuario por su nombre de usuario.
     */
    User findByUsername(String username);
}
