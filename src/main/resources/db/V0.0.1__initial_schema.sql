CREATE TABLE "app_user" (
	"id" serial NOT NULL,
	"username" varchar(128) NOT NULL UNIQUE,
	"password" varchar(128) NOT NULL,
	"enabled" BOOLEAN NOT NULL,
	"credentials_non_expired" BOOLEAN NOT NULL,
	"account_non_expired" BOOLEAN NOT NULL,
	"account_non_locked" BOOLEAN NOT NULL,
	"requires_two_factor" BOOLEAN NOT NULL,
	"main_email" bigint NOT NULL UNIQUE,
	CONSTRAINT app_user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "authority" (
	"id" serial NOT NULL UNIQUE,
	"name" varchar(128) NOT NULL UNIQUE,
	CONSTRAINT authority_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "app_user_link_authority" (
	"id" serial NOT NULL,
	"authority_id" bigint NOT NULL,
	"app_user_id" bigint NOT NULL,
	CONSTRAINT app_user_link_authority_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "email" (
	"id" serial NOT NULL,
	"app_user_id" bigint NOT NULL UNIQUE,
	"name" varchar NOT NULL UNIQUE,
	CONSTRAINT email_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "refresh_token" (
	"id" serial NOT NULL,
	"app_user_id" bigint NOT NULL UNIQUE,
	"token" varchar(128) NOT NULL,
	"device_uuid" varchar(128) NOT NULL UNIQUE,
	"creation_date" DATE NOT NULL,
	CONSTRAINT refresh_token_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "check_list" (
	"id" serial NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT check_list_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "check_list_item" (
	"id" serial NOT NULL,
	"check_list_id" bigint NOT NULL UNIQUE,
	"name" varchar(128) NOT NULL,
	CONSTRAINT check_list_item_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "check_list_link_app_user" (
	"id" serial NOT NULL,
	"app_user_id" bigint NOT NULL,
	"check_list_id" bigint NOT NULL,
	CONSTRAINT check_list_link_app_user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "registration" (
	"uuid" varchar NOT NULL,
	"app_user_id" bigint NOT NULL UNIQUE,
	"registration_date" DATE NOT NULL,
	"activation_date" DATE NOT NULL,
	"active" BOOLEAN NOT NULL,
	CONSTRAINT registration_pk PRIMARY KEY ("uuid")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "app_user" ADD CONSTRAINT "app_user_fk0" FOREIGN KEY ("main_email") REFERENCES "email"("id");


ALTER TABLE "app_user_link_authority" ADD CONSTRAINT "app_user_link_authority_fk0" FOREIGN KEY ("authority_id") REFERENCES "authority"("id");
ALTER TABLE "app_user_link_authority" ADD CONSTRAINT "app_user_link_authority_fk1" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

ALTER TABLE "email" ADD CONSTRAINT "email_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

ALTER TABLE "refresh_token" ADD CONSTRAINT "refresh_token_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");


ALTER TABLE "check_list_item" ADD CONSTRAINT "check_list_item_fk0" FOREIGN KEY ("check_list_id") REFERENCES "check_list"("id");

ALTER TABLE "check_list_link_app_user" ADD CONSTRAINT "check_list_link_app_user_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");
ALTER TABLE "check_list_link_app_user" ADD CONSTRAINT "check_list_link_app_user_fk1" FOREIGN KEY ("check_list_id") REFERENCES "check_list"("id");

ALTER TABLE "registration" ADD CONSTRAINT "registration_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");
