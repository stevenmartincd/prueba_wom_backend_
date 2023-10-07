package com.prueba.wom.controller;

import com.prueba.wom.model.Task;
import com.prueba.wom.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar tareas.
 *
 * Proporciona endpoints para crear, actualizar, eliminar y consultar tareas.
 * La documentación de la API se realiza mediante Swagger.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@RestController
@RequestMapping("/api/tasks")
@Api(tags = "Task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @ApiOperation(value = "Lista de todos las tareas")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Tareas por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public Optional<Task> getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/list/{idUser}")
    @ApiOperation(value = "Tareas por ID usario")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer idUser) {
        List<Task> tasks = taskService.getTasksByUserId(idUser);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    @ApiOperation(value = "Crear tarea")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public Task updateTask(@PathVariable Integer id, @RequestBody Task task) {
        task.setId(id);
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
