-- StartNoTest
DELIMITER //

DROP PROCEDURE IF EXISTS addQcType//
CREATE PROCEDURE addQcType(
  iName varchar(255),
  iDescription varchar(255),
  iQcTarget varchar(50),
  iUnits varchar(20)
) BEGIN
  IF NOT EXISTS (SELECT 1 FROM QCType WHERE name = iName AND qcTarget = iQcTarget) THEN
    INSERT INTO QCType(name, description, qcTarget, units)
    VALUES (iName, iDescription, iQcTarget, iUnits);
  END IF;
END//

DELIMITER ;
-- EndNoTest