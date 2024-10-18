create table livros(id uuid primary key, titulo varchar(255) not null, autor varchar(255) not null, isbn varchar(20) unique, data_publicacao date, data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

create table usuarios(id uuid primary key, nome varchar(255) not null, email varchar(255) not null, telefone varchar(255) not null);

create table emprestimos(
    id uuid primary key, usuario_id uuid, livro_id uuid, data_emprestimo date, data_prevista_devolucao date,
    status varchar(20) check (status in ('ATIVO', 'FINALIZADO')),
    constraint fk_usuario_emprestimo foreign key (usuario_id) references usuarios(id) on delete cascade,
    constraint fk_livro_emprestimo foreign key (livro_id) references livros(id) on delete cascade);

create table devolucoes(
    id uuid primary key, usuario_id uuid, livro_id uuid, emprestimo_id uuid, data_devolucao date,
    constraint fk_usuario_devolucao foreign key (usuario_id) references usuarios(id) on delete cascade,
    constraint fk_livro_devolucao foreign key (livro_id) references livros(id) on delete cascade,
    constraint fk_emprestimo_devolucao foreign key (emprestimo_id) references emprestimos(id) on delete cascade);
