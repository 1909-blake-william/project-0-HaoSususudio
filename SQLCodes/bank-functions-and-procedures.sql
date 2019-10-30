


CREATE OR REPLACE TRIGGER user_id_trig
BEFORE INSERT OR UPDATE ON user_login
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        SELECT user_id_seq.nextval INTO :new.user_id FROM dual;
    ELSIF UPDATING THEN
        SELECT :old.user_id INTO :new.user_id FROM dual;
    END IF;
END;
/
