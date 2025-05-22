-- USERS
INSERT INTO users (id, email, username, password_hash, global_role) VALUES
    (1, 'dave@example.com', 'Dave', '$2a$10$vOMhb/NKkXWm3Tz5y2viGuBbpD1HKTYwVNasQ8YeKMSqXl0hUawp2', 'USER'), -- pw: Dangerous2024
    (2, 'chuck@example.com', 'Chuck', '$2a$10$ukyCw.OJVP6002QehKAqnO9pzyPTEeGsxZy6AXqUkZutpmJIijAry', 'ADMIN') -- pw: Nunchucks2024
ON CONFLICT DO NOTHING;

-- PROVIDER ORGANIZATIONS
INSERT INTO provider_organizations (id, name, currency, approved) VALUES
    (1, 'Apache Software Foundation', 'USD', true),
    (2, 'NTNU', 'NOK', true),
    (3, 'Pearson', 'USD', true),
    (4, 'Microsoft', 'NOK', true),
    (5, 'Oracle', 'USD', true),
    (6, 'Amazon', 'USD', true),
    (7, 'Adobe', 'NOK', true),
    (8, 'Apple', 'NOK', true),
    (9, 'Google', 'USD', true),
    (10, 'Handelshøyskolen BI', 'NOK', true),
    (11, 'University of Oslo', 'NOK', true),
    (12, 'University of Bergen', 'NOK', true)
    ON CONFLICT DO NOTHING;

-- CATEGORIES
INSERT INTO categories (id, name) VALUES
    (1, 'Information Technologies'),
    (2, 'Digital Marketing'),
    (3, 'Business and Entrepreneurship'),
    (4, 'Data Science and Analytics')
    ON CONFLICT DO NOTHING;

-- COURSES
INSERT INTO courses (id, title, description, level, price, is_hidden, category_id, provider_id, image_url) VALUES
    (1, 'Introduction to SQL Essentials', 'Dive into the fundamentals of database management with our beginner-level online course, "Introduction to SQL Essentials." Geared towards those new to SQL, the course provides a comprehensive foundation for understanding and utilizing SQL. You’ll learn about database design, querying, and data manipulation using practical exercises to build confidence and skill.', 'BEGINNER', 800.00, false, 1, 1, 'https://thumb.tildacdn.com/tild6238-3035-4335-a333-306335373139/-/format/webp/IMG_3349.jpg'),
    (2, 'Creating Web Application with .Net', 'Embark on your journey into web development with our beginner-level online course, "Creating Web Applications with .NET." Learn to build web applications using ASP.NET and C#, explore UI design, server-side scripting, and complete hands-on projects.', 'BEGINNER', 2999.00, false, 1, 2, 'https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/223525883/original/75355314f2634045dbac0e3ba746934e4fa701eb/do-develop-in-asp-net-mvc-asp-net-core-asp-net-web-api.jpg'),
    (3, 'Azure Cloud Fundamentals', 'Designed for beginners, "Azure Fundamentals" prepares you for the AZ-900 exam and a career in cloud computing. Learn essential Azure services, pricing, compliance, and get hands-on experience through labs and real-world scenarios.', 'BEGINNER', 200.00, false, 1, 3, 'https://digitaltransformation.org.au/sites/default/files/2022-01/azure.png'),
    (4, 'Azure Administration (Intermediate)', 'Dive deep into Azure with this intermediate-level course tailored to AZ-104 certification prep. Learn virtual networking, identity management, governance strategies, and get hands-on experience in managing and administering cloud environments.', 'INTERMEDIATE', 400.00, false, 1, 3, 'https://digitaltransformation.org.au/sites/default/files/2022-01/azure.png'),
    (5, 'AWS Cloud Practitioner', 'Discover cloud fundamentals in this entry-level course preparing you for the AWS CLF-C02 certification. Gain hands-on experience with AWS services, infrastructure, pricing models, and security.', 'BEGINNER', 100.00, false, 1, 6, 'https://blog.adobe.com/en/publish/2021/08/31/media_1649ebc3fbbce0df508081913819d491fc3f7c7a9.png?width=750&format=png&optimize=medium'),
    (6, 'Search Engine Optimization', 'Deepen your digital skills in this intermediate SEO course. Learn keyword research, technical and off-page optimization, and advanced analytics through practical, real-world applications.', 'INTERMEDIATE', 6000.00, false, 2, 7, 'https://www.rgbwebtech.com/blogs/images/uploads/what-is-seo-search-engin-optimization.png'),
    (7, 'Social Media Marketing', 'Gain hands-on expertise in this intermediate course focused on advanced social media strategy. Explore audience targeting, content creation, campaign optimization, and performance analysis.', 'INTERMEDIATE', 6000.00, false, 2, 7, 'https://hotdogmarketing.net/wp-content/uploads/2021/11/110321-HDM8'),
    (8, 'Business Strategy', 'Master strategic thinking in this expert-level course. Learn competitive analysis, innovation, and global strategy through case studies, simulations, and expert-led sessions.', 'EXPERT', 50000.00, false, 3, 10, 'https://granthigginson.com/wp-content/uploads/2017/09/business-strategy.jpg'),
    (9, 'Machine Learning Basics with Python', 'Get introduced to machine learning using Python in this beginner-friendly course. Cover supervised/unsupervised learning, ML algorithms, and build projects with no prior experience needed.', 'BEGINNER', 20000.00, false, 4, 11, 'https://miro.medium.com/v2/resize:fit:1358/1*cG6U1qstYDijh9bPL42e-Q.jpeg'),
    (10, 'Image Recognition', 'Delve into computer vision and neural networks in this intermediate course. Learn CNNs, image preprocessing, and apply your skills in projects involving image classification and recognition.', 'INTERMEDIATE', 30000.00, false, 4, 11, 'https://www.perficient.com/-/media/images/insights/hero-images/research_image-recognition-accuracy-study_hero.ashx?h=931&iar=0&w=1400&hash=E0AED7417F234D3C66F9037149BB7612'),
    (11, 'Databricks Fundamentals', 'Learn the basics of data engineering and collaborative science using Databricks. This course is ideal for beginners stepping into the world of big data and analytics.', 'BEGINNER', 20000.00, false, 4, 11, 'https://upload.wikimedia.org/wikipedia/commons/6/63/Databricks_Logo.png')
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
        JOIN pg_namespace ON pg_namespace.oid = pg_class.relnamespace
        JOIN information_schema.columns ON
        columns.column_default LIKE ('nextval(' || quote_literal(pg_class.relname) || '::regclass)%')
WHERE
    pg_class.relkind = 'S'
    LOOP
        EXECUTE seq.reset_sql;
END LOOP;
END;
$$;