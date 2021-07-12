drop table if exists CARD;
drop table if exists BankAccounts;
drop table if exists CLIENTS;

create table CLIENTS
    (ID IDENTITY primary key,
    FIRSTNAME VARCHAR(30) not null,
    LASTNAME VARCHAR(30) not null);

create table BankAccounts
    (ID IDENTITY primary key,
    ACCOUNTNUMBER BIGINT not null,
    CLIENT_ID BIGINT not null references CLIENTS(ID));

create table CARDS
    (ID IDENTITY primary key,
    NUMBER BIGINT auto_increment not null,
    ACCOUNT_ID BIGINT not null references BankAccounts(ID),
    BALANCE DECIMAL(15, 2) DEFAULT 0);

INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Vladislav', 'Skripkin');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Ivan', 'Ivanov');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Petr', 'Petrov');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Vasiliy', 'Sidorov');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Anatoliy', 'Kuzmenko');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Aleksey', 'Gusev');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Aleksandr', 'Baranov');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Mihail', 'Kozlov');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Andrey', 'Nikolaev');
INSERT INTO CLIENTS(FIRSTNAME, LASTNAME) VALUES ('Evgeniy', 'Motrosov');

INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (1111111111111111, 1);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (2222222222222222, 2);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (3333333333333333, 3);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (4444444444444444, 4);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (5555555555555555, 5);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (6666666666666666, 6);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (7777777777777777, 7);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (8888888888888888, 8);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (9999999999999999, 9);
INSERT INTO BankAccounts(ACCOUNTNUMBER, CLIENT_ID) VALUES (0000000000000000, 10);

INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000001, 1);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000002, 1);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000003, 1);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000004, 2);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000005, 3);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000006, 3);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000007, 4);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000008, 5);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000009, 6);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000010, 7);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000011, 7);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000012, 7);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000013, 7);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000014, 8);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000015, 8);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000016, 9);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000017, 9);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000018, 9);
INSERT INTO Cards(NUMBER, ACCOUNT_ID) VALUES (1000000000000019, 9);
