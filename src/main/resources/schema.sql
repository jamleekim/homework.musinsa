-- 1) 기존 테이블 제거 (테스트/재실행 용)
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS brand;

-- 2) brand 테이블 생성
CREATE TABLE brand
(
    id   BIGINT auto_increment PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 3) product 테이블 생성
CREATE TABLE product
(
    id       BIGINT auto_increment PRIMARY KEY,
    price    INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    brand_id BIGINT NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand(id)
);