# nomenclatures schema

# --- !Ups
CREATE TABLE documents
(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    contractor_id INTEGER NOT NULL,
    is_conducted BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE UNIQUE INDEX documents_id_unique ON documents (id);

# --- !Downs
