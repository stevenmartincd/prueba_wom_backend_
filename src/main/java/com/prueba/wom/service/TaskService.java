package com.prueba.wom.service;

import com.prueba.wom.model.Task;
import com.prueba.wom.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que proporciona operaciones relacionadas con las tareas (Tasks).
 * Interactúa con la base de datos mediante el repositorio de tareas.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Constructor que inicializa el repositorio de tareas.
     */
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Crea una nueva tarea.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Actualiza una tarea existente.
     */
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Obtiene las tareas asociadas a un usuario específico.
     */
    public List<Task> getTasksByUserId(Integer idUser) {
        return taskRepository.findByIdUser(idUser);
    }
    /**
     * Cambia el estado de las tareas de forma masiva.
     */
    @Transactional
    public void updateTasksStatus(List<Integer> taskIds, Integer status) {
        taskRepository.updateStatusForTasks(status, taskIds);
    }
    /**
     * Elimina las tareas de forma masiva.
     */
    @Transactional
    public void deleteTasks(List<Integer> taskIds) {
        taskRepository.deleteByIdIn(taskIds);
    }
}
