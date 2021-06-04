-- 採番テーブル
-- DROP TABLE public.sequence;
INSERT INTO public.system(
	key, insert_date, update_date, version, value
)
VALUES(
	'tax', NOW(), NOW(), 0, "10"
);