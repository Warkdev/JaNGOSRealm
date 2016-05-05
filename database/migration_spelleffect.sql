SET foreign_key_checks = 0;
INSERT INTO jangosworld.spellspelleffects
SELECT 
id as fk_spell, 
effect1 as fk_effect, 
1 as sequence,
NULLIF(effectAura1,0) as fk_spellaura, 
NULLIF(effectRadiusIndex1,0) as fk_spellradius,
NULLIF(implicitTargetA1,0) as fk_targetA, 
NULLIF(implicitTargetB1,0) as fk_targetB, 
NULLIF(effectMechanic1,0) as fk_spellmechanic, 
NULLIF(effectTriggerSpell1,0) as fk_triggeredspell, 
effectDiesides1 as dieSide, 
effectBasePoints1 as basePoint, 
effectBaseDice1 as baseDice, 
effectDicePerLevel1 as dicePerLevel, 
effectRealPointsPerLevel1 as realPointsPerLevel, 
effectPointsPerCombo1 as pointPerCombo,
effectAmplitude1 as amplitude, 
effectMultipleValue1 as multiple, 
effectChainTarget1 as chainTarget,
 -- effectItemType1 as , 
effectMiscValue1 as miscValue, 
damageMultiplier1 as multiplier 
FROM dbc.dbc0_spell
WHERE effect1 <> 0
UNION
SELECT 
id as fk_spell, 
effect2 as fk_effect, 
2 as sequence,
NULLIF(effectAura2,0) as fk_spellaura, 
NULLIF(effectRadiusIndex2,0) as fk_spellradius,
NULLIF(implicitTargetA2,0) as fk_targetA, 
NULLIF(implicitTargetB2,0) as fk_targetB, 
NULLIF(effectMechanic2,0) as fk_spellmechanic, 
NULLIF(effectTriggerSpell2,0) as fk_triggeredspell, 
effectDiesides2 as dieSide, 
effectBasePoints2 as basePoint, 
effectBaseDice2 as baseDice, 
effectDicePerLevel2 as dicePerLevel, 
effectRealPointsPerLevel2 as realPointsPerLevel, 
effectPointsPerCombo2 as pointPerCombo,
effectAmplitude2 as amplitude, 
effectMultipleValue2 as multiple, 
effectChainTarget2 as chainTarget,
 -- effectItemType2 as , 
effectMiscValue2 as miscValue, 
damageMultiplier2 as multiplier 
FROM dbc.dbc0_spell
WHERE effect2 <> 0
UNION
SELECT 
id as fk_spell, 
effect3 as fk_effect, 
3 as sequence,
NULLIF(effectAura3,0) as fk_spellaura, 
NULLIF(effectRadiusIndex3,0) as fk_spellradius,
NULLIF(implicitTargetA3,0) as fk_targetA, 
NULLIF(implicitTargetB3,0) as fk_targetB, 
NULLIF(effectMechanic3,0) as fk_spellmechanic, 
NULLIF(effectTriggerSpell3,0) as fk_triggeredspell, 
effectDiesides3 as dieSide, 
effectBasePoints3 as basePoint, 
effectBaseDice3 as baseDice, 
effectDicePerLevel3 as dicePerLevel, 
effectRealPointsPerLevel3 as realPointsPerLevel, 
effectPointsPerCombo3 as pointPerCombo,
effectAmplitude3 as amplitude, 
effectMultipleValue3 as multiple, 
effectChainTarget3 as chainTarget,
 -- effectItemType3 as , 
effectMiscValue3 as miscValue, 
damageMultiplier3 as multiplier 
FROM dbc.dbc0_spell
WHERE effect3 <> 0;

UPDATE jangosworld.spellspelleffects as sse
LEFT JOIN spells as t
ON t.spellID = sse.fk_triggeredspell
SET fk_triggeredspell = NULL
WHERE t.spellid IS NULL;
SET foreign_key_checks = 1;