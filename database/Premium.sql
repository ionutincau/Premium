-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2017 at 12:25 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `premium`
--

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

CREATE TABLE `alerts` (
  `id_alert` int(11) NOT NULL,
  `deadline` date NOT NULL,
  `text` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alerts_employees`
--

CREATE TABLE `alerts_employees` (
  `id_alert_employee` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_alert` int(11) NOT NULL,
  `delivery_date` date NOT NULL,
  `status` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `clockings`
--

CREATE TABLE `clockings` (
  `id_clocking` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `date` date NOT NULL,
  `hour_in` int(200) NOT NULL,
  `hour_break` int(200) NOT NULL,
  `hour_work` int(200) NOT NULL,
  `hour_out` int(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clockings`
--

INSERT INTO `clockings` (`id_clocking`, `id_employee`, `date`, `hour_in`, `hour_out`, `hour_break`, `hour_work`) VALUES
(1, 1, '2017-03-25', 480, 800, 600, 780),
(2, 1, '2017-03-26', 600, 1000, 800, 900),
(3, 1, '2017-03-28', 500, 600, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id_department` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `id_manager` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `id_document` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `id_doctype` int(11) NOT NULL,
  `document_path` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `document_types`
--

CREATE TABLE `document_types` (
  `id_doctype` int(11) NOT NULL,
  `doctype_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
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
  `role` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id_job` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `min_salary` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `id_document` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `jobs_history`
--

CREATE TABLE `jobs_history` (
  `id_job` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_department` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `id_request` int(11) NOT NULL,
  `id_document` int(11) NOT NULL,
  `Status` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `deadline` date NOT NULL,
  `date_approval` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alerts`
--
ALTER TABLE `alerts`
  ADD PRIMARY KEY (`id_alert`);

--
-- Indexes for table `alerts_employees`
--
ALTER TABLE `alerts_employees`
  ADD PRIMARY KEY (`id_alert_employee`),
  ADD KEY `fk_emp_alert_emp` (`id_employee`),
  ADD KEY `fk_alerts_alert_emp` (`id_alert`);

--
-- Indexes for table `clockings`
--
ALTER TABLE `clockings`
  ADD PRIMARY KEY (`id_clocking`),
  ADD KEY `FK_Employees_Clockings` (`id_employee`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id_department`);

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id_document`),
  ADD KEY `fk_documents_employees` (`id_employee`),
  ADD KEY `fk_documents_doctype` (`id_doctype`);

--
-- Indexes for table `document_types`
--
ALTER TABLE `document_types`
  ADD PRIMARY KEY (`id_doctype`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id_employee`),
  ADD KEY `FK_Department_employee` (`id_departament`),
  ADD KEY `fk_jobs_emp` (`id_job`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id_job`);

--
-- Indexes for table `jobs_history`
--
ALTER TABLE `jobs_history`
  ADD PRIMARY KEY (`id_job`),
  ADD KEY `fk_emp_jobsh` (`id_employee`),
  ADD KEY `fk_dep_jobsh` (`id_department`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`id_request`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
