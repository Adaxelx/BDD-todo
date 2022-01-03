Narrative:
Jako użytkownik
Chcę móc pobierać zadania z listy
Abym mógł zobaczyć ich treść

Scenario: pobieranie pustej listy
Given lista zadan jest pusta
When pobiorę listę zadań
Then zostanie zwrócona pusta lista

Scenario: pobieranie zadania o indeksie 0
Given lista zadan ma zadanie o indeksie 0
When pobiorę zadanie o indeksie 0
Then zostanie zwrócone zadanie o indeksie 0