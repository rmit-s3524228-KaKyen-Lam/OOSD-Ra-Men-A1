s3503728, David Limantoro
s3524228, Lam Ka Kyen
s3585826, Fabio Monsalve Duque

Notes:
- The game is developed from scratch and all images are drawn ourselves, so there are no copyright issue.
- This project is done in IntelliJ Idea IDE
- GitHub page of our assignment: https://github.com/rmit-s3524228-KaKyen-Lam/OOSD-Ra-Men-A1
- The main class is located under src/GameMain.java. Start the game by starting this class
- Instead of using original Saboteur's winning condition for saboteur, saboteur group wins when one of the saboteur
  places the last card connected to grid, similar to miner

Starting the game:
- As mentioned above, start the game by executing GameMain.java, located at root of source code folder.
- The configuration must be filled in. Specifically, the board size must be valid
  and at least one gold location is defined

Game's instruction:
- To place a card on a board, select a path card (by clicking on player's hand),
  and then click the board where the card supposed to be placed
- The path card placed must be connected to the main path and connects correctly with other paths
- To discard a card, click on a card you wish to discard and then click the trash bin icon
  to the right of player's hand
- To rotate card, right click the card directly on player's hand section
- To use personal card on other player, select personal card and click on the target player (cannot target self)
- Saving and loading should be quite self-explanatory, however, you can only load a game after the configuration menu.

Inclusion of SOLID, GRASP, DBC and DESIGN PATTERNS:

SOLID PRINCIPLES:
- The Single-Responsibility principle was implemented in Card.java  as it only has a single responsibility
- The Open-Closed principle was implemented in Card.java which allows for future additions of new types of Cards
- The Dependency Inversion Principle was implemented in Card.java as the dependence of the Card types is abstract and not concrete

GRASP PRINCIPLES:
- The Creator principle was used in Deck.java only using objects of type Card.
- The Protected Variations principle was implemented in Deck by using cardConfig.txt as an external representation.

DBC:
- Some methods in Game.java class requires certain preconditions such as playPathCard, playActionCard, playPersonalCard, shareGold method
- Pathcard, ActionCard, PersonalCard contains cardAction and undoCardAction methods that have prerequisites.

PATTERNS:
- Flyweight pattern is implemented in CardFlyweight, as well as ImageFlyweight.
- Abstract Factory pattern is implemented in AbstractCardFactory, ActionCardFactory, GoalCardFactory and PersonalCardFactory.
- Composite and Template pattern is implemented mainly in Card and its subclasses (Action card, personal card, path card, goal card, etc)
- Command pattern is implemented in the Command, CommandAbstract, Command_DiscardCard and Command_PlayCard. Also,
  in addition to these classes, CommandHistory is also created as part of the pattern that executes the Command and
  keeping track of the history
- Singleton pattern is used in ImageTinter class since having more ImageTinter class is waste of memory.