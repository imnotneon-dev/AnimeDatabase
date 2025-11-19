DROP DATABASE IF EXISTS `db_anime1`;
CREATE DATABASE IF NOT EXISTS `db_anime1`;
USE `db_anime1`;


--
-- Dropping existing tables
--
DROP TABLE IF EXISTS `watchHistory`;
DROP TABLE IF EXISTS `likedEpisode`;
DROP TABLE IF EXISTS `episodeReviews`;
DROP TABLE IF EXISTS `actorSeries`;
DROP TABLE IF EXISTS `episodes`;

DROP TABLE IF EXISTS `series`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `actors`;

--
-- Table structure for table `users`
--
CREATE TABLE `users` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `country` VARCHAR(50) NOT NULL,
    `top_genre` VARCHAR(50) DEFAULT 'N/A',
    `date_user_created` DATE DEFAULT (CURRENT_DATE()),
    `status` VARCHAR(20) DEFAULT 'Active'
);

--
-- Dumping data for table `users`
--
LOCK TABLES `users` WRITE;
INSERT INTO `users` (`username`, `password`, `date_of_birth`, `country`, `date_user_created`) VALUES
('admin', 'admin', '2006-07-01', 'Philippines', '2025-11-19'),
('thistlezhi', 'zh15p4ssword', '2006-08-21', 'Malaysia', '2014-03-18'),
('cerizz_', 'xLuvr8', '2014-06-16', 'Philippines', '2025-11-02'),
('_zafibleh', 'm3oW!', '2005-10-28', 'Singapore', '2023-05-27'),
('icep0p', 'xD_rul3z', '2006-07-27', 'Philippines', '2016-08-09'),
('Mother_Coco', 'AFDG2028.switz!', '2004-12-18', 'Philippines', '2023-02-14'),
('IJ.Orts', '1ts_IJcUhh', '2005-06-29', 'Malaysia', '2020-12-21'),
('Azii95', 'k4tch0W!', '2005-08-14', 'Malaysia', '2019-07-30'),
('YSA', 'gl@ssy!!', '2012-08-10', 'Singapore', '2024-05-04'),
('SMHMyHead', 'r4ndOm_p@ssw0rd!', '2006-10-06', 'Malaysia', '2023-09-18'),
('RYAN12345801', 's3cret!S4NT4', '2007-12-05', 'Philippines', '2025-01-12');
UNLOCK TABLES;

--
-- Table structure for table `series`
--
CREATE TABLE `series` (
    `series_id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(200) NOT NULL,
    `genre` VARCHAR(100) NOT NULL,
    `release_year` INT,
    `total_episode_count` INT,
    `status_of_series` VARCHAR(10)
);

--
-- Dumping data for table `series`
--
LOCK TABLES `series` WRITE;
INSERT INTO series (title, genre, release_year, total_episode_count, status_of_series)
VALUES

('One Punch Man', 'Comedy', '2015', '12', 'Complete'),
('Ouran High School Host Club', 'Comedy', '2006', '26', 'Complete'), --
('Demon Slayer: Kimetsu no Yaiba Hashira Training Arc', 'Action', '2024', '8', 'Complete'),
('My Hero Academia Season 8', 'Action', '2025', '6', 'On-Going'),
('Spy x Family Season 3', 'Comedy', '2025', '13', 'On-Going'),
('GACHIAKUTA', 'Fantasy', '2025', '24', 'On-Going'),
('Tougen Anki: Dark Demon of Paradise', 'Fantasy', '2025', '15', 'On-Going'), --
('Kaguya-sama: Love Is War', 'Romance', '2019', '12', 'Complete'), --
('Paradise Kiss', 'Romance', '2005', '12', 'Complete'),
('Given', 'Boys Love', '2019', '11', 'Complete'), --
('Elfen Lied', 'Action', '2004', '13', 'Complete'); --
UNLOCK TABLES;



--
-- Table structure for table `episodes`
--
CREATE TABLE `episodes` (
    `episode_id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(200) NOT NULL,
    `release_date` DATE NOT NULL,
    `synopsis` TEXT,
    `no_of_views` INT DEFAULT 0,
    `series_id` INT,
    `runtime` INT NOT NULL,
     FOREIGN KEY (`series_id`) REFERENCES `series`(`series_id`)
);

