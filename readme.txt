create table first_practical_project.books(
id_book INT (6) unsigned auto_increment primary key,
title varchar(30) not null,
description varchar(30) not null);

create table first_practical_project.authors(
id_author INT (6) unsigned auto_increment primary key,
first_Name varchar(30) not null,
last_Name varchar(30) not null);

create table first_practical_project.books_authors(
id_books_authors INT (6) unsigned auto_increment primary key,
id_book INT (6) unsigned,
id_author INT (6)unsigned,
foreign key (id_book) references books (id_book),
foreign key (id_author) references authors (id_author)
);

insert into first_practical_project.authors
(firstname , lastName)
values
('Jonas', 'Biliunas');

insert into first_practical_project.books
(title , description )
values
('Kliudziau', 'apsakymas');

insert into first_practical_project.authors
(first_Name , last_Name )
values
('Zita', 'Vasiliauskaitė'),
('Rosita', 'Lekavičienė'),
('Junona Silvija', 'Almonaitienė'),
('Dalia', 'Antinienė');

insert into first_practical_project.books
(title , description )
values
('Marketingas', 'mokslinė literatūra'),
('Bendravimo psichologija', 'mokslinė literatūra'),
('Mūsiškiai', 'grožinė literatūra');
