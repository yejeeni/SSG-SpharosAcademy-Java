----------------------------------------------------
-- 최상위 카테고리
----------------------------------------------------

create table TOP_CATEGORY(
	TOP_CATEGORY_ID INT primary key auto_increment
	, TOP_CATEGORY_NAME VARCHAR(20)
);


insert into TOP_CATEGORY(TOP_CATEGORY_NAME) values('상의');
insert into TOP_CATEGORY(TOP_CATEGORY_NAME) values('하의');
insert into TOP_CATEGORY(TOP_CATEGORY_NAME) values('신발');
insert into TOP_CATEGORY(TOP_CATEGORY_NAME) values('액세서리');

----------------------------------------------------
-- 하위 카테고리
----------------------------------------------------

create table SUB_CATEGORY(
	SUB_CATEGORY_ID INT primary key auto_increment
	, SUB_CATEGORY_NAME VARCHAR(20)
	, TOP_CATEGORY_ID INT
	, constraint FK_TOP_CATEGORY_SUB_CATEGORY foreign key(TOP_CATEGORY_ID)
	  references TOP_CATEGORY(TOP_CATEGORY_ID)
);

insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('티셔츠', 1);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('가디건', 1);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('점퍼', 1);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('셔츠', 1);

insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('치마', 2);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('반바지', 2);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('청바지', 2);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('면바지', 2);

insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('운동화', 3);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('구두', 3);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('슬리퍼', 3);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('샌들', 3);

insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('반지', 4);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('귀걸이', 4);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('팔찌', 4);
insert into sub_category(SUB_CATEGORY_NAME, TOP_CATEGORY_ID) VALUES('목걸이', 4);

select * from sub_category where TOP_CATEGORY_ID = 3;

----------------------------------------------------
-- 색상표
----------------------------------------------------
create table COLOR(
	COLOR_ID INT primary key auto_increment
	, COLOR_NAME VARCHAR(15)
);

----------------------------------------------------
-- 사이즈
----------------------------------------------------
create table SIZE(
	SIZE_ID INT primary key auto_increment
	, SIZE_NAME VARCHAR(15)
);


----------------------------------------------------
-- 상품
----------------------------------------------------
create table PRODUCT(
	PRODUCT_ID INT primary key auto_increment
	, PRODUCT_NAME VARCHAR(25)
	, BRAND VARCHAR(15)
	, PRICE INT default 0
	, DISCOUNT INT default 0
	, INTRODUCT VARCHAR(250)
	, DETAIL TEXT
	, SUB_CATEGORY_ID INT
	, constraint FK_SUB_CATEGORY_PRODUCT foreign key(SUB_CATEGORY_ID) references SUB_CATEGORY(SUB_CATEGORY_ID)
);

----------------------------------------------------
-- 상품의 사이즈 정보
----------------------------------------------------
create table PRODUCT_SIZE(
	PRODUCT_ID INT primary key auto_increment
	, SIZE_ID INT
	, constraint FK_PRODUCT_PRODUCT_SIZE foreign key(PRODUCT_ID) references PRODUCT(PRODUCT_ID)
	, constraint FK_SIZE_PRODUCT_SIZE foreign key(SIZE_ID) references SIZE(SIZE_ID)
);

----------------------------------------------------
-- 상품의 색상 정보
----------------------------------------------------
create table PRODUCT_COLOR(
	PRODUCT_COLOR_ID INT primary key auto_increment
	, PRODUCT_ID INT
	, COLOR_ID INT
	, constraint FK_PRODUCT_PRODUCT_COLOR foreign key(PRODUCT_ID) references PRODUCT(PRODUCT_ID)
	, constraint FK_COLOR_PRODUCT_COLOR foreign key(COLOR_ID) references COLOR(COLOR_ID)
);

create table product_img(
	product_img_id int primary key auto_increment
	, filename varchar(20)
	, product_id int
	, constraint fk_product_product_img foreign key(product_id) references product(product_id)
);





select * from top_category;
select * from sub_category;
select * from product;

select * 
from sub_category sc
	join top_category tp
		on sc.TOP_CATEGORY_ID = tp.TOP_CATEGORY_ID;


insert into TOP_CATEGORY(TOP_CATEGORY_NAME) VALUES('가방');

-- 각 상위 카테고리 별 하위 카테고리의 수
select TC.TOP_CATEGORY_ID, COUNT(SC.SUB_CATEGORY_ID)
from top_category tc
left outer join sub_category sc 
	on TC.TOP_CATEGORY_ID  = SC.TOP_CATEGORY_ID
group by TC.TOP_CATEGORY_ID

create table test(
	test_id int primary key auto_increment
	, name varchar(20)
);

insert into test(name) values('a');
insert into test(name) values('b');
insert into test(name) values('c');

select *
from test
order by test_id desc
limit 1;

-- last_insert_id(): 가장 최근에 성공적으로 수행된 insert 구문의 첫 번째 auto_increment 값을 반환 
select last_insert_id() as id;









