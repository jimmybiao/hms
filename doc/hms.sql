use hms;

/*table memo*/
create table memo(
	id int(10) not null auto_increment,
    title varchar(30),
    amount decimal(10,2),
    remark varchar(500),
    memo_date datetime not null default now(),
    primary key (id)
);

create index idx_memo on memo(id);

/*table investment*/
create table investment(
	id int(10) not null auto_increment,
    invest_category varchar(30) not null,
    invest_subcategory varchar(20) not null,
    amount decimal(10,2) not null,
    remark varchar(100),
    invest_date datetime not null,
    updated_date datetime not null default now(),
    primary key (id)
);

create index idx_investment on investment(id);

/*table expense*/
create table expense(
	id int(10) not null auto_increment,
    expense_category varchar(30) not null,
    expense_subcategory varchar(30) not null,
    amount decimal(10,2) not null,
    remark varchar(100),
    created_date datetime not null default now(),
    updated_date datetime not null,
    primary key (id)
);

create index idx_expense on expense(id);

/*table income*/
create table income(
	id int(10) not null auto_increment,
    income_category varchar(30) not null,
    amount decimal(10,2) not null,
    remark varchar(100),
    created_date date not null,
    updated_date datetime not null,
    primary key (id)
);

create index idx_income on income(id);