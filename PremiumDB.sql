-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2017 at 04:17 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Premium`
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
  `hour_out` int(200) NOT NULL,
  `hour_break` int(200) NOT NULL,
  `hour_work` int(200) NOT NULL,
  PRIMARY KEY (`id_clocking`),
  KEY `FK_Employees_Clockings` (`id_employee`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE IF NOT EXISTS `documents` (
  `id_document` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
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
  `id_employee` int(11) NOT NULL,
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
