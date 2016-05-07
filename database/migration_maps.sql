INSERT INTO maps
SELECT id, internalMapName, mapName, MinLvl, MaxLvl, MaxPlayers FROM dbc.dbc0_map;