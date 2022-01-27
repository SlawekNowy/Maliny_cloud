CREATE TABLE public.maliny_users (
                                     username varchar NOT NULL,
                                     "password" varchar NULL,
                                     is_password_valid bool NULL,
                                     CONSTRAINT maliny_users_pk PRIMARY KEY (username)
);


-- public.maliny_users foreign keys

ALTER TABLE public.maliny_users ADD CONSTRAINT maliny_users_fk FOREIGN KEY (username) REFERENCES public.maliny_authorities(username) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE public.maliny_authorities (
                                           authority varchar NULL,
                                           username varchar NOT NULL,
                                           CONSTRAINT maliny_authorities_pk PRIMARY KEY (username)
);