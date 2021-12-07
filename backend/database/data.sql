drop table teacher_courses;
drop table student_assignment;
DROP table student_courses;
drop table student;
drop table teacher;
Drop table course;
drop table users;
drop table assignments;
drop table lesson;
drop table homework;
drop table student_grades;





CREATE TABLE course 
(
course_id SERIAL PRIMARY KEY,
 course_name CHARACTER VARYING(400) NOT NULL,
 course_description TEXT, 
  difficulty_level INTEGER NOT NULL CHECK(difficulty_level BETWEEN 1 AND 5),
  course_cost NUMERIC
 
 
);

CREATE TABLE teacher
(
teacher_id SERIAL,
course_id INTEGER NOT NULL,

CONSTRAINT pk_teacher PRIMARY KEY (teacher_id),
CONSTRAINT fk_teacher_courses FOREIGN KEY (course_id) REFERENCES course (course_id),
CONSTRAINT fk_user FOREIGN KEY (teacher_id) REFERENCES users (user_id)
);

CREATE TABLE student
(
student_id SERIAL,
student_name CHARACTER VARYING(400) NOT NULL,
 
  CONSTRAINT pk_student PRIMARY KEY (student_id), 
  CONSTRAINT fk_user FOREIGN KEY (student_id) REFERENCES users (user_id)

  );

CREATE TABLE teacher_courses
(
teacher_id INTEGER,
course_id INTEGER,

CONSTRAINT pk_teacher_courses PRIMARY KEY (teacher_id, course_id),
CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (course_id)
);



  
  CREATE TABLE student_courses
(
student_id INTEGER,
course_id INTEGER,

CONSTRAINT pk_student_courses PRIMARY KEY (student_id, course_id),
CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (course_id),
CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student (student_id)

);


CREATE TABLE assignments
(
course_id INTEGER NOT NULL,
assignment_id SERIAL,
lesson_number INTEGER NOT NULL,
lesson_name CHARACTER VARYING(400),
description TEXT,
possible_points INTEGER,
due_date DATE,


CONSTRAINT pk_assignments PRIMARY KEY (assignment_id), 
  CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (course_id) 
 
);

CREATE TABLE lesson
(
course_id INTEGER NOT NULL,
lesson_id SERIAL,
lesson_number INTEGER UNIQUE,
lesson_name CHARACTER VARYING(400),
description TEXT,

CONSTRAINT pk_lesson PRIMARY KEY (lesson_id),
CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (course_id)
);

 CREATE TABLE student_assignments
 (
 student_id INTEGER,
 homework_id INTEGER,
 grade NUMERIC,
 submission TEXT,
 submission_time TIMESTAMP,
 
  CONSTRAINT pk_student_assignment PRIMARY KEY (student_id, homework_id),
  CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student (student_id), 
  CONSTRAINT fk_assignment FOREIGN KEY (homework_id) REFERENCES assignments (assignment_id)
);

