create extension hstore;
create schema keywords;
create table if not exists keywords ("id" BIGSERIAL NOT NULL PRIMARY KEY, "name" VARCHAR NOT NULL);
insert into keywords ("id", "name") values ('1', 'фиаско'), ('2', 'агент'), ('3', 'марш'), ('4', 'алтарь'), ('5', 'арахис'), ('6', 'санки'), ('7', 'сверток');
