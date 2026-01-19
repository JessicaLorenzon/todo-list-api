CREATE TABLE tasks (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    user_id TEXT NOT NULL,

    CONSTRAINT fk_task_user
            FOREIGN KEY (user_id)
            REFERENCES users (id)
);