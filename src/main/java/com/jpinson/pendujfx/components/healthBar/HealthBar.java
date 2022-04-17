package com.jpinson.pendujfx.components.healthBar;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;

// Health bar based on percentage
// Changes color on specific thresholds.

public class HealthBar extends ProgressBar implements InitResetInterface {
    public HealthBar() {
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.setFullHealth();
    }

    @Override
    public void reset() {

    }

    // Methods
    public void setFullHealth() {
        this.setHealth(100);
    }

    public void setHealth(double percentage) {
        double health = percentage / 100;
        this.setProgress(health);

        // Change color on specific threshold
        if ( percentage > 60) {
            this.changeColorClass("high");
        }
        else if ( percentage > 20) {
            this.changeColorClass("middle");
        }
        else {
            this.changeColorClass("low");
        }
    }

    private void changeColorClass (String str) {
        final ObservableList<String> styleClass = this.getStyleClass();
        String[] classes = new String[] {
            "low",
            "middle",
            "high"
        };

        for (String cl : classes ) {
            styleClass.remove(cl);
        }

        styleClass.add(str);
    }

}
