INSERT INTO faction
SELECT ID,
	reputationIndex,
    nullif(parentFactionID, 0),
    Name,
    Description
 FROM dbc.dbc0_faction;