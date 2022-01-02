Scenario: Kiedy użytkownik doda zadanie do pustej listy pojawi się ono na liście

Given nie ma żadnych zadań
When użytkownik spróbuje dodać zadanie z uzupełnionym opisem test
Then zadanie zostanie dodane i pojawi się na liście zadan zadanie z opisem test


Scenario: Kiedy użytkownik chce zapisać listę do pliku

Given nie ma zadań do zapisu
When uzytkownik próbuje wykonac zapis do pliku
Then zapis nie jest wykonany i wyświetlany jest komunikat o braku zadań
