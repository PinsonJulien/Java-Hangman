package com.jpinson.pendujfx.components.health;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HangedGuy extends Pane implements InitResetInterface {
    private final double minX = 0;
    private final double minY = 0;
    private final double maxX;
    private final double maxY;

    private final ArrayList<Shape> gallowsParts = new ArrayList<>();
    private final ArrayList<Shape> bodyParts = new ArrayList<>();
    private final Text healthText = new Text();

    public HangedGuy (double x, double y) {
        this.maxX = x;
        this.maxY = y;
        this.init();
    }

    // Shows label until a threshold, then hides it.
    // Show each body depending on current life

    @Override
    public void init() {
        this.prefWidth(maxX);
        this.prefHeight(maxY);

        // gallows
        Line bottomGallows = new Line(
            minX,
            maxY,
            maxX,
            maxY
        );

        Line leftGallows = new Line(
            bottomGallows.getStartX(),
            bottomGallows.getStartY(),
            bottomGallows.getStartX(),
            minY
        );

        Line bottomPlankGallows = new Line(
            bottomGallows.getEndX() / 5,
            leftGallows.getStartY(),
            leftGallows.getStartX(),
            leftGallows.getStartY() - leftGallows.getStartY() / 5
        );

        Line topGallows = new Line(
           leftGallows.getEndX(),
           leftGallows.getEndY(),
           bottomGallows.getEndX() /2,
           leftGallows.getEndY()
        );

        Line topPlankGallows = new Line(
            bottomPlankGallows.getStartX(),
            topGallows.getEndY(),
            bottomPlankGallows.getEndX(),
            leftGallows.getEndY() + leftGallows.getStartY() / 5
        );

        Line rightGallows = new Line(
            topGallows.getEndX(),
            topGallows.getEndY(),
            topGallows.getEndX(),
            topPlankGallows.getEndY()
        );

        Circle head = new Circle(
            rightGallows.getEndX(),
            rightGallows.getEndY() + rightGallows.getEndY() / 2,
            rightGallows.getEndY() / 2
        );
        head.setStroke(head.getFill());
        head.setFill(null);

        Line body = new Line(
            head.getCenterX(),
            head.getCenterY() + head.getRadius(),
            head.getCenterX(),
            head.getRadius()*2*4
        );

        Line leftArm = new Line(
            body.getStartX(),
            body.getStartY() + (body.getEndY() - body.getStartY()) / 4,
            body.getStartX() - head.getRadius(),
            body.getStartY() + (body.getEndY() - body.getStartY()) / 2
        );

        Line rightArm = new Line(
            body.getStartX(),
            leftArm.getStartY(),
            body.getStartX() + head.getRadius(),
            leftArm.getEndY()
        );

        Line leftLeg = new Line(
            body.getStartX(),
            body.getEndY(),
            leftArm.getEndX(),
            body.getEndY() + leftArm.getEndY() - leftArm.getStartY()
        );

        Line rightLeg = new Line(
            body.getStartX(),
            leftLeg.getStartY(),
            rightArm.getEndX(),
            leftLeg.getEndY()
        );

        this.getChildren().addAll(
            this.healthText,
            bottomGallows,
            leftGallows,
            bottomPlankGallows,
            topGallows,
            topPlankGallows,
            rightGallows,
            head,
            body,
            leftArm,
            rightArm,
            leftLeg,
            rightLeg
        );

        // Set lists
        gallowsParts.add(bottomGallows);
        gallowsParts.add(leftGallows);
        gallowsParts.add(bottomPlankGallows);
        gallowsParts.add(topGallows);
        gallowsParts.add(topPlankGallows);
        gallowsParts.add(rightGallows);

        bodyParts.add(head);
        bodyParts.add(body);
        bodyParts.add(leftArm);
        bodyParts.add(rightArm);
        bodyParts.add(leftLeg);
        bodyParts.add(rightLeg);

        this.healthText.setVisible(false);
        this.hideBodyParts();
    }

    @Override
    public void reset() {

    }

    // Methods

    public void setHealth(int health) {
        final int len = this.bodyParts.size();

        if (health > len) {
            // Show life text
            this.hideBodyParts();
            this.healthText.setText(String.valueOf(health));
            // reposition the text
            this.healthText.setX(maxX/2 - this.healthText.getLayoutBounds().getWidth() / 2);
            this.healthText.setY(maxY/2 - this.healthText.getLayoutBounds().getHeight() / 2);

            this.healthText.setVisible(true);
        } else {
            // hide life text
            this.healthText.setVisible(false);

            // Shows all parts depending on current life.
            health = len - health -1;
            for (int i = 0; i < len; ++i) {
                Shape shape = this.bodyParts.get(i);
                shape.setVisible((i <= health));
            }
        }
    }

    private void hideBodyParts() {
        for (Shape shape : this.bodyParts) {
            shape.setVisible(false);
        }
    }
}
