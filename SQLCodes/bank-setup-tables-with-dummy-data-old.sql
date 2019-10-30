DROP SEQUENCE user_id_seq;
DROP TRIGGER user_id_trig;
DROP PROCEDURE new_user_login;

DROP TABLE  user_info;
DROP TABLE  user_login;

CREATE SEQUENCE user_id_seq; -- used as id assign value 
CREATE TABLE user_login
(
    username varchar2(20) PRIMARY KEY,
    secure_key varchar2(24) NOT NULL,
    salt_for_password varchar2(24) NOT NULL,
    role varchar2(10) DEFAULT 'customer' NOT NULL
);

  CREATE TABLE user_info
(
    user_id int PRIMARY KEY,
    username varchar2(20) REFERENCES user_login(username),
    first_name varchar2(20),
    last_name varchar2(20),
    phone_number varchar2(20),
    email varchar2(50) 
);
---------------------Procedures----------------------------------
CREATE OR REPLACE TRIGGER user_id_trig
BEFORE INSERT OR UPDATE ON user_info
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        SELECT user_id_seq.nextval INTO :new.user_id FROM dual;
    ELSIF UPDATING THEN
        SELECT :old.user_id INTO :new.user_id FROM dual;
    END IF;
END;
/
CREATE OR REPLACE PROCEDURE new_user_login
(
    username IN varchar2,
    secure_key IN varchar2,
    salt IN varchar2
)
AS
BEGIN
    INSERT INTO user_login(username, secure_key, salt_for_password)
    VALUES (username, secure_key, salt);
END;
/

CREATE OR REPLACE PROCEDURE new_user_info
(
    username IN varchar2,
    first_name IN varchar2,
    last_name IN varchar2,
    phone_number IN varchar2,
    email IN varchar2,
    generated_id OUT int
)
AS
BEGIN
    INSERT INTO user_info(username, first_name, last_name, phone_number, email)
    VALUES (username, first_name, last_name, phone_number, email)
    RETURNING user_id INTO generated_id;
END;
/


--------------------------------------------------------------
SET SERVEROUTPUT ON
DECLARE gen_id int;
BEGIN
    new_user_login('mickey', 'RR1lFw6Ll8bbyBX/RfwQTQ==' ,'ChnFQQqYl8JUKBuRlNHF1A==');
    new_user_login('chip', 'YKdlCHGaXu4k18nU5evfBA==' ,'oAkxJeAq8NpTtZsvF+cNJg==');
    new_user_login('dale', '3XXMb2TNK8K5ssrmcPepBg==' ,'XiXA5pB+ftrTQqEhpDs/qQ==');
    new_user_login('pluto', '9GWplpIvllyQQAy4CiUgWg==', 'z8l6xGh2lRNF6+68jp5gzA==');
    new_user_login('donaldduck', 'uZH898cC8VYZVHeoCzX6LQ==','flo/5f/mwEKZQViCAhp7mQ==');
    new_user_login('whinniethepooh', 'gY5npUOBFZ3GnfS6VX7nIQ==','tyWhPi0AbTfNERQzKeRqcQ==');
    new_user_info('mickey', 'Mickey', 'von Sewer', '+1-000-265-9875', 'mickeymouse@disney.com', gen_id);
    new_user_info('chip', 'Chip', 'Bigtooth', '516.454.0981', 'info@chippendales.com', gen_id );
    new_user_info('dale', 'Dale', 'Rednose', '516.454.0981', 'daletherednosechipmunk@chippendales.com', gen_id);
    new_user_info('pluto', 'Pluto', 'Bonedigger', 'barkwoofbarkbark', 'pluto@disney.com', gen_id);
    new_user_info('donaldduck', 'Donald', 'Truck', '25687', 'potus@wh.gov', gen_id);
    new_user_info('whinniethepooh', 'Winnie', 'Xi', '8699504523', 'wxjp@163.com', gen_id);
END;
/
UPDATE  user_login
SET     role = 'admin'
WHERE   username = 'mickey';
UPDATE  user_login
SET     role = 'manager'
WHERE   username = 'chip';
UPDATE  user_login
SET     role = 'manager'
WHERE   username = 'dale';


COMMIT;

SELECT * FROM user_login;
SELECT * FROM user_info;
