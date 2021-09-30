--testデータを入れる
--会社テストデータ
INSERT 
INTO public.company (company_seq, address, name, phone, zip) VALUES 
(1, '東京都千代田区丸の内〇-〇-〇', '宮木総合商事', '03-〇〇〇-〇〇〇', '○○○○'),
(2, '東京都港区赤坂〇-〇-〇', '木原商事', '03-〇〇〇-〇〇〇', '○○○○'),
(3, '東京都渋谷区道玄坂〇-〇-〇', '株式会社nagato', '03-〇〇〇-〇〇〇', '○○○○'),
(4, '東京都豊島区西池袋〇-〇-〇', '高山商店', '03-〇〇〇-〇〇〇', '○○○○')
;

--クライアントテストデータ
INSERT 
INTO PUBLIC.clients(clients_seq, name) VALUES 
(1, '佐藤太郎'), 
(2, '鈴木一郎'), 
(3, '山田花子'), 
(4, '小林優太')
;

--部署テストデータ
INSERT 
INTO PUBLIC.department(department_seq, name) 
VALUES (1, '人事部'), 
(2, '総務部'), 
(3, '経理部'), 
(4, '営業部')
;
