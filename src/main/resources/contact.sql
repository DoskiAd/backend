DROP TABLE IF EXISTS contacts;

CREATE TABLE IF NOT EXISTS contacts (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL,
  val VARCHAR(50) NOT NULL,
  item_id INT(11) NOT NULL,
  
  PRIMARY KEY (id),
  UNIQUE KEY contact_id_uindex (id),
  
  FOREIGN KEY (item_id)  REFERENCES items (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contacts (name, val, item_id) VALUE ('мобильный','123456789','1');
INSERT INTO contacts (name, val, item_id) VALUE ('почта','vasily@mail.ru','1');
INSERT INTO contacts (name, val, item_id) VALUE ('мобильный','987654321','2');
