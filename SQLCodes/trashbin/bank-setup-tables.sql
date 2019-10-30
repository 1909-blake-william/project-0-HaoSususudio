DROP SEQUENCE bank_users_id_seq;
CREATE SEQUENCE bank_users_id_seq; -- used as user_id assign value from 

DROP TABLE  bank_users;
CREATE TABLE bank_users (
    user_id INT PRIMARY KEY, --Cannot use AUTO_INCREMENT in Oracle SQL, assign a value form bank_users_id_seq in Java
    username VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(20) NOT NULL,
    role VARCHAR2(15) DEFAULT 'customer' NOT NULL 
);

SELECT * FROM bank_users