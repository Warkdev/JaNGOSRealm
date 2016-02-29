UPDATE jangosworld.spells as s
JOIN dbc.dbc0_spell as spell 
ON s.spellid = spell.id

SET targetFlagUnused1 = (spell.targets & b'1'),
targetFlagUnit = (spell.targets >> 1 & b'1'),
targetFlagUnused2 = (spell.targets >> 2 & b'1'),
targetFlagUnused3 = (spell.targets >> 3 & b'1'),
targetFlagItem = (spell.targets >> 4 & b'1'),
targetFlagSourceLocation =  (spell.targets >> 5 & b'1'),
targetFlagDestLocation = (spell.targets >> 6 & b'1'),
targetFlagOjectUnknown = (spell.targets >> 7 & b'1'),
targetFlagUnitUnknown = (spell.targets >> 8 & b'1'),
targetFlagPvpCorpse = (spell.targets >> 9 & b'1'),
targetFlagUnitCorpse = (spell.targets >> 10 & b'1'),
targetFlagObject = (spell.targets >> 11 & b'1'),
targetFlagTradeItem = (spell.targets >> 12 & b'1'),
targetFlagString = (spell.targets >> 13 & b'1'),
targetFlagUnkown1 = (spell.targets >> 14 & b'1'),
targetFlagCorpse = (spell.targets >> 15 & b'1'),
targetFlagUnknown2 = (spell.targets >> 16 & b'1')
;