-- Step 1: Connect to the database
\c learniverse;

-- Step 2: Insert sample categories (avoid duplicates)
INSERT INTO category (name) VALUES
    ('Information Technology'),
    ('Digital Marketing'),
    ('Business and Entrepreneurship'),
    ('Data Science and Analytics')
ON CONFLICT (name) DO NOTHING;

-- Step 3: Insert sample providers (avoid duplicates)
INSERT INTO provider (name, currency) VALUES
    ('NTNU', 'NOK'),
    ('Oracle', 'USD'),
    ('Microsoft', 'USD')
ON CONFLICT (name) DO NOTHING;

-- Step 4: Insert sample courses (avoid duplicates)
INSERT INTO course (title, description, level, category_id, closest_session) VALUES
    ('Real-Time Programming in Java', 'Learn Java for real-time applications', 'Expert', 1, '2025-06-03'),
    ('SQL Essentials', 'Master SQL and relational databases', 'Beginner', 1, '2025-06-10'),
    ('Azure Fundamentals', 'Learn Microsoft Azure Cloud Basics', 'Beginner', 1, '2025-08-05')
ON CONFLICT (title) DO NOTHING;

-- Step 5: Insert sample course-provider price mappings
INSERT INTO course_provider (course_id, provider_id, price) VALUES
    (1, 1, 29999), -- NTNU offers Java Programming for 29,999 NOK
    (1, 2, 32000), -- Oracle offers Java Programming for 32,000 USD
    (2, 3, 200)    -- Microsoft offers SQL Essentials for 200 USD
ON CONFLICT DO NOTHING;
