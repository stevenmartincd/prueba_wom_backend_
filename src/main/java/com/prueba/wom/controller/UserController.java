package com.prueba.wom.controller;

import com.prueba.wom.dto.request.LoginRequest;
import com.prueba.wom.dto.response.LoginResponse;
import com.prueba.wom.model.User;
import com.prueba.wom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Controlador REST para la gestión y autenticación de usuarios.
 *
 * Proporciona endpoints para iniciar sesión y obtener la información del usuario
 * La documentación de la API se realiza mediante Swagger.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    @ApiOperation(value = "Autenticación de usuario, devuelve el token")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "username/password provided are not valid")})
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return this.userService.signin(loginRequest.getUsername(), loginRequest.getPassword());
    }
    @GetMapping("/current")
    @ApiOperation(value = "Datos base de usario logueado")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not exist"),
            @ApiResponse(code = 500, message = "The expired or invalid JWT Token")})
    public ResponseEntity<User> getCurrentUser() {
        User currentUser = userService.getCurrentlyLoggedInUser();
        return ResponseEntity.ok(currentUser);
    }
}
