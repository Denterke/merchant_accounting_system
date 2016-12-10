# contractors schema

# --- !Ups
CREATE TABLE contractors
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX contractors_id_unique ON contractors (id);

# --- !Downs
