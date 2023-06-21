CREATE TABLE post
(
    post_id BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 PK',
    content VARCHAR(1500) NOT NULL COMMENT '내용',
    title   VARCHAR(50)   NOT NULL COMMENT '제목'
) engine = InnoDB COMMENT '게시글';;

CREATE TABLE bookmark
(
    bookmark_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 PK',
    post_id     BIGINT NOT NULL,
    CONSTRAINT bookmark_post_post_id FOREIGN KEY (post_id) REFERENCES post (post_id)
) engine = InnoDB COMMENT '북마크';

