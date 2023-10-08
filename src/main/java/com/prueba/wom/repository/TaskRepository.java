package com.prueba.wom.repository;

import com.prueba.wom.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    /**
     * Busca todas los por ID de las tareas para cambiar el estado.
     */
    @Modifying
    @Query("UPDATE Task t SET t.status = :status WHERE t.id IN :ids")
    void updateStatusForTasks(@Param("status") Integer status, @Param("ids") List<Integer> taskIds);
    /**
     * Busca todas los por ID de las tareas para eliminar la tarea.
     */
    void deleteByIdIn(List<Integer> ids);
}
