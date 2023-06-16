CREATE TABLE user
(
    user_id           BIGINT                   NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 PK',
    nickname          VARCHAR(15)              NOT NULL COMMENT '닉네임',
    profile_image_url VARCHAR(1500)            NULL COMMENT '프로필 이미지',
    role              ENUM ('NORMAL', 'ADMIN') NOT NULL COMMENT '권한',
    deleted           ENUM ('TRUE', 'FALSE')   NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '사용자';

CREATE TABLE club
(
    club_id          BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '소모임 PK',
    user_id          BIGINT                 NOT NULL COMMENT '사용자 PK',
    title            VARCHAR(20)            NOT NULL COMMENT '제목',
    content          VARCHAR(1500)          NOT NULL COMMENT '내용',
    logo_image_url   VARCHAR(1500)          NULL COMMENT '소모임 로고 이미지',
    member_count     INT UNSIGNED           NULL COMMENT '방문자 수',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_club_user_user_id FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE = InnoDB COMMENT '소모임 클럽';

CREATE TABLE club_image
(
    club_image_id    BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '클럽 이미지 PK',
    club_id          BIGINT                 NOT NULL COMMENT '클럽 PK',
    image_url        VARCHAR(1500)          NOT NULL COMMENT '이미지 URL',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_club_image_club_club_id FOREIGN KEY (club_id) REFERENCES club (club_id)
) ENGINE = InnoDB COMMENT '클럽 이미지';

CREATE TABLE hashtag
(
    hashtag_id       BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '해시태그 PK',
    tag_name         VARCHAR(30)                       NOT NULL COMMENT '태그명',
    created_by       BIGINT                            NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)                       NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                            NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)                       NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE')            NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '해시태그';

CREATE TABLE club_hashtag
(
    club_hashtag_id  BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '소모임 해시태그 PK',
    club_id          BIGINT                            NOT NULL COMMENT '소모임 PK',
    hashtag_id       BIGINT                            NOT NULL COMMENT '해시태그 PK',
    created_by       BIGINT                            NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)                       NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                            NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)                       NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE')            NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_club_hashtag_club_club_id FOREIGN KEY (club_id) REFERENCES club (club_id)
) ENGINE = InnoDB COMMENT '소모임 해시태그';

CREATE TABLE club_user
(
    club_user_id     BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '소모임 회원 PK',
    user_id          BIGINT                 NOT NULL COMMENT '사용자 PK',
    club_id          BIGINT                 NOT NULL COMMENT '소모임 PK',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_club_user_user_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_club_user_club_user_id FOREIGN KEY (club_id) REFERENCES club (club_id)
) ENGINE = InnoDB COMMENT '소모임 회원';

CREATE TABLE notice
(
    notice_id        BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '공지사항 PK',
    user_id          BIGINT                 NOT NULL COMMENT '사용자 PK',
    club_id          BIGINT                 NOT NULL COMMENT '소모임 PK',
    content          VARCHAR(1500)          NOT NULL COMMENT '댓글 내용',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_notice_user_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_notice_club_club_id FOREIGN KEY (club_id) REFERENCES club (club_id)
) ENGINE = InnoDB COMMENT '공지사항';

CREATE TABLE post
(
    post_id          BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 PK',
    user_id          BIGINT                 NOT NULL COMMENT '사용자 PK',
    club_id          BIGINT                 NOT NULL COMMENT '소모임 PK',
    content          VARCHAR(1500)          NOT NULL COMMENT '댓글 내용',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_post_user_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_post_club_club_id FOREIGN KEY (club_id) REFERENCES club (club_id)
) ENGINE = InnoDB COMMENT '게시글';

CREATE TABLE post_image
(
    post_image_id    BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 이미지 PK',
    post_id          BIGINT                 NOT NULL COMMENT '게시글 PK',
    image_url        VARCHAR(1500)          NOT NULL COMMENT '이미지 URL',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_post_image_post_post_id FOREIGN KEY (post_id) REFERENCES post (post_id)
) ENGINE = InnoDB COMMENT '게시글 이미지';

CREATE TABLE comment
(
    comment_id       BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 PK',
    user_id          BIGINT                 NOT NULL COMMENT '사용자 PK',
    post_id          BIGINT                 NOT NULL COMMENT '게시글 PK',
    content          VARCHAR(1500)          NOT NULL COMMENT '댓글 내용',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_comment_user_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_comment_post_post_id FOREIGN KEY (post_id) REFERENCES post (post_id)
) ENGINE = InnoDB COMMENT '댓글';

CREATE TABLE comment_image
(
    comment_image_id BIGINT                 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '클럽 이미지 PK',
    comment_id       BIGINT                 NOT NULL COMMENT '댓글 PK',
    image_url        VARCHAR(1500)          NOT NULL COMMENT '이미지 URL',
    created_by       BIGINT                 NOT NULL COMMENT '최초 생성한 사용자 PK',
    created_at       DATETIME(6)            NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                 NULL COMMENT '최종 수정한 사용자 PK',
    last_modified_at DATETIME(6)            NULL COMMENT '최종 수정일',
    deleted          ENUM ('TRUE', 'FALSE') NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_comment_image_comment_comment_id FOREIGN KEY (comment_id) REFERENCES comment (comment_id)
) ENGINE = InnoDB COMMENT '댓글 이미지';
