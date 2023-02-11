ALTER TABLE `order` DROP FOREIGN KEY `FK_store_owner_TO_order_1`;
ALTER TABLE `compensation` DROP FOREIGN KEY `FK_store_owner_TO_compensation_1`;
ALTER TABLE `settlement` DROP FOREIGN KEY `FK_store_owner_TO_settlement_1`;

DROP TABLE store_owner;