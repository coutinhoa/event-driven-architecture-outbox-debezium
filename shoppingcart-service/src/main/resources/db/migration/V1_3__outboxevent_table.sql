CREATE TABLE outboxevent(
    id uuid primary key,
    aggregateType VARCHAR,
    aggregateId VARCHAR,
    type VARCHAR,
    payload VARCHAR,
    timestamp TIMESTAMP
);

