CREATE DATABASE Pix;

USE Pix;

CREATE TABLE PIX_KEY(
    ID VARCHAR(36) PRIMARY KEY,
    KEY_TYPE VARCHAR(9),
    KEY_VALUE VARCHAR(77),
    AGENCY_NUMBER INT,
    ACCOUNT_TYPE VARCHAR(10),
    ACCOUNT_NUMBER INT,
    ACCOUNT_HOLDER_NAME VARCHAR(30),
    ACCOUNT_HOLDER_LAST_NAME VARCHAR(45),
    PERSON_TYPE VARCHAR(9),
    DATETIME_INCLUSION DATETIME,
    DATETIME_INACTIVATION DATE
)
