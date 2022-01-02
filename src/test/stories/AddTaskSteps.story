Scenario: Kiedy użytkownik doda zadanie do pustej listy pojawi się ono na liście

Given nie ma żadnych zadań
When użytkownik spróbuje dodać zadanie z uzupełnionym opisem test
Then zadanie zostanie dodane i pojawi się na liście zadan zadanie z opisem test

Scenario: Kiedy użytkownik chce zapisać pustą listę do pliku

Given nie ma zadań do zapisu
When uzytkownik spróbuje wykonac zapis do pliku
Then zapis nie jest wykonany i wyświetlany jest komunikat o braku zadań

Scenario: Kiedy użytkownik chce zapisać listę zadań do pliku i otrzymuje komunikat o pomyślnym zapisie

Given istnieją jakieś zadania na liście
When użytkownik spróbuje wykonac zapis do pliku
Then zapis został wykonany pomyślnie i została wyświetlona ściezka do pliku


Scenario: Jako uzytkownik chce dodac zadanie do listy zadan

Given istnieje zadanie z opisem test1
When uzytkownik sprobuje dodac zadanie z opisem=<description>
Then zostanie zwrocona wiadomosc=<expectedMessage> i długość listy będzie wynosić <length>

Examples:
|description|expectedMessage                 |length|
|test       |Pomyślnie dodano zadanie        |2     |
|test1      |Istnieje zadanie z takim opisem |1     |
|           |Pomyślnie dodano zadanie        |2     |