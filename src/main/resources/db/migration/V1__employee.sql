CREATE TABLE employee (
  id BIGINT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_employee PRIMARY KEY (id)
);

INSERT INTO employee (id,first_name,last_name) VALUES (1, 'employee1','lastname1');
INSERT INTO employee (id,first_name,last_name) VALUES (2, 'employee2','lastname2');
INSERT INTO employee (id,first_name,last_name) VALUES (3, 'employee3','lastname3');
INSERT INTO employee (id,first_name,last_name) VALUES (4, 'employee4','lastname4');
