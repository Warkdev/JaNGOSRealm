-- First, only direct damage spells are handled (no item).
SELECT temp.spellid, temp.name, temp.direct_bonus, temp.direct_portion, temp.dot_bonus, temp.over_time_portion FROM (
	SELECT spellid,
	name,
	(spellcasttime / 3500) as direct_bonus,
    1 as direct_portion,
	0 as dot_bonus,    
    0 as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		WHERE spellcasttime >= 1500 AND fk_spellFamily IS NOT NULL AND t2.fk_effect IN (2,8,9,7, 62,10) and t1.fk_damageType = 1
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13)
	UNION
	SELECT spellid,
	name,
	 (1500 / 3500) as direct_bonus,
	 1 as direct_portion,
     0 as dot_bonus,     
	0 as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		WHERE spellcasttime < 1500 AND fk_spellFamily IS NOT NULL AND t2.fk_effect IN (2,8,9,7, 62,10) and t1.fk_damageType = 1
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13)
UNION
	-- Then we add DoT/HoT spells
	SELECT spellid,
	 name,
	 0 as direct_bonus,
     0 as direct_portion,
	 ((t3.duration / 15000) / (t3.duration / t2.amplitude)) as dot_bonus,     
     1 as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		JOIN jangosworld.spellduration as t3
		ON t1.fk_spellduration = t3.id
		WHERE fk_spellFamily IS NOT NULL AND t2.fk_effect = 6 and t1.fk_damageType = 1 
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13) AND t2.fk_spellaura IN (3, 8) AND attrExChanneled = 0
	) as temp
-- Now we exclude all hybrid spells
WHERE spellID NOT IN (
	SELECT temp1.spellid as hybridspell
	FROM
		(SELECT spellid from spells as t1
		JOIN spellspelleffects as t2
		ON t1.spellid = t2.fk_spell
		WHERE t2.fk_spellaura IN (3,8,53) AND t1.fk_spellFamily IS NOT NULL and t1.fk_damageType = 1 
        AND (t2.fk_targetA NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t2.fk_targetA IS NULL)
        AND (t2.fk_targetB NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t2.fk_targetB IS NULL)) as temp1
	JOIN
		(SELECT spellid from spells as t3
		JOIN spellspelleffects as t4
		ON t3.spellid = t4.fk_spell
		WHERE t4.fk_effect IN (2,8,9,7, 62,10) AND t3.fk_spellFamily IS NOT NULL and t3.fk_damageType = 1 
        AND (t4.fk_targetA NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t4.fk_targetA IS NULL)
        AND (t4.fk_targetB NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t4.fk_targetB IS NULL)) as temp2
		ON temp1.spellid = temp2.spellID)

-- But we need hybrid spells with an updated formula (excluding area spells) :-)
UNION
SELECT temp2.spellid, temp2.name, temp2.direct_bonus, temp2.direct_portion, temp2.dot_bonus, temp2.over_time_portion FROM (
	-- We only need DoT/HoT spells since they are part of hybrid spells for sure.
	SELECT spellid,
	 name,
	 (spellcasttime / 3500) * (1 - ((t3.duration / 15000) / ((t3.duration/15000) + (spellcasttime/3500)))) as direct_bonus,
     1 - ((t3.duration / 15000) / ((t3.duration/15000) + (spellcasttime/3500))) as direct_portion,                
        ((t3.duration / 15000) / (t3.duration / t2.amplitude)) as dot_bonus,        
		((t3.duration / 15000) / ((t3.duration/15000) + (spellcasttime/3500))) as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		JOIN jangosworld.spellduration as t3
		ON t1.fk_spellduration = t3.id
		WHERE fk_spellFamily IS NOT NULL AND t2.fk_effect = 6 and t1.fk_damageType = 1  
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13) AND t2.fk_spellaura IN (3, 8) AND spellcasttime >= 1500 AND attrExChanneled = 0
    UNION
    -- DoT/HoT with instant casts must be considered as well
    	SELECT spellid,
	 name,
	 (1500 / 3500) * (1 - ((t3.duration / 15000) / ((t3.duration/15000) + (1500/3500)))) as direct_bonus,
     1 - ((t3.duration / 15000) / ((t3.duration/15000) + (1500/3500))) as direct_portion,                
        ((t3.duration / 15000) / (t3.duration / t2.amplitude)) as dot_bonus,        
		((t3.duration / 15000) / ((t3.duration/15000) + (1500/3500))) as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		JOIN jangosworld.spellduration as t3
		ON t1.fk_spellduration = t3.id
		WHERE fk_spellFamily IS NOT NULL AND t2.fk_effect = 6 and t1.fk_damageType = 1 
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13) AND t2.fk_spellaura IN (3, 8, 53) AND spellcasttime < 1500 AND attrExChanneled = 0
	) as temp2
