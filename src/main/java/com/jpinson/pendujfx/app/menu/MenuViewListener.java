package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.framework.view.ViewListener;

public interface MenuViewListener extends ViewListener {
    void quickPlayButtonPressed();
    void startButtonPressed();
    void scoresButtonPressed();
    void quitButtonPressed();
}
