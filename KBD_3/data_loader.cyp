CREATE (alex_klecevich:Person {name: 'Alexandr Klecevich', age : 19, email: 'alex_klevcevich@gmail.com'}),
(denis_timofeenko:Persin {name: 'Denis Timofeenko' , age : 19, email: 'denis_timofeenko@gmail.com'}),
(yauheni_kazachenka:Student {name: 'Yauheni Kazachenka' , age : 20, email: 'yauheni_kazachenka@mail.ru'})

CREATE (football: Sport {name: 'Football', type:'team'}),
(basketball: Sport{name: 'Basketball', type: 'team'}),
(hockey: Sport{name: 'Hockey', type: 'team'}),
(swimming: Sport{name: 'Swimming', type: 'singles'}),
(esports: Sport{name: 'Esports', type: 'team'}),

(alex_klecevich)-[:LIKE {rating: 9}]->(football),
(alex_klecevich)-[:LIKE {rating: 8}]->(basketball),
(alex_klecevich)-[:LIKE {rating: 8}]->(hockey),
(alex_klecevich)-[:LIKE {rating: -4}]->(esports),
(alex_klecevich)-[:LIKE {rating: 7}]->(swimming),

(yauheni_kazachenka)-[:LIKE {rating: -6}]->(football),
(yauheni_kazachenka)-[:LIKE {rating: 8}]->(basketball),
(yauheni_kazachenka)-[:LIKE {rating: 8}]->(hockey),
(yauheni_kazachenka)-[:LIKE {rating: 9}]->(esports),
(yauheni_kazachenka)-[:LIKE {rating: -6}]->(swimming),

(denis_timofeenko)-[:LIKE {rating: -1}]->(football),
(denis_timofeenko)-[:LIKE {rating: 3}]->(basketball),
(denis_timofeenko)-[:LIKE {rating: -6}]->(hockey),
(denis_timofeenko)-[:LIKE {rating: 9}]->(esports),
(denis_timofeenko)-[:LIKE {rating: 9}]->(swimming),

(alex_klecevich)-[:FRIEND]->(denis_timofeenko),
(alex_klecevich)-[:FRIEND]->(yauheni_kazachenka),
(alex_klecevich)<-[:FRIEND]-(denis_timofeenko),
(alex_klecevich)<-[:FRIEND]-(yauheni_kazachenka),
(denis_timofeenko)-[:FRIEND]->(yauheni_kazachenka),
(denis_timofeenko)<-[:FRIEND]-(yauheni_kazachenka)