--
-- Dumping data for table `episodes`
--
LOCK TABLES `episodes` WRITE;
INSERT INTO `episodes` (`series_id`, `title`, `release_date`, `synopsis`, `no_of_views`, `runtime`)
VALUES
-- OURAN HIGH SCHOOL HOST CLUB (Series 2)
(2, 'Starting Today, You Are a Host!', '2006-04-04', 'Haruhi Fujioka stumbles across the Host Club and accidentally breaks a vase which she is unable to pay for, so she must work as a new Host despite being a girl.', 3000, 23),
(2, 'The Job of a High School Host!', '2006-04-11', 'Haruhi has no problems adjusting to her new duties as a Host; Haruhi takes an interest in her newest client; with a formal dance coming up Haruhi needs to learn how to dance.', 3500, 23),
(2, 'Beware the Physical Exam!', '2006-04-18', 'It\'s physical exam time at Ouran Academy which could spell trouble for someone who spends her days in disguise. Meanwhile, Tamaki launches the Host Club on diversionary tactics, and Kyoya has a backup plan.', 4000, 23),
(2, 'Attack of the Lady Manager!', '2006-04-25', 'Tamaki is jealous of the relationship between the twins and Haruhi. Meanwhile, a young woman obsessed with dating sims declares herself the Host Club\'s new manager and her first order of business is to give all of the Hosts a \"dark side.\"', 4600, 23),
(2, 'The Twins Fight!', '2006-05-02', "Haruhi divulges her secret for telling Hikaru and Kaoru apart, as the rivalry between the twins begins to spiral out of control and is causing a dip in the Club's profits.", 5200, 23),
(2, 'The Grade School Host Is Naughty Type', '2006-05-09', 'Elementary student, Shiro Takaoji, has an aspiration of becoming a Host ventures into Music Room 3. He learns how to make people happy.', 5750, 23),
(2, 'Jungle Pool SOS', '2006-05-16', 'The Hosts are forced to cut their vacation short when Honey goes missing. Haruhi expresses her interest in going to a real beach.', 6430, 23),
(2, 'The Sun, The Sea, and the Host Club!', '2006-05-23', 'Haruhi proves fearless when faced with challenges set by the twins, but some see his bravery as recklessness.', 5884, 23),
(2, 'A Challenge From Lobelia Girls Academy', '2006-05-30', 'The Zuka Club comes for a visit and tries to steal Haruhi from the boys; the Hosts make a plan to keep Haruhi at Ouran.', 9800, 23),
(2, 'A Day in the Life of the Fujioka Family', '2006-06-06', "Tamaki investigates what Haruhi's aggregate commoner dwelling is like. He finds there is plenty of room for trouble.", 8452, 23),

