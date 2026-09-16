CREATE SCHEMA `hr_db` ;

CREATE TABLE `hr_db`.`employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `department_code` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `hr_db`.`employee` (`emp_id`, `name`, `department_code`) VALUES ('101', 'John Doe', 'FIN');
INSERT INTO `hr_db`.`employee` (`emp_id`, `name`, `department_code`) VALUES ('102', 'Jane Smith', 'ENG');
INSERT INTO `hr_db`.`employee` (`emp_id`, `name`, `department_code`) VALUES ('103', 'Ravi Perera', 'FIN');
INSERT INTO `hr_db`.`employee` (`emp_id`, `name`, `department_code`) VALUES ('104', 'Alex Fernando', 'MKT');
