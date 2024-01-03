use LTW
go
select * from product

CREATE TABLE account (
    accId INT identity(1,1) PRIMARY KEY,
    username NVARCHAR(255),
    [password] NVARCHAR(255),
    isAdmin BIT
);

INSERT INTO account VALUES
('admin', 'admin', 1),
('user', 'user', 0),
('tat', 'tat', 0),
('nlu', 'nlu', 1),
('rat', 'rat', 0),
('abc', 'abc', 0);

CREATE TABLE category (
    cateId INT identity(1, 1) PRIMARY KEY,
    cateName NVARCHAR(255)
);

INSERT INTO category VALUES
('Vegetable'),
('Fruit'),
('Juice'),
('Dried');

CREATE TABLE product (
    id INT identity(1, 1) PRIMARY KEY,
    [name] NVARCHAR(255),
    imgUrl NVARCHAR(255),
    price Float,
    sale float,
    cateId INT,
    content NVARCHAR(MAX),
    [weight] INT
);

-- Chèn dữ liệu vào bảng
INSERT INTO product VALUES
    ('Bell Pepper', './shop-assest/img/product-image/product-1.jpg', 120, 0.3, 1, 'Bell peppers are vibrant and crisp vegetables known for their bright colors, including red, yellow, and green. With a mild, sweet flavor, they add a delightful crunch to salads, stir-fries, and various dishes. Packed with vitamins and antioxidants, bell peppers are not only tasty but also a healthy addition to your meals.', 204),
    ('Green Beans', './shop-assest/img/product-image/product-3.jpg', 5, 0, 1, 'Green beans, also known as string beans, are vibrant and crisp vegetables. Packed with nutrients and a delightful crunch, these slender pods are a versatile addition to salads, stir-fries, or enjoyed simply steamed. Their fresh, earthy flavor makes green beans a favorite for adding a burst of color and health to your plate.', 0),
    ('Purple Cabbage', './shop-assest/img/product-image/product-4.jpg', 2, 0, 1, 'Purple cabbage, a vibrant and nutritious vegetable, adds a burst of color to your plate. Packed with antioxidants and rich in vitamins, it not only enhances the visual appeal of your meals but also contributes to a healthy and balanced diet. Incorporate this versatile veggie into salads, coleslaws, or stir-fries for a delightful and nutritious dining experience.', 210),
    ('Tomato', './shop-assest/img/product-image/product-5.jpg', 120, 0.3, 1, 'Tomato is a vibrant, juicy fruit widely used in culinary applications. Known for its rich flavor and versatility, it adds a burst of freshness to salads, sandwiches, and sauces. Packed with essential nutrients, tomatoes are a staple ingredient that enhances the taste of various dishes.', 100),
    ('Broccoli', './shop-assest/img/product-image/product-6.jpg', 7, 0, 1, 'Broccoli, a nutritious green vegetable, is known for its vibrant appearance and health benefits. Packed with vitamins and fiber, broccoli offers a delicious and versatile addition to various dishes. Its crisp texture and earthy flavor make it a popular choice for those seeking a tasty and wholesome culinary experience.', 343),
    ('Carrots', './shop-assest/img/product-image/product-7.jpg', 5, 0, 1, 'Carrots are vibrant orange vegetables with a sweet and crisp texture. Packed with nutrients like beta-carotene, they promote eye health and boost the immune system. A versatile ingredient, carrots can be enjoyed raw, cooked, or juiced for a nutritious addition to any meal.', 0),
    ('Fruit Juice', './shop-assest/img/product-image/product-8.jpg', 20, 0, 3, 'Fruit Juice, a refreshing elixir bursting with natural goodness. Immerse yourself in the vibrant flavors of sun-kissed fruits, meticulously crafted to invigorate your senses. Sip and savor the essence of nature in every delightful drop.', 0),
    ('Onion', './shop-assest/img/product-image/product-9.jpg', 120, 0.3, 1, 'Onions, small but mighty, add depth and flavor to countless dishes. These versatile bulbs boast a pungent aroma and a distinctive taste that elevates savory creations. Whether sautéed to caramelized perfection or enjoyed raw for a zesty crunch, onions are a kitchen essential.', 563),
    ('Apple', './shop-assest/img/product-image/product-10.jpg', 11, 0, 2, 'Apple, a tech giant renowned globally, epitomizes innovation with its sleek and cutting-edge devices. From the iconic iPhone to the powerful MacBook, Apple consistently delivers seamless integration and a user-centric experience. The brand', 157),
    ('Garlic', './shop-assest/img/product-image/product-11.jpg', 9, 0, 1, 'Garlic, a pungent bulbous plant, is renowned for its distinct flavor and aroma. Widely used in culinary practices, it adds a savory touch to various dishes. Apart from its culinary significance, garlic is also valued for its potential health benefits.', 247),
    ('Chili', './shop-assest/img/product-image/product-12.jpg', 3, 0, 1, 'Chili, a spicy and flavorful dish, is a culinary delight hailing from Latin America. Packed with a punch of heat from chili peppers, it boasts a rich combination of beans, meat, and aromatic spices. A bowl of chili is a comforting and satisfying experience for those who crave bold and zesty flavors.', 0);

CREATE TABLE [user] (
    userId INT identity(1, 1) PRIMARY KEY,
    firstName NVARCHAR(255),
    lastName NVARCHAR(255),
    birthDay DATE,
    gender bit,
    address NVARCHAR(255),
    email NVARCHAR(255),
    phone int,
    accId INT
);

INSERT INTO [user]
VALUES
    ('Cris', 'Ronaldo', '2000-04-15', 1, '123 Main Street, Apt 4B Cityville, State 54321 United States', 'ronaldo2000@gmail.com', '252369563', 2),
    ('John', 'Yone', '1990-02-10', 0, '456 Oak Avenue, Townsville, Provinceville, 56789, USA', 'yone1990@gmail.com', '956823568', 3),
    ('Mike', 'Yasuo', '2017-06-12', 1, '789 Pine Lane, Villagetown, Countyville, 54321, USA', 'yasuo2017@gmail.com', '985633246', 5),
    ('Chalotte', 'King', '2005-12-22', 1, '101 Maple Street, Hamletville, Districtville, 98765, USA', 'king2005@gmail.com', '836211359', 6);