CREATE TABLE saving_goal (
    saving_goal_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    saving_goal_uuid VARCHAR NOT NULL,
    child_uuid VARCHAR NOT NULL,
    saving_goal_name VARCHAR NOT NULL,
    saving_goal_value INTEGER NOT NULL
)