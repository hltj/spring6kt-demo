CREATE TABLE `users` (
    `id`       INTEGER     NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(64) NOT NULL,
    `reg_time` TIMESTAMP   NOT NULL,
    PRIMARY KEY (id)
);