create table FLOWERSHOP.TICKETITEMS
(
    IDTICKETITEM            INTEGER auto_increment
        primary key,
    ID_PRODUCT              INTEGER,
    PRODUCT_TYPE            CHARACTER VARYING(45),
    QUANTITY                SMALLINT,
    UNIT_PRICE              DOUBLE PRECISION,
    TOTAL_PURCHASE          INTEGER,
    TICKET_IDTICKET         INTEGER,
    FLOWERSHOP_IDFLOWERSHOP INTEGER not null,
    constraint FK_TICKETITEM_FLOWERSHOP
        foreign key (FLOWERSHOP_IDFLOWERSHOP) references FLOWERSHOP.FLOWERSHOP,
    constraint FK_TICKETITEM_TICKET
        foreign key (TICKET_IDTICKET) references FLOWERSHOP.TICKET
            on delete cascade
);

