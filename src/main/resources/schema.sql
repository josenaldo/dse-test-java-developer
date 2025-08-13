
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS sales_person;

CREATE TABLE `sales_person`
(
    `id`     VARCHAR(36)      NOT NULL,
    `name`   VARCHAR(100)     NOT NULL,
    `age`    TINYINT UNSIGNED NOT NULL,
    `salary` DECIMAL(12, 2)   NOT NULL,

    CONSTRAINT PK_sales_person PRIMARY KEY (`id`),
    CONSTRAINT CHK_sales_person_age CHECK (`age` BETWEEN 0 AND 120),
    CONSTRAINT CHK_sales_person_salary CHECK (`salary` >= 0)
);

CREATE TABLE `customers`
(
    id            VARCHAR(36)  NOT NULL,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    username      VARCHAR(255) NOT NULL,
    city          VARCHAR(100) NOT NULL,
    industry_type CHAR(1)      NOT NULL,

    CONSTRAINT PK_customers PRIMARY KEY (id),
    CONSTRAINT UC_customers_username UNIQUE (username),
    CONSTRAINT UC_customers_email UNIQUE (email),
    CONSTRAINT CHK_customer_industry_type CHECK (`industry_type` IN ('J', 'B'))
);

CREATE TABLE orders
(
    id             VARCHAR(36)    NOT NULL,
    order_date     DATE           NOT NULL,
    customer_id    VARCHAR(36)    NOT NULL,
    sales_person_id VARCHAR(36)    NOT NULL,
    amount         DECIMAL(12, 2) NOT NULL,

    CONSTRAINT PK_orders PRIMARY KEY (`id`),
    CONSTRAINT CHK_orders_amount CHECK (`amount` >= 0),
    CONSTRAINT FK_orders_customer FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
    CONSTRAINT FK_orders_sales_person FOREIGN KEY (`sales_person_id`) REFERENCES `sales_person` (`id`),
    INDEX `IDX_orders_customer` (`customer_id`),
    INDEX `IDX_orders_salesperson` (`sales_person_id`)
);