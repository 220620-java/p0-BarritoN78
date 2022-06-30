/*create schema project_0_banking_app;

--tbl_users DDL
create table tbl_Users(
	userID serial primary key,
	userEmail varchar(30),
	userPassword varchar(30)
);

alter table tbl_users
	alter column useremail type varchar(50);

alter table tbl_users 
	add column userFName varchar(25),
	add column userMInit char(1),
	add column userLName varchar(25);

--tbl_accounts DDL
create table tbl_Accounts(
	accountID serial primary key,
	accountType varchar(25),
	accountNotes text,
	accountBalance decimal,
	userID integer references tbl_Users
);

--tbl_transactions DDL
create table tbl_Transactions(
	transID serial primary key,
	transType varchar(25),
	transPreBalance decimal,
	transPostBalance decimal,
	transNotes text,
	accountID integer references tbl_Accounts
); */

