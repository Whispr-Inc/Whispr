-- Create all schemas needed for the application

DO
$$
DECLARE
    schema_list text[] := array[
        'auth_schema',
        'identity_schema'
    ];
    schema text;
BEGIN
    FOREACH schema IN ARRAY schema_list
    LOOP
        EXECUTE format('CREATE SCHEMA IF NOT EXISTS %I', schema);
    END LOOP;
END;
$$ language 'plpgsql';
