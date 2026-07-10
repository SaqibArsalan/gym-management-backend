--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2 (Debian 17.2-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: activity_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activity_logs (
                                      id character varying(255) NOT NULL,
                                      description text NOT NULL,
                                      log_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                      created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.activity_logs OWNER TO postgres;

--
-- Name: attendance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attendance (
                                   id character varying(255) NOT NULL,
                                   session_id character varying(255) NOT NULL,
                                   member_id character varying(255) NOT NULL,
                                   status character varying(50),
                                   check_in_time timestamp without time zone NOT NULL,
                                   check_out_time timestamp without time zone,
                                   created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT attendance_status_check CHECK (((status)::text = ANY (ARRAY[('Present'::character varying)::text, ('Absent'::character varying)::text, ('Late'::character varying)::text])))
);


ALTER TABLE public.attendance OWNER TO postgres;

--
-- Name: class_bookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.class_bookings (
                                       id character varying(255) NOT NULL,
                                       member_id character varying(255) NOT NULL,
                                       class_id character varying(255) NOT NULL,
                                       booking_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                       created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                       updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.class_bookings OWNER TO postgres;

--
-- Name: classes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classes (
                                id character varying(255) NOT NULL,
                                name character varying(100) NOT NULL,
                                description text,
                                trainer_id character varying(255) NOT NULL,
                                start_time timestamp without time zone NOT NULL,
                                end_time timestamp without time zone NOT NULL,
                                capacity integer NOT NULL,
                                created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.classes OWNER TO postgres;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
                                          id character varying(255) NOT NULL,
                                          author character varying(255) NOT NULL,
                                          filename character varying(255) NOT NULL,
                                          dateexecuted timestamp without time zone NOT NULL,
                                          orderexecuted integer NOT NULL,
                                          exectype character varying(10) NOT NULL,
                                          md5sum character varying(35),
                                          description character varying(255),
                                          comments character varying(255),
                                          tag character varying(255),
                                          liquibase character varying(20),
                                          contexts character varying(255),
                                          labels character varying(255),
                                          deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
                                              id integer NOT NULL,
                                              locked boolean NOT NULL,
                                              lockgranted timestamp without time zone,
                                              lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: enrollment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enrollment (
                                   id character varying(255) NOT NULL,
                                   session_id character varying(255) NOT NULL,
                                   membership_id character varying(255) NOT NULL,
                                   user_id character varying(255) NOT NULL,
                                   member_name character varying(255) NOT NULL,
                                   session_date date NOT NULL,
                                   class_name character varying(255) NOT NULL,
                                   status character varying(50) DEFAULT 'enrolled'::character varying NOT NULL,
                                   attendance_status character varying(50) DEFAULT 'pending'::character varying,
                                   enrolled_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.enrollment OWNER TO postgres;

--
-- Name: equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment (
                                  id character varying(255) NOT NULL,
                                  name character varying(100) NOT NULL,
                                  status character varying(50) DEFAULT 'Available'::character varying,
                                  last_maintenance_date timestamp without time zone,
                                  created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                  updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.equipment OWNER TO postgres;

--
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
                                 id character varying(255) NOT NULL,
                                 user_id character varying(255) NOT NULL,
                                 feedback_text text NOT NULL,
                                 submitted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                 created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                 updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- Name: gym_attendance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gym_attendance (
                                       id character varying(255) NOT NULL,
                                       user_id character varying(255) NOT NULL,
                                       check_in_time timestamp without time zone NOT NULL,
                                       check_out_time timestamp without time zone,
                                       marked_by character varying(255),
                                       attendee_type character varying(50) NOT NULL,
                                       notes text,
                                       created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                       updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.gym_attendance OWNER TO postgres;

--
-- Name: leads; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.leads (
                              id character varying(255) NOT NULL,
                              name character varying(100) NOT NULL,
                              email character varying(100),
                              phone_number character varying(15),
                              inquiry_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              status character varying(50) DEFAULT 'New'::character varying,
                              created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.leads OWNER TO postgres;

--
-- Name: member_workout_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member_workout_plans (
                                             id character varying(255) NOT NULL,
                                             member_id character varying(255) NOT NULL,
                                             workout_plan_id character varying(255) NOT NULL,
                                             assigned_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                             created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                             updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.member_workout_plans OWNER TO postgres;

--
-- Name: membership_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membership_plans (
                                         id character varying(255) NOT NULL,
                                         name character varying(50) NOT NULL,
                                         duration_in_months integer NOT NULL,
                                         price numeric(10,2) NOT NULL,
                                         description text,
                                         created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                         updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.membership_plans OWNER TO postgres;

--
-- Name: membership_subscriptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membership_subscriptions (
                                                 id character varying(255) NOT NULL,
                                                 user_id character varying(255) NOT NULL,
                                                 membership_plan_id character varying(255) NOT NULL,
                                                 join_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                                 expiry_date timestamp without time zone,
                                                 status character varying(50) DEFAULT 'ACTIVE'::character varying,
                                                 created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                                 updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                                 name character varying(255)
);


ALTER TABLE public.membership_subscriptions OWNER TO postgres;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
                                      id character varying(255) NOT NULL,
                                      user_id character varying(255),
                                      message text NOT NULL,
                                      sent_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                      created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
                                 id character varying(255) NOT NULL,
                                 member_id character varying(255) NOT NULL,
                                 amount numeric(10,2) NOT NULL,
                                 payment_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                 payment_method character varying(50) NOT NULL,
                                 created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                 updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
                             id character varying(255) NOT NULL,
                             name character varying(255) NOT NULL,
                             description text DEFAULT ''::text,
                             status character varying(50) DEFAULT ''::character varying,
                             scopes jsonb DEFAULT '[]'::jsonb,
                             created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                             updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: sessions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sessions (
                                 id character varying(255) NOT NULL,
                                 class_id character varying(255) NOT NULL,
                                 session_date timestamp without time zone NOT NULL,
                                 trainer_id character varying(255) NOT NULL,
                                 capacity integer NOT NULL,
                                 created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                 updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.sessions OWNER TO postgres;

--
-- Name: staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff (
                              id character varying(255) NOT NULL,
                              user_id character varying(255) NOT NULL,
                              salary numeric(10,2) NOT NULL,
                              hire_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              name character varying(255) NOT NULL
);


ALTER TABLE public.staff OWNER TO postgres;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
                                   id character varying(255) NOT NULL,
                                   user_id character varying(255) NOT NULL,
                                   role_id character varying(255) NOT NULL,
                                   created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              id character varying(255) NOT NULL,
                              email character varying(100) NOT NULL,
                              password character varying(255) NOT NULL,
                              first_name character varying(50) NOT NULL,
                              last_name character varying(50) NOT NULL,
                              phone_number character varying(15),
                              date_of_birth date,
                              account_status character varying(50) NOT NULL,
                              created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                              updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: workout_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workout_plans (
                                      id character varying(255) NOT NULL,
                                      name character varying(100) NOT NULL,
                                      description text,
                                      created_by character varying(255) NOT NULL,
                                      created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.workout_plans OWNER TO postgres;

--
-- Data for Name: activity_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.activity_logs (id, description, log_date, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: attendance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attendance (id, session_id, member_id, status, check_in_time, check_out_time, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: class_bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.class_bookings (id, member_id, class_id, booking_date, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: classes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classes (id, name, description, trainer_id, start_time, end_time, capacity, created_at, updated_at) FROM stdin;
vSMYwQHcCxuK5YeIGO7C8	Class Example	Class Description	vSMYwQHcCxuK5YeIGO7C7	2025-02-02 00:00:00	2025-02-02 10:00:00	10	2025-04-10 15:33:42.159827	2025-04-10 15:33:42.159827
69rjjUPATPbB0InDy1g1JP	YOGA	YOGA CLASS	vSMYwQHcCxuK5YeIGO7C7	2025-02-02 12:00:00	2025-02-02 14:00:00	15	2025-04-12 01:03:36.152234	2025-04-12 01:03:36.152234
4QcaphHVqPEscJYiLrL95R	HIT Workout	HIT workout description	6wYNCgwNyACwBSi0tjcrml	2025-04-12 12:00:00	2025-04-12 13:00:00	5	2025-04-12 02:14:05.87937	2025-04-12 02:14:05.87937
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
001-create-role	admin	db/changelog/changes/001-create-role.sql	2026-04-16 22:38:53.171956	1	EXECUTED	9:d55f1b6d26a3e79cef3a365cc23d0d22	sql		\N	4.29.2	\N	\N	6361133125
002-create-users	admin	db/changelog/changes/002-create-users.sql	2026-04-16 22:38:53.199435	2	EXECUTED	9:6400a988f1322d5efff9fc5134ceb8fa	sql		\N	4.29.2	\N	\N	6361133125
003-create-user-roles	admin	db/changelog/changes/003-create-user-roles.sql	2026-04-16 22:38:53.213368	3	EXECUTED	9:a3da1a1bddf59eb06768f2497e1bf4db	sql		\N	4.29.2	\N	\N	6361133125
004-create-membership-plans	admin	db/changelog/changes/004-create-membership-plans.sql	2026-04-16 22:38:53.225211	4	EXECUTED	9:a048b81f0c40ca0a068f60eab2319497	sql		\N	4.29.2	\N	\N	6361133125
005-create-membership-subscriptions	admin	db/changelog/changes/005-create-membership-subscriptions.sql	2026-04-16 22:38:53.236006	5	EXECUTED	9:260f514079ec86453645bbbe032889c9	sql		\N	4.29.2	\N	\N	6361133125
006-create-staff	admin	db/changelog/changes/006-create-staff.sql	2026-04-16 22:38:53.247569	6	EXECUTED	9:9a724a2d74b405c1f8540906620dd994	sql		\N	4.29.2	\N	\N	6361133125
007-create-classes	admin	db/changelog/changes/007-create-classes.sql	2026-04-16 22:38:53.25753	7	EXECUTED	9:3d249ce07859e7f5f0f04a0bf3473be8	sql		\N	4.29.2	\N	\N	6361133125
008-create-sessions	admin	db/changelog/changes/008-create-sessions.sql	2026-04-16 22:38:53.267224	8	EXECUTED	9:dadeba636caa82c2ea54f746476a1feb	sql		\N	4.29.2	\N	\N	6361133125
009-create-attendance	admin	db/changelog/changes/009-create-attendance.sql	2026-04-16 22:38:53.283363	9	EXECUTED	9:e05dbbe2a1bba55c46ce3eeec7195817	sql		\N	4.29.2	\N	\N	6361133125
010-create-enrollment	admin	db/changelog/changes/010-create-enrollment.sql	2026-04-16 22:38:53.295011	10	EXECUTED	9:f3bfa801fb4889b7a9b17853492d689f	sql		\N	4.29.2	\N	\N	6361133125
011-create-gym-attendance	saqib	db/changelog/changes/011-create-gym-attendance.sql	2026-04-18 03:01:36.642611	11	EXECUTED	9:717f7ff5a03249d5803c8549153630bd	sql		\N	4.29.2	\N	\N	6463296560
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.enrollment (id, session_id, membership_id, user_id, member_name, session_date, class_name, status, attendance_status, enrolled_at, created_at, updated_at) FROM stdin;
64v7U3lkemV4d62N7IeUkI	vSMYwQHcCxuK5YeIGO7C7	3gshT9d04WAqJQbZ3HF2vC	58BopDdnhaWJk2JTJtbxMu	Test	2025-04-01	Class Example	ENROLLED	PENDING	2026-04-10 23:40:09.120097	2026-04-10 23:40:09.128267	2026-04-10 23:40:09.128267
\.


--
-- Data for Name: equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipment (id, name, status, last_maintenance_date, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feedback (id, user_id, feedback_text, submitted_at, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: gym_attendance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gym_attendance (id, user_id, check_in_time, check_out_time, marked_by, attendee_type, notes, created_at, updated_at) FROM stdin;
1CIKAGJLtGBNgYb4URySOT	58BopDdnhaWJk2JTJtbxMu	2026-06-06 01:20:00.469342	\N	0	MEMBER	test	2026-06-06 01:20:00.49145	2026-06-06 01:20:00.49145
6v7j947VYVNaUxhjBXcWqB	17WNxhpbgptSd6YKWHXtdS	2026-06-13 23:34:35.358188	2026-06-13 01:44:45.029054	0	MEMBER	TEST	2026-06-12 23:34:35.363344	2026-06-13 01:44:45.044654
6GIaAQbZDZsILINV8TBSlr	17WNxhpbgptSd6YKWHXtdS	2026-06-15 23:49:46.109419	2026-06-15 23:49:55.343684	0	MEMBER	test	2026-06-15 23:49:46.126334	2026-06-15 23:49:55.344232
3GjA26ZLV0WmWrrQaTXtK4	17WNxhpbgptSd6YKWHXtdS	2026-06-15 23:50:28.851213	\N	0	MEMBER	test	2026-06-15 23:50:28.857865	2026-06-15 23:50:28.857865
2XVSRPtHonLBnXJzwUdyGe	17WNxhpbgptSd6YKWHXtdS	2026-06-16 23:29:57.860436	2026-06-16 23:30:29.975206	0	MEMBER	test	2026-06-16 23:29:57.871729	2026-06-16 23:30:29.97576
2JxUSWnFafIhczXBZeWStK	58BopDdnhaWJk2JTJtbxMu	2026-06-16 23:30:53.621566	\N	0	MEMBER	test	2026-06-16 23:30:53.62424	2026-06-16 23:30:53.62424
bg8ZerygaCjzOAEUPQ4ns	58BopDdnhaWJk2JTJtbxMu	2026-07-03 19:11:54.876877	2026-07-03 22:59:27.892362	0	MEMBER		2026-07-03 19:11:54.890475	2026-07-03 22:59:27.917359
\.


--
-- Data for Name: leads; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.leads (id, name, email, phone_number, inquiry_date, status, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: member_workout_plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member_workout_plans (id, member_id, workout_plan_id, assigned_at, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: membership_plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membership_plans (id, name, duration_in_months, price, description, created_at, updated_at) FROM stdin;
496lUTDGnqADDQEqadaVMB	Monthly Plan	1	300.00	Access to gym cardio equipment	2025-03-23 23:15:16.262744	2025-03-23 23:15:16.262744
2a2RuSYkMXDu3LwOKuB4FK	Weekly Plan	3	300.00	Access to gym cardio equipment & Strength	2025-03-25 00:00:50.401723	2025-03-25 00:00:50.401723
\.


--
-- Data for Name: membership_subscriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membership_subscriptions (id, user_id, membership_plan_id, join_date, expiry_date, status, created_at, updated_at, name) FROM stdin;
3VcBvzULS9xLShiO7wNHo7	58BopDdnhaWJk2JTJtbxMu	496lUTDGnqADDQEqadaVMB	2025-01-01 00:00:00	2025-02-01 00:00:00	ACTIVE	2025-03-23 23:15:54.920496	2025-03-23 23:15:54.920496	Saqib
4xOutO0LfERf6pJIYVrbkp	1e2K8EEIY5AciZFBMgc43k	2a2RuSYkMXDu3LwOKuB4FK	2025-02-25 00:00:00	2025-04-25 00:00:00	ACTIVE	2025-04-04 01:28:35.865149	2025-04-04 01:28:35.865149	Saqib Arsalan
3gshT9d04WAqJQbZ3HF2vC	58BopDdnhaWJk2JTJtbxMu	2a2RuSYkMXDu3LwOKuB4FK	2026-04-10 00:00:00	2026-05-10 00:00:00	ACTIVE	2026-04-10 13:10:50.086449	2026-04-10 13:10:50.086449	Test
2X30mj3y8BBnw20pgXP2EX	ROAQc667MAe8LSoTyw2bJ	2a2RuSYkMXDu3LwOKuB4FK	2026-04-10 00:00:00	2026-05-10 00:00:00	ACTIVE	2026-04-10 13:14:20.317497	2026-04-10 13:14:20.317497	Test
7XetV6raQIZQA1C08w5FdD	58BopDdnhaWJk2JTJtbxMu	2a2RuSYkMXDu3LwOKuB4FK	2025-01-01 00:00:00	2027-04-01 00:00:00	ACTIVE	2025-03-25 00:02:54.019773	2025-03-25 00:02:54.019773	Saqib Arsalan
2ZM2igipRwAyUpCDN77y6A	17WNxhpbgptSd6YKWHXtdS	2a2RuSYkMXDu3LwOKuB4FK	2026-06-12 00:00:00	2026-07-12 00:00:00	ACTIVE	2026-06-12 23:34:08.232203	2026-06-12 23:34:08.232203	JOHN DOE
\.


--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notifications (id, user_id, message, sent_at, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payments (id, member_id, amount, payment_date, payment_method, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name, description, status, scopes, created_at, updated_at) FROM stdin;
4FkxqzWpRi3I33E5wQbIP2	Member3	Member of gym	ACTIVE	["user.all.role.edit"]	2025-03-23 23:08:46.388178	2025-03-23 23:08:46.388178
\.


--
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sessions (id, class_id, session_date, trainer_id, capacity, created_at, updated_at) FROM stdin;
vSMYwQHcCxuK5YeIGO7C7	vSMYwQHcCxuK5YeIGO7C8	2025-04-01 18:02:23.87631	vSMYwQHcCxuK5YeIGO7C7	100	2025-04-18 20:05:27.20508	2025-04-18 20:05:27.20508
3A90bcvGqtF6Wy39uD9eK9	69rjjUPATPbB0InDy1g1JP	2025-04-22 12:00:00	vSMYwQHcCxuK5YeIGO7C7	100	2025-04-22 01:49:03.771003	2025-04-22 01:49:03.771003
\.


--
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staff (id, user_id, salary, hire_date, created_at, updated_at, name) FROM stdin;
vSMYwQHcCxuK5YeIGO7C7	58BopDdnhaWJk2JTJtbxMu	50000.00	2025-02-02 00:00:00	2025-04-01 18:02:23.87631	2025-04-01 18:02:23.87631	Saqib Arsalan Ijaz
6wYNCgwNyACwBSi0tjcrml	1e2K8EEIY5AciZFBMgc43k	20000.00	2025-03-25 00:00:00	2025-03-26 00:56:19.444383	2026-06-16 01:34:41.439724	John Doe
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (id, user_id, role_id, created_at, updated_at) FROM stdin;
1znatyVun7yhnpk1v4c4kO	58BopDdnhaWJk2JTJtbxMu	4FkxqzWpRi3I33E5wQbIP2	2025-03-23 23:09:27.391965	2025-03-23 23:09:27.391965
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, first_name, last_name, phone_number, date_of_birth, account_status, created_at, updated_at) FROM stdin;
58BopDdnhaWJk2JTJtbxMu	saqibarsalan1998@gmail.com	$2a$10$m0CsKUrnpVm1ifcZ2t6nX.TvmlhrePcwcYtzXBLVkAhuzGUGjrypO	Saqib	user	03362504535	1970-01-01	active	2025-03-23 23:08:16.954231	2025-03-23 23:08:16.954231
1e2K8EEIY5AciZFBMgc43k	saqibarsalan1991@gmail.com	$2a$10$dgglAW7WWBj0Ljo6JlL/jeY7OjsuERU.CU8wwxmoq21Evze/j8wCe	Saqib Arsalan	Doe	03362504530	1970-01-01	active	2025-03-26 00:55:57.779047	2025-03-26 00:55:57.779047
17WNxhpbgptSd6YKWHXtdS	john123@gmail.com	$2a$10$wOyU2071FdGFz6r3MQhLBeIA7.MmKF/HWVepuWcvaoXxXw0K3yl6.	JOHN	DOE	03312055431	1970-01-01	ACTIVE	2025-04-06 02:50:47.497398	2025-04-06 02:50:47.497398
ROAQc667MAe8LSoTyw2bJ	test@gmail.com	$2a$10$1VK.RGZtDc/JMuxViIiyR.IBWtqOEl4yrqNCZNQ2zJ.R1VohVCEJm	Test	Doe	11111111111	1970-01-01	ACTIVE	2026-04-10 13:13:09.553403	2026-04-10 13:13:09.553403
\.


--
-- Data for Name: workout_plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workout_plans (id, name, description, created_by, created_at, updated_at) FROM stdin;
\.


--
-- Name: activity_logs activity_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_logs
    ADD CONSTRAINT activity_logs_pkey PRIMARY KEY (id);


--
-- Name: attendance attendance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_pkey PRIMARY KEY (id);


--
-- Name: class_bookings class_bookings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.class_bookings
    ADD CONSTRAINT class_bookings_pkey PRIMARY KEY (id);


--
-- Name: classes classes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (id);


--
-- Name: equipment equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (id);


--
-- Name: feedback feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);


--
-- Name: gym_attendance gym_attendance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gym_attendance
    ADD CONSTRAINT gym_attendance_pkey PRIMARY KEY (id);


--
-- Name: leads leads_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_pkey PRIMARY KEY (id);


--
-- Name: member_workout_plans member_workout_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_workout_plans
    ADD CONSTRAINT member_workout_plans_pkey PRIMARY KEY (id);


--
-- Name: membership_plans membership_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership_plans
    ADD CONSTRAINT membership_plans_pkey PRIMARY KEY (id);


--
-- Name: membership_subscriptions membership_subscriptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership_subscriptions
    ADD CONSTRAINT membership_subscriptions_pkey PRIMARY KEY (id);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


--
-- Name: role role_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE (name);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- Name: staff staff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_pkey PRIMARY KEY (id);


--
-- Name: staff staff_user_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_user_id_key UNIQUE (user_id);


--
-- Name: enrollment uq_enrollment_session_membership; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT uq_enrollment_session_membership UNIQUE (session_id, membership_id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: workout_plans workout_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workout_plans
    ADD CONSTRAINT workout_plans_pkey PRIMARY KEY (id);


--
-- Name: attendance attendance_member_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_member_id_fkey FOREIGN KEY (member_id) REFERENCES public.membership_subscriptions(id);


--
-- Name: attendance attendance_session_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- Name: class_bookings class_bookings_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.class_bookings
    ADD CONSTRAINT class_bookings_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.classes(id);


--
-- Name: class_bookings class_bookings_member_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.class_bookings
    ADD CONSTRAINT class_bookings_member_id_fkey FOREIGN KEY (member_id) REFERENCES public.membership_subscriptions(id);


--
-- Name: classes classes_trainer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_trainer_id_fkey FOREIGN KEY (trainer_id) REFERENCES public.staff(id);


--
-- Name: feedback feedback_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: enrollment fk_enrollment_session; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT fk_enrollment_session FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- Name: gym_attendance fk_gym_attendance_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gym_attendance
    ADD CONSTRAINT fk_gym_attendance_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: member_workout_plans member_workout_plans_member_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_workout_plans
    ADD CONSTRAINT member_workout_plans_member_id_fkey FOREIGN KEY (member_id) REFERENCES public.membership_subscriptions(id);


--
-- Name: member_workout_plans member_workout_plans_workout_plan_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_workout_plans
    ADD CONSTRAINT member_workout_plans_workout_plan_id_fkey FOREIGN KEY (workout_plan_id) REFERENCES public.workout_plans(id);


--
-- Name: membership_subscriptions membership_subscriptions_membership_plan_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership_subscriptions
    ADD CONSTRAINT membership_subscriptions_membership_plan_id_fkey FOREIGN KEY (membership_plan_id) REFERENCES public.membership_plans(id);


--
-- Name: membership_subscriptions membership_subscriptions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership_subscriptions
    ADD CONSTRAINT membership_subscriptions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: notifications notifications_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: payments payments_member_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_member_id_fkey FOREIGN KEY (member_id) REFERENCES public.membership_subscriptions(id);


--
-- Name: sessions sessions_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.classes(id);


--
-- Name: sessions sessions_trainer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_trainer_id_fkey FOREIGN KEY (trainer_id) REFERENCES public.staff(id);


--
-- Name: staff staff_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: workout_plans workout_plans_created_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workout_plans
    ADD CONSTRAINT workout_plans_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.staff(id);


--
-- PostgreSQL database dump complete
--

