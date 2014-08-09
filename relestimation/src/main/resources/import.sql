-- Loads all the default t-shirt sizing to the appliation, if needed admin can change.
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XP', 1, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'P', 2, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XXS', 4, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XS', 6, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'S', 10, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2S', 15, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3S', 20, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'M', 30, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2M', 40, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3M', 50, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '4M', 60, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'L', 75, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2L', 90, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3L', 100, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XL', 125, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2XL', 150, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3XL', 200, 'MM',now());
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'BIG', 300, 'MM',now());
--  Adds a default root user for this application
INSERT INTO `tms`.`applicationuser` (`CREATED_TS`, `email`, `fname`, `ACTIVE`, `lname`, `mname`, `passcode`, `phone`, `uname`, `UPDATED_TS`) VALUES (now(), 'admin@estool.com', 'Admin', 1, 'Admin', ' ', 'admin', '6000000911', 'root', now());

--  Insert some project groups
INSERT INTO `tms`.`developmentgroup` (`created_ts`, `name`, `updated_ts`) VALUES (now(), 'Default Development Group', now());
INSERT INTO `tms`.`developmentgroup` (`created_ts`, `name`, `updated_ts`) VALUES (now(), 'Default Testing Group', now());
INSERT INTO `tms`.`developmentgroup` (`created_ts`, `name`, `updated_ts`) VALUES (now(), 'Default UIX Group', now());

-- Insert the priority data
INSERT INTO `tms`.`priority` (`CREATED_TS`, `priority`, `UPDATED_TS`) VALUES (Now(), 'High', Now());
INSERT INTO `tms`.`priority` (`CREATED_TS`, `priority`, `UPDATED_TS`) VALUES (Now(), 'Medium', Now());
INSERT INTO `tms`.`priority` (`CREATED_TS`, `priority`, `UPDATED_TS`) VALUES (Now(), 'Low', Now());

-- Insert the Assumption Types
INSERT INTO `tms`.`assumption_type` (`CREATED_TS`, `type_name`, `UPDATED_TS`) VALUES (now(), 'resource', now());
INSERT INTO `tms`.`assumption_type` (`CREATED_TS`, `type_name`, `UPDATED_TS`) VALUES (now(), 'scope', now());
INSERT INTO `tms`.`assumption_type` (`CREATED_TS`, `type_name`, `UPDATED_TS`) VALUES (now(), 'estimation', now());
INSERT INTO `tms`.`assumption_type` (`CREATED_TS`, `type_name`, `UPDATED_TS`) VALUES (now(), 'feature', now());
INSERT INTO `tms`.`assumption_type` (`CREATED_TS`, `type_name`, `UPDATED_TS`) VALUES (now(), 'timeline', now());