-- MOMOTARO (Series 7)
(7, 'Oni\'s Blood', '2025-07-11', 'Shiki Ichinose\'s life is turned upside down when he\'s attacked by a mysterious stranger and learns a shocking secret about his own heritage.', 8452, 24),
(7, 'If You Want to Make It, Keep On Winning', '2025-07-18', 'Shiki awakens in an unfamiliar place and meets Naito, who begins to explain the centuries-old conflict between the Oni and the Momotaro.', 1234, 24),
(7, 'Blood Eclipse Release', '2025-07-25', 'To enroll in the Rasetsu Academy, Shiki and his new acquaintance must first pass a dangerous entrance exam: a game of "demon catcher."', 2345, 24),
(7, 'Work with Me', '2025-08-01', 'A new test is issued to the students, but its true purpose seems to be forcing them to cooperate and combine their unique abilities.', 90, 24),
(7, 'Bad News', '2025-08-08', 'The students\' training takes them to the Kyoto underground, where they learn more about the deep-seated war they have been thrust into.', 45, 24),
(7, 'Even the Loner Path Has Its Limits', '2025-08-15', 'A sudden attack by the undead forces the students into a desperate battle, and Shiki must make a difficult decision that challenges his resolve.', 34, 24),
(7, 'The Beauty Is a Beast', '2025-08-22', 'The underground mission takes a dangerous turn when an enemy seals off the exits, trapping the students in a desperate fight for survival.', 32, 24),
(7, 'The Unreliable Hero', '2025-08-29', 'Kuina is forced into a one-on-one battle against a powerful foe, but the fight puts him in a uniquely difficult and delicate situation.', 0, 24),
(7, 'Momotaro Spirit', '2025-09-05', 'A widespread riot breaks out, compromising the security of the temple. The students must shift their focus to evacuating the civilians.', 0, 24),
(7, 'Children of Kishin', '2025-09-12', 'As Shiki\'s desperate battle rages on, the scene shifts to the Momotaro Agency Headquarters, where a new operative begins her assignment.', 0, 24),
(7, 'Thank You!', '2025-09-19', 'After the chaos of their last mission, the students return to the academy, where they are forced to pair up and share rooms.', 0, 24),
(7, 'Day of the Storm', '2025-09-26', 'A powerful storm traps the students inside the academy for the night, forcing them to find ways to pass the time together.', 0, 24),
(7, 'Mikado, Like Gate of the Gods', '2025-10-03', 'The students get a rare day off to enjoy a local festival, where Shiki has an encounter with a mysterious new individual.', 0, 24),
(7, 'Personal Rule', '2025-10-10', 'The fallout from the festival incident is quickly detected by the Momotaro\'s wide-reaching surveillance network, prompting an immediate response.', 0, 24),
(7, 'Found You', '2025-10-24', 'A new threat emerges as Shinya begins actively hunting for the academy students, intent on using them for his own plan.', 0, 24),

-- KAGUYA-SAMA (Series 8)
(8, 'I Will Make You Invite Me to a Movie / Kaguya Wants to Be Stopped / Kaguya Wants It', '2019-01-12', 'Student council president Miyuki Shirogane and vice-president Kaguya Shinomiya are locked in a battle of wits. When their secretary, Chika, offers up two movie tickets, the war to make the other confess their feelings begins.', 7520, 24),
(8, 'Kaguya Wants to Trade / Chika Wants to Go Somewhere / Miyuki Wants to Hide His Ignorance', '2019-01-19', 'A male student comes to Shirogane for love advice, forcing the inexperienced president to fake his way through the consultation. Kaguya, eavesdropping on the conversation, sees a new opportunity for a battle.', 8140, 24),
(8, 'Miyuki Shirogane Still Hasn\'t Done It / Kaguya Wants to Be Figured Out / Kaguya Wants to Walk', '2019-01-26', 'Chika discovers an unwholesome magazine with a scandalous survey. Kaguya\'s coy response to the survey\'s main question sends Shirogane\'s mind reeling, as he tries to figure out if she\'s bluffing or not.', 6950, 24),
(8, 'Kaguya Wants Affection / The Student Council Wants It to Be Said / Kaguya Wants Him to Send It / Miyuki Shirogane Wants to Talk', '2019-02-02', 'Chika suggests the student council should try on costumes. Kaguya is reluctant, but when she puts on a pair of cat ears, Shirogane\'s reaction is not what she expected. A new battle begins over a forgotten smartphone.', 9010, 24),
(8, 'Kaguya Wants to Handle It / Miyuki Shirogane Wants to Show Off / Kaguya Wants to Be Covered', '2019-02-09', 'Shirogane is brilliant, but he\'s hopelessly unathletic. With an upcoming volleyball class threatening to destroy his perfect image, he secretly begs Chika to train him, leading to a disastrous and hilarious week of practice.', 8830, 24),
(8, 'Yu Ishigami Wants to Live / Chika Fujiwara Wants to Test You / Kaguya Wants to Be Noticed', '2019-02-16', 'The student council\'s reclusive treasurer, Yu Ishigami, finally makes an appearance. He\'s come to Shirogane to resign, claiming that Kaguya is trying to murder him.', 9250, 24),
(8, 'Miyuki Shirogane Wants to Work / Kaguya Wants Him to Join In / Kaguya Wants to Control It', '2019-02-23', 'Shirogane\'s "love expert" status is put to the test again when Kashiwagi\'s boyfriend returns for more advice. Kaguya, listening in, is horrified by the nonsensical strategies Shirogane proposes.', 7880, 24),
(8, 'Kaguya Wants Her to Say It / Miyuki Shirogane Can\'t Lose / Yu Ishigami Closes His Eyes', '2019-03-02', 'Term-end exams are here, and the war moves to the academic battlefield. Kaguya and Shirogane, both proud geniuses, engage in a ruthless psychological battle to see who will claim the #1 spot in the school rankings.', 9590, 24),
(8, 'Kaguya Wants to Give a Gift / Chika Fujiwara Wants to Pay a Visit / About Kaguya Shinomiya, Part 1', '2019-03-09', 'Kaguya is home sick with a cold, and Shirogane offers to bring her the class handouts. Chika warns him that a sick Kaguya becomes extremely affectionate, leading to a high-stakes game to decide who gets to visit her.', 8360, 24),
(8, 'Kaguya Won\'t Forgive / Kaguya Wants to Forgive / Miyuki Shirogane Wants to Go Somewhere', '2019-03-16', 'After a fight over the "sick visit" incident, a cold war develops between Kaguya and Shirogane. Both are too proud to apologize, so they secretly seek advice from Kashiwagi and Ishigami on how to make the other person apologize.', 7290, 24),
(8, 'Ai Hayasaka Wants to Get Soaked / Chika Fujiwara Really Wants to Eat It / Miyuki Shirogane Wants to See You / I Can\'t Hear the Fireworks, Part 1', '2019-03-23', 'Summer vacation has begun, and Kaguya is depressed, having no excuse to see Shirogane. She eagerly awaits the one event they planned: the summer fireworks festival. However, her family\'s strict rules threaten to ruin the night.', 9110, 24),
(8, 'I Cant Hear the Fireworks, Part 2 / Kaguya Doesnt Want to Avoid Him', '2019-03-30', 'Kaguya is confined to her room, devastated that she missed the fireworks. Her attendant, Hayasaka, comes up with a daring plan to sneak her out. Kaguya races to the festival, hoping it\'s not too late.', 10320, 24),

