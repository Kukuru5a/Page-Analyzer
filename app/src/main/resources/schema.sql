DROP TABLE IF EXISTS tbl;

CREATE TABLE tbl(
id INT PRIMARY KEY,
name varchar(255) NOT NULL,
description text
);

DROP TABLE IF EXISTS urls;

CREATE TABLE urls(
id INT PRIMARY KEY,
name varchar(255),
created_at TIMESTAMP
);