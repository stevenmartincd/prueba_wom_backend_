-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-10-2023 a las 02:28:41
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_wom`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(10) NOT NULL COMMENT 'ID ROLE',
  `name` varchar(50) NOT NULL COMMENT 'NAME ROLE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

CREATE TABLE `task` (
  `id` int(10) NOT NULL COMMENT 'ID TASK',
  `id_user` int(10) NOT NULL COMMENT 'USER ID RELATED TO THE TASK ',
  `name` varchar(100) NOT NULL COMMENT 'NAME TASK',
  `status` tinyint(1) NOT NULL COMMENT 'STATUS TASK'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `task`
--

INSERT INTO `task` (`id`, `id_user`, `name`, `status`) VALUES
(1, 1, 'Tarea de prueba', 0),
(2, 2, 'Esta es una tareaaaaa', 0),
(3, 1, 'Soy una tareaa 3', 0),
(4, 2, 'PRUEBA UPDATE', 0),
(7, 2, 'PRUEBA tarea', 1),
(8, 2, 'PRUEBAS', 0),
(9, 2, 'Tarea de prueba :)', 0),
(11, 2, 'Otra tarea', 1),
(12, 2, 'Otra tarea', 0),
(15, 2, 'Tarea de prueba', 0),
(16, 2, 'PRUEBAS', 1),
(17, 1, 'Prueba :)', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL COMMENT 'USER ID',
  `name` varchar(50) NOT NULL COMMENT 'NAME USER',
  `lastname` varchar(50) NOT NULL COMMENT 'USER NAME',
  `username` varchar(20) NOT NULL COMMENT 'USER LASTNAME',
  `password` varchar(255) NOT NULL COMMENT 'PASSWORD USER',
  `role` int(10) NOT NULL COMMENT 'ROLE USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Table that saves user information';

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `lastname`, `username`, `password`, `role`) VALUES
(1, 'Steven', 'Martin', 'steven.cuevas', '$2a$12$u5KDJ2brySfDZuMHvGLgCeBtYbHy0fmGU4OuUZBfv.6/Xmy0S90U6', 2),
(2, 'Steven', 'Cuevas', 'steven', '$2a$12$u5KDJ2brySfDZuMHvGLgCeBtYbHy0fmGU4OuUZBfv.6/Xmy0S90U6', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indices de la tabla `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_user` (`id_user`) USING BTREE;

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_role` (`role`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID ROLE', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `task`
--
ALTER TABLE `task`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID TASK', AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'USER ID', AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
