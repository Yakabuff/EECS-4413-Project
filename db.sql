DROP TABLE Book;
CREATE TABLE Book 
(bid VARCHAR(20) NOT NULL,
title VARCHAR(60) NOT NULL,
price INTEGER NOT NULL,
category VARCHAR(20) check (category in ('Science','Fiction','Engineering')) NOT NULL,
PRIMARY KEY(bid));


INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002','Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003','Mechanics' ,100,'Engineering');

DROP TABLE Address;
CREATE TABLE Address (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
city VARCHAR(20),
PRIMARY KEY(id)
);


INSERT INTO Address (street, province, country, zip, phone) VALUES ('123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567', 'Toronto');
INSERT INTO Address (street, province, country, zip, phone) VALUES ('445 Avenue rd', 'ON',
'Canada', 'M1C 6K5' ,'416-123-8569', 'Toronto');
INSERT INTO Address (street, province, country, zip, phone) VALUES ('789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568', 'Toronto');


DROP TABLE User;

CREATE TABLE User (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
email VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
fname VARCHAR(20) NOT NULL,
lname VARCHAR(20) NOT NULL,
daddressid INTEGER NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (daddressid) REFERENCES Address(id)
);

INSERT INTO User (email, password, fname, lname, daddressid) VALUES ('johnwhite@proj.com', 'passpass', 'John', 'White', '1');
INSERT INTO User (email, password, fname, lname, daddressid) VALUES ('peterblack@proj.com', 'wordword', 'Peter', 'Black', '2');
INSERT INTO User (email, password, fname, lname, daddressid) VALUES ('andygreen@proj.com', 'password', 'Andy', 'Green', '3');


DROP TABLE PO;
CREATE TABLE PO (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
uid INTEGER NOT NULL,
status VARCHAR(20) check (status in ('ORDERED','PROCESSED','DENIED')) NOT NULL,
address INTEGER NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (uid) REFERENCES User (id),
FOREIGN KEY (address) REFERENCES Address (id)
);

INSERT INTO PO (uid, status, address) VALUES ('1', 'PROCESSED', '1');
INSERT INTO PO (uid, status, address) VALUES ('2', 'DENIED', '2');
INSERT INTO PO (uid, status, address) VALUES ('3', 'ORDERED', '3');


CREATE TABLE POItem (
id INTEGER NOT NULL,
bid VARCHAR(20) NOT NULL,
price INTEGER NOT NULL,
FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);

INSERT INTO POItem (id, bid, price) VALUES (1, 'b001', '20');
INSERT INTO POItem (id, bid, price) VALUES (2, 'b002', '201');
INSERT INTO POItem (id, bid, price) VALUES (3, 'b003', '100');


CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
bid varchar(20) not null ##########REFERENCES Book.bid,
eventtype VARCHAR(20) check (eventtype in ('VIEW','CART','PURCHASE')) NOT NULL,
FOREIGN KEY(bid) REFERENCES Book(bid)
);

INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b001', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', 'b001', 'PURCHASE');
