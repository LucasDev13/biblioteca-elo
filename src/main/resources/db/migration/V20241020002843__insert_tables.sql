insert into tbl_users(id, name, email, phone_number)
values('e9216d32-e717-48f8-9700-3361a3ef2f2d'::uuid, 'Lucas Fernando', 'lucas@gmail.com', '999999999999');
insert into tbl_users (id, name, email, phone_number) values
  (gen_random_uuid(), 'Lucas Pontes', 'lucas.pontes@gmail.com', '222222222222'),
  (gen_random_uuid(), 'Thais Silva', 'thais.silva@gmail.com', '333333333333'),
  (gen_random_uuid(), 'Margarida Souza', 'margarida.souza@yahoo.com', '444444444444');

insert into tbl_categories (id, name) values
  ('41f0284c-e740-4842-9c3b-5258715f267a', 'Fiction'),
  ('73d8470a-d772-487b-8929-54f53a44c28f', 'Science'),
  ('0d0a36e6-462b-44e9-a757-f3fa1c0fa585', 'History'),
  ('f74c6388-3c24-44b3-b0ee-2248f0baced3', 'Technology'),
  ('04c02cbb-ef99-44d4-b6a1-92921a30103e', 'Philosophy');

insert into tbl_books(id, title, author, isbn, category_id, publication_date)
values('cea95b10-5082-46b7-bc2c-2f21be968c06'::uuid, 'Meu livro', 'Lucas', '9783127323207', '41f0284c-e740-4842-9c3b-5258715f267a', '2024-10-19');
insert into tbl_books (id, title, author, isbn, category_id, publication_date) values
  (gen_random_uuid(), 'The Great Gatsby', 'F. Scott Fitzgerald', '9783127326207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
  (gen_random_uuid(), 'A Brief History of Time', 'Stephen Hawking', '9783124323207', (select id from tbl_categories where name = 'Science'), '2024-10-19'),
  (gen_random_uuid(), 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', '9283127323207', (select id from tbl_categories where name = 'History'), '2024-10-19'),
  (gen_random_uuid(), 'Clean Code', 'Robert C. Martin', '9783127323205', (select id from tbl_categories where name = 'Technology'), '2024-10-19'),
  (gen_random_uuid(), 'The Republic', 'Plato', '9781127323207', (select id from tbl_categories where name = 'Philosophy'), '2024-10-19'),
  (gen_random_uuid(), '1984', 'George Orwell', '9781123323207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
  (gen_random_uuid(), 'The Selfish Gene', 'Richard Dawkins', '9783167323207', (select id from tbl_categories where name = 'Science'), '2024-10-19'),
  (gen_random_uuid(), 'The Rise and Fall of the Third Reich', 'William L. Shirer', '1283127323207', (select id from tbl_categories where name = 'History'), '2024-10-19'),
  (gen_random_uuid(), 'The Pragmatic Programmer', 'Andrew Hunt and David Thomas', '0183127323207', (select id from tbl_categories where name = 'Technology'), '2024-10-19'),
  (gen_random_uuid(), 'Meditations', 'Marcus Aurelius', '9783127323210', (select id from tbl_categories where name = 'Philosophy'), '2024-10-19');

insert into tbl_loans(id, user_id, book_id, loan_date, expected_return_date_book, status)
values('cdc42a3d-721c-4359-bba8-3ba963fd4931', 'e9216d32-e717-48f8-9700-3361a3ef2f2d', 'cea95b10-5082-46b7-bc2c-2f21be968c06', '2024-10-20', '2024-10-27', 'ACTIVE');
insert into tbl_loans (id, user_id, book_id, loan_date, return_date) values
  (gen_random_uuid(),
    (select id from tbl_users where name = 'Lucas Fernando'),
    (select id from tbl_books where title = 'The Great Gatsby'), '2024-01-10', null),
  (gen_random_uuid(),
    (select id from tbl_users where name = 'Lucas Pontes'),
    (select id from tbl_books where title = 'Clean Code'), '2024-01-15', null),
  (gen_random_uuid(),
    (select id from tbl_users where name = 'Thais Silva'),
    (select id from tbl_books where title = '1984'), '2024-01-20', null),
  (gen_random_uuid(),
    (select id from tbl_users where name = 'Margarida Souza'),
    (select id from tbl_books where title = 'Sapiens: A Brief History of Humankind'), '2024-02-01', null);