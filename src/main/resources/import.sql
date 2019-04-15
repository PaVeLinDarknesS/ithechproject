INSERT INTO users (login,password,role,enabled, email) VALUES ('Admin','$2a$10$cg8NnPbpMNNifa9DHLuopOnLf0XOuVHSk1ZNPoziGsRG9TPUv7y0W','ROLE_ADMIN',TRUE, 'JavaSpringPavel@gmail.com');
INSERT INTO users (login,password,role,enabled, email) VALUES ('User','$2a$10$bVhhRNNZ7k2ia8tSLUiWxOLeRVHVev2Jb5XrWbAbdaKWYo2.aM.IK','ROLE_USER',TRUE, 'JavaSpringPavel@gmail.com');

INSERT INTO authors (first_name,last_name) VALUES ('First1','Last1');
INSERT INTO authors (first_name,last_name) VALUES ('First2','Last2');

INSERT INTO books (title,year,count,author) VALUES ('Title1',2010,2,1);
INSERT INTO books (title,year,count,author) VALUES ('Title2',2013,3,1);
INSERT INTO books (title,year,count) VALUES ('Title3',2015,2);
INSERT INTO books (title,year,count,author) VALUES ('Title4',2017,5,2);
INSERT INTO books (title,year,count,author) VALUES ('Title5',2019,2,2);

INSERT INTO users_books (user_id,books_id) VALUES (1,1);
INSERT INTO users_books (user_id,books_id) VALUES (1,2);
INSERT INTO users_books (user_id,books_id) VALUES (2,1);