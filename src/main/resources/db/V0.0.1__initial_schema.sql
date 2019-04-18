create TABLE "app_user" (
	"id" char(36) NOT NULL,
	"username" varchar(128) NOT NULL UNIQUE,
	"password" varchar(256) NOT NULL,
	"enabled" BOOLEAN NOT NULL,
	"credentials_non_expired" BOOLEAN NOT NULL,
	"account_non_expired" BOOLEAN NOT NULL,
	"account_non_locked" BOOLEAN NOT NULL,
	"requires_two_factor" BOOLEAN NOT NULL,
	"main_email" bigint NOT NULL UNIQUE,
	CONSTRAINT app_user_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "authority" (
	"id" char(36) NOT NULL UNIQUE,
	"name" varchar(128) NOT NULL UNIQUE,
	CONSTRAINT authority_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "app_user_link_authority" (
	"id" char(36) NOT NULL,
	"authority_id" char(36) NOT NULL UNIQUE,
	"app_user_id" char(36) NOT NULL UNIQUE,
	CONSTRAINT app_user_link_authority_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "email" (
	"id" char(36) NOT NULL,
	"app_user_id" char(36) NOT NULL UNIQUE,
	"name" varchar(128) NOT NULL UNIQUE,
	CONSTRAINT email_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "refresh_token" (
	"id" char(36) NOT NULL,
	"app_user_id" char(36) NOT NULL UNIQUE,
	"token" char(36) NOT NULL,
	"device_uuid" char(36) NOT NULL UNIQUE,
	"creation_date" DATE NOT NULL,
	CONSTRAINT refresh_token_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "check_list" (
	"id" char(36) NOT NULL,
	"app_user_id" char(36) NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT check_list_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "check_list_item" (
	"id" char(36) NOT NULL,
	"check_list_id" char(36) NOT NULL UNIQUE,
	"name" varchar(128) NOT NULL,
	CONSTRAINT check_list_item_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "check_list_link_app_user" (
	"id" char(36) NOT NULL,
	"app_user_id" char(36) NOT NULL UNIQUE,
	"check_list_id" char(36) NOT NULL UNIQUE,
	CONSTRAINT check_list_link_app_user_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



create TABLE "registration" (
	"uuid" char(36) NOT NULL,
	"app_user_id" char(36) NOT NULL UNIQUE,
	"registration_date" DATE NOT NULL,
	"activation_date" DATE NOT NULL,
	"active" BOOLEAN NOT NULL,
	CONSTRAINT registration_pk PRIMARY KEY ("uuid")
) with (
  OIDS=FALSE
);



create TABLE "client" (
	"id" char(36) NOT NULL,
	"name" char(36) NOT NULL,
	"password" varchar(256) NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY ("id")
) with (
  OIDS=FALSE
);



alter table "app_user" add CONSTRAINT "app_user_fk0" FOREIGN KEY ("main_email") REFERENCES "email"("id");


alter table "app_user_link_authority" add CONSTRAINT "app_user_link_authority_fk0" FOREIGN KEY ("authority_id") REFERENCES "authority"("id");
alter table "app_user_link_authority" add CONSTRAINT "app_user_link_authority_fk1" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

alter table "email" add CONSTRAINT "email_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

alter table "refresh_token" add CONSTRAINT "refresh_token_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

alter table "check_list" add CONSTRAINT "check_list_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

alter table "check_list_item" add CONSTRAINT "check_list_item_fk0" FOREIGN KEY ("check_list_id") REFERENCES "check_list"("id");

alter table "check_list_link_app_user" add CONSTRAINT "check_list_link_app_user_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");
alter table "check_list_link_app_user" add CONSTRAINT "check_list_link_app_user_fk1" FOREIGN KEY ("check_list_id") REFERENCES "check_list"("id");

alter table "registration" add CONSTRAINT "registration_fk0" FOREIGN KEY ("app_user_id") REFERENCES "app_user"("id");

