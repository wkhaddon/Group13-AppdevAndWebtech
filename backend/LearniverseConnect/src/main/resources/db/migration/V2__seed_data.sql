-- USERS
insert into users (id, email, username, password_hash, global_role)
values
    (1, 'user@example.com', 'User', '$2a$10$GTnB4a1aZXVT64Veo5nPf.lx4YhT9mjrg2xObK/l0Wbh4dz5XLmwK', 'USER'), -- pw: UserPassword
    (2, 'provider@example.com', 'Provider', '$2a$10$EdxgE9ydaXT4CIModUyxi.rgvryN/icS0xCLwV0U/CVzp0c812uDe', 'PROVIDER'), -- pw: ProviderPassword
    (3, 'support@example.com', 'Support', '$2a$10$i/8oK3xv1N7fK296Te26LuVfsjQobNW9VW6pa8273cQTB/hvITEdu', 'SUPPORT'), -- pw: SupportPassword
    (4, 'admin@example.com', 'Admin', '$2a$10$88UWNteGU8qH4VIdDKwKb.1FPbyAOMcJ5A4..26WgrehHkmXX9mOS', 'ADMIN') -- pw: AdminPassword
ON CONFLICT DO NOTHING;

-- PROVIDER ORGANIZATIONS
insert into provider_organizations (id, name, currency, approved)
values
    (1, 'NTNU', 'NOK', true),
    (2, 'Oracle', 'NOK', true),
    (3, 'Apache Software Foundation', 'USD', true),
    (4, 'Pearson', 'USD', true),
    (5, 'Microsoft', 'NOK', true),
    (6, 'Amazon', 'USD', true),
    (7, 'Adobe', 'NOK', true),
    (8, 'Apple', 'NOK', true),
    (9, 'Google', 'USD', true),
    (10, 'Handelshøyskolen BI', 'NOK', true),
    (11, 'UiO', 'NOK', true),
    (12, 'UiB', 'NOK', true)
ON CONFLICT DO NOTHING;

-- MEMBERSHIPS
insert into user_organization_memberships (user_id, organization_id, org_role)
values
    (1, 1, 'INSTRUCTOR'),
    (3, 2, 'OWNER')
ON CONFLICT DO NOTHING;

-- CATEGORIES
insert into categories (id, name)
values
    (1, 'Information Technologies'),
    (2, 'Digital Marketing'),
    (3, 'Business and Entrepreneurship'),
    (4, 'Data Science and Analytics')
ON CONFLICT DO NOTHING;

-- COURSES
insert into courses (id, title, description, level, price, is_hidden, category_id, provider_id)
values
    (1, 'Real-Time Programming in Java', 'Learn Java basics', 'EXPERT', 999.00, false, 1, 1),
    (2, 'SQL Essentials', 'Master SQL and relational databases', 'BEGINNER', 199.00, false, 1, 2),
    (3, 'Digital Marketing Basics', 'Introduction to digital marketing strategies', 'BEGINNER', 399.00, false, 1, 4),
    (4, 'Azure Fundamentals', 'Learn Microsoft Azure Cloud Basics', 'BEGINNER', 299.00, false, 1, 3),
    (5, 'Advanced Data Analysis with Python', 'Deep dive into data analysis techniques using Python', 'EXPERT', 499.00, false, 1, 5),
    (6, 'Creating Web Application with .Net', 'Learn how to create effective business strategies', 'INTERMEDIATE', 699.00, true, 1, 6),
    (7, 'Cloud Computing with AWS', 'Introduction to cloud computing using AWS services', 'BEGINNER', 799.00, true, 2, 7),
    (8, 'Machine Learning with R', 'Learn machine learning algorithms using R programming language', 'EXPERT', 899.00, true, 2, 8),
    (9, 'Project Management Fundamentals', 'Basics of project management principles and practices', 'BEGINNER', 399.00, true, 3, 9),
    (10, 'Search Engine Optimization Techniques', 'Learn SEO best practices for better online visibility', 'INTERMEDIATE', 599.00, true, 4, 10),
    (11, 'Data Visualization with Tableau', 'Create stunning visualizations using Tableau', 'EXPERT', 799.00, true, 4, 11),
    (12, 'Cybersecurity Essentials', 'Introduction to cybersecurity principles and practices', 'BEGINNER', 499.00, true, 4, 12)


-- FAVORITES
insert into favorites (user_id, course_id)
values
    (1, 1),
    (1, 2),
    (3, 1)
ON CONFLICT DO NOTHING;

-- ORDERS
insert into orders (id, user_id, status, total_price)
values
    (1, 1, true, 999.00)
    ON CONFLICT DO NOTHING;

insert into order_courses (order_id, course_id, quantity)
values
    (1, 1, 1)
ON CONFLICT DO NOTHING;

-- RESET SEQUENCES

DO
$$
    DECLARE
        seq RECORD;
    BEGIN
        FOR seq IN
            SELECT
                pg_class.relname AS sequence_name,
                'SELECT setval(' ||
                quote_literal(pg_class.relname) ||
                ', COALESCE(MAX(' || column_name || '), 1)) FROM ' || table_name AS reset_sql
            FROM
                pg_class
                    JOIN
                pg_namespace ON pg_namespace.oid = pg_class.relnamespace
                    JOIN
                information_schema.columns ON
                    columns.column_default LIKE ('nextval(' || quote_literal(pg_class.relname) || '::regclass)%')
            WHERE
                pg_class.relkind = 'S'  -- sequences only
            LOOP
                EXECUTE seq.reset_sql;
            END LOOP;
    END;
$$;
