create table delivery(
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     client_id BIGINT NOT NULL,
     rate DECIMAL(10, 2) NOT NULL,
     d_status VARCHAR(20) NOT NULL,
     order_date DATETIME NOT NULL,
     finalization_date DATETIME,
     recipient_name VARCHAR(60) NOT NULL,
     recipient_street VARCHAR(255) NOT NULL,
     recipient_number VARCHAR(30) NOT NULL,
     recipient_complement VARCHAR(60) NOT NULL,
     recipient_district VARCHAR(30) NOT NULL
);

ALTER TABLE delivery ADD CONSTRAINT fk_delivery_client FOREIGN KEY (client_id) REFERENCES client (id);