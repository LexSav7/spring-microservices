# CREATE DATABASE IF NOT EXISTS spring_microservices;
# CREATE USER IF NOT EXISTS 'spring_microservices'@'localhost:3307' IDENTIFIED WITH mysql_native_password BY 'my-strong-spring_microservices:3307';
# GRANT ALL PRIVILEGES ON spring_microservices.* TO 'spring_microservices'@'localhost:3307';
#
# USE petclinic;

CREATE TABLE IF NOT EXISTS vets (
  id BIGINT(8) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS specialties (
  id BIGINT(8) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS vet_specialties (
  vet_id BIGINT(8) UNSIGNED NOT NULL,
  specialty_id BIGINT(8) UNSIGNED NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id,specialty_id)
) engine=InnoDB;
