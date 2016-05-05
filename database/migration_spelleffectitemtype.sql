INSERT INTO jangosworld.spelleffectitemtype
SELECT 
t1.id as fk_spell, 
t1.effect1 as fk_effect, 
1 as sequence, 
t2.id as fk_applyOnSpell
FROM dbc.dbc0_spell as t1
JOIN dbc.dbc0_spell as t2
ON t1.effectItemType1 & ((t2.spellClassMask2 << 32) + t2.spellClassMask1) AND t1.spellClassSet = t2.spellClassSet
WHERE t1.effectItemType1 <> 0 AND t1.effect1 <> 24
UNION
SELECT 
t1.id as fk_spell, 
t1.effect2 as fk_effect, 
2 as sequence, 
t2.id as fk_applyOnSpell
FROM dbc.dbc0_spell as t1
JOIN dbc.dbc0_spell as t2
ON t1.effectItemType2 & ((t2.spellClassMask2 << 32) + t2.spellClassMask1) AND t1.spellClassSet = t2.spellClassSet
WHERE t1.effectItemType2 <> 0 AND t1.effect1 <> 24
UNION
SELECT 
t1.id as fk_spell, 
t1.effect3 as fk_effect, 
3 as sequence, 
t2.id as fk_applyOnSpell
FROM dbc.dbc0_spell as t1
JOIN dbc.dbc0_spell as t2
ON t1.effectItemType3 & ((t2.spellClassMask2 << 32) + t2.spellClassMask1) AND t1.spellClassSet = t2.spellClassSet
WHERE t1.effectItemType3 <> 0 AND t1.effect1 <> 24;