INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number,verify,role)
VALUES (1, 'Beocin', 'nemanja@gmail.com', 'Nemanja', '345345','Dimsic','dimisav','4535',true,3);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,role)
VALUES (2, 'Sremska Kamenica', 'sveta@gmail.com', 'Svetislav', '2222','Simic','sima123','43537', true,2);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,role)
VALUES (3, 'Klisa', 'dule@gmail.com', 'Dusan', '867867','Svilarkovic','tviksi','4353', true,1);

INSERT INTO rentacar(id, address, description, name)
VALUES (1, 'Bulevar oslobođenja 13/1 Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Compact');
INSERT INTO rentacar(id, address, description, name)
VALUES (2, 'Bulevar oslobođenja 13/1, Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Europcar');
INSERT INTO rentacar(id, address, description, name)
VALUES (3, 'Puškinova, Novi Sad', 'aksjfdalksjflsjadfklsadf', 'NS 021');

INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,rentacar_id) 
VALUES (1,'vozilo1', 'RENAULT', 'Megane',  '2005', '5','Putnicko vozilo', '2' , '20','3');
INSERT INTO vehicle (id,name, brand,model,  year,seats,type, rating,price,rentacar_id) 
VALUES (2, 'vozilo2', 'RENAULT', 'Clio',  '2005', '5', 'Putnicko vozilo', '3', '30','2');
INSERT INTO vehicle (id,name, brand,model,  year,seats,type, rating,price,rentacar_id) 
VALUES (3, 'vozilo3', 'SKODA', 'Fabia',  '2008', '5','Putnicko vozilo', '5', '40','1');


INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (1, 'Bulevar oslobođenja 13/1', 'Novi Sad', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (2, 'Svetosavska bb', 'Beocin', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (3, 'Puškinova 13', 'Novi Sad', '1');

