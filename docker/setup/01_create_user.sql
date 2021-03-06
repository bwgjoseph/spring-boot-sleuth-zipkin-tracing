-- Change session to Pluggable DataBase (PDB)
ALTER SESSION SET container = ORCLPDB1;

-- Create user
CREATE USER SLEUTH_DEV IDENTIFIED BY password1;
GRANT CONNECT, RESOURCE, DBA TO SLEUTH_DEV;
GRANT CREATE SESSION TO SLEUTH_DEV;
GRANT UNLIMITED TABLESPACE TO SLEUTH_DEV;
