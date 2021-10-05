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

--testデータを入れる
INSERT 
INTO public.sequence (seq_name, seq_value) VALUES 
('clients_seq', 1000),
('company_seq', 1000),
('department_seq', 1000),
('destination_seq', 1000),
('lot_seq', 1000),

('product_seq', 1000),
('purchasing_seq', 1000),
('purchasing_detail_seq', 1000),

('sales_seq', 1000),
('sales_detail_seq', 1000),
('stock_seq', 1000),
('supplier_seq', 1000),
('supplier_product_seq', 1000),
('user_seq', 1000),
('warehouse_seq', 1000);
