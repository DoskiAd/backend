DROP TABLE IF EXISTS items;

CREATE TABLE IF NOT EXISTS items (
  id INT(11) NOT NULL AUTO_INCREMENT,
  category_id INT(11) NOT NULL,
  date DATE NOT NULL,
  user_id INT(11) NOT NULL,
  title VARCHAR(250) NOT NULL,
  price INT(11) NOT NULL,
  description VARCHAR(5000) NOT NULL,
  location VARCHAR (25) NOT NULL,
  
  PRIMARY KEY (id),
  UNIQUE KEY item_id_uindex (id),
  
  CONSTRAINT items_category_fk
  FOREIGN KEY (category_id)  REFERENCES categories (id)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO items (category_id, date, user_id, title, price, description, location) VALUES('1', '2018-07-31', '1', 'Сосна обыкновенная', '1300', 'Саженец сосны обыкновенной из питомника ...', 'Москва');
INSERT INTO items (category_id, date, user_id, title, price, description, location) VALUES('2', '2018-08-01', '2', 'Apple MacBook 12', '37000', 'Есть темного потертости на экране ...', 'Москва');
