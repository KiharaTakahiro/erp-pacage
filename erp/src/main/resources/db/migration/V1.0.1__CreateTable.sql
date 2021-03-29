-- 採番テーブル
-- DROP TABLE public.sequence;
CREATE TABLE public.sequence
(
    seq_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    seq_value bigint  NOT NULL,
    CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
);