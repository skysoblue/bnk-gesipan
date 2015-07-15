create user bbs identified by bbs;
grant connect, resource, dba to bbs;

conn bbs/bbs;

drop sequence seq;
drop table Theme;
drop table Addr;
drop table Member;
drop table Book;
drop table Item;
drop table Article;

create sequence seq 
increment by 1
start with 1000
nocycle;
commit;

create table Theme (
    thm_seq  number constraint theme_pk primary key , -- 게시판 카테고리 시퀀스
    board_name    varchar2(100) -- 게시판 타이틀
);
commit;
insert into Theme -- 1000 방명록, 누구나 작성 가능
values (seq.nextval, '방명록'); 
insert into Theme  -- 1001 답글게시판, 묻고 답하기
values (seq.nextval, '게시판'); 
insert into Theme -- 1002 마이북, 사용자만 작성 가능
values (seq.nextval, '마이북'); 
commit;


create table Addr(
	addr_seq number constraint addr_pk primary key,  --1.시퀀스
	zip_code varchar2(10), -- 2.우편번호
	super_city varchar2(100), -- 3.특별, 직할시
	do varchar2(100), -- 4.도 
	city varchar2(100), -- 5.도시
	gu_gun varchar2(100), -- 6.구,군
	doro varchar2(100),  -- 7.도로명
	doro_no varchar2(100), -- 8.도로번호 
	gita varchar2(100)  -- 9.기타상세주소
);
commit;
insert into Addr
	values(seq.nextval, '123-456','서울특별시','','','강남구','강남대로','396','');  
insert into Addr
	values(seq.nextval, '123-789','서울특별시','','','금천구','벚꽃로','309','');
insert into Addr
	values(seq.nextval, '345-123','','경기도','안양시','만안구','강남대로','396','');
insert into Addr
	values(seq.nextval, '345-456','','경기도','수원시','팔달구','덕영대로','944','');
insert into Addr
	values(seq.nextval, '345-789','','경기도','군포시','','군포로','55번길','');
commit;

create table Member(
	mem_id		varchar2(100) constraint member_pk primary key, -- 회원ID
	addr_seq    number , -- 주소 시퀀스
	name	varchar2(100) default 'null', -- 이름
	age		varchar2(10) , -- 나이
	password varchar2(100) default 'null', -- 비번
	email	varchar2(100), -- 이메일
	is_admin number default 0, -- 관리자 1 일반유저 0
	reg_date date default sysdate,
	constraint member_fk_addr foreign key(addr_seq)
		references Addr(addr_seq) on delete cascade  
);
commit;
insert into Member
	values('hong',1003,'홍길동','20','1234','hong@naver.com',0);
insert into Member
	values('kang',1004,'강감찬','30','1234','kang@naver.com',0);
insert into Member
	values('kim',1005,'김유신','35','1234','kim@naver.com',0);
insert into Member
	values('lee',1006,'이순신','33','1234','lee@naver.com',0);
insert into Member
	values('you',1007,'유관순','28','1234','you@naver.com',0);
insert into Member
	values('admin',1003,'관리자','40','1234','you@naver.com',1);
commit;


create table Book(
	book_seq number constraint book_pk primary key,
	mem_id varchar2(100),
	title varchar2(100) default 'null',
	creator varchar2(100) default 'null',
	genre varchar2(100) default 'null',
	reg_date date default sysdate,
	constraint book_fk_member foreign key(mem_id)
		references Member(mem_id) on delete cascade
);
commit;
-- 1008,1009,1010,1011
insert into Book values(seq.nextval,'hong','명예의 조각들','로이스 맥마스터 부졸드','외국판타지소설',sysdate  );
insert into Book values(seq.nextval,'hong','바라야 내전','로이스 맥마스터 부졸드','외국판타지소설',sysdate  );
insert into Book values(seq.nextval,'you','피렌체','살만 루슈디','영국문학',sysdate  );
insert into Book values(seq.nextval,'kang','해리포터','조앤 롤링','외국판타지소설',sysdate  );
commit;

create table Item(
	item_seq number constraint item_pk primary key,
	mem_id varchar2(100) not null,
	file_name varchar(100) default 'null',
	real_path varchar(100) default 'null',
	file_size int default 0,
	download_cnt int default 0,
	description varchar2(2000) default 'null',
	constraint item_fk_member foreign key(mem_id)
		references Member(mem_id) on delete cascade
);
commit;

create table Article (
	art_seq number constraint article_pk primary key, -- 글 시퀀스
	thm_seq number,  --카테고리 시퀀스
	mem_id varchar2(100) ,  -- 회원 ID
	book_seq number, -- null 이면 방명록이나 게시판
	posting_date date default sysdate, -- 글 등록일
	read_cnt number default 0, -- 조회수
	title varchar2(100) default 'null', -- 글 타이틀
	content varchar2(2000) default 'null',  -- 내용
	constraint aritcle_fk_theme foreign key(thm_seq) 
		references Theme(thm_seq) on delete cascade,
	constraint article_fk_member foreign key(mem_id)
		references Member(mem_id) on delete cascade,
	constraint article_fk_book foreign key(book_seq)
		references Book(book_seq) on delete cascade
) ;
commit;
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'hong','1008', sysdate, 0,'북리뷰','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'hong','1008', sysdate, 0,'북리뷰','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');	
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'hong','1008', sysdate, 0,'북리뷰','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');	
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'hong','1008', sysdate, 0,'북리뷰','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');	
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'kang','', sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'kim','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'lee','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'you','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'you','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'hong','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'kang','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'kim','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'lee','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1000, 'you', '', sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1001, 'you','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1001, 'hong','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1001, 'kim','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1001, 'kang','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');
insert into Article(art_seq, thm_seq, mem_id, book_seq, posting_date,read_cnt, title, content) 
	values(seq.nextval, 1001, 'you','',  sysdate, 0,'샘플타이틀','원질이 그림자는 사랑의 살았으며, 것은 있으며, 있는가?');	
commit;	



