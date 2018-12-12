# Tic Tac Toe 2.1

If you are not acquainted with the game Tic Tac Toe, here you can find the rules for the classical game: https://en.wikipedia.org/wiki/Tic-tac-toe

We brought the pen-and-paper classical game to the digital age, but with a little twist: 
- The size of the playfield are configurable between 3x3 and 10x10.
- The number of players are configurable.
- The symbols (usually O and X) are configurable.
- The type of the players are configurable (real players or bots).

Feel free to change the file named 'config', following the default format.

### How to run

On a terminal, type the following commands:

```sh
$ git clone git@github.com:andersonap/tictactoe.git
$ cd tictactoe
```

If you are using a UNIX OS:

```sh
$ ./gradlew build run --console=plain
```

If you are using Windows:

```sh
$ start gradlew build run --console=plain
```

### How to play
To make a move, choose a position on the board and type it in the console. The move should be in the format "horizontalPosition,verticalPosition", where horizontalPosition and verticalPosition are integer numbers that don't exceed the board size.

### Software Design

The software is designed to have real world representations.  
I tried to develop to interfaces as much as possible.  
For example, the interface Interaction is implemented by GameConsole class, but, it is possible to have another implementation that uses Java Swing, or maybe one that sends and requests user interaction by an REST API.  
The same applies to the GameConfiguration interface. This way, you can have other classes that loads the configurations by other ways. The FileConfiguration class doesn't know about the rules of the game, and its only responsability is to load the configurations of the game on the correct file. The Game class is responsable for validating the game.  
The Player interface is implemented by DefaultPlayer class. The Bot extends DefaultPlayer because it has the same attributes, the only difference is that the way a Bot player makes a move is differente from a DefaultPlayer. This way, is possible to develop other Bots that have more intelligence to make a move on the Board.
