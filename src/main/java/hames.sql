CREATE TABLE status
(
  id bigint NOT NULL,
  status_name character varying(255),
  status_value bigint,
  status_description character varying(255),
  CONSTRAINT status_pkey PRIMARY KEY (id)
);

INSERT INTO status values(1,'Active Staff','10','Staff Status');
INSERT INTO status values(2,'In-active Staff','11','Staff Status');
INSERT INTO status values(3,'Active Staff Role','20','Staff Role Status');
INSERT INTO status values(4,'In-active Staff Role','21','Staff Role Status');
INSERT INTO status values(5,'Active Customer','30','Customer Status');
INSERT INTO status values(6,'In-active Customer','31','Customer Status');