# Chess

This project is an online multiplayer version of Chess writen in Java.
It contains three applications:
* A client made with JavaFX to play the game.
* A game server containing the game logic for multiplayer.
* A REST server used for authentication and saving data.

The project is divided in eight modules:
* **chess-core** contains all the logic of chess. Is used by both the client and server.
* **chess-client** contains the JavaFX GUI application.
* **chess-server** contains a websocket server application for multiplayer.
* **commons** for now only contains a serializer that is used in some other modules.
* **rest-client** contains some code to talk to the REST server. Is used by the client and game server.
* **rest-server** contains a Spring Boot application that provides endpoints for authentication with GitHub.
* **rest-shared** contains some classes used by the REST client and server for communication.
* **websocket-shared** contains some interfaces and abstract classes for implementing websocket clients and/or 
  servers and all the DTO classes that represents the messages that can be send or received.