-- ELFEN LIED (Series 11)
(11, 'A Chance Encounter (Encounter)', '2004-07-25', 'Kouta is reunited with his cousin Yuka and moves in with her to start college. On a stroll, they meet a mysterious girl, Nyu, who is naked and speaks only "Nyu".', 15300, 24),
(11, 'The Sifting (Fraying)', '2004-07-31', 'The Military Police begin tracking Lucy, and her first encounter with a special forces team reveals her deadly, violent alternate personality, Lucy, who possesses invisible, razor-sharp vectors.', 16100, 24),
(11, 'Deep Feelings (Depths)', '2004-08-07', 'Kouta struggles to reconcile the sweet Nyu with the lethal Lucy. Flashbacks reveal Kouta and Lucy\'s shared tragic childhood and the origins of their deep, complicated connection.', 15950, 24),
(11, 'Confrontation (Clash)', '2004-08-14', 'The Diclonius research facility sends its most dangerous agent, Bando, to retrieve Lucy. A brutal confrontation takes place on the beach, forcing Kouta and Yuka to flee.', 17200, 24),
(11, 'The House (Reception)', '2004-08-21', 'Kouta, Yuka, and Nyu take refuge in an abandoned restaurant belonging to Kouta\'s family. Mayu, a young girl running away from her troubled home, joins their group.', 16800, 24),
(11, 'Heartfelt Warmth (Innermost)', '2004-08-28', 'Mayu shares her troubled past with the group. Meanwhile, the facility sends another Diclonius, Nana, a silent girl who views her mission as a necessary act of survival, to pursue Lucy.', 17500, 24),
(11, 'The Moment of Truth (Confrontation)', '2004-09-04', 'Nana and Lucy meet, leading to a vicious and highly destructive vector battle. Nana\'s loyalty is tested by the facility’s ruthless orders.', 18000, 24),
(11, 'The Newcomer (Start)', '2004-09-11', 'Kurama, the head scientist, decides to handle the pursuit personally. He brings in a third Diclonius, Mariko, who is psychopathic and extremely powerful.', 17450, 24),
(11, 'A Memory (Reminiscence)', '2004-09-18', 'Kouta attempts to find the key to Lucy\'s past, leading him to a painful memory about the death of his family, which he had suppressed for years.', 18550, 24),
(11, 'The Infants (Infancy)', '2004-09-25', 'The history of the Diclonius is revealed, showing how they were engineered and the chilling reason behind their powerful, violent vectors.', 19100, 24),
(11, 'The Secret Chamber (Complication)', '2004-10-02', 'The facility’s true purpose and the dark conspiracy behind the vectors are brought to light. Kouta and Yuka confront their feelings for each other amidst the chaos.', 19800, 24),
(11, 'A Descent (Vicious Circle)', '2004-10-09', 'The final confrontation begins as the military and the most dangerous Diclonius agents converge on the restaurant, threatening the safety of everyone Kouta cares about.', 20500, 24),
(11, 'No Return (Regret)', '2004-10-16', 'In the heartbreaking series finale, Kouta must make an impossible choice as the events of the past collide with the bloody reality of the present. Lucy/Nyu faces her ultimate fate.', 21900, 24),

