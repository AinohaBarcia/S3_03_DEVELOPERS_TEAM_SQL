create table FLOWERSHOP.FLOWERSHOP
(
    IDFLOWERSHOP INTEGER auto_increment
        primary key,
    NAME         CHARACTER VARYING(255) not null,
    TOTAL_STOCK  INTEGER
);

