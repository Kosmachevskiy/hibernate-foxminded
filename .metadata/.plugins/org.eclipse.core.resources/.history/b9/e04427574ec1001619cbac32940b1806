--<ScriptOptions statementTerminator=";"/>

CREATE TABLE dbuser (
		user_id NUMERIC(5 , 0) NOT NULL,
		username VARCHAR(20) NOT NULL,
		created_by VARCHAR(20) NOT NULL,
		created_date DATE NOT NULL
	);

CREATE UNIQUE INDEX dbuser_pkey ON dbuser (user_id ASC);

ALTER TABLE dbuser ADD CONSTRAINT dbuser_pkey PRIMARY KEY (user_id);

