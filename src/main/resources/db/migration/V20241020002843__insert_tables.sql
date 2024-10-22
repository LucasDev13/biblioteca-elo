insert into tbl_users(id, name, email, phone_number)
values
    ('e9216d32-e717-48f8-9700-3361a3ef2f2d', 'Lucas Fernando', 'lucas@gmail.com', '999999999999'),
    ('bb839ee4-54a1-42b3-b5c2-57997167ae22', 'Lucas Pontes', 'lucas.pontes@gmail.com', '222222222222'),
    ('385491a9-be4c-4132-9b01-6fe3b6a61dee', 'Thais Silva', 'thais.silva@gmail.com', '333333333333'),
    ('af9dfac2-fb45-4a31-97d6-d5dbf4d0d50c', 'Margarida Souza', 'margarida.souza@yahoo.com', '444444444444');

insert into tbl_categories (id, name)
values
  ('41f0284c-e740-4842-9c3b-5258715f267a', 'Fiction'),
  ('73d8470a-d772-487b-8929-54f53a44c28f', 'Science'),
  ('0d0a36e6-462b-44e9-a757-f3fa1c0fa585', 'History'),
  ('f74c6388-3c24-44b3-b0ee-2248f0baced3', 'Technology'),
  ('04c02cbb-ef99-44d4-b6a1-92921a30103e', 'Philosophy');

--Fiction
insert into tbl_books(id, title, author, isbn, category_id, publication_date)
values
    ('cea95b10-5082-46b7-bc2c-2f21be968c06', 'Meu livro', 'Lucas', '9783127323207', '41f0284c-e740-4842-9c3b-5258715f267a', '2024-10-19'),
    ('4795cdcc-ac2f-44f3-86a3-628796b319ac', 'The Great Gatsby', 'F. Scott Fitzgerald', '9783127326207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
    ('585f9336-e7f8-4b95-a256-adbe73417ad5', 'A Brief History of Time', 'Stephen Hawking', '9783124323207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
    ('8bfb42a6-b7dd-4258-8bce-62fba194f41f', '1984', 'George Orwell', '9781123323207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
    ('a7a499d4-150e-4f7d-bf80-7ae1d8aacf2c', 'The Selfish Gene', 'Richard Dawkins', '9783167323207', (select id from tbl_categories where name = 'Fiction'), '2024-10-19'),
--History
  ('c06d77b0-6aec-44a7-83e6-d144c6c20e5b', 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', '9283127323207', (select id from tbl_categories where name = 'History'), '2024-10-19'),
  ('a29a974d-35c6-4ce7-9372-d671d24eb568', 'The Rise and Fall of the Third Reich', 'William L. Shirer', '1283127323207', (select id from tbl_categories where name = 'History'), '2024-10-19'),
--Technology
  ('04ff3cac-32d3-4e5d-a4e0-0049c4d5e263', 'Clean Code', 'Robert C. Martin', '9783127323205', (select id from tbl_categories where name = 'Technology'), '2024-10-19'),
  ('20d0d1b0-b0b0-41bc-9dd1-9b1b4113659a', 'The Pragmatic Programmer', 'Andrew Hunt and David Thomas', '0183127323207', (select id from tbl_categories where name = 'Technology'), '2024-10-19'),
--Philosophy
  ('bd9be8d8-2520-4020-add8-8ebe5c4262cf', 'The Republic', 'Plato', '9781127323207', (select id from tbl_categories where name = 'Philosophy'), '2024-10-19'),
  ('0b3459c9-fc30-4b63-af8f-66090ec87115', 'Meditations', 'Marcus Aurelius', '9783127323210', (select id from tbl_categories where name = 'Philosophy'), '2024-10-19');

insert into tbl_loans(id, user_id, book_id, loan_date, expected_return_date_book, status)
values
    ('cdc42a3d-721c-4359-bba8-3ba963fd4931', 'e9216d32-e717-48f8-9700-3361a3ef2f2d', 'cea95b10-5082-46b7-bc2c-2f21be968c06', '2024-10-20', '2024-10-27', 'ACTIVE'),
    ('2db3d44b-8c44-4c70-8547-e0e44c871157', (select id from tbl_users where name = 'Lucas Fernando'), (select id from tbl_books where title = 'The Great Gatsby'), '2024-01-10', null),
    ('ff9c2fbd-3748-47a9-8a1a-756483a3b7c2', (select id from tbl_users where name = 'Lucas Fernando'), (select id from tbl_books where title = 'Clean Code'), '2024-01-15', null),
    ('fee0cd38-6a84-41f1-8d0a-eb981a43be62', (select id from tbl_users where name = 'Lucas Fernando'), (select id from tbl_books where title = 'The Pragmatic Programmer'), '2024-01-20', null),
    ('0101afe8-73e8-4409-955f-b76d5e91a382', (select id from tbl_users where name = 'Margarida Souza'), (select id from tbl_books where title = 'Sapiens: A Brief History of Humankind'), '2024-02-01', null);