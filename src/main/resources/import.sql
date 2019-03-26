INSERT INTO users (login,password) VALUES ('Admin','Admin');
INSERT INTO users (login,password) VALUES ('User','User');

INSERT INTO authors (first_name,last_name) VALUES ('First1','Last1');
INSERT INTO authors (first_name,last_name) VALUES ('First2','Last2');

INSERT INTO books (title,year,count,author) VALUES ('Title1',2010,2,1);
INSERT INTO books (title,year,count,author) VALUES ('Title2',2013,3,1);
/*INSERT INTO books (title,year,count,author) VALUES ('Title3',2015,2,1);*/
INSERT INTO books (title,year,count) VALUES ('Title3',2015,2);
INSERT INTO books (title,year,count,author) VALUES ('Title4',2017,5,2);

INSERT INTO users_books (user_id,books_id) VALUES (1,1);
INSERT INTO users_books (user_id,books_id) VALUES (1,2);
/*INSERT INTO users_books (user_id,books_id) VALUES (1,3);*/
INSERT INTO users_books (user_id,books_id) VALUES (2,1);