DROP TABLE IF EXISTS "user_account";
CREATE TABLE "user_account" (
    "id" uuid NOT NULL,
    "email" varchar(255) NOT NULL,
    "google_id" varchar,
    "name" varchar(255) NOT NULL,
    "password" varchar NOT NULL,
    CONSTRAINT "user_account_pkey" PRIMARY KEY ("id")
);