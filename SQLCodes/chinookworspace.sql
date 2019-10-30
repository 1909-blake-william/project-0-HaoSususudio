DROP TYPE t_table; 
DROP TYPE t_record;
create or replace type t_record as object (
  i number,
  n varchar2(30)
);
/
create or replace type t_table as table of t_record;
/
create or replace function return_table return t_table as
  v_ret   t_table;
begin
    v_ret  := t_table();
    v_ret.extend; v_ret(v_ret.count) := t_record(1, 'one'  );
    v_ret.extend; v_ret(v_ret.count) := t_record(2, 'two'  );
    v_ret.extend; v_ret(v_ret.count) := t_record(3, 'three');
    return v_ret;
    end return_table;
/