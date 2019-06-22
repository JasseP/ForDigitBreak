package ru.digitbreak.library.entity;

import com.fasterxml.jackson.annotation.JsonView;
import ru.digitbreak.library.jsonview.View;

public class Simple extends GenericAbstractEntity<Simple>{
    
    @JsonView(View.BASIC.class)
    private String description;
    
    public String getDescription() {
        return description;
    }

    public Simple description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
