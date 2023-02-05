ALTER TABLE `order` MODIFY `status` char(1),
    MODIFY `type` char(1);

ALTER TABLE `payment` MODIFY `payment_type` char(1);