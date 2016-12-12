# nomenclatures schema

# --- !Ups
CREATE TABLE nomenclatures
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX nomenclatures_id_unique ON nomenclatures (id);

# --- !Downs
