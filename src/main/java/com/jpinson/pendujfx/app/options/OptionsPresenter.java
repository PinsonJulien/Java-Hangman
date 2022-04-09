package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;

public class OptionsPresenter
    extends ChildPresenter<AppPresenterListener, OptionsView>
    implements OptionsViewListener
{

    public OptionsPresenter(AppPresenterListener listener) {
        super(listener, new OptionsView());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.getView().addListener(this);
    }

    @Override
    public void reset() {}

    // Listeners

    @Override
    public void menuButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void validateButtonPressed() {
        // Validation
    }

    // Methods
}
