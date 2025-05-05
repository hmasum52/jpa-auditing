CREATE TABLE IF NOT EXISTS "collection" (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS "task" (
    id BIGSERIAL PRIMARY KEY,
    collection_id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    status TEXT NOT NULL,
    FOREIGN KEY (collection_id) REFERENCES collection(id)
);


INSERT INTO collection (name, description) VALUES
('My First Collection', 'This is my first collection');

INSERT INTO task (collection_id, name, description, status) VALUES
(1, 'My First Task', 'This is my first task', 'pending'),
(1, 'My Second Task', 'This is my second task', 'completed'),
(1, 'My Third Task', 'This is my third task', 'pending');


-- audit tables
create table revinfo (
    id integer not null,
    modifer varchar(255),
    timestamp timestamp,
    primary key (id)
);


create table collection_aud (
    id bigint not null,
    rev integer not null,
    revtype smallint,
    description text,
    description_mod boolean,
    name text,
    name_mod boolean,
    primary key (rev, id)
);


create table task_aud (
    id bigint not null,
    rev integer not null,
    revtype smallint,
    description text,
    description_mod boolean,
    name text,
    name_mod boolean,
    status text,
    status_mod boolean,
    collection_id bigint,
    collection_mod boolean,
    primary key (rev, id)
);

create sequence revinfo_seq start with 1 increment by 50;

alter table if exists collection_aud 
    add constraint FKi85irm542lohmbe1scaoacfad 
    foreign key (rev) 
    references revinfo;

alter table if exists task_aud 
    add constraint FKaerb34sjraiw4vjh4oh46rb71 
    foreign key (rev) 
    references revinfo;