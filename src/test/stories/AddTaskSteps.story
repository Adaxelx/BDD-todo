Scenario: Kiedy użytkownik doda zadanie do pustej listy pojawi się ono na liście

Given nie ma żadnych zadań
When użytkownik spróbuje dodać zadanie z uzupełnionym opisem test
Then zadanie zostanie dodane i pojawi się na liście zadan zadanie z opisem test