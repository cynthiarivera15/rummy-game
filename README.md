# Project: Implementing Design Patterns - Rummy Game

University of Puerto Rico
Río Piedras Campus
Department of Computer Science
Student Name: Cynthia Marie Rivera Sánchez
Student Number: 801-19-5470
Course & Section: CCOM4029-002
Professor: Patricia Ordonez

## Contents of this file
* Description of the program
* Instructions on how to execute the program
* References used to perform the program

## Description
This project contains the following files: ```CardInterface.java```, ```Card.java```, ```DeckInterface.java```, ```Deck.java```, ```HandInterface.java```, ```Hand.java```, ```SetInterface.java```, ```Set.java```, ```MyStack.java```, ```Table.java```, and ```Proj2.java```.
The only files created by me from scratch are ```MyStack.java``` and ```Proj2.java```, ```Card.java``` and ```Table.java``` were edited so they
could work properly. The other files from the project were given by the professor, therefore the only files that are going to be approach in this read me are the ones edited or created from scratch by me.

The first file, ```Card.java```, simulates a single card of the game. The changes that were done to this file were minimal, it just required us to fix the returns from the functions ```getSuitIndex()``` and ```getRankIndex()```.

The second file, ```Table.java```, simulates the table of the game. In here is the logic of the game is located. And controls what happens when a certain button is pressed.

The third file, ```MyStack.java```, simulates the stack of the game and is implemented with a stack. Therefore, when the player decided to take a card a from the stack it will be taking the card that is at the top of and if it decides to place a card in the stack, it would be placing at the top of it.

The last and fourth file, ```Proj2.java```, is the main file. Is the file that must be run from the command line.

## Instructions
To run the project you must first compile the ```Proj2.java``` using the command ```javac Proj2.java```. Once it has compiled the project must be run using the command ```java proj2.Proj2 > p2-output.txt```

This will called the ```Table.java``` file which in turn will call the rest of the files.

Once the files are running all the user has to do is press the buttons that appear, which will work according to program. Each play will be recorded in the ```p2-output.txt``` file. Note that player one is always the first one to start and the first move it can make is either take a card from the deck or the stack. Once it has taken a card, then it can decide whether to place a set on the table or lay a card on the stack. Its turn ends once the card(s) has been laid. The player two turn will not start until that has happened.

## References
Different refereces were used:
* https://users.dcc.uchile.cl/~lmateu/CC10A/cc10a-lib/stack.html: To construct ```MyStack``` class
* https://stackoverflow.com/questions/27774168/sorting-objects-in-an-arraylist-without-using-collections-sort: To sort the player hands every time a new card is added
* https://stackoverflow.com/questions/32915711/what-can-an-object-array-hold: To deal with an object array
* https://stackoverflow.com/questions/9119481/how-to-present-a-simple-alert-message-in-java: To do the showMessageDialog Box

Also, this project was done with the help of Edward García, Pablo Puig and Eliam Ruiz.
