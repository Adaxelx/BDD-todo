Narrative:
Jako użytkownik
Chcę aktualizować zadania na liście zadań
Abym mógł zmienić status ich wykonania

Scenario: zaktualizowanie statusu zadania na zrobione
Given lista zadań zawiera zadanie o statusie niezrobione
When zaktualizuję stan zadania
Then zadanie powinno mieć status zrobione

Scenario: zaktualizowanie statusu zadania na niezrobione
Given lista zadań zawiera zadanie o statusie zrobione
When zaktualizuję stan zadania
Then zadanie powinno mieć status niezrobione

Scenario: zaktualizowanie nieistniejącego zadania
Given lista zadań nie zawiera zadania o ineksie 1
When zaktualizuję stan zadania o indeksie 1
Then powinien zostać zwrócony komunikat "Zadanie o podanym numerze nie istnieje!"
And lista powinna pozostać niezmieniona

Scenario: zaktualizowanie zadania o ujemnym indeksie
Given lista zadań zawiera zadania o nieujemnych indeksach
When zaktualizuję stan zadania o indeksie -1
Then powinien zostać zwrócony komunikat "Podano nieprawidłowy numer zadania!"
And lista powinna pozostać niezmieniona

Scenario: zaktualizowanie zadania z pustej listy
Given lista zadań jest pusta
When zaktualizuję stan pierwszego zadania
Then powinien zostać zwrócony komunikat "Zadanie o podanym numerze nie istnieje!"
And lista powinna pozostać niezmieniona