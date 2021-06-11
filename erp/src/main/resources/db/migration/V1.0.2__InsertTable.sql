-- システム TAXの値
INSERT INTO public.system(
	key, insert_date, update_date, version, value
)
VALUES(
	'NO_TAX', NOW(), NOW(), 0, 0
);

INSERT INTO public.system(
	key, insert_date, update_date, version, value
)
VALUES(
	'REDUCED_RATE_TAX', NOW(), NOW(), 0, 8
);
INSERT INTO public.system(
	key, insert_date, update_date, version, value
)
VALUES(
	'NOMAL_TAX', NOW(), NOW(), 0, 10
);