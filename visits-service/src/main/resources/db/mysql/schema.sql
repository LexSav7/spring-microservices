# CREATE DATABASE IF NOT EXISTS spring_microservices;
# CREATE USER IF NOT EXISTS 'spring_microservices'@'localhost:3307' IDENTIFIED WITH mysql_native_password BY 'my-strong-spring_microservices:3307';
# GRANT ALL PRIVILEGES ON spring_microservices.* TO 'spring_microservices'@'localhost:3307';
#
# USE petclinic;

CREATE TABLE IF NOT EXISTS visits (
  id BIGINT(8) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id BIGINT(8) UNSIGNED NOT NULL,
  date DATE,
  description VARCHAR(2048)
) engine=InnoDB;

