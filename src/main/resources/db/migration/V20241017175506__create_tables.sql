create table tbl_users(
    id uuid primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(255) not null,
    create_at TIMESTAMP WITH TIME ZONE,
    update_at TIMESTAMP WITH TIME ZONE);

create table tbl_categories (
    id UUID primary key,
    name varchar(255) not null);

create table tbl_books(
    id uuid primary key,
    title varchar(255) not null,
    author varchar(255) not null,
    isbn varchar(20) unique,
    category_id UUID,
    publication_date date,
    registration_date TIMESTAMP WITH TIME ZONE,
    create_at TIMESTAMP WITH TIME ZONE,
    update_at TIMESTAMP WITH TIME ZONE,
    foreign key (category_id) references tbl_categories(id));

create table tbl_loans(
    id uuid primary key, user_id uuid, book_id uuid, loan_date date, return_date date, expected_return_date_book date,
    status varchar(20) check (status in ('ACTIVE', 'RETURNED', 'PENDING')),
    create_at TIMESTAMP WITH TIME ZONE,
    update_at TIMESTAMP WITH TIME ZONE,
    constraint fk_user_loan foreign key (user_id) references tbl_users(id) on delete cascade,
    constraint fk_book_loan foreign key (book_id) references tbl_books(id) on delete cascade);

create table tbl_returned(
    id uuid primary key, user_id uuid, book_id uuid, loan_id uuid, return_date date,
    create_at TIMESTAMP WITH TIME ZONE,
    update_at TIMESTAMP WITH TIME ZONE,
    constraint fk_user_returned foreign key (user_id) references tbl_users(id) on delete cascade,
    constraint fk_book_returned foreign key (book_id) references tbl_books(id) on delete cascade,
    constraint fk_loan_returned foreign key (loan_id) references tbl_loans(id) on delete cascade);
