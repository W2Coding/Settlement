CREATE TABLE `member`
(
    `id`       VARCHAR(36) NOT NULL,
    `email`    VARCHAR(45) NULL,
    `name`     VARCHAR(45) NULL,
    `type`     CHAR(1)     NULL,
    `password` VARCHAR(45) NULL COMMENT 'encrypted',
    primary key (id)
);

CREATE TABLE `order`
(
    `id`              BIGINT(20)  NOT NULL COMMENT 'snowflake id',
    `member_id`       VARCHAR(36) NOT NULL,
    `settlement_id`   BIGINT(20)  NULL COMMENT 'snowflake id',
    `order_detail_id` BIGINT(20)  NULL COMMENT 'snowflake id',
    `user_id`         BIGINT(20)  NULL COMMENT 'snowflake id',
    `status`          TINYINT(1)  NULL COMMENT '1 progressing 2 completed 3 canceled',
    `type`            TINYINT(1)  NULL COMMENT '1 user 2 manager',
    `created_at`      DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

CREATE TABLE `compensation`
(
    `id`            BIGINT(20)   NOT NULL COMMENT 'snowflake id',
    `member_id`     VARCHAR(36)  NOT NULL,
    `settlement_id` BIGINT(20)   NULL COMMENT 'snowflake id',
    `is_reward`     Boolean      NOT NULL COMMENT '보상/보정',
    `cost`          BIGINT(20)   NOT NULL,
    `reason`        VARCHAR(255) NULL,
    `issued_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

CREATE TABLE `payment`
(
    `id`           BIGINT(20) NOT NULL,
    `order_id`     BIGINT(20) NOT NULL COMMENT 'snowflake id',
    `payment_type` TINYINT(1) NULL COMMENT '1 card 2 mobile 3 point 4 coupon 5 shopkeeper coupon',
    `price`        INTEGER    NULL,
    `created_at`   DATETIME   NULL,
    primary key (id)
);

CREATE TABLE `store_owner`
(
    `member_id` VARCHAR(36) NOT NULL,
    `name`      VARCHAR(45) NULL,
    primary key (member_id)
);

CREATE TABLE `settlement`
(
    `id`                      BIGINT(20)  NOT NULL COMMENT 'snowflake id',
    `member_id`               VARCHAR(36) NOT NULL,
    `total_order_cost`        BIGINT(20)  NULL,
    `total_compensation_cost` BIGINT(20)  NULL,
    `payment_date`            DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    `approve`                 BOOLEAN     NULL DEFAULT false,
    primary key (id)
);

ALTER TABLE `order`
    ADD CONSTRAINT `FK_store_owner_TO_order_1` FOREIGN KEY (`member_id`)
        REFERENCES `store_owner` (`member_id`);

ALTER TABLE `order`
    ADD CONSTRAINT `FK_settlement_TO_order_1` FOREIGN KEY (`settlement_id`)
        REFERENCES `settlement` (`id`);

ALTER TABLE `compensation`
    ADD CONSTRAINT `FK_store_owner_TO_compensation_1` FOREIGN KEY (`member_id`)
        REFERENCES `store_owner` (`member_id`);

ALTER TABLE `compensation`
    ADD CONSTRAINT `FK_settlement_TO_compensation_1` FOREIGN KEY (`settlement_id`)
        REFERENCES `settlement` (`id`);

ALTER TABLE `payment`
    ADD CONSTRAINT `FK_order_TO_payment_1` FOREIGN KEY (`order_id`)
        REFERENCES `order` (`id`);

ALTER TABLE `store_owner`
    ADD CONSTRAINT `FK_member_TO_store_owner_1` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`);

ALTER TABLE `settlement`
    ADD CONSTRAINT `FK_store_owner_TO_settlement_1` FOREIGN KEY (`member_id`)
        REFERENCES `store_owner` (`member_id`);

