--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: actors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actors (
    "Actor_ID" integer NOT NULL,
    "Full_Name" character varying(80),
    "TelNumber" character varying(15),
    "Role" character varying(30),
    "Age" integer,
    "DenyScenario" boolean,
    "DenyMovieShooting" boolean,
    "Scenario_ID" integer
);


ALTER TABLE public.actors OWNER TO postgres;

--
-- Name: edited_material; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.edited_material (
    "Edited_ID" integer NOT NULL,
    "Name" character varying(50),
    "Duration" integer,
    "Status" character varying(30),
    "Raw_ID" integer
);


ALTER TABLE public.edited_material OWNER TO postgres;

--
-- Name: edited_material_Edited_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."edited_material_Edited_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."edited_material_Edited_ID_seq" OWNER TO postgres;

--
-- Name: edited_material_Edited_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."edited_material_Edited_ID_seq" OWNED BY public.edited_material."Edited_ID";


--
-- Name: persons_Actor_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."persons_Actor_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."persons_Actor_ID_seq" OWNER TO postgres;

--
-- Name: persons_Actor_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."persons_Actor_ID_seq" OWNED BY public.actors."Actor_ID";


--
-- Name: raw_material; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.raw_material (
    "Raw_ID" integer NOT NULL,
    "Name" character varying(50),
    "Duration" integer,
    "Status" character varying(30),
    "Scenario_ID" integer,
    "Task_ID" integer
);


ALTER TABLE public.raw_material OWNER TO postgres;

--
-- Name: raw_material_Raw_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."raw_material_Raw_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."raw_material_Raw_ID_seq" OWNER TO postgres;

--
-- Name: raw_material_Raw_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."raw_material_Raw_ID_seq" OWNED BY public.raw_material."Raw_ID";


--
-- Name: scenario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scenario (
    "Scenario_ID" integer NOT NULL,
    "Approval" boolean,
    "Version" integer,
    "Link" character varying(50)
);


ALTER TABLE public.scenario OWNER TO postgres;

--
-- Name: scenario_Scenario_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."scenario_Scenario_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."scenario_Scenario_ID_seq" OWNER TO postgres;

--
-- Name: scenario_Scenario_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."scenario_Scenario_ID_seq" OWNED BY public.scenario."Scenario_ID";


--
-- Name: tech_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tech_task (
    "Task_ID" integer NOT NULL,
    "TaskLink" character varying(255)
);


ALTER TABLE public.tech_task OWNER TO postgres;

--
-- Name: tech_task_Task_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."tech_task_Task_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."tech_task_Task_ID_seq" OWNER TO postgres;

--
-- Name: tech_task_Task_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."tech_task_Task_ID_seq" OWNED BY public.tech_task."Task_ID";


--
-- Name: actors Actor_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors ALTER COLUMN "Actor_ID" SET DEFAULT nextval('public."persons_Actor_ID_seq"'::regclass);


--
-- Name: edited_material Edited_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.edited_material ALTER COLUMN "Edited_ID" SET DEFAULT nextval('public."edited_material_Edited_ID_seq"'::regclass);


--
-- Name: raw_material Raw_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raw_material ALTER COLUMN "Raw_ID" SET DEFAULT nextval('public."raw_material_Raw_ID_seq"'::regclass);


--
-- Name: scenario Scenario_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scenario ALTER COLUMN "Scenario_ID" SET DEFAULT nextval('public."scenario_Scenario_ID_seq"'::regclass);


--
-- Name: tech_task Task_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tech_task ALTER COLUMN "Task_ID" SET DEFAULT nextval('public."tech_task_Task_ID_seq"'::regclass);


