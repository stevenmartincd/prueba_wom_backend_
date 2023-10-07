package com.prueba.wom.repository;

import com.prueba.wom.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para operaciones CRUD en la entidad Task.
 * Este repositorio proporciona métodos para las operaciones más comunes de CRUD
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    /**
     * Busca todas las tareas asociadas a un determinado usuario por ID.
     */
    List<Task> findByIdUser(Integer idUser);

}
