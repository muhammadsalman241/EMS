CREATE TABLE "EMS"."EMPLOYEE"(	
    "E_ID" NUMBER NOT NULL ENABLE, 
    "E_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
    "E_AGE" NUMBER NOT NULL ENABLE, 
    "E_SALARY" NUMBER NOT NULL ENABLE, 
    "DATE_OF_APPOINTMENT" DATE NOT NULL ENABLE, 
    CONSTRAINT "Employee_ID" PRIMARY KEY ("E_ID")
);
