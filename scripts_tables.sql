DROP TABLE IF EXISTS client CASCADE;
CREATE TABLE client (
    client_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    marital_status VARCHAR(255),
    dependent_amount int,
    passport_id JSONB,
    employment_id JSONB,
    account_number VARCHAR(255)
);

DROP TABLE IF EXISTS credit CASCADE;
CREATE TABLE credit (
    credit_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount DECIMAL,
    term int,
    monthly_payment DECIMAL,
    rate DECIMAL,
    psk DECIMAL,
    insurance_enabled BOOLEAN,
    salary_client BOOLEAN,
    payment_schedule JSONB,
    credit_status VARCHAR(255)
);

DROP TABLE IF EXISTS statement CASCADE;
CREATE TABLE statement (
    statement_id UUID PRIMARY KEY,
    credit_id UUID REFERENCES credit(credit_id),
    client_id UUID REFERENCES client(client_id),
    status VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    applied_offer JSONB,
    sign_date TIMESTAMP,
    ses_code DECIMAL,
    status_history JSONB
);