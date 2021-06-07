-- システム TAXの値
INSERT INTO public.system(
	key, insert_date, update_date, version, value
)
VALUES(
	'tax', NOW(), NOW(), 0, 10
);