CREATE TABLE FLOWERSHOP.FLOWERS
(
    NAME                           CHARACTER VARYING(50) NOT NULL,
    COLOR                          CHARACTER VARYING(45) NOT NULL,
    PRICE                          DOUBLE PRECISION NOT NULL,
    FLOWERSHOP_IDFLOWERSHOP        INTEGER NOT NULL,
    TICKET_IDTICKET                INTEGER,
    TICKET_FLOWERSHOP_IDFLOWERSHOP INTEGER,
    IDPRODUCT                      INTEGER IDENTITY PRIMARY KEY,
    CONSTRAINT FK_FLOWERS_FLOWERSHOP1 FOREIGN KEY (FLOWERSHOP_IDFLOWERSHOP) REFERENCES FLOWERSHOP.FLOWERSHOP,
    CONSTRAINT FK_FLOWERS_PRODUCTIDGENERATOR FOREIGN KEY (IDPRODUCT) REFERENCES FLOWERSHOP.PRODUCTIDGENERATOR
);
