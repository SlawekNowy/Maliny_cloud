--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

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
-- Name: dostawy; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.dostawy (
    id_dostawy integer NOT NULL,
    id_klienta integer NOT NULL,
    waga numeric NOT NULL,
    id_miary integer NOT NULL,
    id_owoca integer NOT NULL,
    data date NOT NULL,
    koszt_owocow numeric
);


ALTER TABLE public.dostawy OWNER TO slawomir;

--
-- Name: dostawy_id_dostawy_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.dostawy ALTER COLUMN id_dostawy ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dostawy_id_dostawy_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: firmy; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.firmy (
    id_firmy integer NOT NULL,
    nazwa_firmy character varying(250) NOT NULL,
    adres_firmy character varying(250) NOT NULL,
    nip character(12) DEFAULT 0 NOT NULL
);


ALTER TABLE public.firmy OWNER TO slawomir;

--
-- Name: firmy_id_firmy_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.firmy ALTER COLUMN id_firmy ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.firmy_id_firmy_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: klienci; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.klienci (
    id_klienta integer NOT NULL,
    nazwisko character varying(100) NOT NULL,
    imie character varying(100) NOT NULL,
    drugie_imie character varying(100),
    pesel character(11) NOT NULL,
    nip character(12)
);


ALTER TABLE public.klienci OWNER TO slawomir;

--
-- Name: klienci_id_klienta_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.klienci ALTER COLUMN id_klienta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.klienci_id_klienta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: maliny_users; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.maliny_users (
    username character varying(1024) NOT NULL,
    password character varying(1024),
    is_password_valid boolean,
    role character varying(30)
);


ALTER TABLE public.maliny_users OWNER TO slawomir;

--
-- Name: miary; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.miary (
    id_miary integer NOT NULL,
    nazwa_miary character varying(256) NOT NULL,
    mnoznik_miary numeric NOT NULL,
    skrot_miary character varying(10) NOT NULL,
    jest_metryczna boolean NOT NULL
);


ALTER TABLE public.miary OWNER TO slawomir;

--
-- Name: miary_id_miary_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.miary ALTER COLUMN id_miary ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.miary_id_miary_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: owoce; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.owoce (
    id_owoca integer NOT NULL,
    owoc character varying(256) NOT NULL,
    cena_skupu numeric NOT NULL,
    cena_sprzedazy numeric NOT NULL,
    id_miary integer DEFAULT 1 NOT NULL,
    data_cen date
);


ALTER TABLE public.owoce OWNER TO slawomir;

--
-- Name: owoce_id_owoca_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.owoce ALTER COLUMN id_owoca ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.owoce_id_owoca_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: task_execution; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.task_execution (
    task_execution_id bigint NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    task_name character varying(100),
    exit_code integer,
    exit_message character varying(2500),
    error_message character varying(2500),
    last_updated timestamp without time zone,
    external_execution_id character varying(255),
    parent_execution_id bigint
);


ALTER TABLE public.task_execution OWNER TO slawomir;

--
-- Name: task_execution_params; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.task_execution_params (
    task_execution_id bigint NOT NULL,
    task_param character varying(2500)
);


ALTER TABLE public.task_execution_params OWNER TO slawomir;

--
-- Name: task_lock; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.task_lock (
    lock_key character(36) NOT NULL,
    region character varying(100) NOT NULL,
    client_id character(36),
    created_date timestamp without time zone NOT NULL
);


ALTER TABLE public.task_lock OWNER TO slawomir;

--
-- Name: task_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

CREATE SEQUENCE public.task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_seq OWNER TO slawomir;

--
-- Name: task_task_batch; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.task_task_batch (
    task_execution_id bigint NOT NULL,
    job_execution_id bigint NOT NULL
);


ALTER TABLE public.task_task_batch OWNER TO slawomir;

--
-- Name: view_deliveries; Type: VIEW; Schema: public; Owner: slawomir
--

CREATE VIEW public.view_deliveries AS
 SELECT d.id_dostawy,
    concat(k.imie, ' ', k.drugie_imie,
        CASE
            WHEN (k.drugie_imie IS NULL) THEN ''::text
            ELSE ' '::text
        END, k.nazwisko) AS imiona_i_nazwisko,
    o.owoc,
    d.koszt_owocow,
    d.waga,
    m.skrot_miary,
    d.id_klienta
   FROM public.dostawy d,
    public.owoce o,
    public.miary m,
    public.klienci k
  WHERE ((d.id_klienta = k.id_klienta) AND (d.id_miary = m.id_miary) AND (d.id_owoca = o.id_owoca));


