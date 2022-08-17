database pirate_supply_store;
create table pirates (
	pirate_id serial  primary key,
	pirate_name varchar(100),
	address varchar(150),
	email varchar(100)
)

create table store_front (
	store_name varchar(100) primary key,
	address varchar(150)
)

create table orders (
	order_id serial primary key,
	store_id varchar(100) not null,
	pirate_id int not null,
	total_price decimal,  
	foreign key (store_id)
      references store_front (store_name),
    foreign key (pirate_id)
      references pirates (pirate_id)
   
     

);

create table products (
	product_id serial primary key,
	product_name varchar(100) not null,
	store_id varchar(100) not null,
	price numeric,
	category varchar(50),
	foreign key (store_id)
		references store_front (store_name)
)
create table line_item (
	line_item_id serial primary key,
	product_id int not null,
	order_id int not null,
	quantity int,
	foreign key (order_id)
		references orders (order_id),
	foreign key (product_id)
		references products (product_id)
)



insert
	into
	store_front (store_name,
	address)
values ('Captain Branch',
'Fleur Marie: 44° 42` 59.22` N -75° 28` 34.68` W'),
('First Mate Branch',
'Dawn: 42° 25` 15.06` N -81° 21` 30.1212` W'),
('Quartermaster Branch',
'Valentine: 41° 55` 7.32` N -81° 54` 47.16` W'),
('Sailing Master Branch',
'Two Fannies: 41° 33` 51.0012` N -81° 55` 16.7988` W'),
			('Gunner Branch',
'Trade Wind: 42° 25` 8.4` N -80° 12` 3.6` W'),
('Powder Monkey Branch',
'Elk: 44° 14` 37.7988` N -76° 4` 50.9988` W'),
('Boatswain Branch',
'Kinghorn: 44° 22` 36.3612` N -75° 55` 50.2788` W'),
('Surgeon Branch',
'Land Tortoise: 43° 26` 24.72` -73° 41` 33.72` W'),
('Cook Branch',
'Milan: 43° 22` 19.8084` N -78° 11` 28.86` W')

select * from store_front
select * from products;

insert into products (store_id, product_name, price, category)
values(
'Captain Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Captain Branch','Marlinspike',8.00,'navigation'),
('Captain Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Captain Branch','Housewife',20.00,'miscellaneous'),
('Captain Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Captain Branch','Pair of Dice',3.00,'entertainment'),
('Captain Branch','Pipe',5.00,'entertainment'),
('Captain Branch','Tobacco',10.00,'entertainment'),
('Captain Branch','Sailor Palm',10.00,'navigation'),
('Captain Branch', 'Length of Rope',50.00,'navigation'),
('Captain Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Captain Branch', 'Pistol',75.00,'weaponry'),
('Captain Branch', 'Sword',65.00,'weaponry'),
('Captain Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Captain Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')

