SET foreign_key_checks = 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent1 as fk_item ,
reagentCount1 as amount
FROM dbc.dbc0_spell
WHERE reagent1 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent2 as fk_item ,
reagentCount2 as amount
FROM dbc.dbc0_spell
WHERE reagent2 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent3 as fk_item ,
reagentCount3 as amount
FROM dbc.dbc0_spell
WHERE reagent3 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent4 as fk_item ,
reagentCount4 as amount
FROM dbc.dbc0_spell
WHERE reagent4 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent5 as fk_item ,
reagentCount5 as amount
FROM dbc.dbc0_spell
WHERE reagent5 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent6 as fk_item ,
reagentCount6 as amount
FROM dbc.dbc0_spell
WHERE reagent6 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent7 as fk_item ,
reagentCount7 as amount
FROM dbc.dbc0_spell
WHERE reagent7 <> 0;

INSERT INTO jangosworld.spellcomponents
SELECT 
id as fk_spell, 
reagent8 as fk_item ,
reagentCount8 as amount
FROM dbc.dbc0_spell
WHERE reagent8 <> 0;

DELETE sc FROM jangosworld.spellcomponents as sc
LEFT JOIN jangosworld.item as i
ON sc.fk_item = i.entry
WHERE i.entry IS NULL;

SET foreign_key_checks = 1;