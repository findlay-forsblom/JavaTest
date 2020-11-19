CREATE DATABASE mydb2;
\connect  mydb2 ;
CREATE EXTENSION pgcrypto;
CREATE TABLE Users
    (
      ID INT ,
      Username VARCHAR(255) ,
      Password VARCHAR(255)
    );
    
INSERT INTO Users(ID,Username,Password)  
Values(0, 'test', (SELECT crypt('test', gen_salt('bf'))));