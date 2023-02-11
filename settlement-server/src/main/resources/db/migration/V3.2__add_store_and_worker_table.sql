CREATE TABLE `store`
(
    `id`          BIGINT NOT NULL,
    `name`        VARCHAR(45) NULL,
    `created_at`  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `modified_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

CREATE TABLE `worker`
(
    `store_id`    BIGINT      NOT NULL,
    `member_id`   VARCHAR(36) NOT NULL,
    `created_at`  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `modified_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (store_id, member_id)
);

ALTER TABLE `worker`
    ADD CONSTRAINT `FK_member_TO_worker` FOREIGN KEY (`member_id`)
        REFERENCES `member` (`id`);

ALTER TABLE `worker`
    ADD CONSTRAINT `FK_store_TO_worker` FOREIGN KEY (`store_id`)
        REFERENCES `store` (`id`);

ALTER TABLE `order`
    ADD CONSTRAINT `FK_worker_TO_order` FOREIGN KEY (`member_id`)
        REFERENCES `worker` (`member_id`);

ALTER TABLE `compensation`
    ADD CONSTRAINT `FK_worker_TO_compensation` FOREIGN KEY (`member_id`)
        REFERENCES `worker` (`member_id`);

ALTER TABLE `settlement`
    ADD CONSTRAINT `FK_worker_TO_settlement` FOREIGN KEY (`member_id`)
        REFERENCES `worker` (`member_id`);
