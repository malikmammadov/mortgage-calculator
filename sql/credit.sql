CREATE TABLE CREDIT 
(
  ID NUMBER NOT NULL 
, CUSTOMER_ID NUMBER NOT NULL 
, HOME_PRICE NUMBER NOT NULL 
, INITIAL_PAYMENT NUMBER NOT NULL 
, CREDIT_AMOUNT NUMBER NOT NULL 
, INTEREST_AMOUNT NUMBER NOT NULL 
, FIRST_PAYMENT_DATE DATE 
, LAST_PAYMENT_DATE DATE 
, ACTION_DATE DATE DEFAULT sysdate NOT NULL 
, CONSTRAINT CREDIT_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE CREDIT
ADD CONSTRAINT CREDIT_FK1 FOREIGN KEY
(
  CUSTOMER_ID 
)
REFERENCES CUSTOMER
(
  ID 
)
ENABLE;

COMMENT ON COLUMN CREDIT.CUSTOMER_ID IS 'musteri id';

COMMENT ON COLUMN CREDIT.HOME_PRICE IS 'evin AZN olaraq deyeri';

COMMENT ON COLUMN CREDIT.INITIAL_PAYMENT IS 'musterinin odediyi ilkin odenis meblegi';

COMMENT ON COLUMN CREDIT.CREDIT_AMOUNT IS 'bankin ayirdigi kredit meblegi';

COMMENT ON COLUMN CREDIT.INTEREST_AMOUNT IS 'musterinin toplam odediyi faiz meblegi';

COMMENT ON COLUMN CREDIT.FIRST_PAYMENT_DATE IS 'ilk odenis tarixi';

COMMENT ON COLUMN CREDIT.LAST_PAYMENT_DATE IS 'son odenis tarixi';

COMMENT ON COLUMN CREDIT.ACTION_DATE IS 'emeliyyat tarixi';
