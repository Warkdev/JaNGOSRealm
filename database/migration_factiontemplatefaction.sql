INSERT INTO factiontemplatefaction
-- First, the enemies.
SELECT 
	id,
    enemyfaction1Id as fk_faction,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE enemyfaction1Id <> 0
 UNION
 SELECT 
	id,
    enemyfaction2Id as fk_faction,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE enemyfaction2Id <> 0
 UNION
 SELECT 
	id,
    enemyfaction3Id as fk_faction,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE enemyfaction3Id <> 0
 UNION
 SELECT 
	id,
    enemyfaction4Id as fk_faction,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE enemyfaction4Id <> 0
 -- Then, the friendly
 UNION
 SELECT 
	id,
    friendfaction1Id as fk_faction,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE friendfaction1Id <> 0
 UNION
 SELECT 
	id,
    friendfaction2Id as fk_faction,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE friendfaction2Id <> 0
 UNION
 SELECT 
	id,
    friendfaction3Id as fk_faction,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE friendfaction3Id <> 0
 UNION
 SELECT 
	id,
    friendfaction4Id as fk_faction,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE friendfaction4Id <> 0
 ;