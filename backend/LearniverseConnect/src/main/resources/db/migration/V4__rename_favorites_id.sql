-- ==============================
-- Flyway Migration: Rename favorites.favorite_id to favorites.id
-- ==============================

-- Rename the column
ALTER TABLE favorites RENAME COLUMN favorite_id TO id;

-- Rename the sequence (if default is set to use it)
ALTER SEQUENCE favorites_favorite_id_seq RENAME TO favorites_id_seq;

-- Update the DEFAULT to use the renamed sequence
ALTER TABLE favorites ALTER COLUMN id SET DEFAULT nextval('favorites_id_seq'::regclass);
