# Masters of Renaissance
**Lapo Falcone - Marco Fumagalli - Sofia Guggiari**

## Tools to run the game
In order to run the game you should use a Windows 10 machine with Java 11 installed and configured as default java runtime.

To run the CLI in the intended way you should install [this](https://conemu.github.io/en/Downloads.html) terminal (ConEmu) or use the Git Bash (not deeply tested and not recommended), since ANSI color codes are not supported anymore in latest versions of Windows cmd or PowerShell. Using the CLI in cmd or PowerShell is still possible, but it is not recommended, since colors are fundamental in our implementation of the CLI.

## How to run the game
In order to run the game, launch `deliverables/MastersOfRenaissance.jar`. A simple menu will pop up, where you can choose what component to run.

`savedMatches.txt` and `MastersOfRenaissance.jar` must be in he same folder for the game to work.

If you need to generate the jar from the source code, please change the value of the field `recoveryPath` in class controller.match.MatchManager, row 23, from "deliverables/savedMatches.txt" to "./savedMatches.txt" (and vice-versa).

The client will shut down each time there is a fatal error. This doesn't mean the client or the server crashed, for example, if a user enters a wrong IP, the server won't respond, causing a fatal error, and the client will shut down gracefully. We chose this design choice in order to avoid any kind of broken state in the client. The client will also shut down at the end of a match.

### Server
When launching the server, a message asking you what port number you want to use will pop up. If you enter an invalid value (any value not between 1024 and 49152), the default port (14009) will be chosen.

After the server has been launched, it will start logging the events it polls from the specified port, such as players connections, disconnections and matches creation.

By typing `CTRL + C` in the terminal, the server will shut down, and all of the active matches will be saved and restored next time the server is launched. In the unlikely case of server disconnection, match won't be saved, since the server isn't able to detect if the disconnection happened due to client disconnection or server disconnection.

If all of the players in a match get disconnected, the server will instantaneously delete that match. If at least one player in the match is active the server will keep the match alive, and it will skip turns of disconnected players.

If a player disconnects, it can reconnect to the match by using the same name and match ID. This mechanism doesn't ensure that the same physical person actually reconnects to the match.

In case of server failure (not disconnection), all of the players will be kicked from the match, and they can reconnect to the same match, when the server is up again, by using the same name and match ID. The first player to reconnect will be the first to play, so, if you are playing with friends, we suggest that the player who was playing before the disconnection is the first to reconnect.
A server can host as many games as the host memory allows, by specifying different match IDs. Players can have the same name, as long as they are playing in different matches (different match IDs).

In conclusion, the server implements 3 bonus points:
* Multiple matches
* Player disconnection
* Server failure resilience

### GUI
#### Home screen
The home screen of the GUI presents 4 text fields and 2 buttons:
* **Name**: the name of the player. It cannot contain spaces and it is tested to contain only English alphabet letters and numbers. Some names are reserved, if you choose one of these names and try to create a match, you will be told.
* **Server IP**: the IPv4 address of the server to connect to. "localhost" is also allowed for local matches.
* **Server port**: the port number of the server. It defaults to 14009, but it can be changed based on the server configuration.
* **Match ID**: the ID of the match to connect to. If ID doesn't exist on the server, a new match will be created. Same syntactic rules as `Name` applies.
* **Play**: asks the server to create a new multiplayer match, or to connect to an existing multiplayer match.
* **Single player**: asks the server to create a single player match (against Lorenzo il Magnifico).

If you got disconnected and try to reconnect, 'Play' and 'Single player' will do the same thing, and the only criteria to choose between a single player or multiplayer match, is the match ID (whether is was a single player or multiplayer match before the disconnection).

#### Lobby
In the lobby screen, a list of players will show up. Each player has an associated color:
* **Blue**: match leader, is the only player that can press `Start`.
* **Green**: connected player.
* **Red**: disconnected player.

If the leader exits the match, a new leader will be chosen.

#### Pre match
In the pre match screen you can choose your leaders and starting resources. By hoovering on the `Show table` button, you can check the status of the marketplace and development grid, in order to choose wisely your starting items.

#### Match
##### Leaders
Click on an inactive leader to choose whether to activate or discard it. Click on void area to hide the menu.

##### Move items
Click on an item to select it. All of the depots that can receive that item will be highlighted. Click on an highlighted depot to move the selected item there, or click a void area to undo the selection.

##### Swap warehouse rows
Click on a counter-arrows icon near the row, then click on another counter-arrow icon to invert the 2 rows, or click a void area to undo the selection. If the swap isn't possible you will be told.

##### Produce
To activate productions, select them with the relative tick-box. To produce, click the `Produce` button. All of the selected productions will produce. Of course, in order to produce, you must place the perfect amount of items on each active production. If this condition isn't matched, you will be told.

##### Buy marbles
Click on `Marketplace`, select one row or column using the arrows. Click on a marble, then click on the depot where you want to store it. Click on a white marble to color it (if you have the right leader).

During the process of marble acquisition, items cannot be moved, but warehouse rows can be swapped. So, if you have a leader the can store items, we strongly suggest you to place as many marbles as you can in the leader depots, before using the warehouse depots. When you are done, discard remaining marbles (even if 0 marbles remains). During the discard process you will be awarded the faith points for the red marble (if any).

##### Buy cards
Click on `Buy cards`. To buy a card, you must place the correct amount of resources into the paycheck. As you can see, the paycheck has 2 numbers for each item. The first number represents the amount of resources in the paycheck coming form the strongbox, the second one the amount of resources coming from the warehouse/leaders depots.

##### Spy opponents
You can hoover over the players placeholders in the faith track to spy them.

_Known bug: If you are hoovering over a player, and you become the active player in that exact moment, the dashboard of the player you were spying will remain visible. To hide it simply hoover again on that player placeholder in the faith track._

### CLI
The rules for the CLI are, of course, the same as the rules used in the GUI. As stated in the CLI home screen, you can use the `help` command to get all of the necessary info about the commands.

It is important to note that the CLI isn't auto-updating. This means that if an opponent performs an action, the CLI won't show it immediately, in order not to frustrate the user with continuous updates, making it impossible to type any command. So, if the CLI isn't showing what you expect, use the command `u` to update the CLI.

## Ambiguous game rules
The rules of the game specified in the game instruction document were sometimes unclear, or left space for interpretation. Here we list some of the rules we considered ambiguous:
* **White marble discard**: in the rules it isn't clear if white marbles, when discarded, should make the opponents advance in the faith track. We chose not to advance the opponents.
* **Advancement order**: in the rules it isn't specified in what order the players should move on the faith track, once an opponent has discarded any marble. We chose to advance every player by one tile simultaneously, and to trigger the Vatican report at the end of the one-tile advancement (not at the end of all of the advancements).
* **Faith track points**: in the rules it isn't clear if the points gained thanks to the faith track are cumulative or only the ones given by the last tile reached. We chose cumulative.
* **Marble discard**: rules in Italian and English, specify different things: Italian rules says that marbles can be discarded only if they don't fit in the warehouse, whereas English ones states that a player can discard marbles at his will. We chose English rules here.

## Documentation
To look at the javadoc please open `allclasses-index.html` in `deliverables/javadoc`.
