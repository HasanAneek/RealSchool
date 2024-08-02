INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Jan 1 ', 'New Year''s Day', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Oct 31 ', 'Halloween', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Nov 24 ', 'Thanksgiving Day', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Dec 25 ', 'Christmas', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Jan 17 ', 'Martin Luther King Jr. Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' July 4 ', 'Independence Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Sep 5 ', 'Labor Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (' Nov 11 ', 'Veterans Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `roles` (`role_name`, `created_at`, `created_by`)
VALUES ('ADMIN', CURDATE(), 'DBA');

INSERT INTO `roles` (`role_name`, `created_at`, `created_by`)
VALUES ('STUDENT', CURDATE(), 'DBA');

-- DELETE FROM person where email='admin@realschool.com';

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
VALUES ('Admin','admin@realschool.com','34434343431','$2a$12$LXN1QPQOhHfmEM57XlgY1e5fzJJIzb0uVV5snDkhxMMC7jGgvSUsK', 1 ,CURDATE(),'DBA');

-- ALTER TABLE `person` MODIFY `person_id` int NOT NULL AUTO_INCREMENT;
