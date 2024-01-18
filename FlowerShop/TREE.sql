create table FLOWERSHOP.TREE
(
    NAME                           CHARACTER VARYING(50) not null,
    HEIGHT                         SMALLINT              not null,
    PRICE                          DOUBLE PRECISION      not null,
    FLOWERSHOP_IDFLOWERSHOP        INTEGER               not null,
    TICKET_IDTICKET                INTEGER,
    TICKET_FLOWERSHOP_IDFLOWERSHOP INTEGER,
    IDPRODUCT                      INTEGER,
    constraint FK_TREE_FLOWERSHOP1
        foreign key (FLOWERSHOP_IDFLOWERSHOP) references FLOWERSHOP.FLOWERSHOP,
    constraint FK_TREE_PRODUCTIDGENERATOR
        foreign key (IDPRODUCT) references FLOWERSHOP.PRODUCTIDGENERATOR
);

