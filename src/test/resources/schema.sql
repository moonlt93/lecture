-- lecture 테이블
CREATE TABLE lecture (
                         id VARCHAR(255) NOT NULL,
                         lecture_name VARCHAR(255),
                         speaker VARCHAR(255),
                         PRIMARY KEY (id)
);

-- lecture_schedule 테이블
CREATE TABLE lecture_schedule (
                                  schedule_id VARCHAR(255) NOT NULL,
                                  capacity INT,
                                  create_at DATETIME(6),
                                  lecture_day VARCHAR(20),
                                  dead_line VARCHAR(20),
                                  end_time TIME(6),
                                  start_time TIME(6),
                                  target_date DATE,
                                  lecture_id VARCHAR(255),
                                  update_at DATETIME(6),
                                  PRIMARY KEY (schedule_id)
);

-- user_schedules 테이블
CREATE TABLE user_schedules (
                                user_scheduler_id BIGINT NOT NULL AUTO_INCREMENT,
                                create_at DATETIME(6) NOT NULL,
                                update_at DATETIME(6),
                                lecture_id VARCHAR(255) NOT NULL,
                                scheduler_id VARCHAR(255) NOT NULL,
                                user_id VARCHAR(255) NOT NULL,
                                PRIMARY KEY (user_scheduler_id)
);

-- users 테이블
CREATE TABLE users (
                       user_id VARCHAR(255) NOT NULL,
                       create_at DATETIME(6) NOT NULL,
                       update_at DATETIME(6),
                       username VARCHAR(255) NOT NULL,
                       PRIMARY KEY (user_id)
);

-- FK 설정
ALTER TABLE user_schedules
    ADD CONSTRAINT FK_user_schedules_scheduler
        FOREIGN KEY (scheduler_id)
            REFERENCES lecture_schedule (schedule_id);

ALTER TABLE user_schedules
    ADD CONSTRAINT FK_user_schedules_user
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);