CREATE SCHEMA `utility_db` ;

CREATE TABLE `utility_db`.`department` (
  `department_code` varchar(8) NOT NULL ,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`department_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `utility_db`.`department` (`department_code`, `name`) VALUES ('FIN', 'Finance');
INSERT INTO `utility_db`.`department` (`department_code`, `name`) VALUES ('ENG', 'Engineering');
INSERT INTO `utility_db`.`department` (`department_code`, `name`) VALUES ('HR', 'Human Resources');

