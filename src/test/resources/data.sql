INSERT INTO lecture.lecture
( id, lecture_name, speaker)
VALUES ( 'Java Basics', 'Java Basics', '남궁성'),
       ( 'Advanced Java', 'Advanced Java', '이동욱'),
       ( 'Effective Java', 'Effective Java', '조슈아 블로크'),
       ('Java Concurrency', 'Java Concurrency', '브라이언 게츠'),
       ('Spring Framework', 'Spring Framework', '김영한');


-- -- lecture 생성

INSERT INTO lecture.users (id, username, create_at)
VALUES
    ('user1', '해리', NOW()),
    ('user2', '론', NOW()),
    ('user3', '헤르미온느', NOW()),
    ('user4', '드레이코', NOW()),
    ('user5', '네빌', NOW()),
    ('user6', '루나', NOW()),
    ('user7', '진', NOW()),
    ('user8', '프레드', NOW()),
    ('user9', '조지', NOW()),
    ('user10', '초', NOW()),
    ('user11', '덤블도어', NOW()),
    ('user12', '스네이프', NOW()),
    ('user13', '맥고나걸', NOW()),
    ('user14', '해그리드', NOW()),
    ('user15', '말포이', NOW()),
    ('user16', '시리우스', NOW()),
    ('user17', '릴리', NOW()),
    ('user18', '제임스', NOW()),
    ('user19', '루핀', NOW()),
    ('user20', '피터', NOW()),
    ('user21', '톰 리들', NOW()),
    ('user22', '벨라트릭스', NOW()),
    ('user23', '볼드모트', NOW()),
    ('user24', '플리트윅', NOW()),
    ('user25', '뱀', NOW()),
    ('user26', '크레이브', NOW()),
    ('user27', '고일', NOW()),
    ('user28', '빈센트', NOW()),
    ('user29', '그레고리', NOW()),
    ('user30', '펜시', NOW()),
    ('user31', '모우닝 머틀', NOW()),
    ('user32', '플러피', NOW()),
    ('user33', '윅', NOW()),
    ('user34', '돌로레스', NOW()),
    ('user35', '엄브릿지', NOW()),
    ('user36', '니콜라스', NOW()),
    ('user37', '플라멜', NOW()),
    ('user38', '그린델왈드', NOW()),
    ('user39', '콜린 크리비', NOW()),
    ('user40', '딘 토마스', NOW()),
    ('testUser', 'testUser', NOW());

-- user 생성



INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at,target_date,capacity,dead_line)
VALUES
    ('scheduler1',NOW(), 'MONDAY', '2025-01-01 16:00:00', '2025-01-01 14:00:00', 'Java Basics', NOW(),'2025-01-01',30,'PROGRESS');

INSERT INTO lecture.lecture_schedule
(schedule_id,create_at, `day`, end_time, start_time, lecture_id, update_at,target_date,capacity,dead_line)
VALUES
    ('scheduler2',NOW(), 'MONDAY', '2025-01-01 18:00:00', '2025-01-01 16:00:00', 'Spring Framework', NOW(),'2025-01-01',30,'PROGRESS');





