CREATE TABLE parent (
    parent_id INTEGER PRIMARY KEY,
    parent_uuid VARCHAR NOT NULL,
    parent_name VARCHAR NOT NULL,
    child_id INTEGER
)