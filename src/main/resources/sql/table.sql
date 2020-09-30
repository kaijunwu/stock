CREATE TABLE public.stock (
	id int2 NULL,
	"day" varchar(100) NULL,
	"open" float8 NULL,
	high float8 NULL,
	low float8 NULL,
	"close" float8 NULL,
	volume int8 NULL,
	ma_price5 float8 NULL,
	ma_volume5 int8 NULL,
	code varchar NULL,
	"name" varchar NULL
);