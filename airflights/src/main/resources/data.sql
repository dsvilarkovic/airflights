INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number,verify)
VALUES (1, 'Beocin', 'nemanja@gmail.com', 'Nemanja', '345345','Dimsic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4535',true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify)
VALUES (2, 'Sremska Kamenica', 'sveta@gmail.com', 'Svetislav', '2222','Simic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','43537', true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify)
VALUES (3, 'Klisa', 'dule@gmail.com', 'Dusan', '867867','Svilarkovic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4353', true);



INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SYSTEMADMIN');
INSERT INTO roles(name) VALUES('ROLE_AIRFLIGHTADMIN');
INSERT INTO roles(name) VALUES('ROLE_HOTELADMIN');
INSERT INTO roles(name) VALUES('ROLE_RENTACARDMIN');

INSERT INTO user_roles(user_id, role_id) VALUES(1,2);
INSERT INTO user_roles(user_id, role_id) VALUES(2,4);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);

INSERT INTO rentacar(id, address, description, name, rating)
VALUES (1, 'Bulevar oslobođenja 13/1 Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Compact', 2);
INSERT INTO rentacar(id, address, description, name, rating)
VALUES (2, 'Bulevar oslobođenja 13/1, Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Europcar',3);
INSERT INTO rentacar(id, address, description, name, rating)
VALUES (3, 'Puškinova, Novi Sad', 'aksjfdalksjflsjadfklsadf', 'NS 021',4);

INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id) 
VALUES (1,'vozilo1', 'RENAULT', 'Megane',  '2005', '5','Putnicko vozilo', '2' , '20', false,'3');
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id) 
VALUES (2, 'vozilo2', 'RENAULT', 'Clio',  '2005', '5', 'Putnicko vozilo', '3', '30', false,'2');
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id) 
VALUES (3, 'vozilo3', 'SKODA', 'Fabia',  '2008', '5','Putnicko vozilo', '5', '40', false,'1');


INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (1, 'Bulevar oslobođenja 13/1', 'Novi Sad', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (2, 'Svetosavska bb', 'Beocin', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (3, 'Puškinova 13', 'Novi Sad', '1');

