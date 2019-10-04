CREATE DATABASE QA;
GO

USE QA;
GO 

CREATE TABLE RoleUser (
id INT NOT NULL,
type_role VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE UserData (
id INT IDENTITY(1,1) NOT NULL,
name_user VARCHAR(50) NOT NULL,
password VARCHAR(MAX) NOT NULL,
id_role INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT AK_userdata_delete_role
FOREIGN KEY (id_role) REFERENCES RoleUser(id)
ON DELETE CASCADE,
CONSTRAINT AK_name_user UNIQUE(name_user)
);

CREATE TABLE App (
id INT IDENTITY(1,1) NOT NULL,
name_app VARCHAR(MAX) NOT NULL,
PRIMARY KEY(id)
);


CREATE TABLE Version (
id INT IDENTITY(1,1) NOT NULL,
name_version VARCHAR(MAX) NOT NULL,
id_app INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT AK_version_delete_app
FOREIGN KEY (id_app) REFERENCES App(id)
ON DELETE CASCADE
);

CREATE TABLE TestHistory (
id INT IDENTITY(1,1) NOT NULL,
name_test VARCHAR(MAX) NOT NULL,
result VARCHAR(MAX) NOT NULL,
id_version INT NOT NULL,
id_user INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT AK_testhistory_delete_version
FOREIGN KEY (id_version) REFERENCES Version(id)
ON DELETE CASCADE,
CONSTRAINT AK_testhistory_delete_user
FOREIGN KEY (id_user) REFERENCES UserData(id)
ON DELETE CASCADE
);


CREATE TABLE TestTimeHistory (
id INT IDENTITY(1,1) NOT NULL,
start_test BIGINT NOT NULL,
end_test BIGINT NOT NULL,
id_test  INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT AK_testtimehistory_delete_test
FOREIGN KEY (id_test) REFERENCES TestHistory(id)
ON DELETE CASCADE
);


CREATE TABLE MetricsData (
id INT NOT NULL,
description VARCHAR(MAX) NOT NULL,
PRIMARY KEY(id),
);

CREATE TABLE MetricsDataHistory (
id INT IDENTITY(1,1) NOT NULL,
value_metric INT NOT NULL,
id_test INT NOT NULL,
id_metric INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT AK_metricsdatahistory_delete_test
FOREIGN KEY (id_test) REFERENCES TestHistory(id)
ON DELETE CASCADE,
CONSTRAINT AK_metricsdatahistory_delete_metric
FOREIGN KEY (id_metric) REFERENCES MetricsData(id)
ON DELETE CASCADE
);

GO

INSERT INTO MetricsData VALUES (1,'¿Qué tan satisfecho esta con la facilidad de uso de esta aplicación?')
INSERT INTO MetricsData VALUES (2,'¿Qué tan exitosa fue la prueba?')
INSERT INTO MetricsData VALUES (3,'¿Cuantos fallos fueron detectados?')

INSERT INTO RoleUser VALUES (1,'QA')
INSERT INTO RoleUser VALUES (2,'Admin')

GO

CREATE VIEW SummaryTest AS 

SELECT 
App.name_app,
Version.name_version,
TestHistory.name_test,
TestHistory.result,
(TestTimeHistory.end_test -TestTimeHistory.start_test)/60000.0  AS duration_test_inMin,
UserData.name_user,
TestHistory.id_user
FROM TestHistory INNER JOIN TestTimeHistory 
ON TestHistory.id = TestTimeHistory.id_test
INNER JOIN Version 
ON TestHistory.id_version = Version.id
INNER JOIN App
ON Version.id_app = App.id
INNER JOIN UserData
ON TestHistory.id_user = UserData.id


GO

CREATE VIEW FailTestByApp AS 
WITH CountFailures AS (
SELECT
id_version,
COUNT (DISTINCT id_test) AS total_Tests,
COUNT (CASE WHEN MetricsDataHistory.id_metric = 1 
                 AND MetricsDataHistory.value_metric <= 3  THEN 1 END) AS Usability_failures,
COUNT (CASE WHEN MetricsDataHistory.id_metric = 2
                 AND MetricsDataHistory.value_metric <= 3  THEN 1 END) AS App_failures,
SUM (CASE WHEN MetricsDataHistory.id_metric = 3 
                THEN MetricsDataHistory.value_metric ELSE 0 END) AS Sum_failures
FROM TestHistory INNER JOIN MetricsDataHistory
ON TestHistory.id = MetricsDataHistory.id_test
GROUP BY id_version
)

SELECT 
App.name_app,
Version.name_version,
total_Tests,
((Usability_failures * 100)/total_Tests) AS Usability_failures_percent,
((App_failures * 100)/total_Tests) AS App_failures_percent,
Sum_failures
FROM CountFailures INNER JOIN Version
ON CountFailures.id_version = Version.id
INNER JOIN App
ON Version.id_app = App.id

GO

CREATE VIEW Avg_userTime AS 
SELECT 
name_user,
AVG (duration_test_inMin) AS Avg_duration_testInMin
FROM SummaryTest
GROUP BY name_user

GO

CREATE PROCEDURE SaveTest 
@id_user INT,
@id_version INT,
@name_test VARCHAR(MAX),
@result VARCHAR(MAX),
@start_test BIGINT,
@end_test BIGINT,
@metric_A INT,
@metric_B INT,
@metric_C INT
AS
BEGIN
DECLARE @tableID TABLE (
id_test INT NOT NULL);

INSERT INTO TestHistory (name_test,result,id_version,id_user)
OUTPUT inserted.id INTO @tableID
VALUES (@name_test, @result, @id_version, @id_user)

INSERT INTO TestTimeHistory (start_test, end_test , id_test)
VALUES (@start_test, @end_test, (SELECT * FROM @tableID))

INSERT INTO MetricsDataHistory (value_metric, id_test , id_metric)
VALUES(@metric_A , (SELECT * FROM @tableID) , 1)

INSERT INTO MetricsDataHistory (value_metric, id_test , id_metric)
VALUES(@metric_B , (SELECT * FROM @tableID) , 2)

INSERT INTO MetricsDataHistory (value_metric, id_test , id_metric)
VALUES(@metric_C , (SELECT * FROM @tableID) , 3)

END

GO


CREATE PROCEDURE SaveVersion 
@id_app INT,
@name_version VARCHAR(50)

AS
BEGIN

INSERT INTO Version (id_app, name_version)
VALUES (@id_app, @name_version)

END

GO

CREATE PROCEDURE SaveUser
@name_user VARCHAR(50),
@password VARCHAR(MAX),
@id_role INT

AS
BEGIN

INSERT INTO UserData (name_user, password, id_role) 
VALUES (@name_user, @password, @id_role)

END

GO


EXEC SaveUser @name_user = 'admin' , @password = '12345', @id_role = 2;
EXEC SaveUser @name_user = 'test' , @password = '12345', @id_role = 1;

