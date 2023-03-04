CREATE TABLE `member`
(
    `id`          binary(16) NOT NULL,
    `created_at`  DATETIME(6),
    `modified_at` DATETIME(6),
    `email`       VARCHAR(255),
    `name`        VARCHAR(255),
    `password`    VARCHAR(255),
    `status`      INTEGER,
    `type`        CHAR(1),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_email` (`email`)
) ENGINE = InnoDB;

CREATE TABLE `store`
(
    `id`          BIGINT NOT NULL,
    `created_at`  DATETIME(6),
    `modified_at` DATETIME(6),
    `name`        VARCHAR(255),
    `status`      INTEGER,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `settlement`
(
    `id`                      BIGINT NOT NULL,
    `created_at`              DATETIME(6),
    `modified_at`             DATETIME(6),
    `request_date`            DATETIME(6),
    `status`                  smallint,
    `total_compensation_cost` BIGINT,
    `total_order_cost`        BIGINT,
    `store_id`                BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_settlement_to_store__store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `settlement_item`
(
    `dtype`       CHAR(31) NOT NULL,
    `id`          BIGINT   NOT NULL,
    `created_at`  DATETIME(6),
    `modified_at` DATETIME(6),
    `cost`        BIGINT,
    `store_id`    BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_settlement_item_to_store__id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `settlement_detail`
(
    `created_at`         DATETIME(6),
    `modified_at`        DATETIME(6),
    `settlement_id`      BIGINT NOT NULL,
    `settlement_item_id` BIGINT NOT NULL,
    PRIMARY KEY (`settlement_id`, `settlement_item_id`),
    UNIQUE INDEX `UK_settlement_item_id` (`settlement_item_id`),
    CONSTRAINT `FK_settlement_detail_to_settlement__id` FOREIGN KEY (`settlement_id`) REFERENCES `settlement` (`id`),
    CONSTRAINT `FK_settlement_detail_to_settlement_item__id` FOREIGN KEY (`settlement_item_id`) REFERENCES `settlement_item` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `compensation`
(
    `is_reward` bit,
    `reason`    VARCHAR(255),
    `id`        BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_compensation_to_settlement_item__id` FOREIGN KEY (`id`) REFERENCES `settlement_item` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `order`
(
    `status` INTEGER,
    `id`     BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_order_to_settlement_item__id` FOREIGN KEY (`id`) REFERENCES `settlement_item` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `payment`
(
    `id`           BIGINT NOT NULL,
    `created_at`   DATETIME(6),
    `modified_at`  DATETIME(6),
    `payment_type` CHAR(1),
    `price`        INTEGER,
    `order_id`     BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_payment_to_order__id` FOREIGN KEY (`id`) REFERENCES `order` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `payout_history`
(
    `id`            BIGINT NOT NULL,
    `created_at`    DATETIME(6),
    `is_paid`       bit,
    `remarks`       VARCHAR(255),
    `settlement_id` BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_payout_history_to_settlement__id` FOREIGN KEY (`id`) REFERENCES `settlement` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `worker`
(
    `created_at`  DATETIME(6),
    `modified_at` DATETIME(6),
    `status`      INTEGER,
    `member_id`   binary(16) NOT NULL,
    `store_id`    BIGINT     NOT NULL,
    PRIMARY KEY (`member_id`, `store_id`),
    CONSTRAINT `FK_worker_to_member__id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
    CONSTRAINT `FK_worker_to_store__id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE = InnoDB;
