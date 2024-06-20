-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 13 mai 2024 à 13:20
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java_data`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `numAchat` varchar(50) NOT NULL,
  `numProd` varchar(50) NOT NULL,
  `nomClient` varchar(100) NOT NULL,
  `nbrLitre` int(50) NOT NULL,
  `dateAchat` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`numAchat`, `numProd`, `nomClient`, `nbrLitre`, `dateAchat`) VALUES
('489', '12', 'koto', 80, '2024-05-13'),
('78', '12', 'biry', 1, '2024-05-13');

-- --------------------------------------------------------

--
-- Structure de la table `entree`
--

DROP TABLE IF EXISTS `entree`;
CREATE TABLE IF NOT EXISTS `entree` (
  `numEntree` varchar(50) NOT NULL,
  `numProd` varchar(50) NOT NULL,
  `stockEntree` int(50) NOT NULL,
  `dateEntree` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entree`
--

INSERT INTO `entree` (`numEntree`, `numProd`, `stockEntree`, `dateEntree`) VALUES
('4', '11', 40, '2024-05-13'),
('48', '12', 89, '2024-05-13');

-- --------------------------------------------------------

--
-- Structure de la table `entretien`
--

DROP TABLE IF EXISTS `entretien`;
CREATE TABLE IF NOT EXISTS `entretien` (
  `numEntr` varchar(50) NOT NULL,
  `numServ` varchar(50) NOT NULL,
  `Immatriculation_voiture` varchar(50) NOT NULL,
  `nomClient` varchar(100) NOT NULL,
  `dateEntretien` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entretien`
--

INSERT INTO `entretien` (`numEntr`, `numServ`, `Immatriculation_voiture`, `nomClient`, `dateEntretien`) VALUES
('45', '2', '1145FD', 'lolo', '2024-05-13'),
('88888', '2', '5555', 'llkk', '2024-04-17'),
('16', '3', '4455hj', 'lol', '2024-05-13');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `numProd` varchar(50) NOT NULL,
  `design` varchar(50) NOT NULL,
  `stock` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`numProd`, `design`, `stock`) VALUES
('4', 'gasoil', 0),
('12', 'Essence', 8);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `numServ` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `prix` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`numServ`, `service`, `prix`) VALUES
('1', 'Lavage', 50000),
('3', 'Peint', 5000),
('2', 'Gonflage', 30),
('7', 'loko', 5000);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`name`, `password`) VALUES
('', ''),
('admin', 'admin'),
('Bryan', '1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
