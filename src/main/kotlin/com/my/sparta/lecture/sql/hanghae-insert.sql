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

INSERT INTO lecture.users (id, username, create_at)
VALUES
    (UUID(), '해리', NOW()),
    (UUID(), '론', NOW()),
    (UUID(), '헤르미온느', NOW()),
    (UUID(), '드레이코', NOW()),
    (UUID(), '네빌', NOW()),
    (UUID(), '루나', NOW()),
    (UUID(), '진', NOW()),
    (UUID(), '프레드', NOW()),
    (UUID(), '조지', NOW()),
    (UUID(), '초', NOW()),
    (UUID(), '덤블도어', NOW()),
    (UUID(), '스네이프', NOW()),
    (UUID(), '맥고나걸', NOW()),
    (UUID(), '해그리드', NOW()),
    (UUID(), '말포이', NOW()),
    (UUID(), '시리우스', NOW()),
    (UUID(), '릴리', NOW()),
    (UUID(), '제임스', NOW()),
    (UUID(), '루핀', NOW()),
    (UUID(), '피터', NOW()),
    (UUID(), '톰 리들', NOW()),
    (UUID(), '벨라트릭스', NOW()),
    (UUID(), '볼드모트', NOW()),
    (UUID(), '플리트윅', NOW()),
    (UUID(), '뱀', NOW()),
    (UUID(), '크레이브', NOW()),
    (UUID(), '고일', NOW()),
    (UUID(), '빈센트', NOW()),
    (UUID(), '그레고리', NOW()),
    (UUID(), '펜시', NOW());


-- user 생성 

INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at)
VALUES
(UUID(),NOW(), 'MONDAY', '2024-12-25 16:00:00', '2024-12-25 14:00:00', 'Java Basics', NOW());

INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at)
VALUES
(UUID(),NOW(), 'MONDAY', '2024-12-25 16:00:00', '2024-12-25 14:00:00', 'Spring Framework', NOW());

-- 수강 가능한 특강 생성





