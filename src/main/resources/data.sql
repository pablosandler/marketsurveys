INSERT INTO subject(id, code, name) VALUES (1, 'ASDQ', 'Asad asd asd');

INSERT INTO provider(id, name) VALUES (1, 'Provider name');

-- DELETE FROM client WHERE "id"="1";
INSERT INTO client (id, name) VALUES (1, 'Client name');

INSERT INTO market_survey (id, age_from, age_to, country, creation_date, description, income_currency, income_from, income_to, sex, provider_id, subject_id) VALUES (1, 10, 20, 'ES', '2017-12-12', 'Description', 'EUR', 10000, 12000, 'MALE', 1, 1);