ALTER TABLE public.view_deliveries OWNER TO slawomir;

--
-- Name: view_fruits; Type: VIEW; Schema: public; Owner: slawomir
--

CREATE VIEW public.view_fruits AS
 SELECT o.id_owoca,
    o.owoc,
    m.skrot_miary,
    o.cena_skupu,
    o.cena_sprzedazy,
    o.data_cen
   FROM public.owoce o,
    public.miary m
  WHERE (o.id_miary = m.id_miary);


ALTER TABLE public.view_fruits OWNER TO slawomir;

--
-- Name: zamowienia; Type: TABLE; Schema: public; Owner: slawomir
--

CREATE TABLE public.zamowienia (
    id_zamowienia integer NOT NULL,
    id_zamawiajacego integer,
    koszt_zamowienia numeric NOT NULL,
    waga_zamowienia numeric NOT NULL,
    id_miary integer,
    id_owoca integer,
    data_zamowienia date
);


ALTER TABLE public.zamowienia OWNER TO slawomir;

--
-- Name: view_orders; Type: VIEW; Schema: public; Owner: slawomir
--

CREATE VIEW public.view_orders AS
 SELECT z.id_zamowienia,
    f.nazwa_firmy,
    z.koszt_zamowienia,
    z.waga_zamowienia,
    m.skrot_miary,
    o.owoc
   FROM public.zamowienia z,
    public.firmy f,
    public.miary m,
    public.owoce o
  WHERE ((z.id_zamawiajacego = f.id_firmy) AND (z.id_miary = m.id_miary) AND (z.id_owoca = o.id_owoca));


ALTER TABLE public.view_orders OWNER TO slawomir;

--
-- Name: zamowienia_id_zamowienia_seq; Type: SEQUENCE; Schema: public; Owner: slawomir
--

ALTER TABLE public.zamowienia ALTER COLUMN id_zamowienia ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.zamowienia_id_zamowienia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: dostawy; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.dostawy (id_dostawy, id_klienta, waga, id_miary, id_owoca, data, koszt_owocow) FROM stdin;
1	1	1.5	3	1	2021-08-15	7500
\.


--
-- Data for Name: firmy; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.firmy (id_firmy, nazwa_firmy, adres_firmy, nip) FROM stdin;
1	Fruitex s.c.	Ożarów 15	123456789012
2	Owoce i My sp. z o.o.	Kluczkowice 64	234234234234
\.


--
-- Data for Name: klienci; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.klienci (id_klienta, nazwisko, imie, drugie_imie, pesel, nip) FROM stdin;
1	Kowalski	Jan	\N	97123109871	\N
2	Nowak	Anna	\N	84082388921	\N
\.


--
-- Data for Name: maliny_users; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.maliny_users (username, password, is_password_valid, role) FROM stdin;
admin	{bcrypt}$2a$10$58JNm/LZF.BvnaBWF2.TzejyTSQaAohJbpNCU5A5ELxcDSUz2Zlce	t	ADMIN
\.


--
-- Data for Name: miary; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.miary (id_miary, nazwa_miary, mnoznik_miary, skrot_miary, jest_metryczna) FROM stdin;
1	Gram	1	g	t
2	Kilogram	1000	kg	t
3	Tona	1000000	t	t
4	Miligram	0.001	mg	t
5	Uncja	28.3495231	oz	f
6	Funt	453.59237	lbs	f
7	Tona Amerykańska	907184.74	tn	f
8	Tona Angielska	1016046.91	LT	f
\.


--
-- Data for Name: owoce; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.owoce (id_owoca, owoc, cena_skupu, cena_sprzedazy, id_miary, data_cen) FROM stdin;
1	Malina Pulpa	5.00	5.50	4	2021-07-15
2	Malina 1	6	6.5	4	2021-07-15
\.


--
-- Data for Name: task_execution; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.task_execution (task_execution_id, start_time, end_time, task_name, exit_code, exit_message, error_message, last_updated, external_execution_id, parent_execution_id) FROM stdin;
\.


--
-- Data for Name: task_execution_params; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.task_execution_params (task_execution_id, task_param) FROM stdin;
\.


--
-- Data for Name: task_lock; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.task_lock (lock_key, region, client_id, created_date) FROM stdin;
\.


--
-- Data for Name: task_task_batch; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.task_task_batch (task_execution_id, job_execution_id) FROM stdin;
\.


--
-- Data for Name: zamowienia; Type: TABLE DATA; Schema: public; Owner: slawomir
--

COPY public.zamowienia (id_zamowienia, id_zamawiajacego, koszt_zamowienia, waga_zamowienia, id_miary, id_owoca, data_zamowienia) FROM stdin;
2	1	12000	1.5	3	1	2021-08-15
\.


