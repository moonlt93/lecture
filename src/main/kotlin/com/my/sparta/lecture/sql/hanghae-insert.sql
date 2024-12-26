INSERT INTO lecture.lecture
(capacity, id, lecture_name, speaker)
VALUES (30, 'Java Basics', 'Java Basics', '남궁성');
INSERT INTO lecture.lecture
(capacity, id, lecture_name, speaker)
VALUES (40, 'Advanced Java', 'Advanced Java', '이동욱');
INSERT INTO lecture.lecture
(capacity, id, lecture_name, speaker)
VALUES (20, 'Effective Java', 'Effective Java', '조슈아 블로크');
INSERT INTO lecture.lecture
(capacity, id, lecture_name, speaker)
VALUES (25, 'Java Concurrency', 'Java Concurrency', '브라이언 게츠');
INSERT INTO lecture.lecture
(capacity, id, lecture_name, speaker)
VALUES (50, 'Spring Framework', 'Spring Framework', '김영한');


-- lecture 생성 

INSERT INTO lecture.users
(id, username,create_at)
VALUES( UUID(),'해리',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES(UUID(),'론',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES( UUID(),'헤르미온느',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES( UUID(),'헤르미온느',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES( UUID(),'헤르미온느',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES( UUID(),'헤르미온느',now());
INSERT INTO lecture.users
( id, username,create_at)
VALUES( UUID(),'헤르미온느',now());

-- user 생성 


INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at)
VALUES
(UUID(),NOW(), 'MONDAY', '2024-12-25 16:00:00', '2024-12-25 14:00:00', 'Java Basics', NOW());

INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at)
VALUES
(UUID(),NOW(), 'MONDAY', '2024-12-25 16:00:00', '2024-12-25 14:00:00', 'Spring Framework', NOW());

SELECT * FROM lecture.lecture_schedule WHERE lecture_id = 'Java Basics';



