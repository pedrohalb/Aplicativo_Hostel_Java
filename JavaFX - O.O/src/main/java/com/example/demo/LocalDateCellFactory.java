package com.example.demo;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

public class LocalDateCellFactory extends TableCell<Reservation, LocalDate> {
    private final DatePicker datePicker;

    public LocalDateCellFactory() {
        this.datePicker = new DatePicker();
        this.datePicker.setConverter(new LocalDateStringConverter());
        this.datePicker.setOnAction(event -> commitEdit(datePicker.getValue()));
        this.datePicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                commitEdit(datePicker.getValue());
            }
        });
        this.datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
    }

    @Override
    protected void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            this.datePicker.setValue(item);
            setGraphic(this.datePicker);
        }
    }

    @Override
    public void commitEdit(LocalDate newValue) {
        if (!isEditing() && !newValue.equals(getItem())) {
            TableView<Reservation> tableView = getTableView();
            if (tableView != null) {
                TableColumn<Reservation, LocalDate> column = getTableColumn();
                TableColumn.CellEditEvent<Reservation, LocalDate> event = new TableColumn.CellEditEvent<>(
                        tableView, new TablePosition<>(tableView, getIndex(), column),
                        TableColumn.editCommitEvent(), newValue
                );
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(newValue);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(this.getItem().toString());
        setGraphic(null);
    }
}
