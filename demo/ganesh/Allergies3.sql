SELECT
	a.Id,
	a.ActionCode,
	a.Code,
	a.Description,
	a.DiscoveredDate,
	a.Reaction,
	a.SensitivityDescription,
	a.Severity,
	a.SeverityDescription,
	a.Status,
	a.Type,
	a.Message_ID,
	a.PatientKey_ID
-- this is a comment
FROM 
	Allergy a
LEFT OUTER JOIN
	Message m ON a.message_ID = m.id
WHERE
	m.MessageType = 'ADT' AND
	a.PatientKey_ID IN ( 1, 2, 4, 5, 6, 7 )
ORDER BY a.description, a.discoveredDate, a.reaction