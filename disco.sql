-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2021 a las 23:35:40
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `disco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albums`
--

CREATE TABLE `albums` (
  `id_album` int(11) NOT NULL,
  `album_name` varchar(255) NOT NULL,
  `album_artist` varchar(255) NOT NULL,
  `release_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `albums`
--

INSERT INTO `albums` (`id_album`, `album_name`, `album_artist`, `release_date`) VALUES
(1, 'Good & Evil', 'Tally Hall', '0201-06-21'),
(2, 'Hawaii: Part II', 'Joe Hawley', '2012-12-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `songs`
--

CREATE TABLE `songs` (
  `id_song` int(11) NOT NULL,
  `song_name` varchar(255) NOT NULL,
  `song_album` int(11) NOT NULL,
  `song_length` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `songs`
--

INSERT INTO `songs` (`id_song`, `song_name`, `song_album`, `song_length`) VALUES
(1, 'Never Meant to Know', 1, '3:40'),
(2, '&', 1, '3:14'),
(3, 'You & Me', 1, '2:52'),
(4, 'Cannibal', 1, '3:28'),
(5, 'Who You Are', 1, '3:40'),
(6, 'Sacred Beast', 1, '2:22'),
(7, 'Hymn for a Scarecrow', 1, '4:50'),
(8, 'A Lady', 1, '1:05'),
(9, 'The Trap', 1, '4:31'),
(10, 'Turn the Lights Off', 1, '2:56'),
(11, 'Missery Fell', 1, '3:34'),
(12, 'Out in the Twilight', 1, '2:51'),
(13, 'You', 1, '2:57'),
(14, 'Fate of the Stars', 1, '6:50'),
(15, 'Introduction to the Snow', 2, '1:41'),
(16, 'Isle Unto Thyself', 2, '3:46'),
(17, 'Black Rainbows', 2, '2:30'),
(18, 'White Ball', 2, '3:35'),
(19, 'Murders', 2, '3:43'),
(20, '宇宙ステーションのレベル7', 2, '3:23'),
(21, 'The Mind Electric', 2, '6:13'),
(22, 'Labyrinth', 2, '2:33'),
(23, 'Time Machine', 2, '4:12'),
(24, 'Stranded Lullaby', 2, '3:43'),
(25, 'Dream Sweet in Sea Major', 2, '7:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`id_album`);

--
-- Indices de la tabla `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id_song`),
  ADD KEY `song_album` (`song_album`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `albums`
--
ALTER TABLE `albums`
  MODIFY `id_album` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `songs`
--
ALTER TABLE `songs`
  MODIFY `id_song` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`song_album`) REFERENCES `albums` (`id_album`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
