package com.jpinson.pendujfx.components.healthBar;

import javafx.scene.control.ProgressBar;

public class HealthBar extends ProgressBar {
    public HealthBar() {
        this.setFullHealth();
        this.setStyle("-fx-background-color: purple");
        this.init();
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

    public void setFullHealth() {
        this.setHealth(100);
    }

    public void init() {

    }
}
