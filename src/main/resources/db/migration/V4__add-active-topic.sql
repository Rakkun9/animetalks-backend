ALTER TABLE topic ADD active tinyint;
update topic set active = 1;