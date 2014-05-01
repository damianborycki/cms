SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `portal_db`
--
CREATE DATABASE IF NOT EXISTS `portal_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `portal_db`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`id`, `name`, `description`) VALUES
(1, 'admin', 'Administrator'),
(2, 'moderator', 'Moderator tre?ci zamieszczanych w serwisie'),
(3, 'user', 'U?ytkownik serwisu');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(120) NOT NULL,
  `name` varchar(120) DEFAULT '',
  `surname` varchar(120) DEFAULT '',
  `city` varchar(120) DEFAULT '',
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_login_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gender` varchar(120) DEFAULT '',
  `group` bigint(20) NOT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  `info` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `group` (`group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `email`, `name`, `surname`, `city`, `registration_date`, `last_login_date`, `gender`, `group`, `avatar`, `info`) VALUES
(1, 'admin', '123456', '', '', '', '', '2014-05-01 10:27:47', '0000-00-00 00:00:00', '', 1, NULL, ''),
(2, 'mod', '123456', '', '', '', '', '2014-05-01 11:49:17', '0000-00-00 00:00:00', '', 2, NULL, ''),
(3, 'user', '123456', '', '', '', '', '2014-05-01 11:49:17', '0000-00-00 00:00:00', '', 3, NULL, '');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`group`) REFERENCES `groups` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
