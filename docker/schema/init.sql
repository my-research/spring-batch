-- Create the employees table
CREATE TABLE employees
(
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(100) NOT NULL
);

-- Insert data into the employees table
INSERT INTO employees (name)
VALUES ('John Doe'),
       ('Jane Smith'),
       ('Michael Johnson');
