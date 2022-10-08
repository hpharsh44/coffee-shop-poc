create table "customer"(
   id                  BIGSERIAL NOT NULL,
                       PRIMARY KEY (id) ,
   firstName           VARCHAR(50) NOT NULL ,
   lastName            VARCHAR(50) NOT NULL ,
   mobile_number       CHAR(15) ,
   address             VARCHAR(256),
   city                VARCHAR(50) ,
   postcode            VARCHAR(10) ,
   country             VARCHAR(50) ,
   status              CHAR(50) NOT NULL ,
   addressType         CHAR(50) NOT NULL ,
   created_at          TIMESTAMP NOT NULL ,
   updated_at          TIMESTAMP NOT NULL
);