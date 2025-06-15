-- Whispr Database - Postgres 14
-- This file contains the SQL commands to create the Whispr database schema.

-- Reset the database to a clean state

DO
$$
    DECLARE
        schema_name text := current_schema();
    BEGIN
        EXECUTE format('DROP SCHEMA IF EXISTS %I CASCADE', schema_name);
        EXECUTE format('CREATE SCHEMA %I', schema_name);
    END;
$$ language 'plpgsql';


-- --------------------------------------------------------------------------
-- Chat Service Schema
-- --------------------------------------------------------------------------

CREATE TABLE lu_chat_type (
    id smallint NOT NULL,
    name varchar(128),
    CONSTRAINT lu_chat_type_pk PRIMARY KEY (id)
);

INSERT INTO
    lu_chat_type (id, name)
VALUES
    (1, 'PRIVATE'),
    (2, 'GROUP'),
    (3, 'CHANNEL'),
    (4, 'SYSTEM');


CREATE TABLE lu_message_type (
    id smallint NOT NULL,
    name varchar(128),
    CONSTRAINT lu_message_type_pk PRIMARY KEY (id)
);

INSERT INTO
    lu_message_type (id, name)
VALUES
    (1, 'TEXT'),
    (2, 'IMAGE'),
    (3, 'VIDEO'),
    (4, 'AUDIO'),
    (5, 'FILE'),
    (6, 'STICKER'),
    (7, 'LOCATION'),
    (8, 'CONTACT'),
    (9, 'POLL'),
    (10, 'SYSTEM');


CREATE TABLE chats (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    type smallint NOT NULL,
    title varchar(128),
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    created_by uuid NOT NULL,
    deleted_at timestamp with time zone,
    CONSTRAINT chat_pk PRIMARY KEY (id),
    CONSTRAINT chat_type_fk FOREIGN KEY (type) REFERENCES lu_chat_type (id)
);

CREATE TABLE members (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    chat_id uuid NOT NULL,
    account_id uuid NOT NULL,
    is_admin boolean NOT NULL DEFAULT false,
    is_muted boolean NOT NULL DEFAULT false,
    joined_at timestamp with time zone NOT NULL DEFAULT now(),
    left_at timestamp with time zone,
    CONSTRAINT member_pk PRIMARY KEY (id),
    CONSTRAINT member_chat_fk FOREIGN KEY (chat_id) REFERENCES chats (id)
);

CREATE UNIQUE INDEX member_unique_idx ON members (chat_id, account_id);
CREATE INDEX member_chat_idx ON members (chat_id);


CREATE TABLE messages (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    chat_id uuid NOT NULL,
    sender_id uuid NOT NULL,
    content text NOT NULL,
    type smallint,
    thumbnail_url varchar(256),
    media_url varchar(256),
    sent_at timestamp with time zone NOT NULL DEFAULT now(),
    edited_at timestamp with time zone,
    deleted_at timestamp with time zone,
    CONSTRAINT message_pk PRIMARY KEY (id),
    CONSTRAINT message_chat_fk FOREIGN KEY (chat_id) REFERENCES chats (id),
    CONSTRAINT message_type_fk FOREIGN KEY (type) REFERENCES lu_message_type (id),
    CONSTRAINT message_sender_fk FOREIGN KEY (sender_id) REFERENCES members (id)
);

CREATE INDEX message_chat_idx ON messages (chat_id);


CREATE TABLE message_receipts (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    message_id uuid NOT NULL,
    receiver_id uuid NOT NULL,
    read_at timestamp with time zone,
    delivered_at timestamp with time zone,
    CONSTRAINT message_receipt_pk PRIMARY KEY (id),
    CONSTRAINT message_receipt_message_fk FOREIGN KEY (message_id) REFERENCES messages (id) ON DELETE CASCADE,
    CONSTRAINT message_receipt_receiver_fk FOREIGN KEY (receiver_id) REFERENCES members (id)
);

CREATE UNIQUE INDEX message_receipt_unique_idx ON message_receipts (message_id, receiver_id);
