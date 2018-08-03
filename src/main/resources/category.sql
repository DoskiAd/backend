DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  
  PRIMARY KEY (id),
  UNIQUE KEY cat_id_uindex (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categories (name) VALUES('Растения');
INSERT INTO categories (name) VALUES('Ноутбуки');
