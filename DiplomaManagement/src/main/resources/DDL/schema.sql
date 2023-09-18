CREATE DATABASE IF NOT EXISTS `mydatabase`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `role` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE student (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  year INT NOT NULL,
  grade DOUBLE NOT NULL,
  courses INT NOT NULL,
  username VARCHAR(255) NOT NULL
);

CREATE TABLE professor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  speciality VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL
);

CREATE TABLE subjects (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  objectives TEXT NOT NULL,
  professorid INT NOT NULL,
  FOREIGN KEY (professorid) REFERENCES professor (id)
);

CREATE TABLE application (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student INT NOT NULL,
  subject INT NOT NULL,
  professor INT NOT NULL,
  FOREIGN KEY (student) REFERENCES student (id),
  FOREIGN KEY (subject) REFERENCES subjects (id),
  FOREIGN KEY (professor) REFERENCES professor (id)
);

CREATE TABLE thesis (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student INT NOT NULL,
  professor INT NOT NULL,
  subject INT NOT NULL,
  implementationgrade DOUBLE NOT NULL,
  reportgrade DOUBLE NOT NULL,
  presentationgrade DOUBLE NOT NULL,
  totalgrade DOUBLE NOT NULL,
  FOREIGN KEY (student) REFERENCES student (id),
  FOREIGN KEY (professor) REFERENCES professor (id),
  FOREIGN KEY (subject) REFERENCES subjects (id)
);

CREATE TABLE application_subject (
  application_id INT,
  subject_id INT,
  FOREIGN KEY (application_id) REFERENCES application (id),
  FOREIGN KEY (subject_id) REFERENCES subjects (id)
);
