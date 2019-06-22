package ru.digitbreak.library.entity;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Objects;
import ru.digitbreak.library.jsonview.View;

public abstract class GenericAbstractEntity<T> implements IGenericEntity<T>{

    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    @JsonView(View.BASIC.class)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (o == null || getClass() != o.getClass())
            return false;
            
        GenericAbstractEntity element = (GenericAbstractEntity) o;
        
        return element.getId() != null && getId() != null && Objects.equals(getId(), element.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    
}
