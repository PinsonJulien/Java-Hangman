package com.jpinson.pendujfx.components.word;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.scene.control.Label;

public class Letter extends Label implements InitResetInterface {
    final char value;

    final String hiddenCls = "hidden";

    public Letter (char c) {
        this.value = c;
        this.init();
    }

    @Override
    public void init() {
        this.setText(String.valueOf(this.value));
    }

    @Override
    public void reset() {}

    public void setTextVisibility (boolean visible) {
        if (visible) {
            CssClass.remove(this, hiddenCls);
        } else {
            CssClass.add(this, hiddenCls);
        }
    }
}
