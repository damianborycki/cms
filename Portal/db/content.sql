INSERT INTO `groups` (`id`, `description`, `name`) VALUES
(1, 'Administrator serwisu', 'admin'),
(2, 'Moderator serwisu', 'moderator'),
(3, 'Użytkownik serwisu', 'user');

INSERT INTO `comment_state` (`id`, `description`, `name`) VALUES
(1, 'Oczekujący', 'pending'),
(2, 'Zaakceptowany', 'accepted'),
(3, 'Odrzucony', 'rejected');

INSERT INTO `article_rank` (`id`, `description`, `name`, `weight`) VALUES
(1, 'Ważne, bardzo ważne', 'Ważne', 50),
(2, 'Najnowsze', 'Nowe', 40);

INSERT INTO `categories` (`id`, `description`, `name`, `parent`) VALUES
(1, 'Sport i rekreacja', 'Sport', NULL),
(2, 'Biznes i piniondze', 'Biznes', NULL),
(3, 'Studia', 'Studiowanie na UJ', NULL),
(4, 'Mieszkanie', 'Sprawy mieszkaniowe', NULL),
(5, 'Socjal', 'Stypendia, akademiki itd.', NULL),
(6, 'Kultura', 'Wydarzenia kulturalne', NULL),
(7, 'Rozrywka', 'Rozrywka, zabawa, czas wolny', NULL),
(13, 'Studia WFAIS', 'Studiowanie na WFAIS UJ', 3);

