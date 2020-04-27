GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
CREATE DATABASE IF NOT EXISTS employeedb;
USE employeedb;
CREATE TABLE `employeedb`.`employee` (
  `id` INT NOT NULL,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


  CREATE TABLE `employeedb`.`health` (
    `id` INT NOT NULL,
    `status` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

INSERT INTO health(id, status) VALUES (1,'OK');
