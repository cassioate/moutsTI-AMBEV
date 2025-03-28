CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS TB_ORDER (
    id UUID DEFAULT uuid_generate_v4(),
    order_id BIGINT NOT NULL,
    total_value DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    constraint pk_order_id PRIMARY KEY (id)
);
