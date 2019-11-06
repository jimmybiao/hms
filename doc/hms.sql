use hms;

create table memo(
	id int(10) not null auto_increment,
    title varchar(30),
    amount decimal(10,2),
    remark varchar(500),
    memo_date datetime not null default now(),
    primary key (id)
);

create index idx_memo on memo(id);