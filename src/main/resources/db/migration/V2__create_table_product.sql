CREATE TABLE IF NOT EXISTS TB_PRODUCT (
	id UUID DEFAULT uuid_generate_v4(),
	product_id BIGINT NOT NULL,
	name VARCHAR(150) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	quantity INTEGER NOT NULL,
	order_id UUID,
	constraint pk_id primary key (id),
	constraint fk_order_id foreign key (order_id) references TB_ORDER(id)
);