--
-- Data for Name: actors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actors ("Actor_ID", "Full_Name", tel_number, "Role", "Age", deny_scenario, deny_movie_shooting, "Scenario_ID") FROM stdin;
15	James D. Sanders	281-329-8042	Main role	65	f	f	3
13	Edward A. Summers	432-343-7363	Main role	22	f	f	3
12	Ashlee D. Keeling	815-207-4967	Third plane role	57	f	f	3
11	John M. Dickerson	267-491-3803	Secondary role	19	f	f	3
10	Tanya E. West	636-928-3471	Main role	34	f	f	3
9	Roland C. Johnston	410-559-5713	Main role	19	f	f	3
8	Teodoro J. Niles	440-779-1394	Third plane role	50	f	f	3
6	Andrew S. Worthington	973-469-4570	Third plane role	17	f	f	3
5	Tyrone T. Esquivel	407-603-3414	Third plane role	43	f	f	3
4	Paul M. Cole	734-788-3596	Main role	43	f	f	3
3	Gina P. Wilder	831-610-8797	Third plane role	47	f	f	3
2	Joan D. Galvan	812-433-5064	Main role	59	f	f	3
1	Rosalie R. Collins	859-301-1954	Secondary role	41	f	f	3
30	James E. Garcia	404-610-4081	Main role	43	f	f	3
29	John S. Pulliam	716-408-0738	Secondary role	43	f	f	3
27	Herbert N. Diaz	302-822-5464	Main role	33	f	f	3
26	Grace R. Fong	770-830-0435	Main role	60	f	f	3
25	Patricia S. Kelly	713-316-1702	Third plane role	24	f	f	3
24	Eileen A. McCormack	845-802-2003	Secondary role	55	f	f	3
23	Nancy C. Jones	760-819-7643	Third plane role	55	f	f	3
21	Brittany K. Heintz	808-738-1540	Secondary role	27	f	f	3
20	David D. Schmid	808-964-5027	Secondary role	40	f	f	3
19	Virginia T. Roudebush	212-743-1327	Main role	44	f	f	3
18	Leo M. Dorris	203-688-8174	Secondary role	29	f	f	3
17	Vincenzo R. Prichard	765-341-2072	Secondary role	33	f	f	3
16	Harvey B. Hillier	517-425-5646	Main role	43	f	f	3
22	Timothy T. Jones	626-840-5441	Secondary role	69	t	f	1
7	Lamar K. McKenzie	718-479-6312	Secondary role	43	t	f	2
14	Mario W. Andrews	661-852-1261	Third plane role	33	f	t	1
28	Belinda A. Martins	707-467-6781	Secondary role	42	t	t	2
\.


--
-- Data for Name: edited_material; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.edited_material ("Edited_ID", "Name", "Duration", "Status", "Raw_ID") FROM stdin;
28	Restaurant EP3	3	Montage complete	8
19	Laboratory EP2	0	Montage not started	3
24	Street EP3	0	Montage not started	1
29	Restaurant EP4	0	Montage not started	8
3	Underground	5	Improvement required	4
5	Headquarters	7	Improvement required	7
10	Headquarters EP2	2	Improvement required	7
11	Restaurant	7	Improvement required	8
14	Studio EP3	2	Improvement required	5
15	Diner EP4	1	Improvement required	6
12	Underground EP2	4	Improvement required	4
18	Underground EP4	6	Improvement required	4
16	Diner EP5	5	Improvement required	6
17	Laboratory	3	Improvement required	3
22	Underground EP6	6	Improvement required	4
20	Underground EP5	7	Improvement required	4
21	Airplane EP2	4	Improvement required	2
25	Restaurant EP2	6	Improvement required	8
30	Laboratory EP3	4	Improvement required	3
7	Airplane	6	Montage complete	2
4	Diner	6	Montage complete	6
9	Diner EP3	5	Montage complete	6
13	Underground EP3	4	Montage complete	4
2	Studio EP2	0	Montage not started	5
1	Studio EP1	0	Montage not started	5
6	Diner EP2	0	Montage not started	6
23	Street EP2	2	Montage complete	1
26	Underground EP7	4	Montage complete	4
27	Street EP4	5	Montage complete	1
8	Street	0	Montage not started	1
\.


