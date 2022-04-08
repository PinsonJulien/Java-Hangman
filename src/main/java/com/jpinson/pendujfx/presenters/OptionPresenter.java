package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.views.OptionView;
import com.jpinson.pendujfx.views.OptionViewListener;

public class OptionPresenter extends Presenter<OptionView> implements OptionViewListener {
    private final MainPresenter mainPresenter;

    public OptionPresenter (MainPresenter mainPresenter) {
        super(new OptionView());
        this.mainPresenter = mainPresenter;
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
        this.mainPresenter.selectPresenter(PresenterAlias.MENU);
    }

    @Override
    public void validateButtonPressed() {
        // Validation
    }

    // Methods
}
