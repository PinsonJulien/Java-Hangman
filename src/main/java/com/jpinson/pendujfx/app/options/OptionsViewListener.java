package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.framework.view.ViewListener;

public interface OptionsViewListener extends ViewListener {
    void returnButtonPressed();
    void playButtonPressed();
    void networkTogglePressed();
}