--
-- Name: dostawy_id_dostawy_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.dostawy_id_dostawy_seq', 1, true);


--
-- Name: firmy_id_firmy_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.firmy_id_firmy_seq', 2, true);


--
-- Name: klienci_id_klienta_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.klienci_id_klienta_seq', 2, true);


--
-- Name: miary_id_miary_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.miary_id_miary_seq', 9, true);


--
-- Name: owoce_id_owoca_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.owoce_id_owoca_seq', 2, true);


--
-- Name: task_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.task_seq', 1, false);


--
-- Name: zamowienia_id_zamowienia_seq; Type: SEQUENCE SET; Schema: public; Owner: slawomir
--

SELECT pg_catalog.setval('public.zamowienia_id_zamowienia_seq', 2, true);


--
-- Name: dostawy dostawy_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.dostawy
    ADD CONSTRAINT dostawy_pk PRIMARY KEY (id_dostawy);


--
-- Name: firmy firmy_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.firmy
    ADD CONSTRAINT firmy_pk PRIMARY KEY (id_firmy);


--
-- Name: klienci klienci_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.klienci
    ADD CONSTRAINT klienci_pk PRIMARY KEY (id_klienta);


--
-- Name: task_lock lock_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.task_lock
    ADD CONSTRAINT lock_pk PRIMARY KEY (lock_key, region);


--
-- Name: maliny_users maliny_users_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.maliny_users
    ADD CONSTRAINT maliny_users_pk PRIMARY KEY (username);


--
-- Name: miary miary_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.miary
    ADD CONSTRAINT miary_pk PRIMARY KEY (id_miary);


--
-- Name: owoce owoce_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.owoce
    ADD CONSTRAINT owoce_pk PRIMARY KEY (id_owoca);


--
-- Name: task_execution task_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.task_execution
    ADD CONSTRAINT task_execution_pkey PRIMARY KEY (task_execution_id);


--
-- Name: zamowienia zamowienia_pk; Type: CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.zamowienia
    ADD CONSTRAINT zamowienia_pk PRIMARY KEY (id_zamowienia);


--
-- Name: miary_id_miary_idx; Type: INDEX; Schema: public; Owner: slawomir
--

CREATE INDEX miary_id_miary_idx ON public.miary USING btree (id_miary);


--
-- Name: owoce_id_owoca_idx; Type: INDEX; Schema: public; Owner: slawomir
--

CREATE INDEX owoce_id_owoca_idx ON public.owoce USING btree (id_owoca);


--
-- Name: dostawy dostawy_fk; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.dostawy
    ADD CONSTRAINT dostawy_fk FOREIGN KEY (id_klienta) REFERENCES public.klienci(id_klienta);


--
-- Name: dostawy dostawy_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.dostawy
    ADD CONSTRAINT dostawy_fk_1 FOREIGN KEY (id_miary) REFERENCES public.miary(id_miary);


--
-- Name: dostawy dostawy_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.dostawy
    ADD CONSTRAINT dostawy_fk_2 FOREIGN KEY (id_owoca) REFERENCES public.owoce(id_owoca);


--
-- Name: owoce owoce_fk; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.owoce
    ADD CONSTRAINT owoce_fk FOREIGN KEY (id_miary) REFERENCES public.miary(id_miary);


--
-- Name: task_task_batch task_exec_batch_fk; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.task_task_batch
    ADD CONSTRAINT task_exec_batch_fk FOREIGN KEY (task_execution_id) REFERENCES public.task_execution(task_execution_id);


--
-- Name: task_execution_params task_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.task_execution_params
    ADD CONSTRAINT task_exec_params_fk FOREIGN KEY (task_execution_id) REFERENCES public.task_execution(task_execution_id);


--
-- Name: zamowienia zamowienia_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.zamowienia
    ADD CONSTRAINT zamowienia_fk_1 FOREIGN KEY (id_miary) REFERENCES public.miary(id_miary);


--
-- Name: zamowienia zamowienia_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.zamowienia
    ADD CONSTRAINT zamowienia_fk_2 FOREIGN KEY (id_zamawiajacego) REFERENCES public.firmy(id_firmy);


--
-- Name: zamowienia zamowienia_fk_3; Type: FK CONSTRAINT; Schema: public; Owner: slawomir
--

ALTER TABLE ONLY public.zamowienia
    ADD CONSTRAINT zamowienia_fk_3 FOREIGN KEY (id_owoca) REFERENCES public.owoce(id_owoca);


--
-- PostgreSQL database dump complete
--

