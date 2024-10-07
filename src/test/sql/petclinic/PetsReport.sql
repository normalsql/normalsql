   SELECT o.Last_Name AS Last2, p.Name AS First2, t.Name AS Species2, Birth_Date AS DOB2
     FROM Owners o
LEFT JOIN Pets p ON o.Id = p.Owner_Id
LEFT JOIN Types t ON p.Type_Id = T.Id;
