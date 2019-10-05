CREATE TABLE users (
  id         UUID PRIMARY KEY,
  email      VARCHAR,
  name       VARCHAR,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP
);