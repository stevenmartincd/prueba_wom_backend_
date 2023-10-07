package com.prueba.wom.service;

import com.prueba.wom.model.Task;
import com.prueba.wom.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Obtiene todas las tareas disponibles.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Obtiene una tarea por su ID.
     *
     * @param id ID de la tarea.
     * @return la tarea encontrada o un valor vacío.
     */
    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
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
     * Elimina una tarea por su ID.
     */
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    /**
     * Obtiene las tareas asociadas a un usuario específico.
     */
    public List<Task> getTasksByUserId(Integer idUser) {
        return taskRepository.findByIdUser(idUser);
    }
}
