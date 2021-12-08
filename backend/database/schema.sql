BEGIN TRANSACTION;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE courses (
        course_id SERIAL PRIMARY KEY,
        course_name CHARACTER VARYING(400) NOT NULL,
        course_description TEXT,
        difficulty_level INTEGER NOT NULL CHECK(difficulty_level BETWEEN 1 AND 5),
        course_cost NUMERIC
);
CREATE TABLE teachers (
        teacher_id SERIAL,
        user_id INTEGER,
        first_name CHARACTER VARYING(400),
        last_name CHARACTER VARYING(400),
        CONSTRAINT pk_teachers PRIMARY KEY (teacher_id),
        CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE students (
        student_id SERIAL,
        user_id INTEGER,
        first_name CHARACTER VARYING(400),
        last_name CHARACTER VARYING(400),
        CONSTRAINT pk_students PRIMARY KEY (student_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE teacher_courses (
        teacher_id INTEGER,
        course_id INTEGER,
        CONSTRAINT pk_teacher_courses PRIMARY KEY (teacher_id, course_id),
        CONSTRAINT fk_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id),
        CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id)
);
CREATE TABLE student_courses (
        student_id INTEGER,
        course_id INTEGER,
        CONSTRAINT pk_student_courses PRIMARY KEY (student_id, course_id),
        CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id),
        CONSTRAINT fk_students FOREIGN KEY (student_id) REFERENCES students (student_id)
);
CREATE TABLE assignments (
        course_id INTEGER NOT NULL,
        assignment_id SERIAL,
        assignment_number INTEGER NOT NULL,
        assignment_name CHARACTER VARYING(400),
        description TEXT,
        possible_points INTEGER,
        due_date DATE,
        CONSTRAINT pk_assignments PRIMARY KEY (assignment_id),
        CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id)
);
CREATE TABLE lessons (
        course_id INTEGER NOT NULL,
        lesson_id SERIAL,
        lesson_number INTEGER,
        lesson_name CHARACTER VARYING(400),
        description TEXT,
        CONSTRAINT pk_lessons PRIMARY KEY (lesson_id),
        CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id)
);
CREATE TABLE student_assignments (
        student_id INTEGER,
        homework_id INTEGER,
        grade NUMERIC,
        possible_points INTEGER,
        submission TEXT,
        submission_time TIMESTAMP,
        CONSTRAINT pk_student_assignments PRIMARY KEY (student_id, homework_id),
        CONSTRAINT fk_students FOREIGN KEY (student_id) REFERENCES students (student_id),
        CONSTRAINT fk_assignments FOREIGN KEY (homework_id) REFERENCES assignments (assignment_id)
);
COMMIT TRANSACTION;
-- ALL TABLES INSERTED INTO DATABASE GO ABOVE THIS LINE
-- ALL DATA INSERTED INTO TABLES GOES BELOW THIS LINE
-- Insert Teachers & Admins
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('walt', 'Walt', 'Impellicceiri', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('toma', 'Tom', 'Anderson', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('tomm', 'Tom', 'Medvitz', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
-- Insert Students
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('wszabo', 'Will', 'Szabo', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,first_name, last_name, password_hash, role) VALUES ('jmcfadden', 'James', 'McFadden', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,first_name, last_name, password_hash, role) VALUES ('fgiarratani', 'Frank', 'Giarratani', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,first_name, last_name, password_hash, role) VALUES ('nwetzel', 'Nathan', 'Wetzel', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,first_name, last_name, password_hash, role) VALUES ('vmantena', 'Vineeth', 'Mantena', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
-- Insert Courses
INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES ('vampire: blood and empire', 'learn about Erzabet Bathory and Vlad Tzepez', 3, 658);
INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES ('intro to C++', 'learn the basics of programming', 3, 550);
INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES ('into to C#', 'how to be an inferior programmer', 1, 199);
INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES ('intro to English', 'generally the wrong decision', 2, 348);
-- Insert lessons
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (1, 1, 'Gogol', 'read some Gogol');
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (1, 2, 'Tzepez', 'learn about Dracula');
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (2, 1, 'learn the basics of OOP', 'but regret it');
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (3, 1, 'basics of C#', 'maps are dictionaries');
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (4, 1, 'Chaucer', 'read the Decameron');
INSERT INTO lessons (course_id, lesson_number, lesson_name, description) VALUES (4, 2, 'Shakespeare', 'read the Tempest');
-- Insert assignments
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (1, 1, 'Gogol', 'write about Gogol', 10, '1/10/2022');
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (1, 2, 'Tzepez', 'write about his dad', 10, '1/24/2022');
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (2, 1, 'learn the basics of OOP', 'practice classes', 100, '1/10/2022');
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (3, 1, 'basics of C#', 'practice dictionaries', 100, '1/12/20200');
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (4, 1, 'Chaucer', 'write about the pot', 25, '1/15/2022');
INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) VALUES (4, 2, 'Shakespeare', 'write about Caliban and Prospero', 25, '1/25/2020');
<<<<<<< HEAD

=======
>>>>>>> 2a505e6d5ea98cb304e3b141ceb3e7d634cfe398
--Insert teachers
INSERT INTO teachers (user_id, first_name, last_name) VALUES (1, 'Walt', 'Impellicceiri');
INSERT INTO teachers (user_id, first_name, last_name) VALUES (2, 'Tom', 'Anderson');
INSERT INTO teachers (user_id, first_name, last_name) VALUES (3, 'Tom', 'Medvitz');
--Insert Students
INSERT INTO students (user_id, first_name, last_name) VALUES (4, 'Will', 'Szabo');
INSERT INTO students (user_id, first_name, last_name) VALUES (5, 'James', 'McFadden');
INSERT INTO students (user_id, first_name, last_name) VALUES (6, 'Frank', 'Giarratani');
INSERT INTO students (user_id, first_name, last_name) VALUES (7, 'Nathan', 'Wetzel');
INSERT INTO students (user_id, first_name, last_name) VALUES (8, 'Vineeth', 'Mantena');
--Insert teacher courses
INSERT INTO teacher_courses (teacher_id, course_id) VALUES (1, 1);
INSERT INTO teacher_courses (teacher_id, course_id) VALUES (1, 2);
INSERT INTO teacher_courses (teacher_id, course_id) VALUES (2, 3);
INSERT INTO teacher_courses (teacher_id, course_id) VALUES (2, 4);
INSERT INTO teacher_courses (teacher_id, course_id) VALUES (3, 4);
--Insert student courses
INSERT INTO student_courses (student_id, course_id) VALUES (1, 1);
INSERT INTO student_courses (student_id, course_id) VALUES (2, 1);
INSERT INTO student_courses (student_id, course_id) VALUES (3, 2);
INSERT INTO student_courses (student_id, course_id) VALUES (4, 3);
INSERT INTO student_courses (student_id, course_id) VALUES (5, 1);
--Insert student assignments
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (1, 1, 5, 10, 'Everyone likes "the nose"', CURRENT_TIMESTAMP);
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (1, 2, 9, 10, 'His dad was Dracul', CURRENT_TIMESTAMP);
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (2, 1, 7, 10, 'better than "Gravitys Rainbow"', CURRENT_TIMESTAMP);
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (2, 2, 10, 10, 'His dad fought the Turks', CURRENT_TIMESTAMP);
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (3, 4, 80, 100, 'are reference types', CURRENT_TIMESTAMP);
INSERT INTO student_assignments (student_id, homework_id, grade, possible_points, submission, submission_time) VALUES (4, 5, 25, 25, 'it was useful for hiding', CURRENT_TIMESTAMP);
