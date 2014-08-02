--  Adding root as only user on load who can log on and start using the application
INSERT INTO `tms`.`applicationuser` (`CREATED_TS`, `email`, `fname`, `ACTIVE`, `lname`, `mname`, `passcode`, `phone`, `uname`, `UPDATED_TS`) VALUES (now(), 'admin@estool.com', 'Admin', 1, 'Admin', ' ', 'admin', '6000000911', 'root', now())

--  Insert default Sizing keys
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XP', 1, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'P', 2, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XXS', 4, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XS', 6, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'S', 10, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2S', 15, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3S', 20, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'M', 30, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2M', 40, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3M', 50, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '4M', 60, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'L', 75, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2L', 90, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3L', 100, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'XL', 125, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '2XL', 150, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), '3XL', 200, MM,Now();
INSERT INTO `tms`.`sizing` (`CREATED_TS`, `sizename`, `sizevalue`, `uom`, `UPDATED_TS`) VALUES (now(), 'BIG', 300, MM,Now();


