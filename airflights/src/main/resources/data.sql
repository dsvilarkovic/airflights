INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SYSTEMADMIN');
INSERT INTO roles(name) VALUES('ROLE_AIRFLIGHTADMIN');
INSERT INTO roles(name) VALUES('ROLE_HOTELADMIN');
INSERT INTO roles(name) VALUES('ROLE_RENTACARADMIN');

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1001, 'Vrsacka 10', 'Novi Sad', '', 'Dash', 0,0);

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1002, 'Fruskogorska 19', 'Novi Sad', 'Lorem ipsum dolor sit amet, no sed adhuc soluta appetere, vix quot percipitur inciderint ea, ferri accusam ad duo. Sed amet theophrastus ad, ad vis meis harum offendit, dolor torquatos voluptatum ei mei. Vim cu aliquid invenire dissentias. Sit esse mundi indoctum at, sit appareat accusata ut, rationibus reprimique ius an. Te augue suscipit ullamcorper sit, sit ei detraxit salutandi vituperatoribus. Sit cu consetetur reprehendunt definitionem.', 'Garni', 42,13);

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1003, 'Bulevar Mihajla Pupina 30', 'Novi Sad', 'Inspirisan pozoristem koje se nalazi sa druge strane ulice', 'Centar', 0,0);

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1004, 'Svetogorska 10', 'Beograd', '', 'Sava', 0,0);

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1005, 'Bulevar Arsenija Carnojevica', 'Beograd', 'Lorem ipsum dolor sit amet, no sed adhuc soluta appetere, vix quot percipitur inciderint ea, ferri accusam ad duo. Sed amet theophrastus ad, ad vis meis harum offendit, dolor torquatos voluptatum ei mei. Vim cu aliquid invenire dissentias. Sit esse mundi indoctum at, sit appareat accusata ut, rationibus reprimique ius an. Te augue suscipit ullamcorper sit, sit ei detraxit salutandi vituperatoribus. Sit cu consetetur reprehendunt definitionem.', 'In', 90,22);

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count) 
VALUES (1006, 'Nikole Pasica', 'Nis', 'Sjajna lokacija, uopšteno, za obilaske znamenitosti, rekreaciju i prevoz u lokalu', 'Eter', 10,2);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1001, 101, true, 2, null, 1, 30, false, 20, 83, 1, 1002);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1002, 102, false, 4, null, 1, 60, false, 15, 50, 2, 1002);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1003, 201, true, 3, null, 2, 40, false, 10, 44, 2, 1002);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1004, 301, true, 2, null, 3, 100, false, 20, 83, 1, 1002);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1005, 303, true, 2, null, 3, 30, false, 20, 83, 1, 1002);

INSERT INTO room(id, number, balcony, beds, discount, floor, price, promo, rating_count, rating_sum, rooms, hotel_id)
VALUES (1006, 310, true, 2, null, 3, 30, false, 20, 83, 1, 1002);

INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number,verify,id_company, role_id)
VALUES (1, 'Beocin', 'nemanja@gmail.com', 'Nemanja', '345345','Dimsic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4535',true,0,2);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_company, role_id, hotel_id)
VALUES (2, 'Sremska Kamenica', 'sveta@gmail.com', 'Svetislav', '2222','Simic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','43537', true,3, 4, 1002);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_company)
VALUES (3, 'Klisa', 'dule@gmail.com', 'Dusan', '867867','Svilarkovic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4353', true,1);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_company)
VALUES (4, 'Subotica', 'pero@gmail.com', 'Pera', '867867','Peric','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4353', false, 0);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_company)
VALUES (5, 'Beocin', 'viktor@gmail.com', 'Viktor', '65986','Djuka','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','66566', true,2);

INSERT INTO user_roles(user_id, role_id) VALUES(1,2);
INSERT INTO user_roles(user_id, role_id) VALUES(2,4);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
INSERT INTO user_roles(user_id, role_id) VALUES(5,5);

INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (1, 'Bulevar oslobođenja 13/1',' Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Compact', 1,5);
INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (2, 'Bulevar oslobođenja 13/1',' Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Europcar',1,5);
INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (3, 'Puškinova',' Novi Sad', 'aksjfdalksjflsjadfklsadf', 'NS 021',2,7);

INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (1, 'Bulevar oslobođenja 13/1', 'Novi Sad', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (2, 'Svetosavska bb', 'Beocin', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (3, 'Puškinova 13', 'Novi Sad', '1');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (4, 'Seljackih buna 13', 'Novi Sad', '2');
INSERT INTO branch_locations(id, address, city, rentacar_id)
VALUES (5, 'Bulevar Despota Stefana 66', 'Novi Sad', '3');

INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (1,'vozilo1', 'RENAULT', 'Megane',  '2005', '5','2', '0' , '20', false,'1', '1',0.3, 0,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (4,'vozilo4', 'RENAULT', 'Clio',  '2005', '5','2', '0' , '20', false,'1', '2',0.1, 0,0);
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (2, 'vozilo2', 'RENAULT', 'Clio',  '2005', '5', '1', '0', '30', false,'2', '4',0, 0,0);
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (3, 'vozilo3', 'SKODA', 'Fabia',  '2008', '5','3', '0', '40', false,'3', '5',0, 0,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (5,'vozilo1', 'RENAULT', 'Laguna',  '2000', '5','2', '0' , '15', false,'1', '1',0.3, 0,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum) 
VALUES (6,'vozilo1', 'RENAULT', 'Capture',  '2010', '5','2', '0' , '50', false,'1', '1',0.3, 0,0);

INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar) 
VALUES ('2019-1-1', '2019-1-14', 560, '2019-2-3', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '1','1','1',false,false,false);
INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar) 
VALUES ('2019-1-14', '2019-1-25', 320, '2019-1-1', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '4','5','1',false,false,false);
INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar) 
VALUES ('2019-2-4', '2019-2-14', 200, '2019-2-3', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '1','5','1',false,false,false);


