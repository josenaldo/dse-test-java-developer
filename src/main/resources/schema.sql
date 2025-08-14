DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS sales_person;

CREATE TABLE `sales_person`
(
    `id`     varchar(36)      NOT NULL,
    `name`   varchar(100)     NOT NULL,
    `age`    tinyint unsigned NOT NULL,
    `salary` decimal(12, 2)   NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `CHK_sales_person_age` CHECK ((`age` between 0 and 120)),
    CONSTRAINT `CHK_sales_person_salary` CHECK ((`salary` >= 0))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `customers`
(
    `id`            varchar(36)  NOT NULL,
    `name`          varchar(255) NOT NULL,
    `email`         varchar(255) NOT NULL,
    `username`      varchar(255) NOT NULL,
    `city`          varchar(100) NOT NULL,
    `industry_type` char(1)      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UC_customers_username` (`username`),
    UNIQUE KEY `UC_customers_email` (`email`),
    CONSTRAINT `CHK_customer_industry_type` CHECK ((`industry_type` in (_utf8mb4'J', _utf8mb4'B')))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `orders`
(
    `id`              varchar(36)    NOT NULL,
    `order_date`      date           NOT NULL,
    `customer_id`     varchar(36)    NOT NULL,
    `sales_person_id` varchar(36)    NOT NULL,
    `amount`          decimal(12, 2) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `IDX_orders_customer` (`customer_id`),
    KEY `IDX_orders_salesperson` (`sales_person_id`),
    CONSTRAINT `FK_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `FK_orders_sales_person` FOREIGN KEY (`sales_person_id`) REFERENCES `sales_person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `CHK_orders_amount` CHECK ((`amount` >= 0))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;