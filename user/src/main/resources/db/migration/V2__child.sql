CREATE TABLE child (
    child_id INTEGER PRIMARY KEY,
    child_name VARCHAR NOT NULL,
    parent_id INTEGER NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES parent(parent_id)
)