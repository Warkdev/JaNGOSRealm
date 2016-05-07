INSERT INTO startingfaction
-- Split per race & class.
-- Human Warrior
SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    2 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 -- Human Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Human priest
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    5 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 -- Human mage
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    8 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 -- Human warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask1 & b'1') as fk_race,
    9 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION

 -- Human Warrior
SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    2 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 -- Human Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Human priest
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    5 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 -- Human mage
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    8 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 -- Human warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask2 & b'1') as fk_race,
    9 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 & b'1' = 1) AND reputationClassMask2 = 0
 
UNION

-- Human Warrior
SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    2 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0
 -- Human Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Human priest
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0
 -- Human mage
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    8 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0
 -- Human warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask3 & b'1') as fk_race,
    9 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 & b'1' = 1) AND reputationClassMask3 = 0

UNION

-- Human Warrior
SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    2 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 -- Human Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Human priest
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 -- Human mage
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    8 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 -- Human warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    (reputationRaceMask4 & b'1') as fk_race,
    9 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Orc Warrior
SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Orc hunter
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    3 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 1 & b'1' = 1) AND reputationClassMask1 = 0
 -- Orc Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Orc Shaman
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    7 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 1 & b'1' = 1) AND reputationClassMask1 = 0
 -- Orc warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    9 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 1 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Orc Warrior
SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 1 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Orc hunter
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    3 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 1 & b'1' = 1) AND reputationClassMask2 = 0
 -- Orc Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 1 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Orc Shaman
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    7 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 1 & b'1' = 1) AND reputationClassMask2 = 0
 -- Orc warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    9 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 1 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Orc Warrior
SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 1 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Orc hunter
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    3 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 1 & b'1' = 1) AND reputationClassMask3 = 0
 -- Orc Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 1 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Orc Shaman
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    7 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 1 & b'1' = 1) AND reputationClassMask3 = 0
 -- Orc warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    9 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 1 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Orc Warrior
SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 1 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Orc hunter
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    3 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 1 & b'1' = 1) AND reputationClassMask4 = 0
 -- Orc Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 1 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Orc Shaman
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    7 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 1 & b'1' = 1) AND reputationClassMask4 = 0
 -- Orc warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    2 as fk_race,
    9 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 1 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Dwarf Warrior
SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 2 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Dwarf Paladin
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    2 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 2 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Dwarf hunter
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    3 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 2 & b'1' = 1) AND reputationClassMask1 = 0
 -- Dwarf Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 2 & b'1' = 1) AND reputationClassMask1 = 0
 -- Dwarf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    5 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 2 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Dwarf Warrior
SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 2 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Dwarf paladin
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    2 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 2 & b'1' = 1) AND reputationClassMask2 = 0
 -- Dwarf hunter
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    3 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 2 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Dwarf rogue
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 2 & b'1' = 1) AND reputationClassMask2 = 0
 -- Dwarf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    5 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 2 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Dwarf Warrior
SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 2 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Dwarf paladin
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    2 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 2 & b'1' = 1) AND reputationClassMask3 = 0
 -- Dwarf hunter
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    3 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 2 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Dwarf Rogue
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 2 & b'1' = 1) AND reputationClassMask3 = 0
 -- Dwarf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 2 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Dwarf Warrior
SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 2 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Dwarf paladin
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    2 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 2 & b'1' = 1) AND reputationClassMask4 = 0
 -- Dwarf hunter
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    3 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 2 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Dwarf Rogue
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 2 & b'1' = 1) AND reputationClassMask4 = 0
 -- Dwarf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    3 as fk_race,
    5 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 2 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Nightelft Warrior
SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 3 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Nightelf hunter
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    3 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 3 & b'1' = 1) AND reputationClassMask1 = 0
 -- Nightelf Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 3 & b'1' = 1) AND reputationClassMask1 = 0
 -- Nightelf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    5 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 3 & b'1' = 1) AND reputationClassMask1 = 0
  -- Nightelf druid
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    11 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 3 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Nightelft Warrior
SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 3 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Nightelf hunter
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    3 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 3 & b'1' = 1) AND reputationClassMask2 = 0
 -- Nightelf Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 3 & b'1' = 1) AND reputationClassMask2 = 0
 -- Nightelf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    5 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 3 & b'1' = 1) AND reputationClassMask2 = 0
  -- Nightelf druid
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    11 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 3 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Nightelft Warrior
SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 3 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Nightelf hunter
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    3 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 3 & b'1' = 1) AND reputationClassMask3 = 0
 -- Nightelf Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 3 & b'1' = 1) AND reputationClassMask3 = 0
 -- Nightelf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 3 & b'1' = 1) AND reputationClassMask3 = 0
  -- Nightelf druid
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    11 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 3 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Nightelft Warrior
SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 3 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Nightelf hunter
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    3 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 3 & b'1' = 1) AND reputationClassMask4 = 0
 -- Nightelf Rogue
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 3 & b'1' = 1) AND reputationClassMask4 = 0
 -- Nightelf priest
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    5 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 3 & b'1' = 1) AND reputationClassMask4 = 0
  -- Nightelf druid
 UNION
 SELECT 
	ID as fk_faction,
    
    4 as fk_race,
    11 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 3 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Undead Warrior
SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 4 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Undead rogue
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 4 & b'1' = 1) AND reputationClassMask1 = 0
 -- Undead priest
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    5 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 4 & b'1' = 1) AND reputationClassMask1 = 0
  -- Undead mage
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    8 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 4 & b'1' = 1) AND reputationClassMask1 = 0
   -- Undead warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    9 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 4 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Undead Warrior
SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 4 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Undead rogue
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 4 & b'1' = 1) AND reputationClassMask2 = 0
 -- Undead priest
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    5 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 4 & b'1' = 1) AND reputationClassMask2 = 0
  -- Undead mage
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    8 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 4 & b'1' = 1) AND reputationClassMask2 = 0
   -- Undead warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    9 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 4 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Undead Warrior
SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 4 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Undead rogue
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 4 & b'1' = 1) AND reputationClassMask3 = 0
 -- Undead priest
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 4 & b'1' = 1) AND reputationClassMask3 = 0
  -- Undead mage
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    8 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 4 & b'1' = 1) AND reputationClassMask3 = 0
   -- Undead warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    9 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 4 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Undead Warrior
SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 4 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Undead rogue
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 4 & b'1' = 1) AND reputationClassMask4 = 0
 -- Undead priest
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    5 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 4 & b'1' = 1) AND reputationClassMask4 = 0
  -- Undead mage
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    8 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 4 & b'1' = 1) AND reputationClassMask4 = 0
   -- Undead warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    5 as fk_race,
    9 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 4 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Tauren Warrior
SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 5 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Tauren hunter
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    3 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 5 & b'1' = 1) AND reputationClassMask1 = 0
 -- Tauren shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    7 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 5 & b'1' = 1) AND reputationClassMask1 = 0
  -- Tauren druid
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    11 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 5 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Tauren Warrior
SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 5 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Tauren hunter
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    3 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 5 & b'1' = 1) AND reputationClassMask2 = 0
 -- Tauren shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    7 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 5 & b'1' = 1) AND reputationClassMask2 = 0
  -- Tauren druid
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    11 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 5 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Tauren Warrior
SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 5 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Tauren hunter
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    3 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 5 & b'1' = 1) AND reputationClassMask3 = 0
 -- Tauren shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    7 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 5 & b'1' = 1) AND reputationClassMask3 = 0
  -- Tauren druid
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    11 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 5 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Tauren Warrior
SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 5 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Tauren hunter
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    3 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 5 & b'1' = 1) AND reputationClassMask4 = 0
 -- Tauren shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    7 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 5 & b'1' = 1) AND reputationClassMask4 = 0
  -- Tauren druid
 UNION
 SELECT 
	ID as fk_faction,
    
    6 as fk_race,
    11 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 5 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Gnome Warrior
SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 6 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Gnome rogue
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 6 & b'1' = 1) AND reputationClassMask1 = 0
 -- Gnome mage
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    8 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 6 & b'1' = 1) AND reputationClassMask1 = 0
  -- Gnome warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    9 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 6 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Gnome Warrior
SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 6 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Gnome rogue
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 6 & b'1' = 1) AND reputationClassMask2 = 0
 -- Gnome mage
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    8 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 6 & b'1' = 1) AND reputationClassMask2 = 0
  -- Gnome warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    9 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags2 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 6 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Gnome Warrior
SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 6 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Gnome rogue
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 6 & b'1' = 1) AND reputationClassMask3 = 0
 -- Gnome mage
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    8 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 6 & b'1' = 1) AND reputationClassMask3 = 0
  -- Gnome warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    9 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags3 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 6 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Gnome Warrior
SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 6 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Gnome rogue
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 6 & b'1' = 1) AND reputationClassMask4 = 0
 -- Gnome mage
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    8 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 6 & b'1' = 1) AND reputationClassMask4 = 0
  -- Gnome warlock
 UNION
 SELECT 
	ID as fk_faction,
    
    7 as fk_race,
    9 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags4 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 6 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
  -- Troll Warrior
SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    1 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
  UNION
 -- Troll hunter
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    3 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
 -- Troll rogue
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    4 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
 -- Troll priest
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    5 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
  -- Troll shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    7 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
  -- Troll mage
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    8 as fk_class,
    reputationBase1 as baseRep,
    (reputationFlags1 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags1 >> 2 & b'1') as hide,
    (reputationFlags1 >> 3 & b'1') as forceHide,
    (reputationFlags1 >> 4 & b'1') as forcePeace,
    (reputationFlags1 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask1 >> 7 & b'1' = 1) AND reputationClassMask1 = 0
 UNION
  -- Troll Warrior
SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    1 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
	(reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
  UNION
 -- Troll hunter
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    3 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
 -- Troll rogue
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    4 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
 -- Troll priest
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    5 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
  -- Troll shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    7 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
  -- Troll mage
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    8 as fk_class,
    reputationBase2 as baseRep,
    (reputationFlags2 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags2 >> 2 & b'1') as hide,
    (reputationFlags2 >> 3 & b'1') as forceHide,
    (reputationFlags2 >> 4 & b'1') as forcePeace,
    (reputationFlags2 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask2 >> 7 & b'1' = 1) AND reputationClassMask2 = 0
 UNION
  -- Troll Warrior
SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    1 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
  UNION
 -- Troll hunter
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    3 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
 -- Troll rogue
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    4 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
 -- Troll priest
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    5 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
  -- Troll shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    7 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
  -- Troll mage
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    8 as fk_class,
    reputationBase3 as baseRep,
    (reputationFlags3 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags3 >> 2 & b'1') as hide,
    (reputationFlags3 >> 3 & b'1') as forceHide,
    (reputationFlags3 >> 4 & b'1') as forcePeace,
    (reputationFlags3 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask3 >> 7 & b'1' = 1) AND reputationClassMask3 = 0
 UNION
  -- Troll Warrior
SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    1 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0
  UNION
 -- Troll hunter
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    3 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0
 UNION
 -- Troll rogue
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    4 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0
 -- Troll priest
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    5 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0
  -- Troll shaman
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    7 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0
  -- Troll mage
 UNION
 SELECT 
	ID as fk_faction,
    
    8 as fk_race,
    8 as fk_class,
    reputationBase4 as baseRep,
    (reputationFlags4 & b'1') as visible,
    (reputationFlags1 >> 1 & b'1') as war,
    (reputationFlags4 >> 2 & b'1') as hide,
    (reputationFlags4 >> 3 & b'1') as forceHide,
    (reputationFlags4 >> 4 & b'1') as forcePeace,
    (reputationFlags4 >> 5 & b'1') as inactive            
 FROM dbc.dbc0_faction
 WHERE (reputationRaceMask4 >> 7 & b'1' = 1) AND reputationClassMask4 = 0