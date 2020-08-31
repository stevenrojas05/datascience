//Roles

CREATE ROLE ecommerce WITH LOGIN ENCRYPTED PASSWORD 'ecommerce';

CREATE ROLE ecommerce_admin WITH LOGIN ENCRYPTED PASSWORD '234567';
CREATE ROLE ecommerce_client WITH LOGIN ENCRYPTED PASSWORD '345678';
CREATE ROLE ecommerce_guest WITH LOGIN ENCRYPTED PASSWORD '456789';

// Base de datos

CREATE DATABASE ecommerce
WITH
OWNER = ecommerce
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

// Tablas

CREATE TABLE categories (
id bigserial NOT NULL,
published integer NOT NULL DEFAULT '0',
name varchar (255) NOT NULL,
icon varchar (255) NOT NULL,
created_at timestamp without time zone DEFAULT now (),
updated_at timestamp without time zone DEFAULT now (),
CONSTRAINT categories_pkey PRIMARY KEY (id)
)
WITH (OIDS=FALSE);
ALTER TABLE public.categories OWNER TO ecommerce;



CREATE TABLE products (
id bigserial NOT NULL,
published integer NOT NULL DEFAULT '0',
rating_cache double precision NOT NULL DEFAULT '3.0',
rating_count double precision NOT NULL DEFAULT '0',
category_id bigint NOT NULL,
name varchar (255) NOT NULL,
pricing double precision NOT NULL DEFAULT '0.00',
short_description varchar (255) NOT NULL,
long_description text NOT NULL,
icon varchar (255) NOT NULL,
created_at timestamp without time zone DEFAULT now (),
updated_at timestamp without time zone DEFAULT now (),
CONSTRAINT products_pkey PRIMARY KEY (id),
CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id)
REFERENCES public.categories (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE public.products OWNER TO ecommerce;



CREATE TABLE users (
id bigserial NOT NULL,
user_type integer NOT NULL DEFAULT '0',
username varchar (128) NOT NULL,
email varchar (128) NOT NULL,
password varchar (128) NOT NULL,
created_at timestamp without time zone DEFAULT now (),
updated_at timestamp without time zone DEFAULT now (),
CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (OIDS=FALSE);
ALTER TABLE public.users OWNER TO ecommerce;


CREATE TABLE reviews (
id bigserial NOT NULL,
user_id bigint NOT NULL,
item_id bigint NOT NULL,
rating double precision NOT NULL,
comment text NOT NULL,
approved integer NOT NULL DEFAULT '1',
spam integer NOT NULL DEFAULT '0',
created_at timestamp without time zone DEFAULT now (),
updated_at timestamp without time zone DEFAULT now (),
CONSTRAINT reviews_pkey PRIMARY KEY (id)
)
WITH (OIDS=FALSE);
ALTER TABLE public.reviews OWNER TO ecommerce;





CREATE TABLE public.purchases
(
    id bigserial NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT purchases_pkey PRIMARY KEY (id),
    CONSTRAINT purchases_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)

ALTER TABLE public.purchases
    OWNER to ecommerce;



CREATE TABLE public.purchase_items
(
    id bigserial NOT NULL,
    purchase_id bigint NOT NULL,
    product_id bigint NOT NULL,
    price double precision NOT NULL,
    quantity double precision NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    CONSTRAINT purchase_items_pkey PRIMARY KEY (id),
    CONSTRAINT purchase_items_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT purchase_items_purchase_id_fkey FOREIGN KEY (purchase_id)
        REFERENCES public.purchases (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)


ALTER TABLE public.purchase_items
    OWNER to ecommerce;




// Insercciones

INSERT INTO categories ("published", "name", "icon") VALUES
(1, 'COMIDA RÁPIDA', 'comida_rapida.jpg'),
(1, 'COMIDA A LA CARTA', 'comida_a_la_carta.jpg'),
(1, 'BEBIDAS', 'bebidas.jpg'),
(1, 'POSTRES', 'postres.jpg')



INSERT INTO products ("published", "rating_cache", "rating_count", "category_id",
"name", "pricing", "short_description", "long_description", "icon") VALUES
(1, 3.0, 0, 1, 'Hamburguesa Mexicana', 9500, 'El mejor sabor mexicano que puedas encontrar', 'Carne a la parrilla, vegetales, queso, tacos, guacamole',
'product01.jpg'),
(1, 3.0, 0, 1, 'Hamburguesa Barbacoa', 11000, 'El mejor sabor de carne a la parrilla que puedas encontrar', 'Carne a la parrilla, vegetales, queso, chorizo, tocineta, vegetales, salsa BBQ',
'product02.jpg'),
(1, 3.0, 0, 1, 'Hamburguesa Queso Extremo', 8500, 'El mejor sabor del queso quepuedas encontrar', 'Carne, vegetales, queso, queso cheddar, queso azul',
'product03.jpg'),
(1, 3.0, 0, 1, 'Hamburguesa Clásica', 7000, 'En la simpleza puede estar tu mejor opción', 'Carne, vegetales, salsa de la casa',
'product04.jpg'),
(1, 3.0, 0, 1, 'Hamburguesa Hawaiana', 8000, 'El mejor sabor hawaiano que puedas encontrar', 'Carne, piña, queso doble, vegetales',
'product05.jpg'),
(1, 3.0, 0, 2, 'Filet mignon', 15000, 'El mejor sabor de filet mignon que puedas encontrar', 'filet mignon, salsa de champiñones, arroz, ensalda',
'product06.jpg'),
(1, 3.0, 0, 2, 'Carne a las brasas', 13000, 'El mejor sabor de carne a las  brasas que puedas encontrar', 'Carne a las brasas, queso derretido, salsa bechamel, ensalada',
'product07.jpg'),
(1, 3.0, 0, 2, 'Espaguetis a la bolognesa', 10000, 'El mejor sabor de espaguetis que puedas encontrar', 'Espaguetis, salsa bolognesa, carne molida',
'product08.jpg'),
(1, 3.0, 0, 2, 'Filete', 11000, 'El mejor sabor de filete que puedas encontrar', 'Filete, arroz, ensalada',
'product09.jpg'),
(1, 3.0, 0, 2, 'Churrasco', 12000, 'El mejor sabor de churrasco que puedas encontrar', 'Carne a la medida, papas, ensalada, salsa de la casa',
'product10.jpg'),
(1, 3.0, 0, 3, 'Cerveza corona', 4000, 'Cerveza corona especial para ti', 'Cerveza corona por unidad',
'product11.jpg'),
(1, 3.0, 0, 3, 'Cocacola', 6000, 'Gaseosa marca Cocacola especial para ti', 'Cocacola 2 litros',
'product12.jpg'),
(1, 3.0, 0, 3, 'Vino tinto', 9000, 'Vino tinto especial para ti', 'Copa de vino tinto',
'product13.jpg'),
(1, 3.0, 0, 3, 'Sprite', 6000, 'Gaseosa marca Sprite especial para ti', 'Sprite 2 litros',
'product14.jpg'),
(1, 3.0, 0, 3, 'Colombiana', 5500, 'Gaseosa marca Colombiana especial para ti', 'Colombiana 2 litros',
'product15.jpg'),
(1, 3.0, 0, 4, 'Postre 3 leches', 3000, 'El mejor sabor del postre 3 leches que puedas encontrar', 'Postre personal 3 leches',
'product16.jpg'),
(1, 3.0, 0, 4, 'Pastel', 3000, 'El mejor sabor del pastel que puedas encontrar', 'Porción personal de pastel',
'product17.jpg'),
(1, 3.0, 0, 4, 'Torta fría', 2000, 'El mejor sabor de torta fría que puedas encontrar', 'Porción personal de torta fría',
'product18.jpg'),
(1, 3.0, 0, 4, 'Pasta dulce', 2000, 'El mejor sabor de pasta dulce que puedas encontrar', 'Porción personal de pasta dulce',
'product19.jpg'),
(1, 3.0, 0, 4, 'Especial', 3000, 'El mejor sabor de la casa que puedas encontrar', 'Porcion personal del especial de la casa',
'product20.jpg')



INSERT INTO users ("username", "email", "password") VALUES
('Henry', 'henry@gmail.com', 'henry123'),
('Maria', 'maria@gmail.com', 'maria123'),
('Pedro', 'pedro@gmail.com', 'pedro123');



INSERT INTO reviews ("item_id", "user_id", "rating", "comment") VALUES
(1,1,4.5,'Comment 001'),
(1,2,3.5,'Comment 002'),
(1,3,4.0,'Comment 003'),
(2,1,2.5,'Comment 004'),
(2,2,3.5,'Comment 005'),
(2,3,3.0,'Comment 006'),
(3,1,4.0,'Comment 007'),
(3,2,3.5,'Comment 008'),
(3,3,2.0,'Comment 009'),
(4,1,5.0,'Comment 010'),
(4,2,3.0,'Comment 011'),
(4,3,3.5,'Comment 012'),
(5,1,3.0,'Comment 013'),
(5,2,4.5,'Comment 014'),
(5,3,4.0,'Comment 015'),
(6,1,3.0,'Comment 016'),
(6,2,4.5,'Comment 017'),
(6,3,4.0,'Comment 018'),
(7,1,3.0,'Comment 019'),
(7,2,4.5,'Comment 020'),
(7,3,4.0,'Comment 021'),
(8,1,3.0,'Comment 022'),
(8,2,4.5,'Comment 023'),
(8,3,2.5,'Comment 024'),
(9,1,3.0,'Comment 025'),
(9,2,4.5,'Comment 026'),
(9,3,4.0,'Comment 027'),
(10,1,3.0,'Comment 028'),
(10,2,4.5,'Comment 029'),
(10,3,4.0,'Comment 030'),
(11,1,3.0,'Comment 031'),
(11,2,4.5,'Comment 032'),
(11,3,4.0,'Comment 033'),
(12,1,3.0,'Comment 034'),
(12,2,4.5,'Comment 035'),
(12,3,4.0,'Comment 036'),
(13,1,3.0,'Comment 037'),
(13,2,4.5,'Comment 038'),
(13,3,4.0,'Comment 039'),
(14,1,3.0,'Comment 040'),
(14,2,4.5,'Comment 041'),
(14,3,4.0,'Comment 042'),
(15,1,3.0,'Comment 043'),
(15,2,4.5,'Comment 044'),
(15,3,4.0,'Comment 045'),
(16,2,4.5,'Comment 046'),
(16,3,4.0,'Comment 047'),
(16,1,3.0,'Comment 048'),
(17,2,4.5,'Comment 049'),
(17,3,4.0,'Comment 050'),
(17,1,3.0,'Comment 051'),
(18,2,4.5,'Comment 052'),
(18,3,4.0,'Comment 053'),
(18,1,3.0,'Comment 054'),
(19,2,4.5,'Comment 055'),
(19,3,4.0,'Comment 056'),
(19,1,3.0,'Comment 057'),
(20,2,4.5,'Comment 058'),
(20,3,4.0,'Comment 059'),
(20,1,3.0,'Comment 060');




//Permisos


grant select, insert, update, delete on categories to ecommerce_admin;
grant select, insert, update, delete on products to ecommerce_admin;
grant select, insert, update, delete on reviews to ecommerce_admin;
grant select, insert, update, delete on purchases to ecommerce_admin;
grant select, insert, update, delete on purchase_items to ecommerce_admin;
grant select, insert, update, delete on users to ecommerce_admin;


grant select on categories to ecommerce_client;
grant select on products to ecommerce_client;
grant select,update, insert on reviews to ecommerce_client;
grant select, insert on purchases to ecommerce_client;
grant select, insert on purchase_items to ecommerce_client;
grant select on users to ecommerce_client;
//grant usage, select  on sequence reviews_id_seq TO ecommerce_client;
//grant usage, select  on sequence purchases_id_seq TO ecommerce_client;
//grant usage, select  on sequence purchase_items_id_seq TO ecommerce_client;
 

grant select on categories to ecommerce_guest;
grant select on products to ecommerce_guest;
grant select on reviews to ecommerce_guest;








