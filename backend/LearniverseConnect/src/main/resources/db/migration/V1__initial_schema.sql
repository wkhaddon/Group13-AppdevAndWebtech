-- ==============================
-- Users & Roles
-- ==============================

create table users (
    id bigserial primary key,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    password_hash varchar(255) not null,
    global_role varchar(255) not null
       check (global_role in ('USER', 'SUPPORT', 'ADMIN')) default 'USER',
    created_at timestamp not null default now(),
    updated_at timestamp
);

-- ==============================
-- Provider Organizations
-- ==============================

create table provider_organizations (
    id bigserial primary key,
    name varchar(255) not null unique,
    currency varchar(255) not null,
    approved boolean not null default false
);

-- ==============================
-- Organization Memberships
-- ==============================

create table user_organization_memberships (
    user_id bigint not null,
    organization_id bigint not null,
    org_role varchar(255) not null
       check (org_role in ('OWNER', 'MANAGER', 'INSTRUCTOR')),
    joined_at timestamp not null default now(),
    primary key (user_id, organization_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (organization_id) references provider_organizations(id) on delete cascade
);

-- ==============================
-- Organization Join Requests
-- ==============================

create table organization_join_requests (
    id bigserial primary key,
    requested_org_name varchar(255) not null,
    message text,
    status varchar(255) not null
        check (status in ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED', 'EXPIRED')) default 'PENDING',
    submitted_at timestamp not null default now(),
    requested_by bigint not null references users(id) on delete cascade
);

-- ==============================
-- Categories
-- ==============================

create table categories (
    category_id bigserial primary key,
    name varchar(255) not null unique,
    description text
);

-- ==============================
-- Courses
-- ==============================

create table courses (
     id bigserial primary key,
     title varchar(255) not null,
     description text not null,
     level varchar(255) check (level in ('BEGINNER', 'INTERMEDIATE', 'EXPERT')),
     price numeric(38,2) not null,
     is_hidden boolean not null default false,
     session_start_date date,
     session_end_date date,
     ects_credits numeric(38,2),
     hours_per_week numeric(38,2),
     related_certification varchar(255),
     category_id bigint not null references categories(category_id),
     provider_id bigint not null references provider_organizations(id)
);

-- ==============================
-- Favorites (user <-> course)
-- ==============================

create table favorites (
    favorite_id bigserial primary key,
    user_id bigint not null references users(id) on delete cascade,
    course_id bigint not null references courses(id) on delete cascade
);

-- ==============================
-- Orders & Order Items
-- ==============================

create table orders (
    order_id bigserial primary key,
    user_id bigint not null references users(id) on delete cascade,
    order_date timestamp not null default now(),
    status boolean not null, -- consider changing to enum in future
    total_price numeric(38,2)
);

create table order_courses (
    order_course_id bigserial primary key,
    order_id bigint not null references orders(order_id) on delete cascade,
    course_id bigint not null references courses(id),
    quantity integer
);
