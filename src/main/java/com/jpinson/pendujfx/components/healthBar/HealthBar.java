package com.jpinson.pendujfx.components.healthBar;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
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
        this.setStyle("-fx-background-color: purple");
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
            this.setStyle("-fx-accent: green");
        }
        else if ( percentage > 20) {
            this.setStyle("-fx-accent: yellow");
        }
        else {
            this.setStyle("-fx-accent: red");
        }
    }

}
