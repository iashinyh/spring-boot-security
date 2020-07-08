CREATE TABLE user (
	id bigint primary key auto_increment,
	username VARCHAR(200) not null,
	password VARCHAR(200) not null,
	version bigint not null default 0,
	enabled char(1) not null default 'Y',
	create_at timestamp,
	update_at timestamp
);
INSERT INTO user VALUES (1, "user", "user", 0, 'Y', now(), now());
INSERT INTO user VALUES (2, "admin", "admin", 0, 'Y', now(), now());

CREATE TABLE role (
	id bigint primary key auto_increment,
	username VARCHAR(200) not null,
	role VARCHAR(200) not null,
	version bigint not null default 0,
	create_at timestamp,
	update_at timestamp
);
INSERT INTO role VALUES (1, "user", "USER", 0, now(), now());
INSERT INTO role VALUES (2, "admin", "ADMIN", 0, now(), now());