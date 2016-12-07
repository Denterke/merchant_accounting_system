# contractors schema

# --- !Ups
CREATE TABLE contractors
(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX contractors_id_unique ON contractors (id);

# --- !Downs
