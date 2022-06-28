/*create schema project_0_banking_app;

create table tbl_Users(
	userID serial primary key,
	userEmail varchar(30),
	userPassword varchar(30)
);

create table tbl_Accounts(
	accountID serial primary key,
	accountType varchar(25),
	accountNotes text,
	accountBalance decimal,
	userID integer references tbl_Users
);

create table tbl_Transactions(
	transID serial primary key,
	transType varchar(25),
	transPreBalance decimal,
	transPostBalance decimal,
	transNotes text,
	accountID integer references tbl_Accounts
); */
