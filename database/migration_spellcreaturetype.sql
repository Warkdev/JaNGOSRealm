SET @CPT=1;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=2;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=3;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=4;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=5;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=6;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=7;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=8;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=9;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=10;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;

SET @CPT=11;
INSERT INTO jangosworld.spellcreaturetype
SELECT 
id as spellID,  
@CPT as fk_creaturetype
FROM dbc.dbc0_spell
WHERE (targetCreatureType >> (@CPT-1) & b'1') = 1;