-- GIVEN (Series 4)
(4, 'He Is Not My Kind of Person', '2019-07-11', 'High schooler Ritsuka Uenoyama is bored with everything—especially his passion for music—until he meets Mafuyu Sato, a quiet boy holding a broken guitar.', 12500, 24),
(4, 'Like Someone Is Not There', '2019-07-18', 'Uenoyama reluctantly agrees to fix Mafuyu\'s guitar and teach him chords. He finds himself drawn to Mafuyu\'s voice, which holds a powerful emotional depth.', 13150, 24),
(4, 'Somebody Else', '2019-07-25', 'Uenoyama and the band members try to figure out how to incorporate Mafuyu into the group. Haruki, the bassist, reveals a crush on the drummer, Akihiko.', 13700, 24),
(4, 'Loving Someone Is Not a Dream', '2019-08-01', 'Mafuyu\'s silence and lack of musical experience frustrate Uenoyama. A revelation about Mafuyu\'s past and the broken guitar sheds light on his quiet demeanor.', 14200, 24),
(4, 'The Time Not Spent With You', '2019-08-08', 'Haruki confronts Akihiko about his confusing behavior. Uenoyama struggles to write a song for Mafuyu\'s voice, feeling pressure from his own unacknowledged emotions.', 14850, 24),
(4, 'Empty World', '2019-08-15', 'The band prepares for their live show. Mafuyu finally tries singing, and his raw, emotional performance shocks Uenoyama and the audience.', 16000, 24),
(4, 'A Story of the Past', '2019-08-22', 'Flashbacks explore the relationship between Mafuyu and his late boyfriend, Yuki, revealing the tragic context behind Mafuyu\'s emotional repression and his powerful voice.', 15550, 24),
(4, 'I Was There', '2019-08-29', 'Uenoyama finally understands the depth of Mafuyu\'s grief. He realizes his feelings for Mafuyu are intertwined with the song he wrote and the need to help Mafuyu move forward.', 16400, 24),
(4, 'A Line in the Sand', '2019-09-05', 'After the concert, Mafuyu begins to process his feelings. Uenoyama and Mafuyu have an emotional encounter backstage, leading to a major turning point in their relationship.', 17200, 24),
(4, 'The World Change', '2019-09-12', 'The band deals with the aftermath of the successful live show. Uenoyama and Mafuyu navigate their new feelings, forcing the band to face their uncertain future.', 17950, 24),
(4, 'The Song of a Snowfall', '2019-09-19', 'Mafuyu and Uenoyama try to balance their relationship with their musical commitments. The entire band is focused on writing new music, but the creative pressure mounts.', 18500, 24);
UNLOCK TABLES;


--
-- Table structure for table `actors`
--
CREATE TABLE `actors` (
    `actors_id` INT NOT NULL AUTO_INCREMENT,
    `last_name` VARCHAR(50),
    `first_name` VARCHAR(50),
    `gender` VARCHAR(10),
    `date_of_birth` DATE,
    `place_of_birth` VARCHAR(100),
    `agency` VARCHAR(100),
    PRIMARY KEY (`actors_id`)
);

