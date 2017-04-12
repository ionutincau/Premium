-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: sql11.freesqldatabase.com
-- Generation Time: Apr 06, 2017 at 06:14 PM
-- Server version: 5.5.53-0ubuntu0.14.04.1
-- PHP Version: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sql11164406`
--

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

CREATE TABLE IF NOT EXISTS `alerts` (
  `id_alert` int(11) NOT NULL,
  `deadline` date NOT NULL,
  `text` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_alert`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alerts_employees`
--

CREATE TABLE IF NOT EXISTS `alerts_employees` (
  `id_alert_employee` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_alert` int(11) NOT NULL,
  `delivery_date` date NOT NULL,
  `status` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_alert_employee`),
  KEY `fk_emp_alert_emp` (`id_employee`),
  KEY `fk_alerts_alert_emp` (`id_alert`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `clockings`
--

CREATE TABLE IF NOT EXISTS `clockings` (
  `id_clocking` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `date` date NOT NULL,
  `hour_in` int(200) NOT NULL,
  `hour_break` int(200) NOT NULL,
  `hour_work` int(200) NOT NULL,
  `hour_out` int(200) NOT NULL,
  PRIMARY KEY (`id_clocking`),
  KEY `FK_Employees_Clockings` (`id_employee`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clockings`
--

INSERT INTO `clockings` (`id_clocking`, `id_employee`, `date`, `hour_in`, `hour_break`, `hour_work`, `hour_out`) VALUES
(1, 1, '2017-03-25', 480, 600, 780, 800),
(2, 1, '2017-03-26', 600, 800, 900, 1000),
(3, 1, '2017-03-28', 500, 0, 0, 600);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE IF NOT EXISTS `departments` (
  `id_department` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `id_manager` int(11) NOT NULL,
  PRIMARY KEY (`id_department`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id_department`, `name`, `id_manager`) VALUES
(1, 'Dezvoltare', 10),
(2, 'Business', 11);

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE IF NOT EXISTS `documents` (
  `id_document` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `id_doctype` int(11) NOT NULL,
  `document_path` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_document`),
  KEY `fk_documents_employees` (`id_employee`),
  KEY `fk_documents_doctype` (`id_doctype`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `document_types`
--

CREATE TABLE IF NOT EXISTS `document_types` (
  `id_doctype` int(11) NOT NULL,
  `doctype_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_doctype`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE IF NOT EXISTS `employees` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(13) NOT NULL,
  `password` varchar(50) NOT NULL,
  `cnp` varchar(13) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_job` int(11) NOT NULL,
  `id_departament` int(11) NOT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `FK_Department_employee` (`id_departament`),
  KEY `fk_jobs_emp` (`id_job`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id_employee`, `last_name`, `first_name`, `username`, `password`, `cnp`, `id_job`, `id_departament`, `email`, `phone`, `role`) VALUES
(1, 'Costel', 'Biju', 'admin', '123456', '1905851254121', 1, 1, 'cos-tel-bi-ju@premium.com', '0750999999', 'Admin'),
(2, 'Sandu', 'Ciorba', 'valoare', '999999', '195254822418', 2, 2, 'sanduc@valoare.com', '0765987456', 'HR'),
(3, 'Mos', 'Craciun', 'mosu', '123456', '0009984515478', 3, 3, 'mosu@christmas.com', '0751852123', 'User'),
(10, 'Cristi', 'Stoica', 'cristi.stoica', 'qazwsxedc', '1234567890987', 4, 1, 'cristi.stoica@gmail.com', '1234567890', 'Manager'),
(11, 'Micu', 'Alina', 'alina.micu', 'qweasdzxc', '2143654321547', 4, 2, 'alina.micu', '0987654325', 'Manager'),
(12, 'Moldovan', 'Elisei', 'elisei.m', 'qwerasdf', '1542365876554', 1, 1, 'elsiei.moldoban95@gmail.com', '0751686601', 'Programator');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE IF NOT EXISTS `jobs` (
  `id_job` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `min_salary` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `id_document` int(11) NOT NULL,
  PRIMARY KEY (`id_job`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id_job`, `name`, `min_salary`, `number`, `id_document`) VALUES
(1, 'Programator', '1500', '20', 1),
(2, 'Tester', '1000', '5', 2),
(3, 'Project Manager', '1000', '10', 3),
(4, 'Manager', '1500', '4', 4);

-- --------------------------------------------------------

--
-- Table structure for table `jobs_history`
--

CREATE TABLE IF NOT EXISTS `jobs_history` (
  `id_job` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_department` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id_job`),
  KEY `fk_emp_jobsh` (`id_employee`),
  KEY `fk_dep_jobsh` (`id_department`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE IF NOT EXISTS `requests` (
  `id_request` int(11) NOT NULL,
  `id_document` int(11) NOT NULL,
  `Status` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `deadline` date NOT NULL,
  `date_approval` date NOT NULL,
  PRIMARY KEY (`id_request`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