WHERE spellID IN (
	SELECT temp1.spellid as hybridspell
	FROM
		(SELECT spellid from spells as t1
		JOIN spellspelleffects as t2
		ON t1.spellid = t2.fk_spell
		WHERE t2.fk_spellaura IN (3,8,53) AND t1.fk_spellFamily IS NOT NULL and t1.fk_damageType = 1 
        AND (t2.fk_targetA NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t2.fk_targetA IS NULL)
        AND (t2.fk_targetB NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t2.fk_targetB IS NULL)) as temp1
	JOIN
		(SELECT spellid from spells as t3
		JOIN spellspelleffects as t4
		ON t3.spellid = t4.fk_spell
		WHERE t4.fk_effect IN (2,8,9,7, 62,10) AND t3.fk_spellFamily IS NOT NULL and t3.fk_damageType = 1 
        AND (t4.fk_targetA NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t4.fk_targetA IS NULL)
        AND (t4.fk_targetB NOT IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR t4.fk_targetB IS NULL)) as temp2
		ON temp1.spellid = temp2.spellID)
-- We add channeled spells
UNION
SELECT temp2.spellid, temp2.name, temp2.direct_bonus, temp2.direct_portion, temp2.dot_bonus, temp2.over_time_portion FROM (	
	SELECT spellid,
	 name,
	 0 as direct_bonus,
     0 as direct_portion,                
	((t3.duration / 3500) / (t3.duration / t2.amplitude)) as dot_bonus,    
     1 as over_time_portion
	FROM jangosworld.spells as t1
		JOIN jangosworld.spellspelleffects as t2
		ON t1.spellID = t2.fk_spell
		JOIN jangosworld.spellduration as t3
		ON t1.fk_spellduration = t3.id
		WHERE fk_spellFamily IS NOT NULL AND t2.fk_effect = 6 and t1.fk_damageType = 1  
		AND t1.fk_spellFamily NOT IN (4, 8, 9, 13) AND t2.fk_spellaura IN (3) AND attrExChanneled = 1
	) as temp2
-- We add AoE spells (TODO)
UNION
	SELECT temp1.spellid as aoespell
	FROM
		(SELECT spellid from spells as t1
		JOIN spellspelleffects as t2
		ON t1.spellid = t2.fk_spell
		WHERE t1.fk_spellFamily IS NOT NULL and t1.fk_damageType = 1 
        AND (t2.fk_targetA IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR 
        t2.fk_targetB IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61))) as temp1
	JOIN
		(SELECT spellid from spells as t3
		JOIN spellspelleffects as t4
		ON t3.spellid = t4.fk_spell
		WHERE t3.fk_spellFamily IS NOT NULL and t3.fk_damageType = 1 
        AND (t4.fk_targetA IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61) OR 
        t4.fk_targetB IN (7,8,15,16,20,24,28,30,31,33,34,37,52,56,61))) as temp2
		ON temp1.spellid = temp2.spellID
;