--
-- Dumping data for table `actors`
--
LOCK TABLES `actors` WRITE;
INSERT INTO actors (last_name, first_name, gender, date_of_birth, place_of_birth, agency)
VALUES
('Furukawa', 'Makoto', 'Male', '1989-09-29', 'Kumamoto Prefecture', 'Japan Toy''s Factory'), -- ONE PUNCH MAN -- !! also in KAGUYA SAMA
('Ishikawa', 'Kaito', 'Male', '1993-10-13', 'Bunkyo, Tokyo, Japan', 'Stay Luck'),
('Kaji', 'Yūki', 'Male', '1985-09-03', 'Tokyo, Japan', 'VIMS'),
('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'), -- also in MY HERO ACADEMIA and SPY X FAMILY
('Sakamoto', 'Maaya', 'Female', '1980-03-31', 'Tokyo, Japan', 'Flying Dog'), -- OURAN HIGH SCHOOL HOST CLUB
('Miyano', 'Mamoru', 'Male', '1983-06-08', 'Saitama Prefecture, Japan', 'Ken-On'),
('Matsukaze', 'Masaya', 'Male', '1976-09-09', 'Fukushima Prefecture, Japan', 'Aoni Production'),
('Suzumura', 'Kenichi', 'Male', '1974-09-12', 'Niigata Prefecture, Japan', 'INTENTION'), -- also in DEMON SLAYER
('Hanae', 'Natsuki', 'Male', '1991-06-26', 'Kanagawa Prefecture, Japan', 'Across Entertainment'), -- DEMON SLAYER -- !! hanae natsuki is also tougen anki
('Kito', 'Akari', 'Female', '1994-10-16', 'Nagoya, Aichi Prefecture, Japan', 'Raccoon Dog'),
('Shimono', 'Hiro', 'Male', '1980-04-21', 'Tokyo, Japan', 'I''m Enterprise'),
('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'), -- also in GACHIAKUTA and GIVEN -- ('Suzumura', 'Kenichi', 'Male', '1974-09-12', 'Niigata Prefecture, Japan', 'INTENTION'),
('Yamashita', 'Daiki', 'Male', '1989-09-07', 'Hamamatsu, Shizuoka Prefecture, Japan', 'Arts Vision'), -- MY HERO ACADEMIA
('Okamoto', 'Nobuhiko', 'Male', '1986-10-24', 'Tokyo, Japan', 'Raccoon Dog'),
('Sakura', 'Ayane', 'Female', '1994-01-29', 'Shibuya, Tokyo, Japan', 'Aoni Production'), -- ('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'),
('Eguchi', 'Takuya', 'Male', '1987-05-22', 'Setagaya, Tokyo, Japan', '81 Produce'), -- SPY X FAMILY
('Tanezaki', 'Atsumi', 'Female', '1990-09-27', 'Oita Prefecture, Japan', 'Tokyo Actor''s Consumer''s Cooperative Society / Haikyo'), -- 3
('Matsuda', 'Kenichiro', 'Male', '1978-09-22', 'Saitama Prefecture, Japan', 'Arts Vision'),
('Ono', 'Kensho', 'Male', '1989-10-05', 'Fukuoka, Fukuoka Prefecture, Japan', 'Animo Produce'), -- ('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'),
('Ichikawa', 'Aoi', 'Male', '1991-10-02', 'Fukuoka, Japan', 'Office Osawa'), -- GACHIAKUTA
('Konishi', 'Katsuyuki', 'Male', '1973-04-21', 'Kanagawa Prefecture, Japan', 'Ken Production'),
('Hanamori', 'Yumiri', 'Female', '1997-08-29', 'Saitama Prefecture, Japan', 'Aster Nine'),
('Morikawa', 'Toshiyuki', 'Male', '1967-05-26', 'Toyama Prefecture, Japan', 'Axlone'), -- ('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'),
('Ura', 'Kazuki', 'Male', '1995-10-18', 'Hyogo Prefecture, Japan', 'VIMS'), -- TOUGEN ANKI
('Kamiya', 'Hiroshi', 'Male', '1975-01-28', 'Yokohama, Kanagawa Prefecture, Japan', 'Aoni Production'),
('Nishiyama', 'Kotaro', 'Male', '1989-06-07', 'Niigata Prefecture, Japan', 'Aoni Production'),
('Iwami', 'Manaka', 'Female', '1998-11-30', 'Hiroshima Prefecture, Japan', 'Raccoon Dog'), -- ('Hanae', 'Natsuki', 'Male', '1991-06-26', 'Kanagawa Prefecture, Japan', 'Across Entertainment'),
('Koga', 'Aoi', 'Female', '1998-08-24', 'Saga Prefecture, Japan', '81 Produce'), -- KAGUYA SAMA
('Kohara', 'Konomi', 'Female', '1992-06-28', 'Chiba Prefecture, Japan', 'Office Osawa'),
('Suzuki', 'Ryota', 'Male', '1998-03-29', 'Aichi Prefecture, Japan', 'INTENTION'),
('Tomita', 'Miyu', 'Female', '1999-11-15', 'Saitama Prefecture, Japan', 'Amuse'), -- ('Furukawa', 'Makoto', 'Male', '1989-09-29', 'Kumamoto Prefecture', 'Japan Toy''s Factory'),
('Katou', 'Keiko', 'Female', '1969-02-06', 'Tokyo, Japan', 'Production Baobab'), -- PARADISE KISS
('Kawanishi', 'Kenn', 'Male', '1965-02-14', 'Tokyo, Japan', 'Aoni Production'),
('Fukuyama', 'Jun', 'Male', '1978-11-26', 'Hiroshima Prefecture, Japan', 'Axlone'),
('Taneda', 'Risa', 'Female', '1988-07-12', 'Tokyo, Japan', 'Office Osawa'),
('Horie', 'Yui', 'Female', '1987-09-05', 'Tokyo, Japan', 'Arts Vision'), -- also in GIVEN
('Ichimichi', 'Mikako', 'Female', '1996-10-26', 'Tokyo, Japan', 'Haikyo'), -- GIVEN
('Fukuhara', 'Kaori', 'Female', '1981-07-29', 'Tokyo, Japan', '81 Produce'),
('Sugita', 'Tomokazu', 'Male', '1980-10-11', 'Tokyo, Japan', 'Haikyo'),  -- ('Horie', 'Yui', 'Female', '1987-09-05', 'Tokyo, Japan', 'Arts Vision'), -- ('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'),
('Kobayashi', 'Sanae', 'Female', '1980-01-26', 'Hamakita, Shizuoka, Japan', 'Sigma Seven'), -- ELFEN LIED
('Suzuki', 'Chihiro', 'Male', '1977-02-17', 'Yamagata Prefecture, Japan', 'Haikyou'),
('Noto', 'Mamiko', 'Female', '1980-02-06', 'Kanazawa, Ishikawa, Japan', 'Office Osawa'),
('Okamura', 'Akemi', 'Female', '1969-03-12', 'Tokyo, Japan', 'Mausu Promotion'),
('Gibu', 'Yūko', 'Female', '1981-01-28', 'Kobe, Hyogo, Japan', 'Freelance');
UNLOCK TABLES;



