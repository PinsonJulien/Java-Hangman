package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.components.keyboard.KeyboardKeyListener;
import com.jpinson.pendujfx.framework.view.ViewListener;

public interface GameViewListener extends ViewListener, KeyboardKeyListener {
    void forfeitButtonPressed();
}
