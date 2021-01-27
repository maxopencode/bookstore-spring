INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781072913009',
       'Dubliners',
       id,
       'Grant Richards Ltd',
       1914,
       'A collection of fifteen short stories by James Joyce, first published in 1914. The stories comprise a naturalistic depiction of Irish middle class life in and around Dublin in the early years of the 20th century.'
FROM authors
WHERE name = 'James Joyce';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780142437346',
       'A Portrait of the Artist as a Young Man',
       id,
       'B. W. Huebsch',
       1916,
       'The first novel of Irish writer James Joyce. A Künstlerroman written in a modernist style, it traces the religious and intellectual awakening of young Stephen Dedalus, Joyce''s fictional alter ego, whose surname alludes to Daedalus, Greek mythology''s consummate craftsman. Stephen questions and rebels against the Catholic and Irish conventions under which he has grown, culminating in his self-exile from Ireland to Europe.'
FROM authors
WHERE name = 'James Joyce';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781772266726',
       'Ulysses',
       id,
       'Sylvia Beach',
       1922,
       'A modernist novel by Irish writer James Joyce. It was first serialized in parts in the American journal The Little Review from March 1918 to December 1920 and then published in its entirety in Paris by Sylvia Beach on 2 February 1922, Joyce''s 40th birthday.'
FROM authors
WHERE name = 'James Joyce';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT ' 9780156628709',
       'Mrs Dalloway',
       id,
       'Hogarth Press',
       1925,
       'A novel by Virginia Woolf that details a day in the life of Clarissa Dalloway, a fictional high-society woman in post–First World War England. It is one of Woolf''s best-known novels.'
FROM authors
WHERE name = 'Virginia Woolf';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780156907392',
       'To the Lighthouse',
       id,
       'Hogarth Press',
       1927,
       'A novel by Virginia Woolf. The novel centres on the Ramsay family and their visits to the Isle of Skye in Scotland between 1910 and 1920.'
FROM authors
WHERE name = 'Virginia Woolf';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781557427663',
       'The Metamorphosis',
       id,
       'Kurt Wolff Verlag',
       1915,
       'A novella written by Franz Kafka which was first published in 1915. One of Kafka''s best-known works, The Metamorphosis tells the story of salesman Gregor Samsa who wakes one morning to find himself inexplicably transformed into a huge insect (German ungeheures Ungeziefer, literally "monstrous vermin"), subsequently struggling to adjust to this new condition.'
FROM authors
WHERE name = 'Franz Kafka';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780805211061',
       'The Castle',
       id,
       'Kurt Wolff Verlag',
       1926,
       'A novel by Franz Kafka. In it a protagonist known only as "K." arrives in a village and struggles to gain access to the mysterious authorities who govern it from a castle.'
FROM authors
WHERE name = 'Franz Kafka';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781841598970',
       'In Search of Lost Time',
       id,
       'Grasset and Gallimard',
       1913,
       'A novel in seven volumes by Marcel Proust (1871–1922). It is his most prominent work, known both for its length and its theme of involuntary memory; the most famous example of this is the "episode of the madeleine," which occurs early in the first volume.'
FROM authors
WHERE name = 'Marcel Proust';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781476764528',
       'A Farewell to Arms',
       id,
       'unknown',
       1929,
       'A novel by American writer Ernest Hemingway, set during the Italian campaign of World War I. First published in 1929, it is a first-person account of an American, Frederic Henry, serving as a lieutenant ("tenente") in the ambulance corps of the Italian Army.'
FROM authors
WHERE name = 'Ernest Hemingway';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780684803357',
       'For Whom the Bell Tolls',
       id,
       'Charles Scribner''s Sons',
       1940,
       'A novel by Ernest Hemingway published in 1940. It tells the story of Robert Jordan, a young American volunteer attached to a Republican guerrilla unit during the Spanish Civil War. As a dynamiter, he is assigned to blow up a bridge during an attack on the city of Segovia.'
FROM authors
WHERE name = 'Ernest Hemingway';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780486434681',
       'Sister Carrie',
       id,
       'Doubleday',
       1900,
       'A novel by Theodore Dreiser about a young country girl who moves to the big city where she starts realizing her own American Dream, first as a mistress to men that she perceives as superior, and later becoming a famous actress.'
FROM authors
WHERE name = 'Theodore Dreiser';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780451531551',
       'An American Tragedy',
       id,
       'Boni & Liveright’',
       1925,
       'A novel by American writer Theodore Dreiser. He began the manuscript in the summer of 1920, but a year later abandoned most of that text. It was based on the notorious murder of Grace Brown in 1906 and the trial of her lover.'
FROM authors
WHERE name = 'Theodore Dreiser';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780140187724',
       'Martin Eden',
       id,
       'Macmillan',
       1909,
       'A novel by American author Jack London about a young proletarian autodidact struggling to become a writer.'
FROM authors
WHERE name = 'Jack London';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780671867805',
       'Nausea',
       id,
       'Gallimard',
       1938,
       'A philosophical novel by the existentialist philosopher Jean-Paul Sartre, published in 1938. It is Sartre''s first novel and, in his own opinion, one of his best works.'
FROM authors
WHERE name = 'Jean-Paul Sartre';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780811201902',
       'The Wall',
       id,
       'Gallimard',
       1939,
       'A collection of short stories published in 1939 containing the eponymous story "The Wall", is considered one of the author''s greatest existentialist works of fiction.'
FROM authors
WHERE name = 'Jean-Paul Sartre';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9780140283297',
       'On the Road',
       id,
       'Viking Press',
       1957,
       'A novel by American writer Jack Kerouac, based on the travels of Kerouac and his friends across the United States. It is considered a defining work of the postwar Beat and Counterculture generations, with its protagonists living life against a backdrop of jazz, poetry, and drug use.'
FROM authors
WHERE name = 'Jack Kerouac';

INSERT INTO books (isbn, title, author_id, publisher, publication_year, description)
SELECT '9781451673319',
       'Fahrenheit 451',
       id,
       'Ballantine Books',
       1953,
       'A dystopian novel by American writer Ray Bradbury, first published in 1953. Often regarded as one of his best works,[4] the novel presents a future American society where books are outlawed and "firemen" burn any that are found.'
FROM authors
WHERE name = 'Ray Bradbury';
