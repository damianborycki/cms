SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `portal_db`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_owner` varchar(255) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `expiration_date` datetime NOT NULL,
  `galery` bigint(20) DEFAULT NULL,
  `image` bigint(20) NOT NULL,
  `publication_date` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `views` bigint(20) NOT NULL DEFAULT '0',
  `category_id` bigint(20) NOT NULL,
  `rank` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKB6C0D23DE128050` (`category_id`),
  KEY `FKB6C0D23DFECE0F58` (`rank`),
  KEY `FKB6C0D23D148209EC` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `article_rank`
--

CREATE TABLE IF NOT EXISTS `article_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `weight` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `weight` (`weight`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4D47461C7714DCFE` (`parent`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `number_of_responses` bigint(20) NOT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `state` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKDC17DDF4787B6BE4` (`article_id`),
  KEY `FKDC17DDF44E5A4519` (`parent_id`),
  KEY `FKDC17DDF4220D4A59` (`state`),
  KEY `FKDC17DDF4148209EC` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `comment_state`
--

CREATE TABLE IF NOT EXISTS `comment_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(600) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `gallery`
--

CREATE TABLE IF NOT EXISTS `gallery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `add_datetime` date NOT NULL,
  `add_usr` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(600) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `id` varchar(255) NOT NULL,
  `add_datetime` date NOT NULL,
  `add_usr` bigint(20) NOT NULL,
  `app_datetime` date DEFAULT NULL,
  `app_usr` bigint(20) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `height` bigint(20) NOT NULL,
  `link` varchar(255) NOT NULL,
  `type` varchar(10) NOT NULL,
  `width` bigint(20) NOT NULL,
  PRIMARY KEY (`id`, `width`, `height`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` bigint(20) NOT NULL,
  `tag` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK1BF9A6EA8327A` (`tag`),
  KEY `FK1BF9A3E309B18` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tag_type`
--

CREATE TABLE IF NOT EXISTS `tag_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` bigint(20) DEFAULT NULL,
  `city` varchar(120) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `registration_date` datetime NOT NULL,
  `email` varchar(120) NOT NULL,
  `gender` varchar(120) DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `name` varchar(120) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `surname` varchar(120) DEFAULT NULL,
  `user_group` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `FK6A68E08E6FC0FF4` (`user_group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `FKB6C0D23D148209EC` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKB6C0D23DE128050` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FKB6C0D23DFECE0F58` FOREIGN KEY (`rank`) REFERENCES `article_rank` (`id`);

--
-- Ograniczenia dla tabeli `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `FK4D47461C7714DCFE` FOREIGN KEY (`parent`) REFERENCES `categories` (`id`);

--
-- Ograniczenia dla tabeli `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FKDC17DDF4148209EC` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKDC17DDF4220D4A59` FOREIGN KEY (`state`) REFERENCES `comment_state` (`id`),
  ADD CONSTRAINT `FKDC17DDF44E5A4519` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`),
  ADD CONSTRAINT `FKDC17DDF4787B6BE4` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`);

--
-- Ograniczenia dla tabeli `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `FK1BF9A3E309B18` FOREIGN KEY (`type`) REFERENCES `tag_type` (`id`),
  ADD CONSTRAINT `FK1BF9A6EA8327A` FOREIGN KEY (`tag`) REFERENCES `articles` (`id`);

--
-- Ograniczenia dla tabeli `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK6A68E08E6FC0FF4` FOREIGN KEY (`user_group`) REFERENCES `groups` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
