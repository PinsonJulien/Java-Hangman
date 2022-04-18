package com.jpinson.pendujfx.app.gameOver;

import com.jpinson.pendujfx.framework.view.ViewListener;

public interface GameOverViewListener extends ViewListener {
    void ReplayButtonPressed();
    void MenuButtonPressed();
    void ScoreButtonPressed();
}