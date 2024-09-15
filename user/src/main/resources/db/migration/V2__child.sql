CREATE TABLE child (
    child_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    child_uuid VARCHAR NOT NULL,
    child_name VARCHAR NOT NULL,
    parent_id INTEGER NOT NULL,
    child_chore_id INTEGER,
    FOREIGN KEY (parent_id) REFERENCES parent(parent_id)
)