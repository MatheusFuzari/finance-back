DROP TABLE IF EXISTS "user_account";
CREATE TABLE "user_account" (
    "id" uuid NOT NULL,
    "email" character varying(255) NOT NULL,
    "google_id" character varying(255),
    "name" character varying(255) NOT NULL,
    "password" character varying(255) NOT NULL,
    CONSTRAINT "user_account_pkey" PRIMARY KEY ("id")
);