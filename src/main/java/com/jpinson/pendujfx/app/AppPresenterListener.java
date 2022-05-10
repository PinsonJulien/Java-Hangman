package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.enums.MusicEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ParentPresenterListener;

public interface AppPresenterListener extends ParentPresenterListener<PresenterEnum> {
    void selectMusic(MusicEnum e);
}
