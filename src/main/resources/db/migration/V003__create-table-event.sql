CREATE TABLE event(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  delivery_id BIGINT NOT NULL,
  descricao TEXT NOT NULL,
  registration_moment DATETIME NOT NULL
);

ALTER TABLE event ADD CONSTRAINT fk_event_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (id);