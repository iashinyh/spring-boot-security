CREATE TABLE user (
	id bigint primary key auto_increment,
	username text not null,
	password text not null,
	version bigint not null default 0,
	enabled char(1) not null default 'Y',
	create_at timestamp,
	update_at timestamp
);
INSERT INTO user VALUES (1, "user", "user", 0, 'Y', now(), now());
INSERT INTO user VALUES (2, "admin", "admin", 0, 'Y', now(), now());

CREATE TABLE authority (
	id bigint primary key auto_increment,
	username text not null,
	authority text not null,
	version bigint not null default 0,
	create_at timestamp,
	update_at timestamp
);
INSERT INTO authority VALUES (1, "user", "USER", 0, now(), now());
INSERT INTO authority VALUES (2, "admin", "ADMIN", 0, now(), now());