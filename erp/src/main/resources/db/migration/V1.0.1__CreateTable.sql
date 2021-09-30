-- 採番テーブル
-- DROP TABLE public.sequence;
CREATE TABLE public.sequence
(
    seq_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    seq_value bigint  NOT NULL,
    CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
);

-- システムテーブル
-- DROP TABLE public.sequence;
CREATE TABLE public.system
(
    key character varying(64) COLLATE pg_catalog."default" NOT NULL,
    insert_date timestamp default NOW() NOT NULL,
    update_date timestamp default NOW() NOT NULL,
    version character varying(10) NOT NULL,
    value character varying(100) NOT NULL 
    
);

-- クライアントテーブル
-- DROP TABLE public.clients;
create table public.clients (
  clients_seq bigint not null
  , insert_date timestamp(6) without time zone
  , update_date timestamp(6) without time zone
  , version bigint
  , name character varying(255) not null
  , primary key (clients_seq)
);

-- 会社テーブル
-- DROP TABLE public.company;
create table public.company (
  company_seq bigint not null
  , insert_date timestamp(6) without time zone
  , update_date timestamp(6) without time zone
  , version bigint
  , address character varying(255) not null
  , name character varying(255) not null
  , phone character varying(255) not null
  , zip character varying(255) not null
  , primary key (company_seq)
);