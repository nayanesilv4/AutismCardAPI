CREATE TABLE `cards`
(
    `id`                binary(16)   NOT NULL,
    `birth_date`        date         NOT NULL,
    `cid`               varchar(255) NOT NULL,
    `cpf`               varchar(11)  NOT NULL,
    `full_name`         varchar(255) NOT NULL,
    `phone`             varchar(255) NOT NULL,
    `report_link`       varchar(255) NOT NULL,
    `responsible_name`  varchar(255) NOT NULL,
    `responsible_phone` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;