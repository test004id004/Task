USE assignment;
CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(45) NOT NULL,
    `first_name` varchar(20) NOT NULL,
    `last_name` varchar(20) NOT NULL,
    `password` varchar(64) NOT NULL,
    `unmasked_password` varchar(64),
    PRIMARY KEY (`id`), UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB;

INSERT INTO `users` (email, first_name, last_name, password, unmasked_password) VALUES ("ab@gmail.com", "AaAa", "BbBb", "ABabABab", "ABabABabABabABab");
INSERT INTO `users` (email, first_name, last_name, password, unmasked_password) VALUES ("cd@gmail.com", "CcCc", "DdDd", "CDcdCDcd", "CDcdCDcdCDcdCDcd");
INSERT INTO `users` (email, first_name, last_name, password, unmasked_password) VALUES ("pq@gmail.com", "PpPp", "QqQq", "PQpqPQpq", "PQpqPQpqPQpqPQpq");