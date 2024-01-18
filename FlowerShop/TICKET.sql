create table FLOWERSHOP.TICKET
(
    IDTICKET                INTEGER auto_increment
        primary key,
    FLOWERSHOP_IDFLOWERSHOP INTEGER,
    DATE                    CHARACTER VARYING(45) not null,
    constraint FK_TICKET_FLOWERSHOP
        foreign key (FLOWERSHOP_IDFLOWERSHOP) references FLOWERSHOP.FLOWERSHOP
);

