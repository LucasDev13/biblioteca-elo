insert into tbl_users(id, name, email, phone_number)
values('e9216d32-e717-48f8-9700-3361a3ef2f2d'::uuid, 'Lucas', 'lucas@gmail.com', '999999999999');

insert into tbl_books(id, title, author, isbn, category, publication_date)
values('cea95b10-5082-46b7-bc2c-2f21be968c06'::uuid, 'Meu livro', 'Lucas', '9783127323207', 'Suspense', '2024-10-19');

insert into tbl_loans(id, user_id, book_id, loan_date, expected_return_date_book, status)
values('cdc42a3d-721c-4359-bba8-3ba963fd4931', 'e9216d32-e717-48f8-9700-3361a3ef2f2d', 'cea95b10-5082-46b7-bc2c-2f21be968c06', '2024-10-20', '2024-10-27', 'ACTIVE');