--
-- Table structure for table `likedEpisode`
--

CREATE TABLE `likedEpisode` (
  `like_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `episode_id` INT,
  `date_added` DATE DEFAULT (CURRENT_DATE()),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
  FOREIGN KEY (`episode_id`) REFERENCES `episodes`(`episode_id`)
);

--
-- Dumping data for table `likedEpisode`
--
LOCK TABLES `likedEpisode` WRITE;
INSERT INTO LikedEpisode (user_id, episode_id)
VALUES
(1, 2),
(2, 5),
(2, 6),
(4, 3),
(5, 2),
(6, 7),
(6, 4),
(8, 1),
(9, 9),
(10, 8);
UNLOCK TABLES;



--
-- Table structure for table `watchHistory`
--

CREATE TABLE `watchHistory` (
    `watch_id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `episode_id` INT NOT NULL,
    `watch_date` DATE DEFAULT (CURRENT_DATE()),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    FOREIGN KEY (`episode_id`) REFERENCES `episodes`(`episode_id`)
);

--
-- Dumping data for table `watchHistory`
--
LOCK TABLES `watchHistory` WRITE;
INSERT INTO watchHistory (user_id, episode_id, watch_date)
VALUES
(1, 1, '2025-11-22'),
(1, 2, '2025-11-22'),
(1, 3, '2025-11-22'),
(1, 4, '2025-11-23'),
(2, 1, '2025-11-24'),
(2, 3, '2025-11-25'),
(3, 2, '2025-11-23'),
(4, 1, '2025-11-25'),
(5, 2, '2025-11-25'),
(6, 1, '2025-11-25');
UNLOCK TABLES;



