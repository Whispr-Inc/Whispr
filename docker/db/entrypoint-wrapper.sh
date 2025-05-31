#!/bin/bash

# Start the original postgres entrypoint in the background
docker-entrypoint.sh postgres &

# Wait for Postgres to be ready
until pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB"; do
  echo "Waiting for postgres..."
  sleep 1
done

# Run your schema creation script every start (idempotent)
echo "Running schema creation script..."
psql -v ON_ERROR_STOP=1 -U "$POSTGRES_USER" -d "$POSTGRES_DB" -f /init-schema.d/init-schemas.sql

# Wait for the original entrypoint process to exit
wait
