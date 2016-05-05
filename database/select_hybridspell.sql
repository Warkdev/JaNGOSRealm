SELECT temp1.spellid as hybridspell
FROM
(SELECT spellid from spells as t1
JOIN spellspelleffects as t2
ON t1.spellid = t2.fk_spell
WHERE t2.fk_spellaura IN (3,8,53) AND t1.fk_spellFamily IS NOT NULL and t1.fk_damageType = 1) as temp1
JOIN
(SELECT spellid from spells as t3
JOIN spellspelleffects as t4
ON t3.spellid = t4.fk_spell
WHERE t4.fk_effect IN (2,8,9,7, 62,10) AND t3.fk_spellFamily IS NOT NULL and t3.fk_damageType = 1) as temp2
ON temp1.spellid = temp2.spellID;