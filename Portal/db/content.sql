INSERT INTO `groups` (`id`, `description`, `name`) VALUES
(1, 'Administrator serwisu', 'admin'),
(2, 'Moderator serwisu', 'moderator'),
(3, 'U?ytkownik serwisu', 'user');

INSERT INTO `comment_state` (`id`, `description`, `name`) VALUES
(1, 'Oczekuj?cy', 'pending'),
(2, 'Zaakceptowany', 'accepted'),
(3, 'Odrzucony', 'rejected');

INSERT INTO `article_rank` (`id`, `description`, `name`, `weight`) VALUES
(1, 'Wa?ne, bardzo wa?ne', 'Wa?ne', 50),
(2, 'Najnowsze', 'Nowe', 40);

INSERT INTO `categories` (`id`, `description`, `name`, `parent`) VALUES
(1, 'Sport i rekreacja', 'Sport', NULL),
(2, 'Biznes i piniondze', 'Biznes', NULL);

INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(1, NULL, 'Artyku? o sporcie', '2014-06-18 00:00:00', '', '0000-00-00 00:00:00', NULL, 0, '0000-00-00 00:00:00', '', 0, 1, 1, 1);

INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(1, 'Lubie myszy', '2014-06-21 12:24:21', 0, 1, NULL, 1, 2),
(2, 'Siema, fajny komentarz', '2014-06-21 12:42:07', 0, 1, 1, 1, 1),
(3, 'Komentuje', '2014-06-21 12:42:51', 0, 1, 2, 1, 1);

INSERT INTO `users` (`id`, `avatar`, `city`, `last_login_date`, `registration_date`, `email`, `gender`, `info`, `login`, `name`, `password`, `surname`, `user_group`) VALUES
(1, NULL, '', NULL, '0000-00-00 00:00:00', '', '', 'M', 'admin', '', 'e10adc3949ba59abbe56e057f20f883e', '', 1),
(2, NULL, '', NULL, '0000-00-00 00:00:00', '', '', 'M', 'mod', '', 'e10adc3949ba59abbe56e057f20f883e', '', 2),
(3, NULL, '', NULL, '0000-00-00 00:00:00', '', '', 'K', 'user', '', 'e10adc3949ba59abbe56e057f20f883e', '', 3),
(4, NULL, '', '0000-00-00 00:00:00', '2014-06-17 04:24:35', 'test@gmail.com', 'M', NULL, 'testowicz', 'Adam', 'e10adc3949ba59abbe56e057f20f883e', 'Nowak', 3),
(6, NULL, '', '0000-00-00 00:00:00', '2014-06-03 06:44:32', 'user2@o2.pl', 'K', NULL, 'user2', 'Userowicz', 'e10adc3949ba59abbe56e057f20f883e', 'Obama', 3),
(7, NULL, '', '0000-00-00 00:00:00', '2014-06-26 00:00:00', 'email@uj.edu.pl', 'M', NULL, 'login', 'Jan', 'e10adc3949ba59abbe56e057f20f883e', 'Nowak', 2),
(8, NULL, '', '0000-00-00 00:00:00', '2014-05-01 19:40:23', 'mail@uj.edu.pl', 'M', NULL, 'login2', 'Mario', 'e10adc3949ba59abbe56e057f20f883e', 'Bros', 3),
(9, NULL, '', '2014-06-25 06:29:32', '2014-07-22 20:13:31', 'john@email.com', 'M', NULL, 'johnny', 'John', 'e10adc3949ba59abbe56e057f20f883e', 'Dorian', 3),
(10, NULL, '', '2014-06-11 13:22:19', '2014-06-03 10:14:33', 'emilia@mail.com', 'K', NULL, 'emilia', 'Emilia', 'e10adc3949ba59abbe56e057f20f883e', 'Terefere', 1);