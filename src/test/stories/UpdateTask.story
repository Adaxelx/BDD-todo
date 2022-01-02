Meta:

Narrative:
As a user
I want to update a task in todo list
So that I can change state of my task

Scenario: update the task state to done
Given a todo list contains the undone task
When I update the task
Then the task state should be done