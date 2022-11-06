create extension hstore;
create schema keywords;
create table if not exists keywords."Keyword" ("keyword_id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL);
