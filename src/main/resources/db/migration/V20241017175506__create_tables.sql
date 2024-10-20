create table tbl_users(id uuid primary key, name varchar(255) not null, email varchar(255) not null, phone_number varchar(255) not null);
create table tbl_books(id uuid primary key, title varchar(255) not null, author varchar(255) not null, isbn varchar(20) unique, publication_date date, registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

create table tbl_loans(
    id uuid primary key, user_id uuid, book_id uuid, loan_date date, return_date date, expected_return_date_book date,
    status varchar(20) check (status in ('ACTIVE', 'RETURNED', 'PENDING')),
    constraint fk_user_loan foreign key (user_id) references tbl_users(id) on delete cascade,
    constraint fk_book_loan foreign key (book_id) references tbl_books(id) on delete cascade);

create table tbl_returned(
    id uuid primary key, user_id uuid, book_id uuid, loan_id uuid, return_date date,
    constraint fk_user_returned foreign key (user_id) references tbl_users(id) on delete cascade,
    constraint fk_book_returned foreign key (book_id) references tbl_books(id) on delete cascade,
    constraint fk_loan_returned foreign key (loan_id) references tbl_loans(id) on delete cascade);
