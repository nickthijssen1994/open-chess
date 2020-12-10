open module chessclient {
	requires chesscore;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires websocketshared;
	requires restclient;
	requires commons;
	requires javax.websocket.client.api;

	exports nl.nickthijssen.chessclient;
	exports nl.nickthijssen.chessclient.game;
	exports nl.nickthijssen.chessclient.game.websocket;
	exports nl.nickthijssen.chessclient.game.websocket.handlers;
	exports nl.nickthijssen.chessclient.ui;
	exports nl.nickthijssen.chessclient.utilities;
}
