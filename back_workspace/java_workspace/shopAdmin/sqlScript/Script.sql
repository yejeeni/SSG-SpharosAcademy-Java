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


