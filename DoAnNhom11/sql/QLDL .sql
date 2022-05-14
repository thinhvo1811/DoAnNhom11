CREATE DATABASE QLDL
USE QLDL
CREATE TABLE NHANVIEN(
maNV varchar(40) primary key Not Null,
pw varchar(40) ,
nlpw varchar(40)
)


CREATE TABLE KHACHHANG(
maKH varchar(40) primary key Not Null,
tenKH varchar(40) ,
email varchar(40) ,
sdt varchar(40),
diaChi varchar(40)
)

CREATE TABLE TOUR(
maTour varchar(40) primary key Not Null,
tenTour varchar(40) ,
soNgay int ,
giaTour money
)
CREATE TABLE LOTRINH(
maLT varchar(40) primary key,
maTour varchar(40) constraint lt_fk_t foreign key (maTour) references Tour(maTour),
ngayXP date,
ngayKT date,
diemXP varchar(40),
diemDen varchar(40)
)

CREATE TABLE VE(
maVe varchar(40) primary key not null,
soNguoi int,
giaVe money,
maLT varchar(40)  constraint v_fk_lt foreign key (maLT) references LOTRINH(maLT),
maKH varchar(40) constraint v_fk_kh foreign key (maKH) references KHACHHANG(maKH),
maTour varchar(40) constraint v_fk_t foreign key (maTour) references Tour(maTour)
)

INSERT INTO KHACHHANG
VALUES
	('KH2', 'Vo Quoc Thinh', 'vothinh5412@gmail.com', '0916523395', 'Campuchia'),
	('KH1', 'Do Quoc Tuan', 'dotuan6569@gmail.com', '0716521784', 'Ha Noi'),
	('KH3', 'Le The Vinh', 'levinh982@gmail.com', '0921864521', 'TP.Ho Chi Minh'),
	('KH4', 'Tran Trong', 'trantronq953@gmail.com', '0924854687', 'Tay Ninh'),
	('KH5', 'Tran Quoc Viet', 'tranviet964@gmail.com', '0546891248','Son La')

INSERT INTO TOUR VALUES
	(   'T01', 'TPHCM-Ha Noi', 3, 250000),
	(   'T02', 'Can Tho-TPHCM', 2, 180000),
	(   'T03', 'Da Nang-TPHCM', 5, 500000),
	(   'T04', 'Can Tho-Ha Noi', 4, 800000),
	(   'T05', 'TPHCM-Da Nang', 4, 400000)

INSERT INTO LOTRINH VALUES
	('LT1', 'T01', '20220517', '20220520','TPHCM', 'Ha Noi'),
	('LT2', 'T02', '20220524', '20220526', 'Can Tho', 'TPHCM'),
	('LT3', 'T03', '20220526', '20220531', 'Da Nang', 'TPHCM'),
	('LT4', 'T04', '20220513', '20220517', 'Can Tho', 'Ha Noi'),
	('LT5', 'T05', '20220531', '20220603','TPHCM', 'Da Nang')



INSERT INTO VE VALUES
('VE1',2,500000,'LT1','KH1','T01'),
('VE2',4,720000,'LT2','KH2','T02'),
('VE3',8,4000000,'LT3','KH3','T03'),
('VE4',4,3200000,'LT4','KH4','T04'),
('VE5',8,3200000,'LT5','KH5','T05')

Select * From [dbo].[KHACHHANG]
Select * From [dbo].[LOTRINH]
Select * From [dbo].[TOUR]
Select * From [dbo].[VE]



SELECT * FROM LOTRINH INNER JOIN TOUR ON LOTRINH.maTour = TOUR.maTour WHERE TOUR.maTour like 'T01'

SELECT * FROM KHACHHANG INNER JOIN VE ON KHACHHANG.maKH = VE.maKH WHERE KHACHHANG.maKH = 'KH7745'

SELECT * FROM VE INNER JOIN TOUR ON TOUR.maTour = VE.maTour WHERE TOUR.maTour = 'T01'

SELECT * FROM  VE  INNER JOIN LOTRINH ON LOTRINH.maLT = VE.maLT WHERE LOTRINH.maLT = 'LT003'
