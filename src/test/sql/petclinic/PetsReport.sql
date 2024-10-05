   SELECT o.Last_Name AS Last, p.Name AS First, t.Name AS Species, Birth_Date AS DOB
     FROM Owners o
LEFT JOIN Pets p ON O.Id = p.Owner_Id
LEFT JOIN Types t ON P.Type_Id = T.Id;
