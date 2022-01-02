Scenario: Jako uzytkownik chce dodac zadanie do listy zadan

Given istnieje zadanie z opisem test1
When uzytkownik sprobuje dodac zadanie z opisem=<description>
Then zostanie zwrocona wiadomosc=<expectedMessage> i długość listy będzie wynosić <length>

Examples:
|description|expectedMessage                 |length|
|test       |Pomyślnie dodano zadanie        |2     |
|test1      |Istnieje zadanie z takim opisem |1     |
|           |Pomyślnie dodano zadanie        |2     |