   SELECT o.Last_Name AS Last2, p.Name AS First2, t.Name AS Species2, p.Birth_Date AS DOB2
     FROM "owners" o
LEFT JOIN "pets" p ON o.Id = p.Owner_Id
LEFT JOIN "types" t ON p.Type_Id = T.Id

