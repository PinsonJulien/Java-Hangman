package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.framework.presenter.ParentPresenter;

import java.util.EnumMap;

public class AppPresenter
    extends ParentPresenter
    <
        AppView,
        PresenterEnum,
        ChildPresenter<?, ?>
    >
    implements AppPresenterListener
{

    public AppPresenter(
        AppView appView
    ) {
        super(appView, new EnumMap<>(PresenterEnum.class));
        this.init();
    }

    // Interfaces
    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }
}
