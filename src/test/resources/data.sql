TRUNCATE TABLE local_users;
INSERT INTO local_users ( name, surname, email, password, post_address) VALUES
    ( 'Hans', 'Müller', 'hans.mueller@example.de', 'password_1', 'Berliner Str. 55, 10115 Berlin, Germany'),
    ( 'Émilie', 'Dubois', 'emilieDubois@example.fr', 'password_2', '12 Rue de Rivoli, 75001 Paris, France'),
    ( 'Luca', 'Rossi', 'luca_rossi@example.it', 'password_3', 'Via Roma 1, 00100 Roma, Italy'),
    ( 'Carlos', 'García', 'carlos.garcia@example.es', 'password_4', 'Calle de Alcalá 45, 28014 Madrid, Spain');

-- TRUNCATE TABLE tickets;
-- INSERT INTO tickets (title, price, local_user_id) VALUES
--     ('Test title',100, 1),
--     ('Test title',90, 1),
--     ('Test title',140, 1),
--     ('Test title',150, 2),
--     ('Test title',80, 2),
--     ('Test title',130,3),
--     ('Test title',160, 4),
--     ('Test title',40, 4);