insert into products (store_id, product_name, price, category)
values(
'First Mate Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('First Mate Branch','Marlinspike',8.00,'navigation'),
('First Mate Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('First Mate Branch','Housewife',20.00,'miscellaneous'),
('First Mate Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('First Mate Branch','Pair of Dice',3.00,'entertainment'),
('First Mate Branch','Pipe',5.00,'entertainment'),
('First Mate Branch','Tobacco',10.00,'entertainment'),
('First Mate Branch','Sailor Palm',10.00,'navigation'),
('First Mate Branch', 'Length of Rope',50.00,'navigation'),
('First Mate Branch', '	Deck of Cards', 25.00, 'entertainment'),
('First Mate Branch', 'Pistol',75.00,'weaponry'),
('First Mate Branch', 'Sword',65.00,'weaponry'),
('First Mate Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('First Mate Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')

insert into products (store_id, product_name, price, category)
values(
'Quartermaster Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Quartermaster Branch','Marlinspike',8.00,'navigation'),
('Quartermaster Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Quartermaster Branch','Housewife',20.00,'miscellaneous'),
('Quartermaster Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Quartermaster Branch','Pair of Dice',3.00,'entertainment'),
('Quartermaster Branch','Pipe',5.00,'entertainment'),
('Quartermaster Branch','Tobacco',10.00,'entertainment'),
('Quartermaster Branch','Sailor Palm',10.00,'navigation'),
('Quartermaster Branch', 'Length of Rope',50.00,'navigation'),
('Quartermaster Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Quartermaster Branch', 'Pistol',75.00,'weaponry'),
('Quartermaster Branch', 'Sword',65.00,'weaponry'),
('Quartermaster Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Quartermaster Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')


insert into products (store_id, product_name, price, category)
values(
'Sailing Master Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Sailing Master Branch','Marlinspike',8.00,'navigation'),
('Sailing Master Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Sailing Master Branch','Housewife',20.00,'miscellaneous'),
('Sailing Master Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Sailing Master Branch','Pair of Dice',3.00,'entertainment'),
('Sailing Master Branch','Pipe',5.00,'entertainment'),
('Sailing Master Branch','Tobacco',10.00,'entertainment'),
('Sailing Master Branch','Sailor Palm',10.00,'navigation'),
('Sailing Master Branch', 'Length of Rope',50.00,'navigation'),
('Sailing Master Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Sailing Master Branch', 'Pistol',75.00,'weaponry'),
('Sailing Master Branch', 'Sword',65.00,'weaponry'),
('Sailing Master Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Sailing Master Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')


insert into products (store_id, product_name, price, category)
values(
'Powder Monkey Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Powder Monkey Branch','Marlinspike',8.00,'navigation'),
('Powder Monkey Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Powder Monkey Branch','Housewife',20.00,'miscellaneous'),
('Powder Monkey Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Powder Monkey Branch','Pair of Dice',3.00,'entertainment'),
('Powder Monkey Branch','Pipe',5.00,'entertainment'),
('Powder Monkey Branch','Tobacco',10.00,'entertainment'),
('Powder Monkey Branch','Sailor Palm',10.00,'navigation'),
('Powder Monkey Branch', 'Length of Rope',50.00,'navigation'),
('Powder Monkey Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Powder Monkey Branch', 'Pistol',75.00,'weaponry'),
('Powder Monkey Branch', 'Sword',65.00,'weaponry'),
('Powder Monkey Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Powder Monkey Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')


insert into products (store_id, product_name, price, category)
values(
'Boatswain Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Boatswain Branch','Marlinspike',8.00,'navigation'),
('Boatswain Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Boatswain Branch','Housewife',20.00,'miscellaneous'),
('Boatswain Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Boatswain Branch','Pair of Dice',3.00,'entertainment'),
('Boatswain Branch','Pipe',5.00,'entertainment'),
('Boatswain Branch','Tobacco',10.00,'entertainment'),
('Boatswain Branch','Sailor Palm',10.00,'navigation'),
('Boatswain Branch', 'Length of Rope',50.00,'navigation'),
('Boatswain Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Boatswain Branch', 'Pistol',75.00,'weaponry'),
('Boatswain Branch', 'Sword',65.00,'weaponry'),
('Boatswain Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Boatswain Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')


insert into products (store_id, product_name, price, category)
values(
'Surgeon Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Surgeon Branch','Marlinspike',8.00,'navigation'),
('Surgeon Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Surgeon Branch','Housewife',20.00,'miscellaneous'),
('Surgeon Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Surgeon Branch','Pair of Dice',3.00,'entertainment'),
('Surgeon Branch','Pipe',5.00,'entertainment'),
('Surgeon Branch','Tobacco',10.00,'entertainment'),
('Surgeon Branch','Sailor Palm',10.00,'navigation'),
('Surgeon Branch', 'Length of Rope',50.00,'navigation'),
('Surgeon Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Surgeon Branch', 'Pistol',75.00,'weaponry'),
('Surgeon Branch', 'Sword',65.00,'weaponry'),
('Surgeon Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Surgeon Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')

insert into products (store_id, product_name, price, category)
values(
'Cook Branch','Eye Patch',10.00,'clothingAndAccessories'

),
('Cook Branch','Marlinspike',8.00,'navigation'),
('Cook Branch','Pillage and Plunder: The Definitive Guide', 15.00,'howToGuides'),
('Cook Branch','Housewife',20.00,'miscellaneous'),
('Cook Branch','Jar of Dirt',100.00, 'magicTokensAndPotions'),
('Cook Branch','Pair of Dice',3.00,'entertainment'),
('Cook Branch','Pipe',5.00,'entertainment'),
('Cook Branch','Tobacco',10.00,'entertainment'),
('Cook Branch','Sailor Palm',10.00,'navigation'),
('Cook Branch', 'Length of Rope',50.00,'navigation'),
('Cook Branch', '	Deck of Cards', 25.00, 'entertainment'),
('Cook Branch', 'Pistol',75.00,'weaponry'),
('Cook Branch', 'Sword',65.00,'weaponry'),
('Cook Branch','The Pirate Code for Dummies', 30.00,'howToGuides'),
('Cook Branch', 'Blackbeard: His Life and Times',35.00,'howToGuides')


select * from products where PRODUCt_id = 1;
