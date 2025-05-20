-- 1) 브랜드 테이블 초기 데이터
INSERT INTO brand (name) VALUES
 ('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I');

-- 2) 상품 테이블 초기 데이터
INSERT INTO product (price, category, brand_id) VALUES
-- 브랜드 A (id=1)
(11200, 'TOP',        1),
(5500,  'OUTER',      1),
(4200,  'PANTS',      1),
(9000,  'SNEAKERS',   1),
(2000,  'BAG',        1),
(1700,  'HAT',        1),
(1800,  'SOCKS',      1),
(2300,  'ACCESSORY',  1),

-- 브랜드 B (id=2)
(10500, 'TOP',        2),
(5900,  'OUTER',      2),
(3800,  'PANTS',      2),
(9100,  'SNEAKERS',   2),
(2100,  'BAG',        2),
(2000,  'HAT',        2),
(2000,  'SOCKS',      2),
(2200,  'ACCESSORY',  2),

-- 브랜드 C (id=3)
(10000, 'TOP',        3),
(6200,  'OUTER',      3),
(3300,  'PANTS',      3),
(9200,  'SNEAKERS',   3),
(2200,  'BAG',        3),
(1900,  'HAT',        3),
(2200,  'SOCKS',      3),
(2100,  'ACCESSORY',  3),

-- 브랜드 D (id=4)
(10100, 'TOP',        4),
(5100,  'OUTER',      4),
(3000,  'PANTS',      4),
(9500,  'SNEAKERS',   4),
(2500,  'BAG',        4),
(1500,  'HAT',        4),
(2400,  'SOCKS',      4),
(2000,  'ACCESSORY',  4),

-- 브랜드 E (id=5)
(10700, 'TOP',        5),
(5000,  'OUTER',      5),
(3800,  'PANTS',      5),
(9900,  'SNEAKERS',   5),
(2300,  'BAG',        5),
(1800,  'HAT',        5),
(2100,  'SOCKS',      5),
(2100,  'ACCESSORY',  5),

-- 브랜드 F (id=6)
(11200, 'TOP',        6),
(7200,  'OUTER',      6),
(4000,  'PANTS',      6),
(9300,  'SNEAKERS',   6),
(2100,  'BAG',        6),
(1600,  'HAT',        6),
(2300,  'SOCKS',      6),
(1900,  'ACCESSORY',  6),

-- 브랜드 G (id=7)
(10500, 'TOP',        7),
(5800,  'OUTER',      7),
(3900,  'PANTS',      7),
(9000,  'SNEAKERS',   7),
(2200,  'BAG',        7),
(1700,  'HAT',        7),
(2100,  'SOCKS',      7),
(2000,  'ACCESSORY',  7),

-- 브랜드 H (id=8)
(10800, 'TOP',        8),
(6300,  'OUTER',      8),
(3100,  'PANTS',      8),
(9700,  'SNEAKERS',   8),
(2100,  'BAG',        8),
(1600,  'HAT',        8),
(2000,  'SOCKS',      8),
(2000,  'ACCESSORY',  8),

-- 브랜드 I (id=9)
(11400, 'TOP',        9),
(6700,  'OUTER',      9),
(3200,  'PANTS',      9),
(9500,  'SNEAKERS',   9),
(2400,  'BAG',        9),
(1700,  'HAT',        9),
(1700,  'SOCKS',      9),
(2400,  'ACCESSORY',  9)
;
