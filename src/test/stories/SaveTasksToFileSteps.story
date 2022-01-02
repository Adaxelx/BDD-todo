Narrative:
Jako użytkownik chce zapisywać zadania do pliku.

Scenario: Kiedy użytkownik chce zapisać pustą listę do pliku

Given nie ma zadań do zapisu
When uzytkownik spróbuje wykonac zapis do pliku
Then zapis nie jest wykonany i wyświetlany jest komunikat o braku zadań

Scenario: Kiedy użytkownik chce zapisać listę zadań do pliku i otrzymuje komunikat o pomyślnym zapisie

Given istnieją jakieś zadania na liście
When użytkownik spróbuje wykonac zapis do pliku
Then zwrócony został komunikat o pomyślnym zapisaniu do pliku wraz ze ścieżką do pliku, plik zawiera wszystkie zadania



