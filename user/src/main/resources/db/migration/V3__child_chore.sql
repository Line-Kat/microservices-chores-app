CREATE TABLE child_chore (
    child_chore_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    child_chore_uuid VARCHAR NOT NULL,
    child_id INTEGER NOT NULL,
    chore_id INTEGER NOT NULL
)