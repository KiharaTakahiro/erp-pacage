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