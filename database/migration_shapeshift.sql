INSERT INTO jangosworld.shapeshift
(
SELECT ID as id,
	name as shapeshift,
    bonusActionBar as actionBar,
    (creatureType * 7) as fk_creaturetype,
    (flags & b'1') as allowedMount,
    (flags >> 1 & b'1') as unshapeOnCast,
    (flags >> 2 & b'1') as warrior,
    (flags >> 3 & b'1') as priest,
    (flags >> 4 & b'1') as unknown3,
    (flags >> 5 & b'1') as rogue,    
    (flags >> 6 & b'1') as unknown5
    
    
 FROM dbc.dbc0_spellshapeshiftform);