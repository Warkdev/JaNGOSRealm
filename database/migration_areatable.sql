INSERT INTO area
SELECT 
	AreaTableId,
    areabit,
    mapId,
    areaname,
    nullif(parentareatableid,0) as parentarea,
    (flags & b'1') as snow,
    (flags >> 1 & b'1') as unk1,
    (flags >> 2 & b'1') as unk2,
    (flags >> 3 & b'1') as slaveCapital,
    (flags >> 4 & b'1') as unk3,
    (flags >> 5 & b'1') as slaveCapital2,
    (flags >> 6 & b'1') as duel,
    (flags >> 7 & b'1') as arena,
    (flags >> 8 & b'1') as capital,
    (flags >> 9 & b'1') as city,
    explorationlevel,
    nullif(liquidTypeId,0) as fk_faction
 FROM dbc.dbc0_areatable
 -- Let's skip wrong data.
 LEFT JOIN maps as m ON mapid = m.id
 WHERE m.id IS NOT NULL
 order by parentarea;