-- USERS
insert into users (id, email, username, password_hash, global_role)
values
    (1, 'user@example.com', 'User', 'hashedpass1', 'USER'),
    (2, 'support@example.com', 'Support', 'hashedpass2', 'SUPPORT'),
    (3, 'admin@example.com', 'Admin', 'hashedpass3', 'ADMIN')
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
insert into categories (category_id, name, description)
values
    (1, 'Programming', 'Coding and software development'),
    (2, 'Math', 'Mathematics and statistics')
ON CONFLICT DO NOTHING;

-- COURSES
insert into courses (title, description, level, price, is_hidden, category_id, provider_id)
values
    ('Intro to Java', 'Learn Java basics', 'BEGINNER', 999.00, false, 1, 1),
    ('Linear Algebra', 'Vectors and matrices', 'INTERMEDIATE', 599.00, true, 2, 2);

-- FAVORITES
insert into favorites (user_id, course_id)
values
    (1, 1),
    (1, 2),
    (3, 1)
ON CONFLICT DO NOTHING;

-- ORDERS
insert into orders (order_id, user_id, status, total_price)
values
    (1, 1, true, 999.00)
    ON CONFLICT DO NOTHING;

insert into order_courses (order_id, course_id, quantity)
values
    (1, 1, 1)
ON CONFLICT DO NOTHING;

-- RESET SEQUENCES

SELECT setval('orders_order_id_seq', (SELECT MAX(id) FROM orders));
SELECT setval('order_courses_order_course_id_seq', (SELECT MAX(id) FROM order_courses));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('provider_organizations_id_seq', (SELECT MAX(id) FROM provider_organizations));
SELECT setval('organization_join_requests_id_seq', (SELECT MAX(id) FROM organization_join_requests));
SELECT setval('categories_category_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses));
SELECT setval('favorites_favorite_id_seq', (SELECT MAX(id) FROM favorites));