--
-- Data for Name: raw_material; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.raw_material ("Raw_ID", "Name", "Duration", "Status", "Scenario_ID", "Task_ID") FROM stdin;
4	Underground	46	Improvement required	1	4
8	Restaurant	0	Not started	3	1
5	Studio	67	During	3	4
1	Street	52	Improvement required	2	2
6	Diner	18	Improvement required	2	2
3	Laboratory	68	During	3	1
7	Headquarters	36	Improvement required	2	3
2	Airplane	88	Filmed	3	3
\.


--
-- Data for Name: scenario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.scenario ("Scenario_ID", "Approval", "Version", "Link") FROM stdin;
1	f	1	https://inlnk.ru/firSur4
2	f	2	https://inlnk.ru/4S8Ent
3	t	3	https://inlnk.ru/jyR3sr
\.


--
-- Data for Name: tech_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tech_task ("Task_ID", task_link) FROM stdin;
1	https://inlnk.ru/map25
2	https://inlnk.ru/rcJA9
3	https://inlnk.ru/rpHS3
4	https://inlnk.ru/vr5Ie
\.


--
-- Name: edited_material_Edited_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."edited_material_Edited_ID_seq"', 1, false);


--
-- Name: persons_Actor_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."persons_Actor_ID_seq"', 1, false);


--
-- Name: raw_material_Raw_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."raw_material_Raw_ID_seq"', 1, false);


--
-- Name: scenario_Scenario_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."scenario_Scenario_ID_seq"', 1, false);


--
-- Name: tech_task_Task_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."tech_task_Task_ID_seq"', 1, false);


--
-- Name: edited_material edited_material_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.edited_material
    ADD CONSTRAINT edited_material_pk PRIMARY KEY ("Edited_ID");


--
-- Name: actors persons_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT persons_pk PRIMARY KEY ("Actor_ID");


--
-- Name: raw_material raw_material_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raw_material
    ADD CONSTRAINT raw_material_pk PRIMARY KEY ("Raw_ID");


--
-- Name: scenario scenario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scenario
    ADD CONSTRAINT scenario_pk PRIMARY KEY ("Scenario_ID");


--
-- Name: tech_task tech_task_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tech_task
    ADD CONSTRAINT tech_task_pk PRIMARY KEY ("Task_ID");


--
-- Name: edited_material_edited_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX edited_material_edited_id_uindex ON public.edited_material USING btree ("Edited_ID");


--
-- Name: persons_actor_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX persons_actor_id_uindex ON public.actors USING btree ("Actor_ID");


--
-- Name: raw_material_raw_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX raw_material_raw_id_uindex ON public.raw_material USING btree ("Raw_ID");


--
-- Name: scenario_scenario_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX scenario_scenario_id_uindex ON public.scenario USING btree ("Scenario_ID");


--
-- Name: tech_task_task_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX tech_task_task_id_uindex ON public.tech_task USING btree ("Task_ID");


--
-- Name: edited_material Raw_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.edited_material
    ADD CONSTRAINT "Raw_ID" FOREIGN KEY ("Raw_ID") REFERENCES public.raw_material("Raw_ID") ON UPDATE SET NULL;


--
-- Name: raw_material Scenario_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raw_material
    ADD CONSTRAINT "Scenario_ID" FOREIGN KEY ("Scenario_ID") REFERENCES public.scenario("Scenario_ID") ON UPDATE SET NULL;


--
-- Name: actors Scenario_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT "Scenario_ID" FOREIGN KEY ("Scenario_ID") REFERENCES public.scenario("Scenario_ID") ON UPDATE SET NULL;


--
-- Name: raw_material Task_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raw_material
    ADD CONSTRAINT "Task_ID" FOREIGN KEY ("Task_ID") REFERENCES public.tech_task("Task_ID") ON UPDATE SET NULL;


--
-- PostgreSQL database dump complete
--

