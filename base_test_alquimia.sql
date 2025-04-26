CREATE DATABASE test-alquimia;
DROP TABLE IF EXISTS  users;
CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	social_reason VARCHAR(100) NOT NULL,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers(
	id SERIAL PRIMARY KEY,
	fk_user INT NOT NULL,
	full_name VARCHAR(100) NOT NULL,
	identification_type VARCHAR(5) NOT NULL,
	identification VARCHAR(15) NOT NULL UNIQUE,
	email VARCHAR(50) NOT NULL,
	cellphone VARCHAR(20),
        province VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	address VARCHAR(100) NOT NULL,
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_user
        FOREIGN KEY (fk_user)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


DROP TABLE IF EXISTS customer_address;
CREATE TABLE customer_address(
	id SERIAL PRIMARY KEY,
	fk_customer INT NOT NULL,
	main_address BOOLEAN DEFAULT FALSE,
	province VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	address VARCHAR(100) NOT NULL,
	CONSTRAINT fk_customer
        FOREIGN KEY (fk_customer)
        REFERENCES customers(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
