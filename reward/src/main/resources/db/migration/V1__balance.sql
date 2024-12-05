CREATE TABLE balance (
    balance_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    balance_uuid VARCHAR NOT NULL,
    child_uuid VARCHAR NOT NULL,
    balance_value INTEGER NOT NULL
)