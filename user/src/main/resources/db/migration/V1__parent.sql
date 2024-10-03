CREATE TABLE parent (
    parent_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    parent_uuid VARCHAR NOT NULL,
    parent_name VARCHAR NOT NULL,
    child_id INTEGER
)