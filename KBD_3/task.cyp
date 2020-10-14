// 1
MATCH (p:Person)
  WHERE p.name = 'Alexandr Klecevich'
RETURN p;

// 2
MATCH (person)-[:LIKE]->(sport)
  WHERE person.name = 'Denis Timofeenko'
RETURN sport;

// 3
MATCH (person)-[l:LIKE]->(sport)
  WHERE sport.name = 'Swimming' AND l.rating > 0
RETURN person.name;

// 4
MATCH (person2)-[:FRIEND]->(person)-[l:LIKE]->(sport:Sport)
  WHERE sport.name = 'Basketball' AND l.rating >= 8
RETURN DISTINCT person2.name;

// 5
MATCH (person2)-[:FRIEND]->(person)-[l:LIKE]->(sport:Sport)
  WHERE person2.name = 'Alexandr Klecevich' AND l.rating > 6
  AND NOT (person2)-[:LIKE]->(sport:Sport)
RETURN sport;


// 6
MATCH (person)-[:FRIEND]->(friend)
RETURN DISTINCT friend;

// 7
MATCH ()-[l:LIKE]->(sport:Sport)
  WHERE l.rating = 8
RETURN DISTINCT sport;

// 8
MATCH (person)-[l:LIKE]->(sport)
  WHERE person.name = 'Alexandr Klecevich' AND l.rating > 0
RETURN sport;

// 9
MATCH (person)-[l:LIKE]->(sport)
  WHERE sport.name = 'Esports' AND l.rating > 0
RETURN person;

// 10
MATCH (person)-[l:LIKE]->(sport:Sport)
  WHERE l.rating < 0
RETURN person.name, sport.name, l.rating;