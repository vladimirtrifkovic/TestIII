-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2021 at 10:40 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `putovanje`
--

-- --------------------------------------------------------

--
-- Table structure for table `destinacija`
--

CREATE TABLE `destinacija` (
  `id_destinacije` int(11) NOT NULL,
  `naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `destinacija`
--

INSERT INTO `destinacija` (`id_destinacije`, `naziv`) VALUES
(1, 'Budimpesta'),
(2, 'Solun'),
(3, 'Portugal'),
(4, 'Francuska'),
(5, 'Spanija'),
(6, 'Maroko');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `id_korisnika` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `korisnicko_ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id_korisnika`, `ime`, `prezime`, `korisnicko_ime`, `lozinka`) VALUES
(1, 'Pera', 'Peric', 'pero', 'pero123'),
(2, 'Marko', 'Markovic', 'marko', 'marko123'),
(3, 'Steva', 'Stevic', 'steva', 'steva123');

-- --------------------------------------------------------

--
-- Table structure for table `prevoz`
--

CREATE TABLE `prevoz` (
  `id_prevoza` int(11) NOT NULL,
  `naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `prevoz`
--

INSERT INTO `prevoz` (`id_prevoza`, `naziv`) VALUES
(1, 'Autobus'),
(2, 'Avion');

-- --------------------------------------------------------

--
-- Table structure for table `putovanje`
--

CREATE TABLE `putovanje` (
  `pid` int(11) NOT NULL,
  `putnik_id` int(11) NOT NULL,
  `destinacija_id` int(11) NOT NULL,
  `vreme_polaska` date NOT NULL,
  `datum_povratka` date NOT NULL,
  `duzina_puta` int(11) NOT NULL,
  `cena_puta` double NOT NULL,
  `id_prevoz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `putovanje`
--

INSERT INTO `putovanje` (`pid`, `putnik_id`, `destinacija_id`, `vreme_polaska`, `datum_povratka`, `duzina_puta`, `cena_puta`, `id_prevoz`) VALUES
(7, 1, 5, '2021-06-02', '2021-06-23', 2300, 854, 1),
(12, 1, 1, '2021-06-01', '2021-06-24', 555, 555, 1),
(17, 1, 5, '2021-06-01', '2021-06-29', 3333, 999, 2),
(18, 1, 2, '2021-06-01', '2021-06-23', 2333, 667, 2),
(20, 1, 6, '2021-06-08', '2021-06-30', 34534, 424, 1),
(25, 1, 2, '2021-06-01', '2021-06-22', 3333, 333, 1),
(27, 1, 2, '2021-06-01', '2021-06-23', 3333, 25, 2),
(35, 1, 1, '2021-06-01', '2021-06-30', 3451, 141, 1),
(44, 3, 3, '2021-09-05', '2021-06-23', 4444, 44, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `destinacija`
--
ALTER TABLE `destinacija`
  ADD PRIMARY KEY (`id_destinacije`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`id_korisnika`);

--
-- Indexes for table `prevoz`
--
ALTER TABLE `prevoz`
  ADD PRIMARY KEY (`id_prevoza`);

--
-- Indexes for table `putovanje`
--
ALTER TABLE `putovanje`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `putnik_id` (`putnik_id`,`destinacija_id`),
  ADD KEY `id_prevoz` (`id_prevoz`),
  ADD KEY `destinacija_id` (`destinacija_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `destinacija`
--
ALTER TABLE `destinacija`
  MODIFY `id_destinacije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `id_korisnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `prevoz`
--
ALTER TABLE `prevoz`
  MODIFY `id_prevoza` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `putovanje`
--
ALTER TABLE `putovanje`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `putovanje`
--
ALTER TABLE `putovanje`
  ADD CONSTRAINT `putovanje_ibfk_1` FOREIGN KEY (`putnik_id`) REFERENCES `korisnik` (`id_korisnika`),
  ADD CONSTRAINT `putovanje_ibfk_2` FOREIGN KEY (`id_prevoz`) REFERENCES `prevoz` (`id_prevoza`),
  ADD CONSTRAINT `putovanje_ibfk_3` FOREIGN KEY (`destinacija_id`) REFERENCES `destinacija` (`id_destinacije`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
