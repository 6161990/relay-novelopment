CREATE TABLE IF NOT EXISTS relay_novel
(
    id                   VARCHAR(255) PRIMARY KEY,
    opening           jsonb,
    is_closed   boolean NOT NULL,
    novels          jsonb,
    created_at          TIMESTAMPTZ,
    version             BIGINT
    );

create unique index relay_novel_id_index
    on "relay_novel" (id);

