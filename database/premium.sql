-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2017 at 10:59 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--
CREATE DATABASE IF NOT EXISTS `cms` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_520_ci;
USE `cms`;

-- --------------------------------------------------------

--
-- Table structure for table `evenimente`
--

CREATE TABLE `evenimente` (
  `id_eveniment` int(11) NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `web-page` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `locatie` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `descriere` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `d_abstract` date NOT NULL,
  `d_propunere` date NOT NULL,
  `d_evaluare` date NOT NULL,
  `d_taxa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id_event` int(11) NOT NULL,
  `d_abstract` date DEFAULT NULL,
  `d_evaluation` date DEFAULT NULL,
  `d_proposal` date DEFAULT NULL,
  `d_taxes` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `end` date DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `start` date DEFAULT NULL,
  `web_page` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event-sesiune`
--

CREATE TABLE `event-sesiune` (
  `id_eveniment` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3),
(3),
(3),
(3),
(3);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id_payment` int(11) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id_payment`, `date`) VALUES
(1, '2015-07-15'),
(2, '2015-07-14');

-- --------------------------------------------------------

--
-- Table structure for table `pc-event`
--

CREATE TABLE `pc-event` (
  `id_utilizator` int(11) NOT NULL,
  `id_eveniment` int(11) NOT NULL,
  `functie` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pc-prop`
--

CREATE TABLE `pc-prop` (
  `id_utilizator` int(11) NOT NULL,
  `id_propunere` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `review` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `presentation`
--

CREATE TABLE `presentation` (
  `id_presentation` int(11) NOT NULL,
  `demo` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `id_section` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `speaker` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prezentari`
--

CREATE TABLE `prezentari` (
  `id_prezentare` int(11) NOT NULL,
  `speaker` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL,
  `rezumat` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `proposal`
--

CREATE TABLE `proposal` (
  `id_proposal` int(11) NOT NULL,
  `abs` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `accept_date` date DEFAULT NULL,
  `document` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `id_author` int(11) DEFAULT NULL,
  `id_session` int(11) DEFAULT NULL,
  `keywords` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `other_authors` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `send_date` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `topics` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `propuneri`
--

CREATE TABLE `propuneri` (
  `id_propunere` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `alti_autori` varchar(200) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `keywords` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `topics` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `tip` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `data_trimitere` date NOT NULL,
  `data_acceptare` date DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `abstract` longblob NOT NULL,
  `document` longblob NOT NULL,
  `id_sesiune` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id_room` int(11) NOT NULL,
  `location` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `nr_sits` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sali`
--

CREATE TABLE `sali` (
  `id_sala` int(11) NOT NULL,
  `nume` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `locatie` varchar(30) COLLATE utf8_unicode_520_ci NOT NULL,
  `nr_locuri` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sesiune`
--

CREATE TABLE `sesiune` (
  `id_session` int(11) NOT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `president` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sesiuni`
--

CREATE TABLE `sesiuni` (
  `id_sesiune` int(11) NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `id_sala` int(11) NOT NULL,
  `data_in` date NOT NULL,
  `data_out` date NOT NULL,
  `presedinte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `president` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tranzactii`
--

CREATE TABLE `tranzactii` (
  `id_tranzactie` int(11) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `affiliation` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `firstName` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `userName` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `webPage` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `affiliation`, `country`, `email`, `firstName`, `lastName`, `password`, `phone`, `type`, `userName`, `webPage`, `status`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, 'new', NULL, NULL, 'johnny', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user-sesiune`
--

CREATE TABLE `user-sesiune` (
  `id_utilizator` int(11) NOT NULL,
  `id_sesiune` int(11) NOT NULL,
  `functie` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `user_payment`
--

CREATE TABLE `user_payment` (
  `User_userId` int(11) NOT NULL,
  `payment_id_payment` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `user_payment`
--

INSERT INTO `user_payment` (`User_userId`, `payment_id_payment`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `utilizatori`
--

CREATE TABLE `utilizatori` (
  `id_utilizator` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `parola` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `nume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `prenume` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `tip` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `tara` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `email` varchar(20) COLLATE utf8_unicode_520_ci NOT NULL,
  `afiliere` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `telefon` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `web-page` varchar(20) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `id_tranzactie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evenimente`
--
ALTER TABLE `evenimente`
  ADD PRIMARY KEY (`id_eveniment`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id_event`);

--
-- Indexes for table `event-sesiune`
--
ALTER TABLE `event-sesiune`
  ADD KEY `id_eveniment` (`id_eveniment`),
  ADD KEY `id_sectiune` (`id_sesiune`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id_payment`);

--
-- Indexes for table `pc-event`
--
ALTER TABLE `pc-event`
  ADD KEY `id_utilizator` (`id_utilizator`),
  ADD KEY `id_eveniment` (`id_eveniment`);

--
-- Indexes for table `pc-prop`
--
ALTER TABLE `pc-prop`
  ADD KEY `id_utilizator` (`id_utilizator`),
  ADD KEY `id_propunere` (`id_propunere`);

--
-- Indexes for table `presentation`
--
ALTER TABLE `presentation`
  ADD PRIMARY KEY (`id_presentation`);

--
-- Indexes for table `prezentari`
--
ALTER TABLE `prezentari`
  ADD PRIMARY KEY (`id_prezentare`),
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `speaker` (`speaker`);

--
-- Indexes for table `proposal`
--
ALTER TABLE `proposal`
  ADD PRIMARY KEY (`id_proposal`);

--
-- Indexes for table `propuneri`
--
ALTER TABLE `propuneri`
  ADD PRIMARY KEY (`id_propunere`),
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `id_autor` (`id_autor`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id_room`);

--
-- Indexes for table `sali`
--
ALTER TABLE `sali`
  ADD PRIMARY KEY (`id_sala`);

--
-- Indexes for table `sesiune`
--
ALTER TABLE `sesiune`
  ADD PRIMARY KEY (`id_session`);

--
-- Indexes for table `sesiuni`
--
ALTER TABLE `sesiuni`
  ADD PRIMARY KEY (`id_sesiune`),
  ADD KEY `presedinte` (`presedinte`),
  ADD KEY `sala` (`id_sala`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_session`);

--
-- Indexes for table `tranzactii`
--
ALTER TABLE `tranzactii`
  ADD PRIMARY KEY (`id_tranzactie`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `user-sesiune`
--
ALTER TABLE `user-sesiune`
  ADD KEY `id_sectiune` (`id_sesiune`),
  ADD KEY `id_utilizator` (`id_utilizator`);

--
-- Indexes for table `user_payment`
--
ALTER TABLE `user_payment`
  ADD UNIQUE KEY `UK_pvk7t7eus8om21bw149mcvhhs` (`payment_id_payment`),
  ADD KEY `FK8a6iaxj67pu1cbr1fc3kh9dst` (`User_userId`);

--
-- Indexes for table `utilizatori`
--
ALTER TABLE `utilizatori`
  ADD PRIMARY KEY (`id_utilizator`),
  ADD KEY `id_tranzactie` (`id_tranzactie`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event-sesiune`
--
ALTER TABLE `event-sesiune`
  ADD CONSTRAINT `event-sesiune_ibfk_1` FOREIGN KEY (`id_eveniment`) REFERENCES `evenimente` (`id_eveniment`),
  ADD CONSTRAINT `event-sesiune_ibfk_2` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`);

--
-- Constraints for table `pc-event`
--
ALTER TABLE `pc-event`
  ADD CONSTRAINT `pc-event_ibfk_1` FOREIGN KEY (`id_eveniment`) REFERENCES `evenimente` (`id_eveniment`),
  ADD CONSTRAINT `pc-event_ibfk_2` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `pc-prop`
--
ALTER TABLE `pc-prop`
  ADD CONSTRAINT `pc-prop_ibfk_1` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `pc-prop_ibfk_2` FOREIGN KEY (`id_propunere`) REFERENCES `propuneri` (`id_propunere`);

--
-- Constraints for table `prezentari`
--
ALTER TABLE `prezentari`
  ADD CONSTRAINT `prezentari_ibfk_1` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`),
  ADD CONSTRAINT `prezentari_ibfk_2` FOREIGN KEY (`speaker`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `propuneri`
--
ALTER TABLE `propuneri`
  ADD CONSTRAINT `propuneri_ibfk_1` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`),
  ADD CONSTRAINT `propuneri_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `utilizatori` (`id_utilizator`);

--
-- Constraints for table `sesiuni`
--
ALTER TABLE `sesiuni`
  ADD CONSTRAINT `sesiuni_ibfk_1` FOREIGN KEY (`presedinte`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `sesiuni_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sali` (`id_sala`);

--
-- Constraints for table `user-sesiune`
--
ALTER TABLE `user-sesiune`
  ADD CONSTRAINT `user-sesiune_ibfk_1` FOREIGN KEY (`id_utilizator`) REFERENCES `utilizatori` (`id_utilizator`),
  ADD CONSTRAINT `user-sesiune_ibfk_2` FOREIGN KEY (`id_sesiune`) REFERENCES `sesiuni` (`id_sesiune`);

--
-- Constraints for table `utilizatori`
--
ALTER TABLE `utilizatori`
  ADD CONSTRAINT `utilizatori_ibfk_1` FOREIGN KEY (`id_tranzactie`) REFERENCES `tranzactii` (`id_tranzactie`);
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Table structure for table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Table structure for table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Table structure for table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Dumping data for table `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"angular_direct\":\"angular\",\"snap_to_grid\":\"off\",\"relation_lines\":\"true\",\"full_screen\":\"off\",\"small_big_all\":\">\"}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Table structure for table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Table structure for table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Table structure for table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

--
-- Dumping data for table `pma__pdf_pages`
--

INSERT INTO `pma__pdf_pages` (`db_name`, `page_nr`, `page_descr`) VALUES
('cms', 1, 'cms_db'),
('premium', 2, 'premium');

-- --------------------------------------------------------

--
-- Table structure for table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"premium\",\"table\":\"document_types\"},{\"db\":\"premium\",\"table\":\"documents\"},{\"db\":\"premium\",\"table\":\"employees\"},{\"db\":\"premium\",\"table\":\"clockings\"},{\"db\":\"premium\",\"table\":\"requests\"},{\"db\":\"premium\",\"table\":\"jobs_history\"},{\"db\":\"premium\",\"table\":\"alerts\"},{\"db\":\"premium\",\"table\":\"alerts_employees\"},{\"db\":\"premium-cms\",\"table\":\"pcproposal\"},{\"db\":\"premium-cms\",\"table\":\"user\"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

--
-- Dumping data for table `pma__relation`
--

INSERT INTO `pma__relation` (`master_db`, `master_table`, `master_field`, `foreign_db`, `foreign_table`, `foreign_field`) VALUES
('premium', 'alerts_employees', 'id_alert', 'premium', 'alerts', 'id_alert'),
('premium', 'alerts_employees', 'id_employee', 'premium', 'employees', 'id_employee'),
('premium', 'clockings', 'id_employee', 'premium', 'employees', 'id_employee'),
('premium', 'documents', 'id_doctype', 'premium', 'document_types', 'id_doctype'),
('premium', 'documents', 'id_employee', 'premium', 'employees', 'id_employee'),
('premium', 'employees', 'id_job', 'premium', 'jobs', 'id_job'),
('premium', 'jobs', 'id_document', 'premium', 'documents', 'id_document'),
('premium', 'jobs_history', 'id_department', 'premium', 'departments', 'id_department'),
('premium', 'jobs_history', 'id_employee', 'premium', 'employees', 'id_employee'),
('premium', 'jobs_history', 'id_job', 'premium', 'jobs', 'id_job'),
('premium', 'requests', 'id_document', 'premium', 'documents', 'id_document');

-- --------------------------------------------------------

--
-- Table structure for table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float UNSIGNED NOT NULL DEFAULT '0',
  `y` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

--
-- Dumping data for table `pma__table_coords`
--

INSERT INTO `pma__table_coords` (`db_name`, `table_name`, `pdf_page_number`, `x`, `y`) VALUES
('cms', 'evenimente', 1, 70, 24),
('cms', 'event-sesiune', 1, 69, 497),
('cms', 'pc-event', 1, 338, 63),
('cms', 'pc-prop', 1, 871, 64),
('cms', 'prezentari', 1, 299, 200),
('cms', 'propuneri', 1, 830, 290),
('cms', 'sali', 1, 353, 563),
('cms', 'sesiuni', 1, 564, 510),
('cms', 'tranzactii', 1, 600, 37),
('cms', 'user-sesiune', 1, 233, 367),
('cms', 'utilizatori', 1, 568, 189),
('premium', 'alerts', 2, 1107, 324),
('premium', 'alerts_employees', 2, 857, 281),
('premium', 'clockings', 2, 857, 14),
('premium', 'departments', 2, 817, 495),
('premium', 'document_types', 2, 128, 326),
('premium', 'documents', 2, 276, 147),
('premium', 'employees', 2, 554, 67),
('premium', 'jobs', 2, 289, 445),
('premium', 'jobs_history', 2, 573, 421),
('premium', 'requests', 2, 85, 82);

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Dumping data for table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'premium-cms', 'pcproposal', '{\"sorted_col\":\"`user_id_user` ASC\"}', '2017-05-18 20:42:26');

-- --------------------------------------------------------

--
-- Table structure for table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data for table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2017-04-03 16:50:46', '{\"collation_connection\":\"utf8mb4_unicode_ci\"}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Table structure for table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indexes for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indexes for table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;--
-- Database: `premium`
--
CREATE DATABASE IF NOT EXISTS `premium` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `premium`;

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

CREATE TABLE `alerts` (
  `id_alert` int(11) NOT NULL,
  `deadline` date NOT NULL,
  `text` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `alerts`
--

INSERT INTO `alerts` (`id_alert`, `deadline`, `text`) VALUES
(1, '2017-05-22', 'Terminati proiectul !!!');

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

--
-- Dumping data for table `alerts_employees`
--

INSERT INTO `alerts_employees` (`id_alert_employee`, `id_employee`, `id_alert`, `delivery_date`, `status`) VALUES
(4, 14, 1, '2017-05-22', 'read'),
(3, 12, 1, '2017-05-22', 'send'),
(2, 10, 1, '2017-05-22', 'send'),
(1, 3, 1, '2017-05-22', 'send');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `clockings`
--

INSERT INTO `clockings` (`id_clocking`, `id_employee`, `date`, `hour_in`, `hour_break`, `hour_work`, `hour_out`) VALUES
(39, 13, '2017-04-16', 480, 720, 900, 1020),
(36, 13, '2017-04-25', 480, 600, 720, 960),
(35, 7, '2017-04-25', 672, 672, 672, 672),
(34, 7, '2017-04-24', 480, 540, 600, 660),
(40, 13, '2017-03-13', 600, 720, 900, 1200),
(20, 13, '2017-04-14', 490, 855, 900, 1140),
(19, 13, '2017-04-15', 480, 549, 610, 670),
(17, 2, '2017-04-15', 696, 696, 696, 696),
(18, 3, '2017-04-15', 1193, 1194, 1194, 1194),
(33, 7, '2017-04-23', 672, 672, 672, 672),
(41, 13, '2017-04-24', 490, 560, 630, 700),
(58, 3, '2017-04-29', 1290, 1290, 1290, 1290),
(53, 7, '2017-04-29', 1191, 1191, 1191, 1191),
(55, 13, '2017-04-29', 1262, 1263, 1324, 1385),
(59, 13, '2017-04-28', 480, 540, 600, 660),
(60, 13, '2017-04-27', 720, 780, 840, 900),
(61, 7, '2017-04-30', 1039, 0, 0, 1039),
(62, 13, '2017-05-01', 540, 600, 660, 720),
(63, 13, '2017-05-02', 540, 600, 660, 720),
(64, 3, '2017-05-20', 1417, 1417, 1417, 1417),
(65, 14, '2017-05-22', 705, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id_department` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `id_manager` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id_department`, `name`, `id_manager`) VALUES
(1, 'Dezvoltare', 10),
(2, 'Business', 11),
(3, 'Party', 13),
(4, 'Concediu', 13),
(5, 'Training', 3);

-- --------------------------------------------------------

--
-- Table structure for table `document_types`
--

CREATE TABLE `document_types` (
  `id_doctype` int(11) NOT NULL,
  `doctype_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `document_types`
--

INSERT INTO `document_types` (`id_doctype`, `doctype_name`) VALUES
(1, 'vacation'),
(2, 'worked'),
(3, 'income');

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
  `document` longblob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `documents`
--

INSERT INTO `documents` (`id_document`, `id_employee`, `name`, `date`, `id_doctype`, `document`) VALUES
(4, 14, 'Income Statement - Elon Musk - 22-05-2017-11-54-48.pdf', '2017-05-22', 3, 0xaced000573720014446f63756d656e7454797065732e496e636f6d65cd06805579bcdbe10200174c0007636f6d70616e797400124c6a6176612f6c616e672f537472696e673b4c0004646174657400144c6a6176612f7574696c2f43616c656e6461723b4c000a6465706172746d656e7471007e00014c000d646f63756d656e745469746c6571007e00014c0004646f747371007e00014c00036a6f6271007e00014c00066d6f6e74683171007e00014c00066d6f6e74683271007e00014c00066d6f6e74683371007e00014c00046e616d6571007e00014c0007707572706f736571007e00014c000773616c617279317400134c6a6176612f6c616e672f496e74656765723b4c000773616c6172793271007e00034c000773616c6172793371007e00034c00097374617274446174657400104c6a6176612f7574696c2f446174653b4c0002743171007e00014c0002743271007e00014c0002743371007e00014c0002743471007e00014c0002743571007e00014c0002743671007e00014c0002743871007e00014c00057469746c6571007e0001787074000645766f7a6f6e7372001b6a6176612e7574696c2e477265676f7269616e43616c656e6461728f3dd7d6e5b0d0c10200014a0010677265676f7269616e4375746f766572787200126a6176612e7574696c2e43616c656e646172e6ea4d1ec8dc5b8e03000b5a000c6172654669656c647353657449000e66697273744461794f665765656b5a0009697354696d655365745a00076c656e69656e744900166d696e696d616c44617973496e46697273745765656b4900096e6578745374616d7049001573657269616c56657273696f6e4f6e53747265616d4a000474696d655b00066669656c64737400025b495b000569735365747400025b5a4c00047a6f6e657400144c6a6176612f7574696c2f54696d655a6f6e653b7870010000000101010000000100000002000000010000015c2f5db04d757200025b494dba602676eab2a502000078700000001100000001000007e1000000040000001500000004000000160000008e0000000200000004000000000000000b0000000b00000036000000300000008d006ddd000036ee80757200025b5a578f203914b85de20200007870000000110101010101010101010101010101010101737200186a6176612e7574696c2e53696d706c6554696d655a6f6e65fa675d60d15ef5a603001249000a647374536176696e6773490006656e6444617949000c656e644461794f665765656b490007656e644d6f6465490008656e644d6f6e7468490007656e6454696d6549000b656e6454696d654d6f64654900097261774f666673657449001573657269616c56657273696f6e4f6e53747265616d490008737461727444617949000e73746172744461794f665765656b49000973746172744d6f646549000a73746172744d6f6e7468490009737461727454696d6549000d737461727454696d654d6f64654900097374617274596561725a000b7573654461796c696768745b000b6d6f6e74684c656e6774687400025b42787200126a6176612e7574696c2e54696d655a6f6e6531b3e9f57744aca10200014c0002494471007e0001787074000d4575726f70652f417468656e730036ee80ffffffff00000001000000020000000900dbba0000000002006ddd0000000002ffffffff00000001000000020000000200a4cb80000000020000000001757200025b42acf317f8060854e002000078700000000c1f1c1f1e1f1e1f1f1e1f1e1f770a00000006ff01ff0102027571007e000d000000020036ee800036ee80787372001a73756e2e7574696c2e63616c656e6461722e5a6f6e65496e666f24d1d3ce001d719b020008490008636865636b73756d49000a647374536176696e67734900097261774f666673657449000d7261774f6666736574446966665a001377696c6c474d544f66667365744368616e67655b00076f66667365747371007e00095b001473696d706c6554696d655a6f6e65506172616d7371007e00095b000b7472616e736974696f6e737400025b4a7871007e001371007e0015ca7117b90036ee80006ddd0000000000007571007e000d00000004006ddd000056da6000a4cb800036ee807571007e000d0000000a00000002ffffffff000000010036ee800000000200000009ffffffff000000010036ee8000000002757200025b4a782004b512b1759302000078700000008affdfdae01dc00001ffe776c82dc00000ffeec8ff19300032ffeedb01ec480000fff2cdad62300032fff2d51117880030fff38677d3680003fff3b613c8980030fff3f29312b00003fff42d7d3dd80000fff7f64a2a700032fff81e300488000000026c350a7000320002b597a97000000002e1b6fb00003200031c4b518000000003549edfc0003200038d44d3c000000003c9c78cc000320004021e89e8000000043f08421800320004792ebb9800000004b4b6d47000320004eea2f08800000005294502a80032000563d95928000000059e6dafa800320005d90206280000000613965ca8003200064e2ab3280000000688bf09a800320006c59428680000000700287ee8003200073abcd5680000000775512be800320007afe5826800000007ea79d8e800320008250e2f68000000085fa285e8003200089a36dc6800000008d4cb32e8003200090f5f89680000000949f3dfe80032000986c8fea800000009c15d552800320009fbf1aba80000000a368602280032000a711a58a80000000aabaeaf280032000ae64305a80000000b20d75c280032000b5b6bb2a80000000b960009280032000bd0945fa80000000c0d697e680032000c5100f5e80000000c82922b680032000cc629a2e80000000cf7bad8680032000d3b524fe80000000d6ce385680032000db2bbc5280000000de20c32680032000e27e472280000000e5734df680032000e9d0d1f280000000ece9e54a80032000f1235cc280000000f43c701a80032000f875e79280000000fb8efaea80032000ffec7ee68000000102e185ba80032001073f09b6800000010a34108a800320010e9194868000000111869b5a8003200115e41f568000000118fd32ae800320011d36aa2680000001204fbd7e80032001248934f68000000127a2484e800320012bffcc4a800000012ef4d31e800320013352571a8000000136475dee800320013aa4e1ea800000013dbdf5428003200141f76cba8000000145108012800320014949f78a800000014c630ae280032001509c825a8000000153b595b280032001581319ae800000015b082082800320015f65a47e80000001625aab528003200166b82f4e8000000169d142a6800320016e0aba1e800000017123cd7680032001755d44ee8000000178765846800320017cd3dc42800000017fc8e316800320018426671280000001871b6de6800320018b78f1e2800000018e92053a8003200192cb7cb28000000195e4900a800320019a1e0782800000019d371ada80032001a170925280000001a489a5aa80032001a8e729a680000001abdc307a80032001b039b47680000001b32ebb4a80032001b78c3f4680000001baa5529e80032001bedeca1680000001c1f7dd6e80032001c63154e680000001c94a683e80032001cda7ec3a80000001d09cf30e80032001d4fa770a80000001d7ef7dde80032001dc4d01da80000001df4208ae80032001e39f8caa80000001e6b8a00280032001eaf2177a80000001ee0b2ad280032001f244a24a8000078fffff4e2f964ac0074000a44657a766f6c74617265740013416465766572696e74612064652076656e69747400402e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e740011416e64726f696420446576656c6f706572740007417072696c69657400064d6172746965740009466562727561726965740009456c6f6e204d75736b740006646f63746f72737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000bb87371007e002900000bb87371007e002900000bb87372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000015b262af0807874002d5072696e207072657a656e7461207365206164657665726573746520636120646f6d6e756c2f646f616d6e612074001c2c206573746520696e63616472617428612920696e206669726d61207400562c20637520636f6e747261637420696e646976696475616c206465206d756e636120706520706572696f6164612064657465726d696e6174612f6e6564657465726d696e617461206465206c612064617461206465207400102c20696e2066756e63746961206465207400102c20646570617274616d656e74756c2074002e2c20637520756e2073616c6172697520627275742c20706520756c74696d656c652033206c756e692c2064653a2074002a5072657a656e746120732d6120656c6962657261742070656e74727520612d69207365727669206c6120740010496e636f6d652053746174656d656e74),
(3, 14, 'Income Statement - Elon Musk - 22-05-2017-11-46-08.pdf', '2017-05-22', 3, 0xaced000573720014446f63756d656e7454797065732e496e636f6d65cd06805579bcdbe10200174c0007636f6d70616e797400124c6a6176612f6c616e672f537472696e673b4c0004646174657400144c6a6176612f7574696c2f43616c656e6461723b4c000a6465706172746d656e7471007e00014c000d646f63756d656e745469746c6571007e00014c0004646f747371007e00014c00036a6f6271007e00014c00066d6f6e74683171007e00014c00066d6f6e74683271007e00014c00066d6f6e74683371007e00014c00046e616d6571007e00014c0007707572706f736571007e00014c000773616c617279317400134c6a6176612f6c616e672f496e74656765723b4c000773616c6172793271007e00034c000773616c6172793371007e00034c00097374617274446174657400104c6a6176612f7574696c2f446174653b4c0002743171007e00014c0002743271007e00014c0002743371007e00014c0002743471007e00014c0002743571007e00014c0002743671007e00014c0002743871007e00014c00057469746c6571007e0001787074000645766f7a6f6e7372001b6a6176612e7574696c2e477265676f7269616e43616c656e6461728f3dd7d6e5b0d0c10200014a0010677265676f7269616e4375746f766572787200126a6176612e7574696c2e43616c656e646172e6ea4d1ec8dc5b8e03000b5a000c6172654669656c647353657449000e66697273744461794f665765656b5a0009697354696d655365745a00076c656e69656e744900166d696e696d616c44617973496e46697273745765656b4900096e6578745374616d7049001573657269616c56657273696f6e4f6e53747265616d4a000474696d655b00066669656c64737400025b495b000569735365747400025b5a4c00047a6f6e657400144c6a6176612f7574696c2f54696d655a6f6e653b7870010000000101010000000100000002000000010000015c2f55c268757200025b494dba602676eab2a502000078700000001100000001000007e1000000040000001500000004000000160000008e0000000200000004000000000000000b0000000b0000002e00000008000001e8006ddd000036ee80757200025b5a578f203914b85de20200007870000000110101010101010101010101010101010101737200186a6176612e7574696c2e53696d706c6554696d655a6f6e65fa675d60d15ef5a603001249000a647374536176696e6773490006656e6444617949000c656e644461794f665765656b490007656e644d6f6465490008656e644d6f6e7468490007656e6454696d6549000b656e6454696d654d6f64654900097261774f666673657449001573657269616c56657273696f6e4f6e53747265616d490008737461727444617949000e73746172744461794f665765656b49000973746172744d6f646549000a73746172744d6f6e7468490009737461727454696d6549000d737461727454696d654d6f64654900097374617274596561725a000b7573654461796c696768745b000b6d6f6e74684c656e6774687400025b42787200126a6176612e7574696c2e54696d655a6f6e6531b3e9f57744aca10200014c0002494471007e0001787074000d4575726f70652f417468656e730036ee80ffffffff00000001000000020000000900dbba0000000002006ddd0000000002ffffffff00000001000000020000000200a4cb80000000020000000001757200025b42acf317f8060854e002000078700000000c1f1c1f1e1f1e1f1f1e1f1e1f770a00000006ff01ff0102027571007e000d000000020036ee800036ee80787372001a73756e2e7574696c2e63616c656e6461722e5a6f6e65496e666f24d1d3ce001d719b020008490008636865636b73756d49000a647374536176696e67734900097261774f666673657449000d7261774f6666736574446966665a001377696c6c474d544f66667365744368616e67655b00076f66667365747371007e00095b001473696d706c6554696d655a6f6e65506172616d7371007e00095b000b7472616e736974696f6e737400025b4a7871007e001371007e0015ca7117b90036ee80006ddd0000000000007571007e000d00000004006ddd000056da6000a4cb800036ee807571007e000d0000000a00000002ffffffff000000010036ee800000000200000009ffffffff000000010036ee8000000002757200025b4a782004b512b1759302000078700000008affdfdae01dc00001ffe776c82dc00000ffeec8ff19300032ffeedb01ec480000fff2cdad62300032fff2d51117880030fff38677d3680003fff3b613c8980030fff3f29312b00003fff42d7d3dd80000fff7f64a2a700032fff81e300488000000026c350a7000320002b597a97000000002e1b6fb00003200031c4b518000000003549edfc0003200038d44d3c000000003c9c78cc000320004021e89e8000000043f08421800320004792ebb9800000004b4b6d47000320004eea2f08800000005294502a80032000563d95928000000059e6dafa800320005d90206280000000613965ca8003200064e2ab3280000000688bf09a800320006c59428680000000700287ee8003200073abcd5680000000775512be800320007afe5826800000007ea79d8e800320008250e2f68000000085fa285e8003200089a36dc6800000008d4cb32e8003200090f5f89680000000949f3dfe80032000986c8fea800000009c15d552800320009fbf1aba80000000a368602280032000a711a58a80000000aabaeaf280032000ae64305a80000000b20d75c280032000b5b6bb2a80000000b960009280032000bd0945fa80000000c0d697e680032000c5100f5e80000000c82922b680032000cc629a2e80000000cf7bad8680032000d3b524fe80000000d6ce385680032000db2bbc5280000000de20c32680032000e27e472280000000e5734df680032000e9d0d1f280000000ece9e54a80032000f1235cc280000000f43c701a80032000f875e79280000000fb8efaea80032000ffec7ee68000000102e185ba80032001073f09b6800000010a34108a800320010e9194868000000111869b5a8003200115e41f568000000118fd32ae800320011d36aa2680000001204fbd7e80032001248934f68000000127a2484e800320012bffcc4a800000012ef4d31e800320013352571a8000000136475dee800320013aa4e1ea800000013dbdf5428003200141f76cba8000000145108012800320014949f78a800000014c630ae280032001509c825a8000000153b595b280032001581319ae800000015b082082800320015f65a47e80000001625aab528003200166b82f4e8000000169d142a6800320016e0aba1e800000017123cd7680032001755d44ee8000000178765846800320017cd3dc42800000017fc8e316800320018426671280000001871b6de6800320018b78f1e2800000018e92053a8003200192cb7cb28000000195e4900a800320019a1e0782800000019d371ada80032001a170925280000001a489a5aa80032001a8e729a680000001abdc307a80032001b039b47680000001b32ebb4a80032001b78c3f4680000001baa5529e80032001bedeca1680000001c1f7dd6e80032001c63154e680000001c94a683e80032001cda7ec3a80000001d09cf30e80032001d4fa770a80000001d7ef7dde80032001dc4d01da80000001df4208ae80032001e39f8caa80000001e6b8a00280032001eaf2177a80000001ee0b2ad280032001f244a24a8000078fffff4e2f964ac0074000a44657a766f6c74617265740013416465766572696e74612064652076656e69747400402e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e740011416e64726f696420446576656c6f706572740007417072696c69657400064d6172746965740009466562727561726965740009456c6f6e204d75736b740011706c656361726561207065206d61727465737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000bb87371007e00290000000071007e002c7372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000015bc0a9b8807874002d5072696e207072657a656e7461207365206164657665726573746520636120646f6d6e756c2f646f616d6e612074001c2c206573746520696e63616472617428612920696e206669726d61207400562c20637520636f6e747261637420696e646976696475616c206465206d756e636120706520706572696f6164612064657465726d696e6174612f6e6564657465726d696e617461206465206c612064617461206465207400102c20696e2066756e63746961206465207400102c20646570617274616d656e74756c2074002e2c20637520756e2073616c6172697520627275742c20706520756c74696d656c652033206c756e692c2064653a2074002a5072657a656e746120732d6120656c6962657261742070656e74727520612d69207365727669206c6120740010496e636f6d652053746174656d656e74),
(2, 3, 'Vacation Request - Mos Craciun - 21052017202339.pdf', '2017-05-21', 1, 0xaced000573720016446f63756d656e7454797065732e5661636174696f6ea147fac6ebdc9989020010490009757365645f646179734c0007636f6d70616e797400124c6a6176612f6c616e672f537472696e673b4c0004646174657400144c6a6176612f7574696c2f43616c656e6461723b4c000a6465706172746d656e7471007e00014c000d646f63756d656e745469746c6571007e00014c000c656e645f7661636174696f6e71007e00014c00036a6f6271007e00014c00046e616d6571007e00014c000e73746172745f7661636174696f6e71007e00014c0002743171007e00014c0002743271007e00014c0002743371007e00014c0002743471007e00014c0002743571007e00014c0002743671007e00014c00057469746c6571007e000178700000000074000645766f7a6f6e7372001b6a6176612e7574696c2e477265676f7269616e43616c656e6461728f3dd7d6e5b0d0c10200014a0010677265676f7269616e4375746f766572787200126a6176612e7574696c2e43616c656e646172e6ea4d1ec8dc5b8e03000b5a000c6172654669656c647353657449000e66697273744461794f665765656b5a0009697354696d655365745a00076c656e69656e744900166d696e696d616c44617973496e46697273745765656b4900096e6578745374616d7049001573657269616c56657273696f6e4f6e53747265616d4a000474696d655b00066669656c64737400025b495b000569735365747400025b5a4c00047a6f6e657400144c6a6176612f7574696c2f54696d655a6f6e653b7870010000000101010000000100000002000000010000015c2c0933fc757200025b494dba602676eab2a502000078700000001100000001000007e1000000040000001500000004000000150000008d0000000100000003000000010000000800000014000000170000002700000284006ddd000036ee80757200025b5a578f203914b85de20200007870000000110101010101010101010101010101010101737200186a6176612e7574696c2e53696d706c6554696d655a6f6e65fa675d60d15ef5a603001249000a647374536176696e6773490006656e6444617949000c656e644461794f665765656b490007656e644d6f6465490008656e644d6f6e7468490007656e6454696d6549000b656e6454696d654d6f64654900097261774f666673657449001573657269616c56657273696f6e4f6e53747265616d490008737461727444617949000e73746172744461794f665765656b49000973746172744d6f646549000a73746172744d6f6e7468490009737461727454696d6549000d737461727454696d654d6f64654900097374617274596561725a000b7573654461796c696768745b000b6d6f6e74684c656e6774687400025b42787200126a6176612e7574696c2e54696d655a6f6e6531b3e9f57744aca10200014c0002494471007e0001787074000d4575726f70652f417468656e730036ee80ffffffff00000001000000020000000900dbba0000000002006ddd0000000002ffffffff00000001000000020000000200a4cb80000000020000000001757200025b42acf317f8060854e002000078700000000c1f1c1f1e1f1e1f1f1e1f1e1f770a00000006ff01ff0102027571007e000b000000020036ee800036ee80787372001a73756e2e7574696c2e63616c656e6461722e5a6f6e65496e666f24d1d3ce001d719b020008490008636865636b73756d49000a647374536176696e67734900097261774f666673657449000d7261774f6666736574446966665a001377696c6c474d544f66667365744368616e67655b00076f66667365747371007e00075b001473696d706c6554696d655a6f6e65506172616d7371007e00075b000b7472616e736974696f6e737400025b4a7871007e001171007e0013ca7117b90036ee80006ddd0000000000007571007e000b00000004006ddd000056da6000a4cb800036ee807571007e000b0000000a00000002ffffffff000000010036ee800000000200000009ffffffff000000010036ee8000000002757200025b4a782004b512b1759302000078700000008affdfdae01dc00001ffe776c82dc00000ffeec8ff19300032ffeedb01ec480000fff2cdad62300032fff2d51117880030fff38677d3680003fff3b613c8980030fff3f29312b00003fff42d7d3dd80000fff7f64a2a700032fff81e300488000000026c350a7000320002b597a97000000002e1b6fb00003200031c4b518000000003549edfc0003200038d44d3c000000003c9c78cc000320004021e89e8000000043f08421800320004792ebb9800000004b4b6d47000320004eea2f08800000005294502a80032000563d95928000000059e6dafa800320005d90206280000000613965ca8003200064e2ab3280000000688bf09a800320006c59428680000000700287ee8003200073abcd5680000000775512be800320007afe5826800000007ea79d8e800320008250e2f68000000085fa285e8003200089a36dc6800000008d4cb32e8003200090f5f89680000000949f3dfe80032000986c8fea800000009c15d552800320009fbf1aba80000000a368602280032000a711a58a80000000aabaeaf280032000ae64305a80000000b20d75c280032000b5b6bb2a80000000b960009280032000bd0945fa80000000c0d697e680032000c5100f5e80000000c82922b680032000cc629a2e80000000cf7bad8680032000d3b524fe80000000d6ce385680032000db2bbc5280000000de20c32680032000e27e472280000000e5734df680032000e9d0d1f280000000ece9e54a80032000f1235cc280000000f43c701a80032000f875e79280000000fb8efaea80032000ffec7ee68000000102e185ba80032001073f09b6800000010a34108a800320010e9194868000000111869b5a8003200115e41f568000000118fd32ae800320011d36aa2680000001204fbd7e80032001248934f68000000127a2484e800320012bffcc4a800000012ef4d31e800320013352571a8000000136475dee800320013aa4e1ea800000013dbdf5428003200141f76cba8000000145108012800320014949f78a800000014c630ae280032001509c825a8000000153b595b280032001581319ae800000015b082082800320015f65a47e80000001625aab528003200166b82f4e8000000169d142a6800320016e0aba1e800000017123cd7680032001755d44ee8000000178765846800320017cd3dc42800000017fc8e316800320018426671280000001871b6de6800320018b78f1e2800000018e92053a8003200192cb7cb28000000195e4900a800320019a1e0782800000019d371ada80032001a170925280000001a489a5aa80032001a8e729a680000001abdc307a80032001b039b47680000001b32ebb4a80032001b78c3f4680000001baa5529e80032001bedeca1680000001c1f7dd6e80032001c63154e680000001c94a683e80032001cda7ec3a80000001d09cf30e80032001d4fa770a80000001d7ef7dde80032001dc4d01da80000001df4208ae80032001e39f8caa80000001e6b8a00280032001eaf2177a80000001ee0b2ad280032001f244a24a8000078fffff4e2f964ac0074000550617274797400204365726572652070656e74727520636f6e6365646975206465206f6469686e6174000a32322e30352e3230313774000f50726f6a656374204d616e6167657274000b4d6f73204372616369756e74000a32312e30352e3230313774000f53756273656d6e6174756c286129207400102c20616e67616a617428612920616c2074000f20696e2066756e63746961206465207400102c20646570617274616d656e74756c207400472c20766120726f672073612d6d69206170726f6261746920636f6e63656469756c206465206f6469686e6120696e20706572696f6164612063757072696e736120696e747265207400472e200a44696e20746f74616c756c206465203231207a696c65206c75637261746f617265206c61206361726520616d206472657074756c2c20616d206d6169207072696d6974207400105661636174696f6e2052657175657374),
(1, 3, 'Worked Period Statement - Mos Craciun - 21-05-2017-20-22-50.pdf', '2017-05-21', 2, 0xaced00057372001a446f63756d656e7454797065732e576f726b6564506572696f6436cc2fe8ae8acf260200104c0003434e507400124c6a6176612f6c616e672f537472696e673b4c0007636f6d70616e7971007e00014c0004646174657400144c6a6176612f7574696c2f43616c656e6461723b4c000a6465706172746d656e7471007e00014c000d646f63756d656e745469746c6571007e00014c00036a6f6271007e00014c00046e616d6571007e00014c0002743171007e00014c0002743271007e00014c0002743371007e00014c0002743471007e00014c0002743571007e00014c0002743671007e00014c0002743771007e00014c0002743871007e00014c00057469746c6571007e0001787074000d3030303939383435313534373874000645766f7a6f6e7372001b6a6176612e7574696c2e477265676f7269616e43616c656e6461728f3dd7d6e5b0d0c10200014a0010677265676f7269616e4375746f766572787200126a6176612e7574696c2e43616c656e646172e6ea4d1ec8dc5b8e03000b5a000c6172654669656c647353657449000e66697273744461794f665765656b5a0009697354696d655365745a00076c656e69656e744900166d696e696d616c44617973496e46697273745765656b4900096e6578745374616d7049001573657269616c56657273696f6e4f6e53747265616d4a000474696d655b00066669656c64737400025b495b000569735365747400025b5a4c00047a6f6e657400144c6a6176612f7574696c2f54696d655a6f6e653b7870010000000101010000000100000002000000010000015c2c0872df757200025b494dba602676eab2a502000078700000001100000001000007e1000000040000001500000004000000150000008d00000001000000030000000100000008000000140000001600000032000000cf006ddd000036ee80757200025b5a578f203914b85de20200007870000000110101010101010101010101010101010101737200186a6176612e7574696c2e53696d706c6554696d655a6f6e65fa675d60d15ef5a603001249000a647374536176696e6773490006656e6444617949000c656e644461794f665765656b490007656e644d6f6465490008656e644d6f6e7468490007656e6454696d6549000b656e6454696d654d6f64654900097261774f666673657449001573657269616c56657273696f6e4f6e53747265616d490008737461727444617949000e73746172744461794f665765656b49000973746172744d6f646549000a73746172744d6f6e7468490009737461727454696d6549000d737461727454696d654d6f64654900097374617274596561725a000b7573654461796c696768745b000b6d6f6e74684c656e6774687400025b42787200126a6176612e7574696c2e54696d655a6f6e6531b3e9f57744aca10200014c0002494471007e0001787074000d4575726f70652f417468656e730036ee80ffffffff00000001000000020000000900dbba0000000002006ddd0000000002ffffffff00000001000000020000000200a4cb80000000020000000001757200025b42acf317f8060854e002000078700000000c1f1c1f1e1f1e1f1f1e1f1e1f770a00000006ff01ff0102027571007e000c000000020036ee800036ee80787372001a73756e2e7574696c2e63616c656e6461722e5a6f6e65496e666f24d1d3ce001d719b020008490008636865636b73756d49000a647374536176696e67734900097261774f666673657449000d7261774f6666736574446966665a001377696c6c474d544f66667365744368616e67655b00076f66667365747371007e00085b001473696d706c6554696d655a6f6e65506172616d7371007e00085b000b7472616e736974696f6e737400025b4a7871007e001271007e0014ca7117b90036ee80006ddd0000000000007571007e000c00000004006ddd000056da6000a4cb800036ee807571007e000c0000000a00000002ffffffff000000010036ee800000000200000009ffffffff000000010036ee8000000002757200025b4a782004b512b1759302000078700000008affdfdae01dc00001ffe776c82dc00000ffeec8ff19300032ffeedb01ec480000fff2cdad62300032fff2d51117880030fff38677d3680003fff3b613c8980030fff3f29312b00003fff42d7d3dd80000fff7f64a2a700032fff81e300488000000026c350a7000320002b597a97000000002e1b6fb00003200031c4b518000000003549edfc0003200038d44d3c000000003c9c78cc000320004021e89e8000000043f08421800320004792ebb9800000004b4b6d47000320004eea2f08800000005294502a80032000563d95928000000059e6dafa800320005d90206280000000613965ca8003200064e2ab3280000000688bf09a800320006c59428680000000700287ee8003200073abcd5680000000775512be800320007afe5826800000007ea79d8e800320008250e2f68000000085fa285e8003200089a36dc6800000008d4cb32e8003200090f5f89680000000949f3dfe80032000986c8fea800000009c15d552800320009fbf1aba80000000a368602280032000a711a58a80000000aabaeaf280032000ae64305a80000000b20d75c280032000b5b6bb2a80000000b960009280032000bd0945fa80000000c0d697e680032000c5100f5e80000000c82922b680032000cc629a2e80000000cf7bad8680032000d3b524fe80000000d6ce385680032000db2bbc5280000000de20c32680032000e27e472280000000e5734df680032000e9d0d1f280000000ece9e54a80032000f1235cc280000000f43c701a80032000f875e79280000000fb8efaea80032000ffec7ee68000000102e185ba80032001073f09b6800000010a34108a800320010e9194868000000111869b5a8003200115e41f568000000118fd32ae800320011d36aa2680000001204fbd7e80032001248934f68000000127a2484e800320012bffcc4a800000012ef4d31e800320013352571a8000000136475dee800320013aa4e1ea800000013dbdf5428003200141f76cba8000000145108012800320014949f78a800000014c630ae280032001509c825a8000000153b595b280032001581319ae800000015b082082800320015f65a47e80000001625aab528003200166b82f4e8000000169d142a6800320016e0aba1e800000017123cd7680032001755d44ee8000000178765846800320017cd3dc42800000017fc8e316800320018426671280000001871b6de6800320018b78f1e2800000018e92053a8003200192cb7cb28000000195e4900a800320019a1e0782800000019d371ada80032001a170925280000001a489a5aa80032001a8e729a680000001abdc307a80032001b039b47680000001b32ebb4a80032001b78c3f4680000001baa5529e80032001bedeca1680000001c1f7dd6e80032001c63154e680000001c94a683e80032001cda7ec3a80000001d09cf30e80032001d4fa770a80000001d7ef7dde80032001dc4d01da80000001df4208ae80032001e39f8caa80000001e6b8a00280032001eaf2177a80000001ee0b2ad280032001f244a24a8000078fffff4e2f964ac00740005506172747974001e416465766572696e74612064652076656368696d6520696e206d756e636174000f50726f6a656374204d616e6167657274000b4d6f73204372616369756e74001e2c206120666f737420616e67616a617420616c20636f6d70616e696569207400062c206a6f622074000e2c20646570617274616d656e74207400305072696e207072657a656e7461207365206174657374612066617074756c20636120646f6d6e756c2f646f616d6e61207400062c20434e50207400cd20696e2062617a6120636f6e7472616374756c756920696e646976696475616c206465206d756e6361206375206e6f726d6120696e747265616761202f2063752074696d70207061727469616c20646520e280a6e280a6e280a6e280a6e280a62e202e6f7265202f207a692c20696e636865696174207065206475726174612064657465726d696e617461202f206e6564657465726d696e6174612c20696e726567697374726174206c6120496e73706563746f726174756c2054657269746f7269616c206465204d756e636174004e20e280a6e280a6e280a6e280a6e280a6e280a6206375206e722e20e280a6e280a6e280a62e2e2fe280a6e280a6e280a6e280a62e2c20696e2066756e63746961202f206d657365726961206465207400b120506520647572617461206578656375746172696920636f6e7472616374756c756920696e646976696475616c206465206d756e636120617520696e74657276656e69742075726d61746f6172656c65206d7574617469692028696e63686569657265612c206d6f6469666963617265612c2073757370656e646172656120736920696e6365746172656120636f6e7472616374756c756920696e646976696475616c206465206d756e6361293a200a0a740017576f726b656420506572696f642053746174656d656e74);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id_employee` int(11) NOT NULL,
  `last_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(13) CHARACTER SET latin1 NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `cnp` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `id_job` int(11) NOT NULL,
  `id_department` int(11) NOT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id_employee`, `last_name`, `first_name`, `username`, `password`, `cnp`, `id_job`, `id_department`, `email`, `phone`, `role`) VALUES
(2, 'Incau', 'Ionut', 'johnny', '123456', '1951013011151', 2, 2, 'ionut.incau@gmail.com', '0751311492', 'hr'),
(3, 'Mos', 'Craciun', 'mosu', '123456', '0009984515478', 3, 3, 'mosu@christmas.com', '0751852123', 'user'),
(10, 'Cristi', 'Stoica', 'cristi.stoica', 'qazwsxedc', '1234567890987', 4, 1, 'cristi.stoica@gmail.com', '1234567890', 'user'),
(12, 'Moldovan', 'Elisei', 'elisei.m', 'qwerasdf', '1542365876554', 1, 1, 'elsiei.moldoban95@gmail.com', '0751686601', 'user'),
(7, 'Maierescu', 'Marius', 'mariusdk', '123456', '122345678', 3, 1, 'mariusdk@gmail.com', '0744925179', 'hr'),
(13, 'Incau', 'Ionut', 'white_shadow', '123456', '1951013011151', 1, 1, 'ionut.incau@gmail.com', '0751311492', 'admin'),
(11, 'Emil', 'Jucu', 'admin', '123456', '1950925022261', 3, 2, 'admin@admin.ro', '0751486442', 'admin'),
(14, 'Elon', 'Musk', 'elon', '123456', '195062301151', 6, 1, 'elon@ubb.ro', '051311492', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id_job` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `min_salary` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `number` int(11) NOT NULL,
  `id_document` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id_job`, `name`, `min_salary`, `number`, `id_document`) VALUES
(1, 'Programator', '2100', 3, 1),
(2, 'Tester', '1100', 1, 1),
(3, 'Project Manager', '1500', 2, 1),
(6, 'Android Developer', '3000', 0, 1),
(5, 'Consultant', '750', 0, 1),
(4, 'Concediu', '0', 0, 1),
(7, 'Training', '0', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `jobs_history`
--

CREATE TABLE `jobs_history` (
  `id_job_history` int(11) NOT NULL,
  `id_job` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_department` int(11) NOT NULL,
  `status` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `jobs_history`
--

INSERT INTO `jobs_history` (`id_job_history`, `id_job`, `start_date`, `end_date`, `id_employee`, `id_department`, `status`) VALUES
(0, 4, '2017-05-01', '2017-05-02', 13, 4, 'vacation'),
(1, 6, '2017-05-08', '2017-05-15', 14, 1, 'working'),
(2, 6, '2017-05-16', '2017-05-19', 14, 1, 'working'),
(3, 6, '2017-04-03', '2017-05-26', 14, 1, 'working');

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `id_request` int(11) NOT NULL,
  `id_document` int(11) NOT NULL,
  `Status` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `date_approval` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`id_request`, `id_document`, `Status`, `date_approval`) VALUES
(4, 4, 'pending', '2000-00-00'),
(3, 3, 'rejected', '2017-05-22'),
(2, 2, 'pending', '2000-00-00'),
(1, 1, 'approved', '2017-05-21');

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
-- Indexes for table `document_types`
--
ALTER TABLE `document_types`
  ADD PRIMARY KEY (`id_doctype`);

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id_document`),
  ADD KEY `fk_documents_employees` (`id_employee`),
  ADD KEY `fk_documents_doctype` (`id_doctype`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id_employee`),
  ADD KEY `FK_Department_employee` (`id_department`),
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
  ADD PRIMARY KEY (`id_job_history`),
  ADD KEY `fk_emp_jobsh` (`id_employee`),
  ADD KEY `fk_dep_jobsh` (`id_department`),
  ADD KEY `id_job` (`id_job`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`id_request`);
--
-- Database: `premium-cms`
--
CREATE DATABASE IF NOT EXISTS `premium-cms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `premium-cms`;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id_event` int(11) NOT NULL,
  `d_abstract` date DEFAULT NULL,
  `d_evaluation` date DEFAULT NULL,
  `d_proposal` date DEFAULT NULL,
  `d_taxes` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end` date DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `web_page` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `eventsesiune`
--

CREATE TABLE `eventsesiune` (
  `id` int(11) NOT NULL,
  `event_id_event` int(11) DEFAULT NULL,
  `sesiune_id_session` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(250),
(250),
(250),
(250),
(250),
(250),
(250),
(250),
(250),
(250),
(250);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id_payment` int(11) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pcevent`
--

CREATE TABLE `pcevent` (
  `id` int(11) NOT NULL,
  `functie` varchar(255) DEFAULT NULL,
  `event_id_event` int(11) DEFAULT NULL,
  `user_id_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pcproposal`
--

CREATE TABLE `pcproposal` (
  `id` int(11) NOT NULL,
  `bid` int(11) DEFAULT NULL,
  `nota` int(11) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `proposal_id_proposal` int(11) DEFAULT NULL,
  `user_id_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pcproposal`
--

INSERT INTO `pcproposal` (`id`, `bid`, `nota`, `review`, `proposal_id_proposal`, `user_id_user`) VALUES
(236, 0, 0, NULL, 8, 6),
(237, 0, 0, NULL, 8, 7),
(238, 0, 0, NULL, 9, 6),
(239, 0, 0, NULL, 9, 7),
(240, 0, 0, NULL, 10, 6),
(241, 0, 0, NULL, 10, 7),
(242, 0, 0, NULL, 11, 6),
(243, 0, 0, NULL, 11, 7),
(244, 0, 0, NULL, 12, 6),
(245, 0, 0, NULL, 12, 7),
(246, 0, 0, NULL, 13, 6),
(247, 0, 0, NULL, 13, 7),
(248, 0, 0, NULL, 10, 1),
(249, 0, 0, NULL, 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `presentation`
--

CREATE TABLE `presentation` (
  `id_presentation` int(11) NOT NULL,
  `demo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `speaker_id_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `proposal`
--

CREATE TABLE `proposal` (
  `id_proposal` int(11) NOT NULL,
  `abs` varchar(255) DEFAULT NULL,
  `accept_date` date DEFAULT NULL,
  `document` varchar(255) DEFAULT NULL,
  `id_author` int(11) DEFAULT NULL,
  `id_session` int(11) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `other_authors` varchar(255) DEFAULT NULL,
  `send_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `topics` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proposal`
--

INSERT INTO `proposal` (`id_proposal`, `abs`, `accept_date`, `document`, `id_author`, `id_session`, `keywords`, `name`, `other_authors`, `send_date`, `status`, `topics`, `type`) VALUES
(8, NULL, NULL, NULL, 5, 1, 'a', 'a', 'a', '2017-05-18', 'pending', 'a', ''),
(9, NULL, NULL, NULL, 5, 1, 'b', 'b', 'b', '2017-05-18', 'pending', 'b', ''),
(10, NULL, NULL, NULL, 5, 1, 'c', 'c', 'c', '2017-05-18', 'pending', 'c', ''),
(11, NULL, NULL, NULL, 4, 1, 'd', 'd', 'd', '2017-05-18', 'pending', 'd', ''),
(12, NULL, NULL, NULL, 4, 1, 'e', 'e', 'e', '2017-05-18', 'pending', 'e', ''),
(13, NULL, NULL, NULL, 4, 1, 'f', 'f', 'f', '2017-05-18', 'pending', 'f', '');

-- --------------------------------------------------------

--
-- Table structure for table `proposal_user`
--

CREATE TABLE `proposal_user` (
  `Proposal_id_proposal` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id_room` int(11) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nr_sits` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sesiune`
--

CREATE TABLE `sesiune` (
  `id_session` int(11) NOT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `president` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesiune`
--

INSERT INTO `sesiune` (`id_session`, `date_in`, `date_out`, `id_room`, `name`, `president`) VALUES
(1, NULL, NULL, 0, 'Android', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sesiune_presentation`
--

CREATE TABLE `sesiune_presentation` (
  `Sesiune_id_session` int(11) NOT NULL,
  `presentations_id_presentation` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sesiune_proposal`
--

CREATE TABLE `sesiune_proposal` (
  `Sesiune_id_session` int(11) NOT NULL,
  `proposals_id_proposal` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sesiune_room`
--

CREATE TABLE `sesiune_room` (
  `Sesiune_id_session` int(11) NOT NULL,
  `rooms_id_room` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `affiliation` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `webPage` varchar(255) DEFAULT NULL,
  `payment_id_payment` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `affiliation`, `country`, `email`, `firstName`, `lastName`, `password`, `phone`, `status`, `type`, `userName`, `webPage`, `payment_id_payment`) VALUES
(1, NULL, NULL, 'admin@ubb.ro', 'Ionut', 'Incau', '123456', NULL, 'approved', 'admin', 'admin', NULL, NULL),
(2, NULL, NULL, 'author@ubb.ro', 'Ionut', 'Incau', '123456', NULL, 'approved', 'author', 'author', NULL, NULL),
(4, '', '', 'y', 'y', 'y', '123456', '', 'approved', 'author', 'y', '', NULL),
(5, '', '', 'x', 'x', 'x', '123456', '', 'approved', 'author', 'x', '', NULL),
(6, '', '', 'm', 'm', 'm', '123456', '', 'approved', 'pc', 'm', '', NULL),
(7, '', '', 'n', 'n', 'n', '123456', '', 'approved', 'pc', 'n', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `usersesiune`
--

CREATE TABLE `usersesiune` (
  `id` int(11) NOT NULL,
  `functie` varchar(255) DEFAULT NULL,
  `sesiune_id_session` int(11) DEFAULT NULL,
  `user_id_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id_event`);

--
-- Indexes for table `eventsesiune`
--
ALTER TABLE `eventsesiune`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK14csj259sqlk2c8inw1ri48l6` (`event_id_event`),
  ADD KEY `FK7jbeuxugtcfuofg6t3dvjqefe` (`sesiune_id_session`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id_payment`);

--
-- Indexes for table `pcevent`
--
ALTER TABLE `pcevent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaffmv6yrd4xqv70rlh6l81h0q` (`event_id_event`),
  ADD KEY `FKjyt0a9yfi26aj9nqb4qc7hb8k` (`user_id_user`);

--
-- Indexes for table `pcproposal`
--
ALTER TABLE `pcproposal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsf6m4k10mgrthunfh5ewv0x7t` (`proposal_id_proposal`),
  ADD KEY `FKa584f5mqwqsg92apsltgqdpm7` (`user_id_user`);

--
-- Indexes for table `presentation`
--
ALTER TABLE `presentation`
  ADD PRIMARY KEY (`id_presentation`),
  ADD KEY `FKcuxnymqfu5x4ie7ugmukosq70` (`speaker_id_user`);

--
-- Indexes for table `proposal`
--
ALTER TABLE `proposal`
  ADD PRIMARY KEY (`id_proposal`);

--
-- Indexes for table `proposal_user`
--
ALTER TABLE `proposal_user`
  ADD UNIQUE KEY `UK_8q3u6g2k2nj8i9raxnepc87wo` (`users_id_user`),
  ADD KEY `FKkdnfr1g7a8evb6xboo92imk5b` (`Proposal_id_proposal`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id_room`);

--
-- Indexes for table `sesiune`
--
ALTER TABLE `sesiune`
  ADD PRIMARY KEY (`id_session`);

--
-- Indexes for table `sesiune_presentation`
--
ALTER TABLE `sesiune_presentation`
  ADD UNIQUE KEY `UK_reu3w8l6q6vguasc3ntnjjjr2` (`presentations_id_presentation`),
  ADD KEY `FKkho0hwj9bto3xcabclwnjvyv5` (`Sesiune_id_session`);

--
-- Indexes for table `sesiune_proposal`
--
ALTER TABLE `sesiune_proposal`
  ADD UNIQUE KEY `UK_1w6ro76gdxyknj5255j5f1u65` (`proposals_id_proposal`),
  ADD KEY `FKkfwkf1utf429yv7v563yljhxt` (`Sesiune_id_session`);

--
-- Indexes for table `sesiune_room`
--
ALTER TABLE `sesiune_room`
  ADD UNIQUE KEY `UK_l63qlwfp67egwiw6dft9qhr3v` (`rooms_id_room`),
  ADD KEY `FKa43p0k4msxa0a1slpfp3fxuuf` (`Sesiune_id_session`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FK634su96mptojuthxfl5m70qje` (`payment_id_payment`);

--
-- Indexes for table `usersesiune`
--
ALTER TABLE `usersesiune`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt85kyogw0jlndhsb6tip1h6c1` (`sesiune_id_session`),
  ADD KEY `FKagsk7fojfcop7eded8npfjblx` (`user_id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
