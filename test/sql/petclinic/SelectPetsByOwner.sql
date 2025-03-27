SELECT o.Last_Name AS Last, p.Name AS First, t.Name AS Species, Birth_Date AS DOB
FROM Owners o
    LEFT JOIN Pets p ON o.Id = p.Owner_Id
    LEFT JOIN Types t ON p.Type_Id = T.Id
WHERE o.Last_Name LIKE 'Da%';
