CREATE TABLE IF NOT EXISTS relay_novel
(
    novel_board_id                   VARCHAR(255) PRIMARY KEY,
    opening           jsonb,
    genre VARCHAR(100),
    is_closed   boolean NOT NULL,
    articles          jsonb,
    created_at          TIMESTAMPTZ,
    deleted_at          TIMESTAMPTZ,
    version             BIGINT
    );

create unique index relay_novel_id_index
    on "relay_novel" (novel_board_id);

