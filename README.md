# CPS2002: Software Engineering - Assignment
This game is a programming assignment for [CPS2002: Software Engineering](https://www.um.edu.mt/courses/studyunit/CPS2002), a course forming part of our B.Sc. in Mathematics and Computer Science.
![Game Preview](https://i.imgur.com/8VwMS9v.png) 

## Requirements
A precompiled JAR file is present in the root directory, so all that is required to actually run the game is a working [Java](https://java.com/en/download/) installation on Windows/OSX/Un*x. 
 
## Instructions
Clone the repository, then open a terminal (or _command line_ on Windows) and navigate to the directory where the repository files are present. Then run the command 

    java -cp game.jar edu.um.cps2002.tile_game.Launcher 

which will present you with the console interface for the game. Enter the number of players and desired size of the map when prompted. Then you should notice that an HTML file has been generated for each player (`map_player_1.html`, `map_player_2.html`, etc) in the repository folder. Open these in a browser. After each round is played in the console, refresh the maps in the browser (hit F5) to see where the players have landed. The first player to discover the treasure tile is the winner.

## Compilation
The program is built using [Maven](https://maven.apache.org/), so if you wish to compile the code, navigate to the main directory in a terminal/command line (Windows) and type `mvn clean install`, which should produce an executable JAR file in the `target` directory. If Maven is not present on your system, you can download it from [the main website](https://maven.apache.org/) (Windows/OSX) or open a Linux terminal and run

    sudo  apt-get install maven

## Documentation 
Detailed documentation of the code can be found in the `javadocs` directory. Visit [this webpage](https://cdn.rawgit.com/stefaniatadama/cps2002-assignment/fdc3820c/javadocs/index.html) to view the documentation in your browser or pull the repository, navigate to the `javadocs` directory and open `index.html` for details about the classes/methods involved. Alternatively, one can generate [Doxygen](http://www.stack.nl/~dimitri/doxygen/) documentation for the project using the provided Doxyfile, by running the terminal command
    
    doxygen

in the root directory.