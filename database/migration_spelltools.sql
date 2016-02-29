SET foreign_key_checks = 0;

INSERT INTO jangosworld.spelltools
SELECT 
id as fk_spell, 
totem as fk_item FROM dbc.dbc0_spell
WHERE totem <> 0;

INSERT INTO jangosworld.spelltools
SELECT 
id as fk_spell, 
totem2 as fk_item FROM dbc.dbc0_spell
WHERE totem2 <> 0;

DELETE t FROM jangosworld.spelltools as t
LEFT JOIN jangosworld.item as i
ON t.fk_item = i.entry
WHERE i.entry IS NULL;

SET foreign_key_checks = 1;