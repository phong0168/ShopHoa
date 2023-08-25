create table if not exists category
(
    id   int auto_increment
    primary key,
    name varchar(255) not null
    );

create table if not exists flower
(
    id          int auto_increment
    primary key,
    name        varchar(255) not null,
    detail      text         not null,
    image_url   varchar(255) not null,
    price       float        null,
    category_id int          not null,
    constraint flower_ibfk_1
    foreign key (category_id) references category (id)
    );

create index category_id
    on flower (category_id);

create table if not exists orders
(
    id           int auto_increment
    primary key,
    total_price  float     default 0                 not null,
    f_and_l_name varchar(255)                        not null,
    phone_number varchar(255)                        not null,
    address      text                                not null,
    note         text                                null,
    order_date   timestamp default CURRENT_TIMESTAMP not null,
    user_id      int                                 not null,
    foreign key (user_id) references user (id)
    );

create table if not exists order_detail
(
    order_id  int not null,
    flower_id int not null,
    quantity  int not null,
    primary key (order_id, flower_id),
    constraint order_detail_ibfk_1
    foreign key (order_id) references orders (id),
    constraint order_detail_ibfk_2
    foreign key (flower_id) references flower (id)
    );

create index flower_id
    on order_detail (flower_id);

create definer = springstudent@localhost trigger calculate_total_price
    after insert
    on order_detail
    for each row
BEGIN
    DECLARE sum_price FLOAT;

SELECT SUM(f.price * od.quantity) + 30000 INTO sum_price
FROM flower f
         INNER JOIN order_detail od ON od.flower_id = f.id
WHERE od.order_id = NEW.order_id;

UPDATE `orders` SET total_price = sum_price
WHERE id = NEW.order_id;
END;

create table if not exists role
(
    id   int auto_increment
    primary key,
    name varchar(255) null
    );

create table if not exists user
(
    id         int auto_increment
    primary key,
    username   varchar(255) null,
    password   varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    email      varchar(255) null
    );

create table if not exists users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint FK_ROLE
    foreign key (role_id) references role (id),
    constraint FK_USER_05
    foreign key (user_id) references user (id)
    );

create index FK_ROLE_idx
    on users_roles (role_id);

create table if not exists favorite
(
    id         int auto_increment
    primary key,
    user_id    int not null,
    flower_id  int not null,
    constraint favorite_flower_id_fk
    foreign key (flower_id) references flower (id),
    constraint favorite_user_id_fk
    foreign key (user_id) references user (id)
    );

INSERT INTO shophoa.category (id, name) VALUES (1, 'Hoa sinh nhật');
INSERT INTO shophoa.category (id, name) VALUES (2, 'Hoa khai trương');
INSERT INTO shophoa.category (id, name) VALUES (3, 'Hoa chúc mừng');
INSERT INTO shophoa.category (id, name) VALUES (4, 'Hoa chia buồn');
INSERT INTO shophoa.category (id, name) VALUES (5, 'Hao tình yêu');

INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (1, 'Tình đầu thơ ngây', 'Sản phẩm bao gồm: Cẩm chướng chùm hồng viền: 3 Cúc calimero hồng: 3 Hoa Sao tím: 1 Pink OHara: 1', '1.jpg', 240000, 1);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (2, 'Romance 3', 'Sản phẩm bao gồm:
Hoa Sao tím: 1
Lá phụ khác: 7
Purple Ohara : 18', '2.jpg', 800000, 1);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (3, 'Chuyện yêu', 'Sản phẩm bao gồm:
Hoa baby : 1
Hồng đỏ sa : 20', '3.jpg', 600000, 1);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (4, 'Chuyện của nắng', 'Sản phẩm bao gồm:
Cát tường nhật hồng: 1
Cúc rossi trắng: 4
Free Spirit Rose (10): 6
Hoa baby : 1
Hồng trứng gà : 10', '4.jpg', 750000, 1);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (8, 'Party', 'Sản phẩm bao gồm:
Cúc calimero vàng nhụy nâu : 10
Hoa baby : 1
Hồng cam party: 15
Hồng trứng gà : 5', '5.jpg', 950000, 2);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (9, 'Kệ Chúc Mừng 07', 'Kệ Chúc Mừng 02 được thiết kế từ hoa đồng tiền cam và cúc lưới xanh kết hợp với tông màu đỏ truyền thống của hoa hồng môn. Thiết kế vươn cao, màu sắc rực rỡ chính là điểm nổi bật của mẫu hoa này. Đây sẽ là món quà thích hợp dành tặng cho bạn bè hay đối tác của bạn.
Sản phẩm bao gồm:
Hoa Cúc Lưới Xanh: 20
Môn đỏ: 10
Đồng tiền cam (20): 30', '6.jpg', 1000000, 2);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (10, 'Luxury vase 22', 'Sản phẩm bao gồm:
Cúc mẫu đơn trắng NK : 4
Cúc mẫu đơn vàng NK: 6
Hồ điệp cắt cành vàng: 1
Hoa Sao tím: 1
Đồng tiền trắng : 11', '7.jpg', 1500000, 2);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (11, 'Luxury vase 11', '
Sản phẩm bao gồm:
Cúc calimero trắng: 8
Hoa baby : 1
Hồng đỏ Ecuador DL: 8
Lá bạc : 1
Lá trúc nâu: 2
Lan Moka tím: 7
Pink OHara: 20', '8.jpg', 1600000, 2);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (12, 'Congrats', 'Sản phẩm bao gồm:
Cẩm chướng chùm đỏ : 3
Cẩm chướng đơn đỏ : 3
Hồng đỏ sa : 2
Hướng dương : 2
Lan Moka đỏ: 3
Red Elegance : 3', '9.jpg', 500000, 3);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (13, 'Điệu valse mùa xuân', 'Sản phẩm bao gồm:
Cẩm chướng đơn đỏ : 7
Cúc rossi hồng: 3
Free Spirit Rose: 6
Hoa Cúc Lưới Xanh: 3
Hồng vàng chùa : 3', '10.jpg', 550000, 3);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (14, 'Chuyện của nắng', 'Sản phẩm bao gồm:
Cát tường nhật hồng: 1
Cúc rossi trắng: 4
Free Spirit Rose (10): 6
Hoa baby : 1
Hồng trứng gà : 10', '11.jpg', 750000, 3);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (15, 'Ươm nắng', 'Sản phẩm bao gồm:
Cẩm chướng đơn vàng : 10
Hồng trắng cồ: 11
Hướng dương : 3
Lan vũ nữ: 3
Mõm sói vàng: 10', '12.jpg', 600000, 3);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (16, 'Thành Kính Phân Ưu 3', 'Thành Kính Phân Ưu 3 là một kệ hoa gồm có hoa hồng trắng, hoa cúc trắng, hoa lan thái trắng kết hợp với một số phụ kiện, tạo không khí trang trọng tôn nghiêm. Thích hợp để tặng chia buồn.', '13.jpg', 2650000, 4);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (17, 'Lời tạm biệt', 'Sinh ly tử biệt đều là những khoảnh khắc phải đi qua của một đời người. Khi một người rời bỏ thế gian này sẽ để lại vô vàn sự đau buồn và niềm thương tiếc trong lòng những người đang còn ở lại. Đến tiễn biệt người đã khuất, để thể hiện sự tôn kính và an ủi người thân của họ, nhiều người sẽ chọn gửi gắm những lời động viên chia sẻ của bản thân lên những kệ hoa viếng, giỏ hoa chia buồn.', '14.jpg', 650000, 4);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (18, 'Thành Kính Phân Ưu 1', 'Thành Kính Phân Ưu 1 gồm có hoa Cúc Trắng,, môn xanh.
Thích hợp để tặng chia buồn.
Sản phẩm bao gồm:
Cúc trắng : 25
Hoa mimi: 10
Môn xanh: 4', '15.jpg', 750000, 4);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (19, 'Kệ Chia Buồn 04', 'Sản phẩm bao gồm:
Cúc calimero trắng: 15
Hoa Cúc Lưới Vàng : 23
Lan thái trắng: 12', '16.jpg', 850000, 4);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (20, 'Cloud and you', 'Sản phẩm bao gồm:
Cúc calimero trắng : 10
Hoa baby : 1
Hồng trắng cồ: 15', '17.jpg', 800000, 5);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (21, 'It\'s you', 'Một ngày dạo trên phố, chợt thấy từng khóm hoa trong nắng đung đưa như mời gọi. Lấy ý tưởng từ những bông hoa nắng đó, bó hoa được thiết kế với tông màu cam đào sáng, tươi vui và nhẹ nhàng. Thích hợp tặng sinh nhật, chúc mừng, kỉ niệm, v.v…
                                                                                        Sản phẩm bao gồm:
                                                                                     Cúc calimero trắng: 10
                                                                                     Hồng trắng nhí: 12
                                                                                     Hồng trứng gà : 13', '18.jpg', 650000, 5);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (22, 'Gấm hoa', 'Sản phẩm bao gồm:
                                                                                     Cẩm chướng chùm hồng nhạt : 5
                                                                                     Cẩm chướng đơn hồng: 10
                                                                                     Cúc mai hồng: 5
                                                                                     Hoa Sao tím: 1
                                                                                     Hồng da: 10
                                                                                     Mõm sói song hỷ : 15
                                                                                     Pink OHara: 7', '19.jpg', 1200000, 5);
INSERT INTO shophoa.flower (id, name, detail, image_url, price, category_id) VALUES (23, 'Luxury vase 21', 'Sản phẩm bao gồm:Cẩm chướng đơn hồng: 10Cúc mẫu đơn hồng đậm NK : 2Cúc mẫu đơn hồng đào NK : 4Cúc mẫu đơn đỏ NK: 4Hồng song hỷ cồ : 10', '20.jpg', 1800000, 5);

INSERT INTO shophoa.role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO shophoa.role (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO shophoa.user (id, username, password, first_name, last_name, email) VALUES (1, 'john', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'John', 'Doe', 'john@luv2code.com');
INSERT INTO shophoa.user (id, username, password, first_name, last_name, email) VALUES (2, 'mary', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Mary', 'Smith', 'mary@luv2code.com');

INSERT INTO shophoa.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO shophoa.users_roles (user_id, role_id) VALUES (2, 2);



