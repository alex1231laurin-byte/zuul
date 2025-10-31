Project: zuul-bad
Authors: Michael Kölling and David J. Barnes

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling

This project is a simple framework for an adventure game. In this version,
it has a few rooms and the ability for a player to walk between these rooms.
That's all.

To start this application, create an instance of class "Game" and call its
"play" method.

This version of the game contains some very bad class design. It should NOT
be used as a basis for extending the project without fixing these design
problems. It serves as an example to discuss good and bad design (chapter 8
of the book).

Chapter 8 of the book contains a detailed description of the problems in this
project, and how to fix them.

The project 'zuul-better' contains a version of this project with better
designed class structure. It includes the fixes discussed in the book.

1: A: It creates a world where we can explore with text.
B: It accepts "go", "go east", "go north", "go west", "go south", "help", "quit", "quit + anything else" and anything else.
C: Go allows you to go to a room mapped to a direction you give if possible. Help gives you the commands and the 
description of the where you are.
D: There are 5 rooms in the game.
E: You start at outside. To the east of you is the theater. To the west of you is the pub. To the south of you is the lab.
To the east of the lab is the office.
2: The Game class allows te player to control the game. The Parser class reads user imput and returns the answer as an object of
class Command. The Command class holds information that was issued by the user. The Room class represents one location in the
scenery of the game and is connected to other rooms via exits. Finally, the CommandWords class holds an enumeration of all
command words known in the game.
13: It sets the currentRoom object to the room that was mapped to that direction.

