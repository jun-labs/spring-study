# PRIMARY(use camelcase because of company policy)
CREATE TABLE user
(
    userId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
) ENGINE = INNODB;


# REPLICATION(use camelcase because of company policy)
CREATE TABLE study
(
    studyId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
) ENGINE = INNODB;