INSERT INTO `users` (`id`, `avatar`, `city`, `last_login_date`, `registration_date`, `email`, `gender`, `info`, `login`, `name`, `password`, `surname`, `user_group`) VALUES
( 1, NULL, 'Kraków', '2014-04-01 00:00:00', '2014-04-01 00:00:00', 'admin@ebiznes.pl', 'M', 'admin', 'admin', 'Admin', 'e10adc3949ba59abbe56e057f20f883e', 'Kowalski', 1),
( 2, NULL, 'Kraków', '2014-05-02 00:00:00', '2014-05-02 00:00:00', 'mod@ebiznes.pl', 'M', 'mod', 'mod', 'Mod', 'e10adc3949ba59abbe56e057f20f883e', 'Kwiatkowski', 2),
( 3, NULL, 'Kraków', '2014-06-03 00:00:00', '2014-06-03 00:00:00', 'user@ebiznes.pl', 'K', 'user', 'user', 'User', 'e10adc3949ba59abbe56e057f20f883e', 'Kot', 3),
(11, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'mike.adamczyk@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'mike.adamczyk', 'Michał', 'e10adc3949ba59abbe56e057f20f883e', 'Adamczyk', 1),
(12, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'mateusz.bogus@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'mateusz.bogus ', 'Mateusz', 'e10adc3949ba59abbe56e057f20f883e', 'Boguś', 1),
(13, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'kamil.brozyc@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'kamil.brozyc', 'Kamil', 'e10adc3949ba59abbe56e057f20f883e', 'Brożyc', 1),
(14, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'slawek.chyla@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'slawek.chyla', 'Sławomir', 'e10adc3949ba59abbe56e057f20f883e', 'Chyła', 1),
(15, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'bernard.czypionka@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'bernard.czypionka', 'Bernard', 'e10adc3949ba59abbe56e057f20f883e', 'Czypionka', 1),
(16, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'weronika.dragula@gmail.com', 'K', 'IS, WFAIS, UJ', 'weronika.dragula', 'Weronika', 'e10adc3949ba59abbe56e057f20f883e', 'Draguła', 1),
(17, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'tomegru@gmail.com', 'M', 'IS, WFAIS, UJ', 'tomegru', 'Tomasz', 'e10adc3949ba59abbe56e057f20f883e', 'Gruntowski', 1),
(18, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'm4tius@gmail.com', 'M', 'IS, WFAIS, UJ', 'm4tius', 'Mateusz', 'e10adc3949ba59abbe56e057f20f883e', 'Jancarz', 1),
(19, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'lucas.jasek@gmail.com', 'M', 'IS, WFAIS, UJ', 'lucas.jasek', 'Łukasz', 'e10adc3949ba59abbe56e057f20f883e', 'Jasek', 1),
(10, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'kinga.juszka', 'K', 'IS, WFAIS, UJ', 'kinga.juszka', 'Kinga', 'e10adc3949ba59abbe56e057f20f883e', 'Juszka', 1),
(21, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'jaroslaw.kasza@gmail.com', 'M', 'IS, WFAIS, UJ', 'jaroslaw.kasza', 'Jarosław', 'e10adc3949ba59abbe56e057f20f883e', 'Kasza', 1),
(22, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'karo.kir@gmail.com', 'K', 'IS, WFAIS, UJ', 'karolina.kiryjczuk', 'Karolina', 'e10adc3949ba59abbe56e057f20f883e', 'Kiryjczuk', 1),
(23, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'a.konieczny@uj.edu.pl', 'K', 'IS, WFAIS, UJ', 'a.konieczny', 'Anna', 'e10adc3949ba59abbe56e057f20f883e', 'Konieczny', 1),
(24, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'justyna.kubisztal@uj.edu.pl', 'K', 'IS, WFAIS, UJ', 'justyna.kubisztal', 'Justyna', 'e10adc3949ba59abbe56e057f20f883e', 'Kubisztal', 1),
(25, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'rafal.linca@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'rafal.linca', 'Rafał', 'e10adc3949ba59abbe56e057f20f883e', 'Linca', 1),
(26, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'damian.lipka@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'damian.lipka', 'Damian', 'e10adc3949ba59abbe56e057f20f883e', 'Lipka', 1),
(27, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'michal.ladanowski@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'michal.ladanowski', 'Michał', 'e10adc3949ba59abbe56e057f20f883e', 'Ładanowski', 1),
(28, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'm.malek@uj.edu.pl', 'K', 'IS, WFAIS, UJ', 'm.malek@uj', 'Magdalena', 'e10adc3949ba59abbe56e057f20f883e', 'Małek', 1),
(29, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'bartosz.marcisz@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'bartosz.marcisz', 'Bartosz', 'e10adc3949ba59abbe56e057f20f883e', 'Marcisz', 1),
(30, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'Katerina.nowak@uj.edu.pl', 'K', 'IS, WFAIS, UJ', 'Katerina.nowak', 'Katarzyna', 'e10adc3949ba59abbe56e057f20f883e', 'Nowak', 1),
(31, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'maciej.noworyta@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'maciej.noworyta', 'Maciej', 'e10adc3949ba59abbe56e057f20f883e', 'Noworyta', 1),
(32, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'michal.pachel@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'michal.pachel', 'Michał', 'e10adc3949ba59abbe56e057f20f883e', 'Pachel', 1),
(33, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'maciej.paluszek@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'maciej.paluszek', 'Maciej', 'e10adc3949ba59abbe56e057f20f883e', 'Paluszek', 1),
(34, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'mateusz.piekarczyk@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'mateusz.piekarczyk', 'Mateusz', 'e10adc3949ba59abbe56e057f20f883e', 'Piekarczyk', 1),
(35, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'marcin.radlak@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'marcin.radlak', 'Marcin', 'e10adc3949ba59abbe56e057f20f883e', 'Radlak', 1),
(36, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'sylwia.szklarek@uj.edu.pl', 'K', 'IS, WFAIS, UJ', 'sylwia.szklarek', 'Sylwia', 'e10adc3949ba59abbe56e057f20f883e', 'Szklarek', 1),
(37, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'adrian.szumski@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'adrian.szumski', 'Adrian', 'e10adc3949ba59abbe56e057f20f883e', 'Szumski', 1),
(38, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'dawid.tomsia@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'dawid.tomsia', 'Dawid', 'e10adc3949ba59abbe56e057f20f883e', 'Tomsia', 1),
(39, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'p.wilk@ibk.pl', 'M', 'IS, WFAIS, UJ', 'p.wilk@', 'Piotr', 'e10adc3949ba59abbe56e057f20f883e', 'Wilk', 1),
(40, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'adam.winiarczyk@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'adam.winiarczyk', 'Adam', 'e10adc3949ba59abbe56e057f20f883e', 'Winiarczyk', 1),
(41, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'konrad.winiarski@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'konrad.winiarski', 'Konrad', 'e10adc3949ba59abbe56e057f20f883e', 'Winiarski', 1),
(42, NULL, 'Kraków', '2014-06-25 06:29:32', '2014-06-02 20:13:31', 'szymon.wojtowicz@uj.edu.pl', 'M', 'IS, WFAIS, UJ', 'szymon.wojtowicz', 'Szymon', 'e10adc3949ba59abbe56e057f20f883e', 'Wojtowicz', 1);

/* artykuł 1 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(1, 22, '
16 czerwca 2014 r. odbył się "Bieg na Kampus". Odległość dzieląca obecny i przyszły Instytut Fizyki została wytyczona wzdłuż Wisły.

Wyniki „Biegu na Kampus" - dystans 5 km

Wśród kobiet zwyciężyła Kiryjczuk Karolina (24:35), z panów nalepszy był (Piróg Michał 20:32).

Pełna lista:
Kobiety:
Kiryjczuk Karolina 24:35
Nałódka Małgorzata 31:00
Mężczyźni:
Piróg Michał 20:32
Mach Patryk 20:45
Sacha Krzysztof 20:52
Rostworowski Andrzej 22:30
 Stabrawa Artur 23:23
Pięta Tomasz 23:50
Hadasz Leszek 24:05
Maliborski Maciej 24:06
Kosior Arkadiusz 24:34
Marcinek Antoni 26:38
Chmielnicki Wiesław 26:51
Marcinek Roman 27:04
Legutko Andrzej 27:07
Ciesielski Adam 30:52
Sędziowie:
Mateusz Łącki, Jan Major, Małgorzata Mochol
', '2014-06-16 00:00:00', 'Wydziałowy Bieg na Kampus', '2014-07-16 00:00:00', NULL, 0, '2014-06-16 00:00:00', 'Bieg na Kampus', 0, 1, 1, 22);

/* komentarze do artykułu 1 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(1, 'Gratulacje!', '2014-06-21 12:24:21', 0, 1, NULL, 2, 11),
(2, 'A mnie tam nie bylo...', '2014-06-22 12:42:07', 1, 1, NULL, 2, 12),
(3, 'Mnie tez nie.', '2014-06-21 12:42:51', 0, 1, 2, 2, 13);

/* artykuł 2 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(2, 23, '
Lubisz poznawać nowych ludzi? Chciałbyś mieć przyjaciół wśród studentów z całego świata? Nie musisz w tym celu wyjeżdżać zagranicę. Wystarczy, że jesteś osobą otwartą i znasz jakiś język obcy w stopniu komunikatywnym, a możesz świetnie się zabawić z erazmusami – studentami, którzy właśnie Polskę wybrali jako kraj, w którym przez semestr albo dwa będą studiować.

Erazmus, czyli kto?
„Erazmus” to potocznie student, który w ramach międzynarodowego europejskiego programu wymiany studentów Erasmus postanowił pewien okres swojej nauki spędzić, studiując na innej uczelni poza granicami własnego kraju. Do Polski na studia przyjeżdżają żacy z całej Europy, nawet z Islandii czy Turcji. Uczą się na różnych kierunkach: od prawa, przez rozmaite filologie, kierunki techniczne, aż po medycynę. Z reguły są to osoby otwarte, niepospolite, poszukujące nowych wrażeń i doświadczeń, pragnące poznać interesujących ludzi.
Dlaczego właśnie u nas?
Powody, dla których wybierają właśnie Polskę są różnorodne: niezwykle często przyjeżdżają osoby, które mają z naszym krajem coś wspólnego – któreś z rodziców albo dziadek lub babcia pochodzi z Polski, może nawet sam erazmus urodził się w Polsce, ale wychowywał się w innym państwie. Motywację dla niektórych stanowi wysoki poziom znanych polskich uczelni, z których wiele cieszy się w Europie zasłużonym szacunkiem. Bywa że przyczyną takiego, a nie innego wyboru jest chęć zobaczenia tego egzotycznego kraju w środku cywilizowanej Europy, w którym podobno po ulicach wciąż biegają niedźwiedzie...
„Bardzo bawiły mnie pytania typu: czy można normalnie oddychać, gdy jest -15C; czy wychodzicie wtedy normalnie z domów?” – śmieje się Anna Laudy z Erasmus Student Network przy Uniwersytecie Warszawskim. – „Teraz po tak srogim styczniu Hiszpanie przywykli do minusowych temperatur. Niestety nadal istnieje niezbyt korzystny stereotyp Polski jako kraju mało rozwiniętego lub nadal uzależnionego od Rosji. Większość znajomych moich Hiszpanów dziwiła się, dlaczego wybrali to państwo: <<Polska! Tam tak zimno, to jak Rosja!>>. Na szczęście to może się zmienić dzięki erazmusom, którzy podczas stypendium poznają tę prawdziwa Polskę”.
Kim jest mentor?
Mentor to student uczelni, na której będzie się uczył erazmus i osoba która ma się stać swojego rodzaju przewodnikiem dla studenta zza granicy. „Podstawowym zadaniem każdego mentora jest wprowadzenie erazmusa w życie akademickie” – pisze Michał Paluchowski z ESN na Uniwersytecie Jagiellońskim w Krakowie w swoim liście do kandydatów na mentorów. – „Oznacza to, że dostaniesz jego maila, kontaktujesz się z nim, ustalacie datę przyjazdu, odbierasz go z dworca/lotniska, prowadzisz do miejsca zamieszkania oraz biura Erasmus Student Network. Dobrze jest także pokazać mu jego wydział, gdzie ma się uczyć i przedstawić koordynatora programu Erasmus z danego wydziału. Być może obowiązkiem mentorów będzie również pomoc w znalezieniu mieszkania dla podopiecznych, z naszym wsparciem. Tyle podstawowych obowiązków. Reszta to już Wasza obopólna inicjatywa, możecie połazić po knajpach, możesz jemu/jej pokazać miasto, zwiedzić muzea, galerie, iść na imprezy – szczególnie te organizowane przez ESN, a będzie ich wiele!”.
„Latem zeszłego roku okazało się że mój planowany przez dłuższy czas wyjazd do Francji nie dojdzie do skutku” – mówi Marta Wąsowska, członek warszawskiego ESN. „Wówczas na stronie UW znalazłam ogłoszenie dotyczące programu Mentor. Dwa zdania szczególnie na mnie podziałały, brzmiały mniej więcej tak: <<Nie możesz wyjechać za granicę? Zrób sobie zagranicę w Polsce!>> I tak zostałam mentorem”.
„Minęły już dwa lata odkąd sama wyjechałam na stypendium w ramach Erasmusa” – relacjonuje Iwona Borowska, Vice-Przewodnicząca ESN przy Uniwersytecie Łódzkim. – „Mediolan jest piękny, ale trudny dla obcokrajowców, którzy jeszcze się nie orientują co, gdzie i jak. Niestety, wiele rzeczy musiałam sama sobie załatwiać i dowiadywać się, bowiem nie miałam nikogo, kto by mi pomógł przynajmniej na początku mojej avventura italiana. Chociaż istnieje tam ESN Milano, to nie realizuje bądź nie realizował za moich czasów programu Mentor”.
Przyjemność – owszem, ale także zobowiązanie
Jeżeli więc chciałbyś zabawić się w przewodnika i mentora, to przede wszystkim zastanów się, czy aby na pewno będziesz w stanie podołać obowiązkom. Zdarzają się bowiem sytuacje nieprzewidywalne. Przekonał się o tym Michał Molka z Uniwersytetu Warszawskiego. „W tym roku zostałem mentorem dwóch studentek: Marty z Węgier i Niny ze Słowenii. Okazało się, że Marta przyjeżdżała tego samego dnia i o tej samej godzinie co Nina, z tym że ta pierwsza na Dworzec Centralny druga na Okęcie... Szczęście Marta przyjeżdżała z kolegą i on miał swoją mentorkę”.
„Mentorowanie to nie tylko przyjemność, ale także odpowiedzialność” – opowiada Marta Dąbrowska z Uniwersytetu Łódzkiego. – „Miałam okazję przekonać się o tym bardzo szybko kiedy Sophie, Niemka którą się opiekuję, złamała nogę. Wtedy była jej bardzo potrzebna osoba, która mówi po polsku i zna polskie realia”.
Jednak nie wszyscy mentorzy mają tyle pracy. „Spotkanie z moim pierwszym erazmusem, Włochem, niezupełnie odpowiadało moim wyobrażeniom” – mówi dalej Marta z UW. –  „Jak się okazało, Dante wcale nie szukał mentora – po prostu znajomy, który pomagał mu załatwiać formalności przed wyjazdem, pomyłkowo zgłosił go do programu. Pomogłam mu w paru standardowych sprawach, po czym nasz kontakt się urwał. Na szczęście później zostałam opiekunką dwóch wspaniałych Finek. Teraz jednak wiem, że nigdy nie można zakładać, że mentor i erazmus zostaną parą przyjaciół. Ludzie są różni. Bardzo często zdarza się, że po jakimś czasie mentor „mentoruje” i spędza czas z innymi, nie przydzielonymi mu w ramach programu erazmusami. Tak też było i w moim przypadku”.
Jeżeli uważasz, że podołasz zadaniu i chcesz zostać mentorem, to zastanów się w jakich językach mógłbyś się komunikować. Pamiętaj o tym słowie: „komunikować” – nie musisz być naturalnie biegły, ale na jakimś poziomie powinieneś się porozumiewać. Zdarzają się oczywiście erazmusi, którzy bardzo dobrze mówią po Polsku, jednak nawet wtedy warto wykazać się przynajmniej jako taką znajomością angielskiego, gdyż jest on podstawowym językiem podczas spotkań w liczniejszych grupach. Masz również pewien wpływ na to, kim będzie „Twój” erazmus. „Dopuszczalne preferencje co do wyboru podopiecznego ograniczają się w zasadzie do płci, kraju pochodzenia i kierunku studiów – innych cech przyjeżdżających erazmusów sami nie znamy” – dodaje Michał Paluchowski.
Poznaj miasto i ludzi
Erasmus Student Network wykazuje dużą aktywność, jeśli chodzi o zapewnienie rozrywki erazmusom i mentorom. W Krakowie najważniejszą trwającą aż siedem dni imprezą jest zapoznawczy „Orientation week”, czyli pełny tydzień rozrywek dla ciała i ducha, które mają wciągnąć erazmusów w życie akademickie. Niedługo po rozpoczęciu nowego semestru studenci UJ z Polski i ich podopieczni spotykają się wszyscy razem i wyruszają odkrywać Kraków. „To był zwariowany tydzień” – opowiada Natalia. – „Pierwszego wieczoru każdy z nas, mentorów dostał pod opiekę grupkę erazmusów. Oni z kolei dostali od nas wcześniej przygotowane zdjęcia różnych budowli, pomników itp. znajdujących się w obrębie Starego Miasta. Ich zadaniem było znaleźć te miejsca i zaznaczyć na planie – my nie mogliśmy im pomagać, mieliśmy jedynie służyć za tłumaczy i pilnować, żeby się nie zgubili. Dwa dni później mieliśmy podobną zabawę, tylko że tym razem obiektami poszukiwań nie były zabytki, ale puby – mieliśmy je zlokalizować i sprawdzić, czy dają tam dobre piwo” – śmieje się Natalia.
W Gdańsku pierwsza impreza integracyjną to Open Party. Organizowany jest również „bieg na orientację” połączony z poznawaniem miasta. „W trakcie „biegu” uczestnicy mają do wykonania różne zadania” – wyjaśnia Mateusz Radzimski z gdańskiego ESN. – „Mogą to być na przykład oświadczyny pierwszej napotkanej dziewczynie. Każda grupa erazmusów rywalizuje z innymi, zbierając jak najwięcej punktów, co dodatkowo wyzwala całą masę różnych niecodziennych pomysłów”.
Ale nie tylko początek pobytu erazmusów w Polsce obfituje w rozrywki. ESN stara się, jak może najlepiej, aby zapewnić studentom sporo atrakcji i okazji do wspólnego spędzania czasu. Organizowane są najróżniejsze warsztaty i wspólne wycieczki. Świetnym pomysłem są na przykład Warsztaty Językowe. To okazja dla Polaków i obcokrajowców na poznanie się i dyskusję na dowolny temat. Spotykają się w wyznaczonym klubie, wybierają stolik z określonym językiem i po prostu przyłączają się do dyskusji, które nie są moderowane. W ten sposób można poznać osoby z różnych krajów i ewentualnie zdecydować się na naukę języka w tandemie – Ty uczysz erazmusa polskiego, a on Ciebie swojego ojczystego języka.
Większe, ogólnopolskie projekty imprez dla erazmusów i mentorów koordynuje ESN Polska.
Dlaczego warto?
„Chciałam zostać mentorem przede wszystkim dlatego żeby mieć kontakt z językiem francuskim – nie taki książkowy, tylko żywy, naturalny” – wyjaśnia Małgorzata Stańczyk, członek ESN przy UŁ. – „Chciałam też poznać osobę z innego kraju, jej podejście do życia, wydarzeń na świecie, dowiedzieć się, jakie zwyczaje panują w jej kraju. Wszystko to dostałam”.
„Mentorem zostałem przypadkowo” – opowiada Michał, przyszły prawnik z Uniwersytetu Warszawskiego. – „Mój kolega z grupy, Marcin, był wtedy koordynatorem programu Erasmus na UW – zainteresował mnie, zachęcając tym, że będę mógł uzupełnić swoją kolekcję monet. Przemyślałem, co erazmusom może się wydać inne, dziwne i starałem się im pomagać. Po załatwieniu formalności,  gdy się już zaaklimatyzują mniej potrzebują nas jako mentorów, stajemy się bardziej znajomymi” – dodaje Michał. – „Dzięki nam, mentorom oraz dzięki wszystkim akcjom ESN erazmusi są lepiej zintegrowani między sobą (chcociaż może Hiszpanie i Włosi preferują bardziej integrację narodową, za to Skandynawowie, Niemcy – międzynarodową). Ogólnie trzeba w to trochę siebie włożyć, ale myślę że korzyści są obopólne: my zyskujemy kontakty, używamy języków, mamy poczucie że robimy coś pożytecznego. Erazmusom z kolei minimalizowany jest szok kulturowy (wiadomo, mniejszy jest dla Czechów, większy dla Niemców, a totalny dla przybyszów z Półwyspu Iberyjskiego czy Apenińskiego), uzyskują pomoc w sprawach najtrudniejszych i mają poczucie bezpieczeństwa”.
To się sprawdza!
	„Uważam ze idea programu Erasmus bez programu mentor jest niepełna” – mówi Maciek, student psychologii na UW – „ponieważ dopiero dzięki mentorom następuje pełna integracja. Erazmusi są wciągani w nasze życie i naprawdę czuję się jak byśmy byli wszyscy z jednego kraju. To niesamowite uczucie kiedy tworzy się w tobie nowa tożsamość – Europejczyk; nigdy wcześniej czegoś takiego nie doświadczyłem”.
	„Długie rozmowy na temat naszych krajów, różnic kulturowych, ale również o spojrzeniu na życie młodych obcokrajowców i Polaków to bezcenne doświadczenia” – dodaje Emilia Gajda z ESN UŁ. – „To zadziwiające jak wiele możemy się od siebie nauczyć. Przyjaźń nie zna granic”.
I rzeczywiście, instytucja mentora jak najbardziej się sprawdza. Potwierdzają to doświadczenia Marty z UW: „Tuż przed świętami Bożego Narodzenia spotkałam się z moim znajomym erazmusem na kawie” – opowiada Marta. –  „Powiedziałam, że pewnie cieszy się, że jedzie na święta do domu. Jego odpowiedź zupełnie mnie zaskoczyła. Powiedział <<Wiesz, momentami sam już nie wiem, czy jadę na święta do domu, czy z domu na święta wyjeżdżam>>. Wtedy pomyślałam, że chyba się udało – bo w końcu cała praca mentorów i innych działaczy ESN sprowadza się do tego, by erazmusi poczuli się jak u siebie w domu”.
Gdzie ich szukać?
	Sekcje ESN w Polsce znajdują się od Opola, przez Warszawę, po Gdańsk. Właśnie powstaje oficjalna strona Erasmus Student Network Polska: www.esn.pl. Dostępna jest już na niej lista wszystkich działających w Polsce sekcji. Strona dopiero się rozwija, ale można tam znaleźć także kontakt do przewodniczących oraz adresy i linki stron internetowych niektórych oddziałów ESN, a także wiele innych przydatnych informacji dotyczących programu Erasmus.
Jeżeli Twoja uczelnia nie oferuje współpracy ze studiującymi u nas obcokrajowcami, sam ich poszukaj. Zainteresuj się tym, gdzie można ich spotkać. Zaproponuj swoją pomoc i towarzystwo, wspólne zwiedzanie miasta i poznawanie życia studenckiego. Nie zawiedziesz się, a kto wie – może sam zapragniesz wyjechać i zostać erazmusem?


O Erasmusie

Program Erasmus powstał w 1987 roku. Głównym jego celem jest propagowanie i ułatwianie wymiany studentów miedzy uczelniami państw należących do Wspólnoty Europejskiej. Udział biorą w nim wszystkie kraje Unii Europejskiej, a także uczelnie z Turcji, Rumunii, Islandii, Lichtensteinu czy Bułgarii. W roku 1995 Erasmus stał się częścią programu Socrates, który wspiera międzynarodową współpracę w zakresie edukacji.
Najważniejszym zadaniem, jakie postawili przed sobą twórcy programu jest stałe podnoszenie poziomu kształcenia oraz utrwalanie jego europejskiego charakteru. Cele te realizowane są poprzez wymianę studentów i nauczycieli akademickich, wprowadzanie Europejskiego Systemu Punktów Transferowych (ECTS), opracowywanie i wspólne wprowadzanie w życie programów nauczania przez uczelnie stowarzyszone oraz prowadzenie zajęć przez międzynarodową grupę wykładowców dla międzynarodowej grupy studentów.
Polska przystąpiła do programu w roku akademickim 1998/1999 – wtedy 46 polskich szkół wyższych zawarło kontrakt z Komisją Europejską, obecnie jest ich już 187. W latach 1998 – 2004 w Erasmusie uczestniczyło 24 tysiące polskich studentów. W tym samym czasie do Polski przyjechało studiować ponad 4,5 tysiąca obcokrajowców.
', '2014-06-17 00:00:00', 'Studenci zagraniczni w Polsce', '2014-07-30 00:00:00', NULL, 0, '2014-06-19 00:00:00', 'Być mentorem', 0, 3, 1, 25);

/* komentarze do artykułu 2 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(4, 'Bzdury!!', '2014-06-21 12:24:21', 2, 2, NULL, 2, 14),
(5, 'A wcale, ze nie!', '2014-06-22 12:42:07', 0, 2, 4, 2, 15),
(6, 'Zgadzam sie!!!', '2014-06-21 12:42:51', 0, 2, 4, 2, 16);

/* artykuł 3 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(3, 27, '
On chce mieć kota – ty wolałbyś psa, on kocha towarzystwo – ty preferujesz spokój, on jest nocnym markiem – ty wstajesz skoro świt, on jest uosobieniem nieładu – z ciebie wielki czyścioch... Takie czy inne, z pozoru nieistotne, różnice w stylu życia bywają czasem zarzewiem większych konfliktów nawet pomiędzy dobrymi znajomymi, którzy zdecydują się poddać swoją przyjaźń najcięższej z prób – wspólnemu mieszkaniu.
DS czyli studenckie zoo
	Pokój w akademiku to los na loterii – nie każdy wygrywa, ale ryzyko jest przecież wkalkulowane w koszty. Mieszkanie w Domu Studenckim może być sposobem na poznanie innych studentów z tej samej uczelni, kierunku, a nawet roku. To okazja do zawarcia nowych znajomości, nawiązania ciekawych kontaktów towarzyskich, często dostarcza też praktycznych korzyści, np. z sąsiadem obcokrajowcem można potrenować języki obce. Poza tym akademik to przecież skarbnica wiedzy i mądrości – czasem wystarczy udać się piętro wyżej, żeby zdobyć kompletne notatki z interesującego nas przedmiotu. Bywa też gorzej – zdarza się niestety, że koleżanki z pokoju lubią bez pytania pożyczać ciuchy, nieczęsto trudzą się sprzątaniem po sobie łazienki itp... O mniej lub bardziej popularnych problemach opowiada Natalia, studentka matematyki na UJ:
– Pierwsza noc w akademiku raczej potwierdziła moje obawy i krążące opinie: za ścianą muzyka (chociaż ładna i w moim guście, to jednak stanowczo za głośna) i jeszcze głośniejsze rozmowy do trzeciej nad ranem. Następnego dnia okazało się, że obok mieszka dość rozrywkowy Amerykanin, więc tłumacząc mu zasady wspólnego życia obok siebie, ćwiczyłam jednocześnie mój angielski – śmieje się Natalia. – Sąsiad grzecznie przeprosił i obiecał poprawę. Po kilku dniach jego miejsce zajął o wiele spokojniejszy Węgier, który znał tylko kilka słów po polsku, więc okazji do praktycznych ćwiczeń rozmówek polsko-angielskich dalej mi nie brakowało, z czego akurat bardzo się cieszę – dodaje. – Z kolei jedna z moich znajomych mieszkała podczas studiów przez ścianę z kleptomanką, ich pokoje były połączone wspólną łazienką. Opowiadała mi, że trzeba było wykazać się sporą dozą inwencji, żeby poradzić sobie w takiej sytuacji. Chowała ubrania do łóżka zamiast do szafy, a wychodząc, zawsze zostawiała włączone radio, które miało sugerować, że pokój wcale nie jest pusty.
Luksus nie dla każdego
	Istnieje prosty sposób na uniknięcie wszelkich kłopotów. Wystarczy zamieszkać w „jedynce”. Jednak łatwiej powiedzieć niż zrobić, bo pokojów jednoosobowych jest dużo mniej niż chętnych, a zasady ich przydzielania skomplikowane. Dla przykładu w Domu Studenckim Politechniki Gdańskiej Rada Mieszkańców przyznaje każdemu studentowi punkty – za staż na uczelni i w akademiku, za pełnienie funkcji w samorządzie wydziałowym lub uczelnianym itd. Są też punkty ujemne za złe zachowanie. Oczywiście im więcej punktów na liczniku, tym większe szanse na wymarzone lokum. Wyjściem z sytuacji jest również kandydowanie na członka Rady Mieszkańców – osobny punkt regulaminu mówi, że otrzymują oni pokoje poza kolejnością.
Co zrobić, żeby przetrwać
Niestety studenci pierwszego roku szanse na zdobycie jedynki mają praktycznie zerowe i muszą przywyknąć do przebywania w większym gronie. A gdy w parę osób trzeba się pomieścić na kilku metrach kwadratowych, to zawsze znajdzie się mnóstwo powodów, dla których ma się powyżej uszu swojego współmieszkańca lub sąsiada zza ściany. Jednak przy odrobinie dobrej woli większość problemów można szybko i bezboleśnie rozwiązać. Jeśli męczy cię hałas, wypróbuj stopery i zawczasu, jeszcze zanim zbliżająca się nieuchronnie sesja zmusi cię do poświęcania książkom każdej wolnej chwili, poszukaj jakiegoś cichego ustronnego miejsca, w którym można poczytać, pouczyć się spokojnie. Może w twoim akademiku jest taki specjalny pokój do nauki, może w pobliskiej bibliotece (niekoniecznie uczelnianej! poszukaj w okolicy publicznej książnicy) jest przytulna wygodna czytelnia, a może kolega z roku mieszkający w tym samym budynku ma lepsze układy ze współlokatorem i lubi wspólnie zakuwać. Jeśli Twój sąsiad uwielbia głośne techno (a nie możesz nic z tym zrobić, bo z nastaniem ciszy nocnej grzecznie ucisza swój sprzęt), przystaw kolumnę do ściany i zaaplikuj natrętowi V Symfonię Beethovena – to mu powinno dać do myślenia. Jeżeli amatorem głośnej muzyki jest twój dobry znajomy, przy najbliższej okazji (urodziny, święta) daj mu w prezencie porządne słuchawki – może to nie najtańsze rozwiązanie, ale efekt długoterminowy. Aby uniknąć kłótni o pieniądze, lepiej od razu ustalić zasady. Na przykład każdy kupuje to, co uważa za potrzebne, potem zbiera rachunki i rozliczacie się pod koniec każdego miesiąca. Jeśli macie wolną chwilę, razem wybierzcie się na zakupy – to może być dobra okazja, żeby lepiej poznać upodobania i nawyki osób, z którymi mieszkasz (i tym skuteczniej później z nimi walczyć!). Wprowadź w życie zasadę „Tydzień Prezesa”. Na kalendarzu każdy kolejny tydzień oznacz inicjałami jednego z lokatorów – aktualny miłościwie panujący Prezes jest odpowiedzialny za utrzymanie porządku we wspólnych pomieszczeniach, może on też na przykład zarządzać waszymi finansami przez okres swojej kadencji. Niech każda osoba mieszkająca z tobą w pokoju powiesi na szafie swój plan zajęć. Dzięki takiemu grafikowi pozostali będą wiedzieli, kiedy najlepiej zapraszać gości, żeby mieć trochę prywatności i nikomu przy tym nie przeszkadzać, jak również kiedy jest najlepsza pora, żeby wziąć długą kąpiel i nie obawiać się, że za chwilę współlokator zacznie się dobijać do drzwi łazienki. Jeżeli chciałbyś udostępnić swój komputer współmieszkańcom, ale obawiasz się o prywatność, utwórz osobne konto dla „gości”, na swoim załóż hasło i zablokuj dostęp do najważniejszych danych. 
Gdy zdarzają się poważniejsze incydenty, a grzeczne perswazje nie dają rezultatów, pozostaje jeszcze droga oficjalna lub półoficjalna. Czasem spór może rozwiązać Sąd Koleżeński – o ile obie strony wyrażą zgodę i zobowiążą się do akceptacji wyroku. Jeśli i to nie przyniesie efektów, a delikwent jawnie gwałci przepisy, pomocy należy szukać na wyższych szczeblach. W regulaminie „Mikrusa” – Domu Studenckiego Politechniki Łódzkiej możemy przeczytać: „Osoby, które w jakikolwiek sposób naruszą obowiązujące zasady, zostaną ukarane w trybie przyśpieszonym przez kierownika II DS i Radę Mieszkańców (włącznie z wydaleniem z Osiedla Akademickiego).” Gdzie się udać w razie kłopotów i co można tam zdziałać wyjaśnia także recepcjonista z Hotelu Studenckiego:
 – Skargi studenci zgłaszają nam, my przekazujemy kierownikowi. Mogą też bezpośrednio zgłaszać problemy do kierownika. Jeżeli skargi na jakiegoś studenta się powtarzają, to kierownik może go wyrzucić z akademika. Może też ograniczyć studentowi godziny odwiedzin w przypadku ponawianych zażaleń współlokatorów, którzy nie mogą się np. uczyć. Na szczęście takie sytuacje nie zdarzają się tak często. W minionym roku akademickim jeden student został wyrzucony za awanturowanie się po pijanemu, była jedna skarga na współlokatorkę, do której codziennie przychodził chłopak w odwiedziny. I jedna bójka, w wyniku której poszkodowany na własne życzenie został przekwaterowany do innego pokoju.
	Zanim jednak zdecydujesz się na radykalne posunięcia, pomyśl, co się stanie, jeżeli twoja skarga zostanie odrzucona – nie warto chyba wyrabiać sobie opinii kujona, któremu przeszkadza najlżejszy nawet hałas i narażać się na jeszcze większe konflikty.
Gdy trzeba sobie radzić samemu
	Część z powyższych rad i sugestii nadaje się do zastosowania także w mieszkaniach prywatnych, wynajmowanych „na własną rękę”. Kwestie bardziej formalne reguluje umowa najmu, dlatego koniecznie dokładnie zapoznaj się z dokumentami. Pamiętaj też o dwóch rzeczach: po pierwsze – nawet jeśli nic nie podpiszesz, to i tak według prawa obowiązuje cię umowa ustna, której warunków musisz przestrzegać; po drugie – to, czego nie ma w umowie, reguluje kodeks cywilny. Jeśli chcesz dowiedzieć się dokładnie, na co zwrócić uwagę, aby zawczasu zabezpieczyć się przed ewentualnymi problemami, zapytaj prawnika. Nie musisz tego robić osobiście. Łatwym i tanim rozwiązaniem są bezpłatne porady prawne on-line. W ten sposób dowiesz się, czy lepiej, aby umowa najmu opiewała na wszystkich lokatorów czy tylko na jednego i czy w takim wypadku ma on większe prawa lub obowiązki niż inni; co zrobić, gdy współlokator nie płaci rachunków; czy tobie też może się oberwać, jeżeli osoba, z którą mieszkasz, zakłóca spokój... Odpowiedzi na te i inne pytania uzyskasz na stronach http://www.poradaprawna.pl/, http://web.reporter.pl/ lub pod adresem porada.prawna@vp.pl.
	Na razie jednak zachowaj spokój – nie taki student straszny jak go malują. Opinie i plotki mogą być mocno przesadzone. Pozostaje trzymać kciuki i mieć nadzieję, że obcy, z którym przyjdzie ci dzielić pokój, bardziej będzie się bał ciebie niż ty jego.
', '2014-06-10 00:00:00', 'O mieszkaniu z ludźmi i studentami', '2014-07-03 00:00:00', NULL, 0, '2014-06-19 00:00:00', 'Jak sobie radzić z trudnym współlokatorem', 0, 4, 1, 27);

/* komentarze do artykułu 3 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(7, 'Bzdury!!', '2014-06-21 12:24:21', 0, 3, NULL, 2, 27),
(8, 'A wcale, ze nie!', '2014-06-22 12:42:07', 0, 3, NULL, 2, 28),
(9, 'Zgadzam sie!!!', '2014-06-21 12:42:51', 0, 3, NULL, 2, 29),
(10, 'Zgadzam sie!!!', '2014-06-21 12:42:51', 0, 3, NULL, 2, 30);

/* artykul 4 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(4, 31, '
Zacznij studia trochę wcześniej
Przed Tobą ostatni rok liceum i egzamin dojrzałości. Po maturze marzą Ci się zapewne długie wakacje. Pamiętaj jednak, że po zasłużonym odpoczynku czeka Cię skok na głęboką wodę – studia. Nowe miasto, uczelnia, nowi ludzie… Możesz zrobić coś, żeby oszczędzić sobie szoku, a przy tym spędzić miło czas – wybierz się na obóz adaptacyjny.

Adapciak, czyli…
Obóz adaptacyjny dla studentów „roku zerowego” przybiera różne formy. Najczęściej grupa przyszłych studentów – mniejsza lub większa – wyjeżdża na kilka dni wspólnie z kolegami ze starszych lat. Czasem jest to kameralne zgromadzenie matematyków w bieszczadzkiej głuszy, kiedy indziej zebranie prawników nad brzegiem Bałtyku albo spotkanie studentów najrozmaitszych kierunków nad Jeziorem Solińskim. Choć tak różne, wszystkie te imprezy mają wspólny mianownik – spotkasz tam osoby, które już niedługo razem z Tobą wkroczą w nowy studencki świat.
Zdecydowałem się pojechać na obóz głównie po to, aby dowiedzieć się czegoś o studiach, które miałem rozpocząć – opowiada Michał Handzlik, student informatyki na Uniwersytecie Jagiellońskim. – Pierwszy rok studiów to dla każdego  krok w nieznane i chciałem, żeby to nieznane stało się choć trochę znane. Poza tym – kontynuuje Michał – miałem nadzieję nawiązać jakieś znajomości – zawsze to jakoś raźniej mieć z kim zamienić kilka słów w przerwie miedzy zajęciami.
Kto i dla kogo
Najczęściej organizatorem obozów adaptacyjnych są samorządy wydziałowe i uczelniane. Często swój wkład mają także kluby i organizacje turystyczne działające przy uczelni albo w danym mieście. A po co są organizowane „adapciaki”?
Trzeba powiedzieć, że w tej chwili jest to prawie wymóg, by uczelnia takie przedsięwzięcie organizowała – mówi Maciek Biernacki, koordynator obozu adaptacyjnego uczelni białostockich. – A skoro ministerstwo zawsze się do takich inicjatyw dokłada, to czemu by nie uprzyjemnić wejścia na uczelnię świeżo upieczonym studentom!
Przyjęcie w poczet studentów pierwszego roku to główny warunek, jaki musisz spełnić, aby wziąć udział w takiej imprezie. Pozostałe kryteria zmieniają się w zależności od rodzaju obozu.
Rolę „opiekunów” najczęściej pełnią starsi studenci, którzy swoje studiowanie zaczynali także jako uczestnicy adapciaka. Niektórym tak się spodobało, że przyjeżdżają na kolejne obozy jako osoby prywatne – już bez dofinansowania z wydziału – mówi Grzegorz Kosiorowski, przewodniczący Samorządu Studentów Matematyki i Informatyki UJ. – To chyba dobrze świadczy o atmosferze naszego obozu – dodaje.
Opiekunowie to po prostu inni studenci, którzy są tam dla Ciebie – odpowiedzą na każde Twoje pytanie, rozwieją wątpliwości, obalą mity i stereotypy, udzielą kilku przydatnych rad i wprowadzą w życie studenckie.
Bywa też, że trzeba się pilnować samemu. U nas jest wysyłana jedna bądź dwie osoby z Samorządu, ale opiekunem na obozie jest każdy sam dla siebie – śmieje się Marek Baranowski z Samorządu Studentów Wyższej Szkoły Bankowej w Gdańsku.
Do wyboru, do koloru
Oferta obozów adaptacyjnych jest naprawdę bardzo bogata. Możesz pojechać na obóz dla adeptów wybranego kierunku. Sprawdź, czy Twoja uczelnia organizuje wyjazd tylko dla swoich studentów lub dla tych, którzy będą się z Tobą uczyć na tym samym wydziale. Są też większe obozy gromadzące studentów z wielu szkół wyższych, niezależnie od kierunku studiów czy miasta, w którym się uczysz. Masz w czym wybierać.

Dla każdego
Jeżeli lubisz, gdy wokół Ciebie kręci się mnóstwo ludzi, niestraszne Ci tłumy i łatwo nawiązujesz znajomości, wybierz duży ogólnopolski obóz, na który może pojechać każdy student pierwszego roku. Bywa, że uczestniczy w nich od pięciuset do nawet tysiąca osób! Oferta na pewno jest ciekawa, ale ma też swoje wady: mało prawdopodobne, że spotkasz tam kolegę z grupy, a gdy wrócisz do domu możesz już nie pamiętać, do której Kasi należy numer telefonu w Twojej komórce – tyle ich było! Dlatego może warto pomyśleć o czymś bardziej kameralnym.

Warunek – lokalizacja
Oferta naszego obozu kierowana jest do wszystkich studentów – mówi Maciek Biernacki z NZS Białystok. – Jedynym wymogiem jest to, by studiowali w naszym mieście. Nauczyciele akademiccy też są zapraszani, ale jako goście, których zadaniem jest odpowiadać na pytania pierwszoroczniaków. Podczas obozu w każdej chwili możesz zapytać starszych kolegów o cokolwiek i dowiedzieć się takich rzeczy, których przy rektorze głośno się nie powie.

Tylko dla wybranych
	Jeżeli jednak najbardziej interesuje Cię poznanie własnej alma mater i ludzi, którzy będą z Tobą studiować, wybierz się na adapciaka organizowanego tylko dla studentów Twojej uczelni.
Generalnie obóz przeznaczony jest dla studentów przyjętych na pierwszy rok na Uniwersytet Jagielloński – wyjaśnia Maciek Chmielecki z Akademickiego Klubu Turystycznego „Rozdroże”. – W praktyce jeździ coraz więcej obóz spoza tej kategorii. Starsi studenci, którzy byli wcześniej na naszym obozie, wracają, żeby przeżyć to jeszcze raz. Jest też coraz więcej ludzi spoza UJ – w tym roku będziemy gościli studentów z Uniwersytetu Wrocławskiego, Bydgoskiego, Rzeszowskiego oraz krakowskich szkół wyższych innych niż UJ. Przyczyny są rożne – jedni jadą ze znajomymi z naszego Uniwersytetu, inni dlatego, że na ich uczelni nie ma podobnego obozu. Zainteresowanie jest bardzo duże.
W tym roku głównym punktem programu obozu dla studentów Wyższej Szkoły Bankowości w Gdańsku jest paintball. W programie przewidzieliśmy również zjazdy na linach rozporowych i bieg na orientację – zachęca do wyjazdu Marek z Samorządu Studentów WSB. – Wieczorami będą dyskoteki, karaoke albo ogniska. Na początku obozu nie znasz nikogo i każdy jest obcy. Po takim obozie masz wrażenie jakbyś znał ludzi już spory kawałek czasu.
Możesz jeszcze bardziej zaostrzyć kryteria wyboru. Wydział Matematyki i Informatyki UJ już od ośmiu lat organizuje niewielki obóz adaptacyjny wyłącznie dla swoich studentów.
Główną atrakcją są codzienne wycieczki górskie zajmujące czas od śniadania do obiadokolacji – opowiada Grzegorz Kosiorowski, organizator zeszłorocznego obozu. – Wieczorami są ogniska, wieczorki z gitarą, sesje gier towarzyskich tradycyjnie popularnych na wydziale. Jeden dzień obozu przeznaczony jest na serię miniwykładów o przedmiotach nauczanych w instytutach wydziału. Nieuniknione są też opowieści o krakowskim życiu studenckim, o wykładowcach i setkach innych tematów niezbędnych studentom.
Korzyści – nieocenione!
Czy obóz spełnił moje oczekiwania? Z duuuuużą nawiązką! – opowiada Michał Handzlik, uczestnik zeszłorocznego obozu. – Spędziłem wspaniale kilka dni, w świetnej atmosferze, poznałem bardzo fajnych ludzi, kilku z nich stało się potem moimi dobrymi kolegami. Dowiedziałem się sporo o tym, jak będzie wyglądać pierwszy rok, zdobyłem cenne znajomości wśród kolegów i koleżanek ze starszych lat. Miłym zaskoczeniem było też to, że głównym założeniem obozu nie było picie alkoholu, a porządne chodzenie po górach.
Dodatkowymi atutami było to, że obóz miał charakter bardzo kameralny, że jechali ludzie tylko z Wydziału Mat-Inf – w razie niepogody zawsze można pograć w blefa całkowego – śmieje się Michał. – No i przede wszystkim – był on w Bieszczadach, górach w które wybierałem się już kilka ładnych lat i jakoś się wybrać nie mogłem... Ogólnie – podsumowuje Michał – naprawdę świetnie się bawiłem i bardzo chętnie wybrałbym się na taki obóz raz jeszcze.
Wrażenia – gwarantowane
Pamiętam jak na biegu na orientację mieliśmy bliskie spotkanie z policją – wspomina Marek Baranowski z gdańskiej WSB. – Na jednym z punktów kontrolnych należało odtworzyć bitwę średniowieczną. Było już dość ciemno, więc ludzie widząc jak „walczymy” postanowili wezwać policję. Na szczęście skończyło się na upomnieniu, abyśmy nie robili takich rzeczy w miejscu publicznym.
Jak widać oprócz kilku dobrych rad od starszych i bardziej doświadczonych studentów, z obozu możesz przywieźć także opowieści o interesujących przygodach.
Kilka lat temu w Gołdapi organizowany był obóz uczelni białostockich – opowiada z kolei Maciek Biernacki.  – Sama miejscowość leży w strefie przygranicznej, około 500 m od granicy z Litwą. Dwóch studentów postanowiło przekroczyć wodną granicę państwa kajakiem –  wypłynąć za bojkę graniczną, zrobić sobie zdjęcie i szybko wrócić. Los chciał, by właśnie w tym momencie przepływała w okolicy motorówka litewskiej straży granicznej. Błyskawicznie panowie zostali aresztowani za nielegalne przekroczenie granicy i odwiezieni do przygranicznego więzienia, gdzie przesiedzieli dwa dni, zanim organizatorom (pominę w jaki sposób) udało się ich stamtąd wyciągnąć. Myślę że ci dwaj naprawdę mieli z obozu NIEZAPOMNIANE wspomnienia.



Przydatne linki
http://campusakademicki.pl – oficjalna strona jednego z największych ogólnopolskich adapciaków – Campus Akademicki Solina;
http://www.campusakademicki.com.pl – strona dużego ogólnopolskiego obozu adaptacyjnego Campus Akademicki Międzyzdroje;
http://www.turystykastudencka.pl – Studencki Portal Turystyczny; znajdziesz tu informacje nie tylko o obozach adaptacyjnych, ale również o różnego rodzaju wyjazdach organizowanych przez studentów i dla studentów z całej Polski;
http://rozdroze.com/ – strona Akademickiego Klubu Turystycznego „Rozdroże”, na której przeczytasz ciekawe wspomnienia uczestników obozów adaptacyjnych;
www.elsa.org.pl/adapciak – szczegółowe informacje na temat ogólnopolskiego obozu dla studentów prawa i administracji;
http://www.iib-pl.org/ – witryna Biura Inicjatyw Międzynarodowych, które organizuje Międzyuczelniany Obóz Adaptacyjny dla studentów politologii i stosunków międzynarodowych;
http://www.bialydunajec.org – o obozie adaptacyjnym w Białym Dunajcu organizowanym przez duszpasterstwa akademickie Wrocławia i Opola;
http://www.bialystokonline.pl – w dziale „imprezy” przeczytasz o szczegółach adapciaka dla studentów uczących się w Białymstoku;
http://azs.civ.pl/azs – pod hasłem „szkolenia, obozy, imprezy” znajdziesz informacje o obozach dla studentów wielu warszawskich uczelni.
', '2014-06-17 00:00:00', 'Poznaj swoich kolegów jeszcze przed początkiem roku akademickiego', '2014-07-30 00:00:00', NULL, 0, '2014-06-20 00:00:00', 'Obozy adaptacyjne', 0, 7, 1, 30);

/* komentarze do artykułu 4 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(11, 'Byłem i mi się podobało :)', '2014-06-22 12:24:21', 1, 4, NULL, 2, 33),
(12, 'A wcale, ze nie!', '2014-06-22 12:42:07', 0, 4, 11, 2, 34);

/* artykul 5 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(5, 28, '
W naszym kraju jest wiele renomowanych uczelni. Ale w całej Europie jest ich jeszcze więcej. A może by tak nie ograniczać się tylko do Starego Kontynentu i wybrać się dalej – na przykład na Harvard?

Po Europie i dalej
Jest wiele różnych programów, które umożliwiają zrealizowanie części studiów na zagranicznej uczelni – najpopularniejszym i jak do tej pory najlepiej się sprawdzającym jest Erasmus. Powstał w 1987 roku, a głównym jego celem jest propagowanie i ułatwianie wymiany studentów miedzy uczelniami państw należących do Wspólnoty Europejskiej. Udział biorą w nim wszystkie kraje UE, a także uczelnie z Turcji, Rumunii, Islandii, Lichtensteinu czy Bułgarii.
Najważniejszym zadaniem, jakie postawili przed sobą twórcy programu jest stałe podnoszenie poziomu kształcenia oraz utrwalanie jego europejskiego charakteru. Cele te realizowane są między innymi poprzez wymianę studentów.
Kto może, a kto nie
Ogólna zasada jest taka, że najpierw trzeba się dostać na jakieś studia w Polsce, najlepiej na uczelni, która uczestniczy w programie Erasmus. Jeżeli chodzi o szczegóły – tu już każdy rządzi się własnymi prawami. Zasady doboru kandydatów na stypendia mogą się różnić nawet na poszczególnych wydziałach. Zazwyczaj jednym z kryteriów jest średnia, dlatego planując wyjazd za granicę, warto postarać się o dobre oceny.
Przeważnie o tym, kto pojedzie, decyduje specjalna komisja, przed którą należy się zaprezentować – wykazać znajomością języka, a także odpowiednią motywacją. Pomocna bywa działalność w organizacjach studenckich (np. w samorządzie, kołach naukowych i różnych klubach tematycznych). Niektóre uczelnie przeprowadzają własne egzaminy i to one są podstawą kwalifikacji. Dużo zależy też od wyboru docelowej uczelni – można zrezygnować z prestiżowego, ale obleganego uniwersytetu na rzecz mniej znanej, lecz także godnej zainteresowania szkoły.
Zazwyczaj wszelkich informacji dotyczących rekrutacji kandydatów na stypendia Erasmusa udzielają biura współpracy z zagranicą – znajdują się na każdej większej uczelni, która prowadzi międzynarodową wymianę studentów – oraz koordynatorzy wydziałowi. Przy wielu polskich szkołach wyższych są także oddziały Erasmus Student Network, ogólnopolskiej organizacji studenckiej. Tam można spotkać studentów z całej Europy, którzy w ramach programu Erasmus przyjechali studiować w Polsce.
Plan pracy
Większość uczestników Erasmusa otrzymuje stypendium, a studiowanie na zagranicznej uczelni jest bezpłatne tzn. nie trzeba tam płacić czesnego. Nie oznacza to jednak, że nie ponoszą oni żadnych kosztów wyjazdu. Wysokość stypendium jest różna każdego roku i uzależniona od dostępnych funduszy i zapotrzebowania na wyjazdy. Jednak jest to z założenia jedynie dofinansowanie mające na celu pokrycie różnicy w kosztach utrzymania w Polsce i kraju, do którego wyjeżdżamy, dlatego kwota ta może wystarczyć na przykład tylko na opłacenie akademika. Dla przykładu w zeszłym roku akademickim studenci Uniwersytetu Warszawskiego wyjeżdżający na stypendium Erasmusa otrzymywali 227€ lub 278€ na miesiąc – w zależności od kraju, do którego wyjeżdżali. Stypendium dla studentów niepełnosprawnych wynosiło 350€. Możliwy jest też wyjazd bez wsparcia finansowego uczelni, całkowicie na własny koszt.
	Student, który otrzyma stypendium, wyjeżdża na semestr lub dwa na wybraną przez siebie uczelnię. Tam studiuje – zalicza odpowiednie kursy. Czasem program realizowany za granicą jest już z góry ustalony, czasem samemu można wybrać przedmioty do zaliczenia. Zależy to od kierunku studiów oraz oferty uczelni partnerskiej.
	Jeżeli komuś pobyt za granicą bardzo się spodoba, to najczęściej można go przedłużyć. Są to jednak indywidualne przypadki i wszystko zależy od uczelni. Trzeba jednak wziąć pod uwagę fakt, że mogą się pojawić problemy z dopasowaniem kolejnych kursów, a w konsekwencji z zaliczeniem roku.
Dla odważnych
Innym rozwiązaniem jest wyjazd na cały okres studiów. Uczelnie mają własne systemy rekrutacji obcokrajowców – czasem aby ubiegać się o przyjęcie na studia za granicą wystarczy mieć maturę i certyfikat znajomości języka, kiedy indziej wymagane jest zdanie egzaminów wstępnych. Wbrew pozorom wcale nie tak trudno dostać się dobrą zagraniczną uczelnię. Aby zostać przyjętym na przykład na Oxford nie trzeba wcale mieć angielskiego A-level. Od 2004 roku, kiedy Polska stała się członkiem Unii Europejskiej niepotrzebna jest także wiza studencka.
Ciekawym pomysłem jest zdawanie tak zwanej międzynarodowej matury. Dobry wynik takiego egzaminu właściwie gwarantuje przyjęcie w poczet studentów takich uczelni jak Harvard albo Oxford! Do tego niektóre uczelnie oferują stypendia dla osób, które bardzo dobrze zaliczyły międzynarodowy egzamin dojrzałości. Ale uwaga – można wpaść w pułapkę. Ponieważ w Polsce międzynarodowa matura została właściwie zrównana z nową maturą, może się zdarzyć, że dostaniesz się do Cambridge, ale nie zostaniesz przyjęty na krajową uczelnię! Poza tym nawet, jeżeli stypendium pokryje koszty czesnego, nadal pozostaje kwestia utrzymania się, co często i tak przekracza możliwości finansowe młodego Polaka. Rozwiązania są dwa: znaleźć pracę albo sponsora. Nawet nieduże lokalne firmy mają dość funduszy, aby wesprzeć zdolnego ucznia.
Dokąd najlepiej
	Jak wybrać odpowiednią szkołę? Czy lepiej starać się o przyjęcie na renomowaną uczelnię, czy może na jakąś mniej znaną?
Jasne, że na renomowaną! Jak już ma się wyjechać raz w życiu, to niech to będzie Sorbona! – śmieje się Marta z UAM. – Lepiej oczywiście na renomowaną, bo nie bez powodu jest ona popularna. Z drugiej strony może być trudniej się tam dostać. Ale przecież nie ma przeszkód i można się starać w tym samym czasie o przyjęcie na kilka uczelni.
	Wybierając szkołę, należy zwrócić uwagę na szczegółowy program oferowanych kursów – czy pokrywają się mniej więcej z programem kierunku na Twojej macierzystej uczelni. Lepiej uniknąć potem problemów z zaliczeniem roku z powodu innego wymiaru godzin albo różnic programowych.
	Jeżeli chodzi o wyjazd na cały okres studiów, to dobrze jest zorientować się, w czym specjalizują się poszczególne uczelnie. I tak na przykład archeologię warto studiować na uniwersytecie w Lyonie, a lingwistykę – w Tybindze. Brytyjskie uniwersytety Oxford i Cambridge a także amerykańskie Harvard i Yale oferują najlepszych profesorów i zaplecze naukowe, jeżeli chodzi o kierunki takie jak filozofia czy socjologia. Umysły ścisłe natomiast powinny spoglądać w stronę naszych bliskich sąsiadów – matematyka i informatyka stoją na wysokim poziomie na Uniwersytecie Karola w Pradze, na Uniwersytecie Alberta Einsteina w niemieckim Ulm, a także... w Polsce – wysoko w rankingach plasują się bowiem Uniwersytet Jagielloński oraz Warszawski.
To się opłaca
Czy warto? – zastanawia się Marta. – Moim zdaniem nic nie można stracić (no może oprócz pieniędzy), a wyjazd za granicę i zwiedzanie nowych miejsc zawsze daje wiele satysfakcji.
Podsumowując – trochę ryzyka, ale też ciekawe doświadczenia, niezapomniane wrażenia i nowi przyjaciele z całego świata. Można tylko dodać, że przyszły pracodawca na pewno będzie pod wrażeniem takiego życiorysu.

Marta – studentka etnolingwistyki na Uniwersytecie Adama Mickiewicza w Poznaniu, obecnie przebywa w Hiszpanii na Universitat de Barcelona.
Wymiany studenckie... Nie wiem, co mnie tknęło, żeby się w to wciągnąć, ale skoro jest okazja, żeby studiować przez jakiś czas za granicą, to grzech jej nie wykorzystać. Niestety wyjazdy na wszelkie stypendia wiążą się często z wysoką średnią i można z nich korzystać po zaliczeniu przynajmniej jednego roku studiów. Ja nie jestem zbyt pilnym uczniem, postanowiłam podejść do tego z innej strony. Wstąpiłam do organizacji studenckiej Erasmus Student Network, która zajmuje się obcokrajowcami przyjeżdżającymi do Polski. Przez półtora roku organizowaliśmy dla studentów z zagranicy imprezy, wycieczki, pomagaliśmy im na początku i żegnaliśmy, gdy wyjeżdżali. W ten sposób poznałam mnóstwo wspaniałych ludzi z całego świata, nauczyłam się doceniać odmienność kulturową i oczywiście mogłam w końcu praktycznie wykorzystywać języki. W końcu sama chciałam zobaczyć, jak to jest po tej drugiej stronie.
Jak się okazało, średnia nie jest najważniejszą kwestią w doborze stypendystów. Ważne jest także, co się robi poza studiami i dlaczego się chce wyjechać. W moim przypadku decydująca okazała się chyba działalność w ESN. Oczywiście nie jest to od razu przepustka do wyjazdu, ale jeśli ktoś ma silną motywację, to trzeba taką szansę wykorzystać.
 	A teraz... teraz sama jestem na Erasmusie. Jak na razie jestem bardzo mile zaskoczona. Myślałam, że skoro to Hiszpania, to wszystko będzie niezorganizowane. A tu nie było żadnego problemu z formalnościami, od razu dostałam potrzebne dokumenty i mogę korzystać z wszystkich studenckich przybytków. Zajęcia są bardzo ciekawe, w każdej sali jest komputer, na tablicy wyświetlane są prezentacje, rzadziej używają tu kredy. Najśmieszniejsze jest to, że nie ma przerw – studenci schodzą się na zajęcia przez pierwsze 20 minut albo i więcej, a i tak nikomu to nie przeszkadza.
Po powrocie – o ile się nie zakocham i nie zechcę zostać tu do końca życia – na pewno wrócę do mojego ESN.

Małgorzata – studiuje na Wydziale Nauk Ekonomicznych i Zarządzania Uniwersytetu Mikołaja Kopernika w Toruniu
Postanowiłam wyjechać, bo stwierdziłam, że może być to fajna zabawa, ale głównym powodem było chyba to, że taki wyjazd ładnie wygląda w CV. Wszelkie informacje znalazłam na stronie internetowej biura programów międzynarodowych – działają one przy większości uniwersytetów. Dużo też zawdzięczam mojemu koordynatorowi wydziałowemu.
Na moim wydziale co roku jest wielu chętnych, dlatego organizowane są testy. Ten, kto ma największą ilość punktów, wybiera jako pierwszy uniwersytet. Ostatni nie ma dużego wyboru... Według mnie nie jest ważne, czy uniwersytet jest znany, czy nie – ważne, aby studiować za granicą. Można zdobyć doświadczenie, podszkolić język, poznać wielu ciekawych ludzi, różne kultury. Jeżeli masz znajomych za granicą, to możesz ich odwiedzić w wakacje!
Studiowałam w Finlandii w Rovaniemi na Uniwersytecie Lapońskim. Miałam swojego mentora, który pomagał mi podczas pobytu w Finlandii: pokazał miasto, załatwiał formalności, zabierał na imprezy. Na początku był bardzo przydatny.
Mówi się o szoku kulturowym, o tym, ze niektórzy nie potrafią się przystosować. Ja nie miałam z tym problemu. Studiowałam w małej miejscowości, wszyscy mieszkali w dwóch akademikach obok siebie, znali się po imieniu. Fajnie, gdy erasmusi nie są rozsypani po całym mieście, ale są razem.
Studiowanie tam jest całkiem inne. Nie wiem czy lepsze, czy gorsze, ale na pewno inne. Na przykład stosunek student-wykładowca. Tam wykładowca jest dla studenta. Traktuje go jak równego, zawsze ma czas i jest szczęśliwy, gdy może w czymś pomóc. Bardzo duży nacisk kładzie się na indywidualną pracą, mało było wykładów. To student ustala sobie tryb nauki. Wykładowca mówi, czego oczekuje, co trzeba przeczytać, z czego egzamin i podaje deadline. Reszta zależy od studenta. Warto jest wyjechać, chociażby po to, aby poznać inny system studiowania.
', '2014-06-17 00:00:00', 'O tym, jak się studiuje za granicą i czy warto wyjechać.', '2014-07-30 00:00:00', NULL, 0, '2014-06-19 00:00:00', 'Jeśli nie w Polsce, to gdzie?', 0, 3, 2, 28);

/* komentarze do artykułu 5 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(13, 'Ja już byłem', '2014-06-21 12:24:21', 0, 5, NULL, 2, 28),
(14, 'Kiedyś na pewno poajdę!', '2014-06-21 13:34:21', 0, 5, NULL, 2, 29),
(15, 'eee, bez sensu, po co wyjeżdząć :P', '2014-06-21 14:24:21', 0, 5, NULL, 2, 30);


/* artykul 6 */
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(6, 19, '
Z przyjemnością informujemy, że na naszej Uczelni podjęto działania w celu rezygnacji z pobierania opłat za drugi i kolejny kierunek studiów oraz zajęcia ponad limitem bezpłatnych punktów ECTS. Po wyroku Trybunału Konstytucyjnego z 5 czerwca br., w którym za niekonstytucyjne uznano wprowadzające je przepisy ustawy Prawo o szkolnictwie wyższym, nasza Uczelnia wykazała się prostudencką postawą, co bardzo nas cieszy. Przygotowano projekt  uchwały Senatu, w której przewidziano zapis o niepobieraniu wspomnianych opłat od osób już studiujących i przyjętych w roku 2014, mimo iż Trybunał orzekł, że przestaną one obowiązywać dopiero 30 września 2015 r.
Ponadto, Uniwersytet Jagielloński, jak na najstarszą Uczelnię w Polsce przystało, czuje się odpowiedzialny za właściwy kierunek zmian w szkolnictwie wyższym w całym kraju. JM Rektor UJ zwrócił się do Konferencji Rektorów Akademickich Szkół Polskich o podjęcie pilnych działań zmierzających do tego, by wszystkie Uczelnie poszły w tym samym kierunku i pod obrady KRASP proponuje poddać uchwałę, w której polskie Uczelnie zobowiążą się do wprowadzenia zwolnień z niekonstytucyjnych opłat. Jesteśmy dumni z takiej postawy naszej Alma Mater!
', '2014-06-20 00:00:00', 'Opłaty za drugi kierunek studiów', '2014-09-30 00:00:00', NULL, 0, '2014-06-21 00:00:00', 'Bezpłatny drugi kierunek powraca!', 0, 5, 2, 19);

/* komentarze do artykułu 6 */
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(16, 'No to wreszcie mogę iść na fizykę :D', '2014-06-24 10:24:21', 0, 6, NULL, 2, 15);


/*
	-- wzór --
 artykul x
INSERT INTO `articles` (`id`, `article_owner`, `content`, `date`, `description`, `expiration_date`, `galery`, `image`, `publication_date`, `title`, `views`, `category_id`, `rank`, `user`) VALUES
(3, 23, '
TRESC TU
', '2014-06-17 00:00:00', 'Opis', '2014-07-30 00:00:00', NULL, 0, '2014-06-19 00:00:00', 'Tytul', 0, 1, 1, 25);

 komentarze do artykułu x
INSERT INTO `comments` (`id`, `content`, `date`, `number_of_responses`, `article_id`, `parent_id`, `state`, `user`) VALUES
(7, 'Bzdury!!', '2014-06-21 12:24:21', 0, 3, NULL, 2, 17),
(8, 'A wcale, ze nie!', '2014-06-22 12:42:07', 0, 3, NULL, 2, 18),
(9, 'Zgadzam sie!!!', '2014-06-21 12:42:51', 0, 3, NULL, 2, 19),
(10, 'Zgadzam sie!!!', '2014-06-21 12:42:51', 0, 3, NULL, 2, 20);
*/