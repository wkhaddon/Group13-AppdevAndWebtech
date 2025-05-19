-- USERS
insert into users (id, email, username, password_hash, global_role)
values
    (1, 'user@example.com', 'User', '$2a$10$GTnB4a1aZXVT64Veo5nPf.lx4YhT9mjrg2xObK/l0Wbh4dz5XLmwK', 'USER'), -- pw: UserPassword
    (2, 'provider@example.com', 'Provider', '$2a$10$EdxgE9ydaXT4CIModUyxi.rgvryN/icS0xCLwV0U/CVzp0c812uDe', 'PROVIDER'), -- pw: ProviderPassword
    (3, 'support@example.com', 'Support', '$2a$10$i/8oK3xv1N7fK296Te26LuVfsjQobNW9VW6pa8273cQTB/hvITEdu', 'SUPPORT'), -- pw: SupportPassword
    (4, 'admin@example.com', 'Admin', '$2a$10$88UWNteGU8qH4VIdDKwKb.1FPbyAOMcJ5A4..26WgrehHkmXX9mOS', 'ADMIN') -- pw: AdminPassword
ON CONFLICT DO NOTHING;

-- ORGANIZATIONS
insert into provider_organizations (id, name, currency, approved)
values
    (1, 'NTNU', 'NOK', true),
    (2, 'Freelance Academy', 'USD', true)
ON CONFLICT DO NOTHING;

-- MEMBERSHIPS
insert into user_organization_memberships (user_id, organization_id, org_role)
values
    (1, 1, 'INSTRUCTOR'),
    (3, 2, 'OWNER')
ON CONFLICT DO NOTHING;

-- CATEGORIES
insert into categories (id, name, description)
values
    (1, 'Programming', 'Coding and software development'),
    (2, 'Math', 'Mathematics and statistics')
ON CONFLICT DO NOTHING;

-- COURSES
insert into courses (id, title, description, level, price, is_hidden, category_id, provider_id)
values
    (1, 'Intro to Java', 'Learn Java basics', 'BEGINNER', 999.00, false, 1, 1),
    (2, 'Linear Algebra', 'Vectors and matrices', 'INTERMEDIATE', 599.00, true, 2, 2);

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
