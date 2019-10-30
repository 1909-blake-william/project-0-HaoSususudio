CREATE OR REPLACE PROCEDURE update_account
(
    owner_username IN varchar2,
    balance IN number,
    account_status IN varchar2,
    generated_id OUT int
)
AS
BEGIN
    UPDATE account
    SET balance = balance, account_status = account_status
    WHERE owner_username = owner_username
    RETURNING account_id INTO generated_id;
END;
/