--
-- Table structure for table `actorSeries`
--
CREATE TABLE `actorSeries` (
    `act_id` INT NOT NULL AUTO_INCREMENT,
    `actors_id` INT NOT NULL,
    `series_id` INT NOT NULL,
    `character_name` VARCHAR(100),
    PRIMARY KEY (`act_id`),
    FOREIGN KEY (`actors_id`) REFERENCES `actors`(`actors_id`),
    FOREIGN KEY (`series_id`) REFERENCES `series`(`series_id`)
);

--
-- Dumping data for table `actorSeries`
--
LOCK TABLES `actorSeries` WRITE;
INSERT INTO actorSeries(actors_id, series_id, character_name)
VALUES
(1, 1, 'Saitama'), -- ONE PUNCH MAN
(5, 2, 'Fujioka Haruhi'), -- OURAN
(9, 3, 'Tanjiro Kamado'), -- DEMON SLAYER
(13, 4, 'Izuku Midoriya'), -- MY HERO ACADEMIA
(16, 5, 'Loid Forger'), -- SPY X FAMILY
(20, 6, 'Rudo'), -- GACHI AKUTA
(24, 7, 'Shiki Ichinose'), -- TOUGEN ANKI
(28, 8, 'Kaguya Shinomiya'), -- KAGUYA SAMA
(32, 9, 'Yukari Hayasaka/Caroline'), -- PARADISE KISS
(37, 10, 'Mafuyu Satou'), -- GIVEN
(40, 11, 'Kaede/Lucy/Nyu'); -- ELFEN LIED
UNLOCK TABLES;



--
-- Table structure for table `episodeReviews`
--
CREATE TABLE `episodeReviews` (
    `review_id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `episode_id` INT NOT NULL,
    `user_review` TEXT,
    `date_reviewed` DATE DEFAULT (CURRENT_DATE()),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    FOREIGN KEY (`episode_id`) REFERENCES `episodes`(`episode_id`)
);

--
-- Dumping data for table `episodeReviews`
--
LOCK TABLES `episodeReviews` WRITE;
INSERT INTO episodeReviews(user_id, episode_id, user_review, date_reviewed)
VALUES
(1, 1, 'OMG THE EPISODE WAS SO GOOD WAADOASKDASKDASD I LOVE THE MAIN ACTOR AND THE WAY THEY ACTED WAS SO GOOD, THE ANIMATION WAS CRAZILY OASNDASM AMAZING?!?!??!? WOW LIKE WHAT HOW DID THEY DO ARIGATOGUZAIMASU FOR THIS AMAZING ANIME I HOPE FOR MORE IN THE FUTURE, WILL REWATCH EPISODE 1 2 3 45678901201238239120312993121 1 1 MILLION EPISODES ALL DAY NONSTOP','2025-11-22'),
(1, 2, 'Not my favorite, but the plot twist was unexpected.','2025-11-22'),
(1, 3, 'WOWOWOWOW LOVE THE EPISODE!','2025-11-22'),
(1, 4, 'Can''t wait for the next episode!','2025-11-23'),
(2, 1, 'Meh, it was okay.','2025-11-24'),
(2, 3, 'THE ANIMATION WAS GOOD!','2025-11-25'),
(3, 2, 'The character development is slow, but I am invested.','2025-11-23'),
(4, 1, 'Funny episode! Had me laughing the whole time.','2025-11-25'),
(5, 2, 'The ending shocked me! Can''t believe that happened.','2025-11-25'),
(6, 1, 'Good episode!','2025-11-25');
UNLOCK TABLES;

--
-- End of dump
--