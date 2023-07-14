package com.minigame.Interface;

public interface GameEventSink {

	public void onGameEvent(int type);

	int GAME_PAUSED = 1;
	int GAME_PLAYING = 2;
	int GAME_OVER = 3;
}
