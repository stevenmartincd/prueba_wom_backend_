package com.prueba.wom.controller;

import com.prueba.wom.dto.request.UpdateTaskStatusRequest;
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

    @GetMapping("/listUser/{idUser}")
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

    @PostMapping("/create")
    @ApiOperation(value = "Crear tarea")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("update/{id}")
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

    @PostMapping("/update-status")
    @ApiOperation(value = "Cambiar el estado de forma masiva")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public ResponseEntity<?> updateTaskStatus(@RequestBody UpdateTaskStatusRequest request) {
        taskService.updateTasksStatus(request.getTaskIds(), request.getStatus());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-massive")
    @ApiOperation(value = "Eliminar tarea de forma masiva")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public ResponseEntity<Void> deleteTasksMassive(@RequestBody List<Integer> taskIds) {
        taskService.deleteTasks(taskIds);
        return ResponseEntity.ok().build();
    }
}
