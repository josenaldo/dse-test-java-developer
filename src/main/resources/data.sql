-- Vendedores
INSERT INTO `sales_person` (id, name, age, salary)
VALUES ('3f0a3a7e-5e2b-4c1d-8f3a-2b7c9f1a0d11', 'Abe', 61, 140000.00),
       ('5c2d9b1e-8fa4-4b3c-92a7-1e6f0d2c4b22', 'Bob', 34, 44000.00),
       ('7e1f4a2b-3c5d-4e6f-8a9b-0c1d2e3f4a33', 'Chris', 34, 40000.00),
       ('9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c44', 'Dan', 41, 52000.00),
       ('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c55', 'Ken', 57, 115000.00);

-- Clientes
INSERT INTO `customers` (id, name, email, username, city, industry_type)
VALUES ('11111111-2222-3333-4444-555555555555', 'Samsonic', 'contact@samsonic.example', 'samsonic',
        'Pleasant', 'J'),
       ('22222222-3333-4444-5555-666666666666', 'Panasung', 'info@panasung.example', 'panasung',
        'Oaktown', 'J'),
       ('33333333-4444-5555-6666-777777777777', 'Samony', 'hello@samony.example', 'samony',
        'Jackson', 'B'),
       ('44444444-5555-6666-7777-888888888888', 'Orange', 'hi@orange.example', 'orange', 'Jackson',
        'B'),
       ('55555555-6666-7777-8888-999999999999', 'Globex', 'sales@globex.example', 'globex',
        'Spring', 'J');

-- Pedidos (note: tabela `crders` conforme seu DDL)
INSERT INTO `orders` (id, order_date, customer_id, sales_person_id, amount)
VALUES ('0a1b2c3d-4e5f-6789-abcd-ef0123456789', '1996-08-02',
        '11111111-2222-3333-4444-555555555555', '5c2d9b1e-8fa4-4b3c-92a7-1e6f0d2c4b22', 540.00),
       ('1b2c3d4e-5f67-489a-bcde-f0123456789a', '1999-01-30',
        '11111111-2222-3333-4444-555555555555', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c55', 1800.00),
       ('2c3d4e5f-6789-4abc-bcde-f0123456789b', '1995-07-14',
        '44444444-5555-6666-7777-888888888888', '3f0a3a7e-5e2b-4c1d-8f3a-2b7c9f1a0d11', 460.00),
       ('3d4e5f67-89ab-4cde-bcde-f0123456789c', '1998-01-29',
        '33333333-4444-5555-6666-777777777777', '5c2d9b1e-8fa4-4b3c-92a7-1e6f0d2c4b22', 2400.00),
       ('4e5f6789-abcd-4def-bcde-f0123456789d', '1998-02-03',
        '22222222-3333-4444-5555-666666666666', '9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c44', 600.00);