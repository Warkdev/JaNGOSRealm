SET @CPT=0;

INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
NULL as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND equippedItemSubClass = 0;

SET @CPT=1;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=2;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=3;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=4;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=5;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=6;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=7;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=8;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=9;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=10;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=11;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=12;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=13;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=13;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=14;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=15;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=16;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=17;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=18;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=19;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=20;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;

SET @CPT=21;
INSERT INTO jangosworld.spellitemsubclass (fk_spell, fk_itemclass, fk_itemsubclass)
SELECT 
id as fk_spell,
equippedItemClass as fk_itemclass, 
(@CPT-1) as fk_itemsubclass 
FROM dbc.dbc0_spell
WHERE equippedItemClass <> 0 AND (equippedItemSubClass >> (@CPT-1) & b'1') = 1;