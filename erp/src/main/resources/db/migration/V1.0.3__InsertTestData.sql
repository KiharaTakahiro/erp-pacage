--testデータを入れる
--会社テストデータ
INSERT 
INTO public.company (company_seq, address, name, phone, zip) VALUES 
(1, '東京都千代田区丸の内〇-〇-〇', 'SENSIBILE（サンシーブル）', '03-〇〇〇-〇〇〇', '○○○○'),
(2, '東京都港区赤坂〇-〇-〇', '', '03-〇〇〇-〇〇〇', '○○○○')
;

--クライアントテストデータ
INSERT 
INTO PUBLIC.clients(clients_seq, name) VALUES 
(1, '宮木総合商事'), 
(2, '木原商事'), 
(3, '株式会社nagato'), 
(4, '高山商店')
;

--部署テストデータ
INSERT 
INTO PUBLIC.department(department_seq, company_seq, name) 
VALUES 
(1, 1, '営業1部'), 
(2, 1, '営業2部'), 
(3, 1, '営業3部'), 
(4, 2, '営業1部'), 
(5, 2, '営業2部')
;

--商品テストデータ
INSERT 
INTO PUBLIC.product(product_seq, name,purchase_unit_price,unit_price,tax_type) 
VALUES 
(1, 'トリートメントウォータードライ', 2500,3950,2), 
(2, 'トリートメントウォーターオイリー', 2500,3950,2),
(3, 'ホワイトアクアウォーター',2950 ,4400,2),
(4, 'リペアクリーム', 2500,3950,2),
(5, '保湿マスク', 1250,2200,2),
(6, 'エッセンシャルセラム', 3150,4620,2),
(7, 'ホワイトセラム', 3150,4620,2),
(8, 'メイクオフオイル', 2150,3500,2),
(9, 'メイクオフミルク', 2150,3500,2),
(10, 'クレンジングフォーム', 2050,3200,2)
;