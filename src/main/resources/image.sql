DROP TABLE IF EXISTS images;

CREATE TABLE IF NOT EXISTS images (
  id INT(11) NOT NULL AUTO_INCREMENT,
  file_name VARCHAR(50) NOT NULL UNIQUE,
  item_id INT(11) NOT NULL,
  
  PRIMARY KEY (id),
  UNIQUE KEY img_id_uindex (id),
  
  FOREIGN KEY (item_id)  REFERENCES items (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO images (file_name, item_id) VALUES('353535.png','1');
INSERT INTO images (file_name, item_id) VALUES('626262.png','1');
INSERT INTO images (file_name, item_id) VALUES('717171.png','2');
INSERT INTO images (file_name, item_id) VALUES('757557.jpg','2');