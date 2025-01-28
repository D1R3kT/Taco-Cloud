DELETE FROM ingredient_ref;
DELETE FROM taco;
DELETE FROM taco_order;
DELETE FROM ingredient;

INSERT INTO ingredient(id, name, type) VALUES ('GRBF', 'GROUND BEEF', 'PROTEIN');
INSERT INTO ingredient(id, name, type) VALUES ('CARN', 'CARNITAS', 'PROTEIN');
INSERT INTO ingredient(id, name, type) VALUES ('TMTO', 'DICED TOMATOES', 'VEGGIES');
INSERT INTO ingredient(id, name, type) VALUES ('LETC', 'LETTUCE', 'VEGGIES');
INSERT INTO ingredient(id, name, type) VALUES ('CHED', 'CHEDDAR', 'CHEESE');
INSERT INTO ingredient(id, name, type) VALUES ('JACK', 'MONTERREY JACK', 'CHEESE');
INSERT INTO ingredient(id, name, type) VALUES ('SLSA', 'SALSA', 'SAUCE');
INSERT INTO ingredient(id, name, type) VALUES ('SRCR', 'SOUR CREAM', 'SAUCE');

