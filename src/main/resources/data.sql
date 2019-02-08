INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SYSTEMADMIN');
INSERT INTO roles(name) VALUES('ROLE_AIRFLIGHTADMIN');
INSERT INTO roles(name) VALUES('ROLE_HOTELADMIN');
INSERT INTO roles(name) VALUES('ROLE_RENTACARADMIN');

INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1001, 'Vrsacka 10', 'Novi Sad', '', 'Dash', 0,0,0);
INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1002, 'Fruskogorska 19', 'Novi Sad', 'Lorem ipsum dolor sit amet, no sed adhuc soluta appetere, vix quot percipitur inciderint ea, ferri accusam ad duo. Sed amet theophrastus ad, ad vis meis harum offendit, dolor torquatos voluptatum ei mei. Vim cu aliquid invenire dissentias. Sit esse mundi indoctum at, sit appareat accusata ut, rationibus reprimique ius an. Te augue suscipit ullamcorper sit, sit ei detraxit salutandi vituperatoribus. Sit cu consetetur reprehendunt definitionem.', 'Garni', 42,13,0);
INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1003, 'Bulevar Mihajla Pupina 30', 'Novi Sad', 'Inspirisan pozoristem koje se nalazi sa druge strane ulice', 'Centar', 0,0,0);
INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1004, 'Svetogorska 10', 'Beograd', '', 'Sava', 0,0, 0);
INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1005, 'Bulevar Arsenija Carnojevica', 'Beograd', 'Lorem ipsum dolor sit amet, no sed adhuc soluta appetere, vix quot percipitur inciderint ea, ferri accusam ad duo. Sed amet theophrastus ad, ad vis meis harum offendit, dolor torquatos voluptatum ei mei. Vim cu aliquid invenire dissentias. Sit esse mundi indoctum at, sit appareat accusata ut, rationibus reprimique ius an. Te augue suscipit ullamcorper sit, sit ei detraxit salutandi vituperatoribus. Sit cu consetetur reprehendunt definitionem.', 'In', 90,22,0);
INSERT INTO hotel(id, address, city, description, name, rating_sum, rating_count, version) 
VALUES (1006, 'Nikole Pasica', 'Nis', 'Sjajna lokacija, uopšteno, za obilaske znamenitosti, rekreaciju i prevoz u lokalu', 'Eter', 10,2,0);

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


INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number,verify,id_rentacar, role_id,change_pass)
VALUES (1, 'Beocin', 'nemanja@gmail.com', 'Nemanja', '345345','Dimsic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4535',true,0,2,true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_rentacar, role_id, hotel_id,change_pass)
VALUES (2, 'Sremska Kamenica', 'sveta@gmail.com', 'Svetislav', '2222','Simic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','43537', true,3, 4, 1002,true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_rentacar,change_pass)
VALUES (3, 'Klisa', 'dule@gmail.com', 'Dusan', '867867','Svilarkovic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4353', true,1,true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_rentacar,change_pass)
VALUES (4, 'Subotica', 'pero@gmail.com', 'Pera', '867867','Peric','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','4353', true, 0,true);
INSERT INTO abstract_user(id, address, email, first_name, index_number,last_name, password ,phone_number, verify,id_rentacar,change_pass)
VALUES (5, 'Beocin', 'viktor@gmail.com', 'Viktor', '65986','Djuka','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','66566', true,2,false);

INSERT INTO hotel_extras(id, price, name, unit, hotel_id) VALUES (1001, 3, 'WiFi', 'PER_DAY', 1002);
INSERT INTO hotel_extras(id, price, name, unit, hotel_id) VALUES (1002, 10, 'Polupansion', 'PER_PERSON', 1002);
INSERT INTO hotel_extras(id, price, name, unit, hotel_id) VALUES (1003, 5, 'Bazen', 'PER_DAY_PER_PERSON', 1002);
INSERT INTO hotel_extras(id, price, name, unit, hotel_id) VALUES (1004, 15, 'Bar', 'TOTAL', 1002);
INSERT INTO hotel_extras(id, price, name, unit, hotel_id) VALUES (1005, 3, 'Budjenje', 'TOTAL', 1002);

INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (1, 'Bulevar oslobođenja 13/1',' Novi Sad', 'Vozi vozi me ko na rolerkosteru', 'Compact', 1,5);
INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (2, 'Bulevar oslobođenja 13/1',' Novi Sad', 'aksjfdalksjflsjadfklsadf', 'Europcar',1,5);
INSERT INTO rentacar(id, address, city, description, name, rating_count,rating_sum)
VALUES (3, 'Puškinova',' Novi Sad', 'aksjfdalksjflsjadfklsadf', 'NS 021',2,7);


INSERT INTO user_roles(user_id, role_id) VALUES(1,2);
INSERT INTO user_roles(user_id, role_id) VALUES(2,4);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
INSERT INTO user_roles(user_id, role_id) VALUES(5,5);
INSERT INTO user_roles(user_id, role_id) VALUES(4,1);

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

INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (1,'vozilo1', 'RENAULT', 'Megane',  '2005', '5','2', '0' , '20', true,'1', '1',0.3,1,5,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (4,'vozilo4', 'RENAULT', 'Clio',  '2005', '5','2', '0' , '20', true,'1', '2',0.3, 1,3,0);
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (2, 'vozilo2', 'RENAULT', 'Clio',  '2005', '5', '1', '0', '30', false,'2', '4',0, 2,5,0);
INSERT INTO vehicle (id,name, brand,model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (3, 'vozilo3', 'SKODA', 'Fabia',  '2008', '5','3', '0', '40', false,'3', '5',0, 1,3,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (5,'vozilo5', 'RENAULT', 'Laguna',  '2000', '5','2', '0' , '15', false,'1', '1',0, 1,1,0);
INSERT INTO vehicle (id,name, brand, model, year,seats,type, rating,price,reserved,rentacar_id,branch_locations_id,discount, rating_count,rating_sum,version) 
VALUES (6,'vozilo6', 'RENAULT', 'Capture',  '2010', '5','2', '0' , '50', false,'1', '1',0, 3,6,0);

INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar,version) 
VALUES ('2019-1-1', '2019-1-14', 560, '2019-2-3', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '1','1','1',false,false,false,0);
INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar,version) 
VALUES ('2019-1-14', '2019-1-25', 320, '2019-1-1', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '4','4','1',false,false,false,0);
INSERT INTO vehicle_reservation(pickupdate,	dropoffdate,price,reservationdate,drop_off_location,pick_up_location,vehicle_id,abstract_user_id,rentacar_id,cancel,rate_vehicle,rate_rentacar,version) 
VALUES ('2019-2-4', '2019-2-14', 200, '2019-2-3', 'Bulevar oslobođenja 13/1', 'Bulevar oslobođenja 13/1', '1','5','1',false,false,false,0);




--unosenje Dusanovog dela

--aviokompanije
INSERT INTO public.airline
(id, full_name, grade_count, grade_sum, promo_info, location_id)
VALUES(1, 'Jat Airway', 0, 0, 'Jugoslovenski avio transport', NULL);
INSERT INTO public.airline
(id, full_name, grade_count, grade_sum, promo_info, location_id)
VALUES(2, 'Air Serbia', 0, 0, 'Opterecenje za budzet', NULL);
INSERT INTO public.airline
(id, full_name, grade_count, grade_sum, promo_info, location_id)
VALUES(3, 'Dusan Galactic', 0, 0, 'Ponos kapitalizma', NULL);


--avioni i njegovi segment i sedista
INSERT INTO public.airplane
(id, full_name, airline_id)
VALUES(1, 'Boing', 1);
INSERT INTO public.airplane
(id, full_name, airline_id)
VALUES(2, 'Boing 2', 2);
INSERT INTO public.airplane
(id, full_name, airline_id)
VALUES(3, 'Boing 3', 3);


--destinacije
--let


--cena leta po klasi


--pravljenje karte 



--prijateljstva
INSERT INTO public.friendship
(accepted, receiver_id, sender_id)
VALUES(true, 1, 3);
INSERT INTO public.friendship
(accepted, receiver_id, sender_id)
VALUES(true, 2, 3);
INSERT INTO public.friendship
(accepted, receiver_id, sender_id)
VALUES(true, 3, 2);
INSERT INTO public.friendship
(accepted, receiver_id, sender_id)
VALUES(true, 4, 3);
INSERT INTO public.friendship
(accepted, receiver_id, sender_id)
VALUES(true, 5, 3);

-- Tabela za bodove i racunanje popusta
-- Bonus bodovi za rezervisanje samo leta, leta i jos necega i sve tri stvari
INSERT INTO misc(id, b, b2, b3) VALUES (1, 1, 2, 3);

-- Granice za popust pri izboru dodataka u hotelu
INSERT INTO misc(id, b, b2, b3) VALUES (2, 1, 3, 5);

-- Popusti za izbor dodatnih usluga na osnovu pragova
INSERT INTO misc(id, b, b2, b3) VALUES (3, 5, 10, 15); 

-- Broj poena i procenat popusta na ukupnu svotu
INSERT INTO misc(id, b, b2, b3) VALUES (4, 5, 10, 15);

-- Rezervacije u paketu
INSERT INTO reservation_package(id, tickets, reserved_rooms) VALUES (1001, 5, false);
INSERT INTO reservation_package(id, tickets, reserved_rooms) VALUES (1002, 5, false);
INSERT INTO reservation_package(id, tickets, reserved_rooms) VALUES (1003, 5, false);
INSERT INTO reservation_package(id, tickets, reserved_rooms) VALUES (1004, 5, false);

-- Rezervacije soba DJUKA

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-02-28', 100.00, false, '2019-02-18', 1001, 1006,4,1002,false,false, '2019-2-10', '2019-2-10');

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-01-10', 200.00, false, '2018-12-29', 1002, 1006,4,1002,false,false, '2018-12-22', '2018-12-22');

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-02-18', 300.00, false, '2019-02-03', 1003, 1006,4,1002,false,false, '2019-2-3', '2019-2-3');

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-02-28', 50.00, false, '2019-02-18', 1001, 1003,4,1002,false,false, '2019-2-13', '2019-2-13');

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-01-10', 150.00, false, '2018-12-29', 1002, 1004,4,1002,false,false, '2018-12-23', '2018-12-23');

INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, abstract_user_id,hotel_id,rate_hotel,rate_room,reservation_date,res_date)
VALUES (true, '2019-02-18', 123.50, false, '2019-02-05', 1004, 1003,4,1002,false,false, '2019-02-03', '2019-02-03');

-- Rezervacije soba SVETA

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-02-28', 100.00, false, '2019-02-18', 1001, 1006, '2019-02-15');

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-01-10', 200.00, false, '2018-12-29', 1002, 1006, '2018-12-15');

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-02-18', 300.00, false, '2019-02-05', 1003, 1006, '2018-12-15');

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-02-28', 50.00, false, '2019-02-18', 1001, 1003, '2018-12-15');

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-01-10', 150.00, false, '2018-12-29', 1002, 1004, '2018-12-15');

-- INSERT INTO room_reservation(active, end_date, price, rated, start_date, reservation_id, room_id, res_date)
-- VALUES (true, '2019-02-18', 123.50, false, '2019-02-05', 1004, 1003, '2018-12-15');



