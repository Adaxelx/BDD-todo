Scenario: Usuwanie istniejącego zadania z indeksem 0

Given lista z zadaniem z indeksem 0
When uzytkownik chce usunac zadanie
Then zadanie z indeksem 0 znika z listy i wyswietlony zostaje komunikat powodzenia

Scenario: Usuwanie zadania z indeksem 0 z pustej listy

Given pusta lista
When uzytkownik chce usunac nieistniejace zadanie
Then zadanie nie zostaje usuniete i wyswietlone zostajy komunikat o pustej liscie

Scenario: Usuwanie zadania z indeksem -1 z listy zawierającej zadanie z indeksem 0

Given lista z zadaniem z indeksem 0
When uzytkownik chce usunac nieistniejace zadanie z indeksem -1
Then zadanie nie zostaje usuniete i zostaje wyswietlony komnikat o nieprawidlowym indeksie

Scenario: Usuwanie nieistniejącego zadania z indeksem 10 z lity zawierającej zadanie z indeksem 0

Given lista z zadaniem z indeksem 0
When uzytkownik chce usunac nieistniejace zadanie z indeksem 10
Then zadanie nie zostaje usuniete i zostaje wyswietlony komunikat o nieistniejacym zadaniu