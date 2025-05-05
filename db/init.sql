CREATE TABLE IF NOT EXISTS "collection" (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS "task" (
    id BIGSERIAL PRIMARY KEY,
    collection_id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    status TEXT NOT NULL,
    FOREIGN KEY (collection_id) REFERENCES collection(id)
);


INSERT INTO collection (name, description) VALUES
('My First Collection', 'This is my first collection');

INSERT INTO task (collection_id, name, description, status) VALUES
(1, 'My First Task', 'This is my first task', 'pending'),
(1, 'My Second Task', 'This is my second task', 'completed'),
(1, 'My Third Task', 'This is my third task', 'pending');