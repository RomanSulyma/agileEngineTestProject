# agileEngineTestProject
Test project

1) Create DB

CREATE TABLE PHOTOS (
ID INT PRIMARY KEY,
	photo_id TEXT,
	author TEXT,
	camera TEXT,
	tags TEXT,
	cropped_picture TEXT,
	full_picture TEXT
)

2) Add DB properties

spring.datasource.url
spring.datasource.username
spring.datasource.password

3) Start app with maven and Java 8
