package com.jpinson.pendujfx.components.pane;

import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ConstrainedGridPane extends GridPane {

    public ConstrainedGridPane() {
        super();
    }

    public void setColumns(double... percents) {
        for (double percent : percents) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(percent);
            col.setFillWidth(true);

            this.getColumnConstraints().add(col);
        }
    }

    public void setRows(double... percents) {
        for (double percent : percents) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(percent);
            row.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(row);
        }
    }

    public ColumnConstraints getColumn(int id) {
        return this.getColumns().get(id);
    }

    public ObservableList<ColumnConstraints> getColumns() {
        return this.getColumnConstraints();
    }

    public RowConstraints getRow(int id) {
        return this.getRows().get(id);
    }

    public ObservableList<RowConstraints> getRows() {
        return this.getRowConstraints();
    }
}
