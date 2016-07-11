create role sudoku login
  encrypted password 'md5e8cc97093d193468389444d8c194d799'
  nosuperuser inherit nocreatedb nocreaterole noreplication;

create database "sudoku-web" with owner = sudoku;

create table sudokupuzzles (
	problem varchar(89),
	solution varchar(89)
);

create unique index problem_un on sudokupuzzles(problem);
