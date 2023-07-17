package com.minigame.Interface;

public interface GameEventSink {

	public void onGameEvent(int type);

	int GAME_PAUSED = 1;
	int GAME_PLAYING = 2;
	int GAME_OVER = 3;
	int EVENT_BALL_HIT_BRICK = 4;
	int EVENT_BALL_HIT_WALL = 5;
	int EVENT_BALL_HIT_WALL_DIE = 6;
	int EVENT_BALL_HIT_BOARD = 7;
	int EVENT_BULLET_HIT_BRICK = 8;
}
