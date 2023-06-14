CREATE TABLE user
(
    user_id           BIGINT                   NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 PK',
    nickname          VARCHAR(15)              NOT NULL COMMENT '닉네임',
    profile_image_url VARCHAR(1500)            NULL COMMENT '프로필 이미지',
    role              ENUM ('NORMAL', 'ADMIN') NOT NULL COMMENT '등급',
)
