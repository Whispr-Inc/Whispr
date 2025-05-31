# Dockerfile for PostgreSQL with custom initialization scripts
FROM postgres:latest

# Copy custom initialization scripts into the container
COPY init-schemas.sql /init-schema.d/init-schemas.sql
COPY entrypoint-wrapper.sh /usr/local/bin/entrypoint-wrapper.sh
RUN chmod +x /usr/local/bin/entrypoint-wrapper.sh

# Override the default entrypoint to use the custom wrapper script
ENTRYPOINT ["/usr/local/bin/entrypoint-wrapper.sh"]
CMD ["postgres"]
