# Bowl Score App.

This application renders a console legible scoreboard from a file with bowling rolls for multiple players.

# How to run

Run the executable jar and the desired text file with the rolls in it as following:

```console
java -jar bowlscoreboard-1.0.jar score.txt
```
On this case, both files are relative to the same directory.

# Sample Cases

This software is supplieth with 3 sample cases: score.txt (regular play), full.txt (perfect 300 score) and empty.txt (0 score play).

# Compilation and Tecnologies

This uses Maven and JUnit12, with parametrized test cases. To compile this code please use on pom.xml directory: 

```console
maven clean install
```
