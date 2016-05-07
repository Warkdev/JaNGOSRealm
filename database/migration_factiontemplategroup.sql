INSERT INTO factiontemplategroup
-- First we migration the main faction group.
-- Main faction is player
SELECT 
	1 as fk_factiongroup,
	id as fk_factiontemplate,
	true as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (factiongroup & b'1') = 1
UNION
-- Main faction is alliance
SELECT 
	2 as fk_factiongroup,
	id as fk_factiontemplate,
	true as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (factiongroup >> 1 & b'1') = 1
-- Main faction is horde 
UNION
 SELECT 
	3 as fk_factiongroup,
	id as fk_factiontemplate,
	true as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (factiongroup >> 2 & b'1') = 1
 UNION
 -- Main faction is creature
 SELECT 
	4 as fk_factiongroup,
	id as fk_factiontemplate,
	true as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (factiongroup >> 3 & b'1') = 1
 -- Then we add friendly factions
UNION
SELECT 
	1 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (friendgroup & b'1') = 1
UNION
-- Friend faction is alliance
SELECT 
	2 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (friendgroup >> 1 & b'1') = 1
-- Friend faction is horde 
UNION
 SELECT 
	3 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (friendgroup >> 2 & b'1') = 1
 UNION
 -- Friend faction is creature
 SELECT 
	4 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    false as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (friendgroup >> 3 & b'1') = 1
  -- Then we add enemy factions
UNION
SELECT 
	1 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (enemygroup & b'1') = 1
UNION
-- Enemy faction is alliance
SELECT 
	2 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (enemygroup >> 1 & b'1') = 1
-- Enemy faction is horde 
UNION
 SELECT 
	3 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (enemygroup >> 2 & b'1') = 1
 UNION
 -- Enemy faction is creature
 SELECT 
	4 as fk_factiongroup,
	id as fk_factiontemplate,
	false as main,
    true as enemy
 FROM dbc.dbc0_factiontemplate
 WHERE (enemygroup >> 3 & b'1') = 1
 ;