package ru.digitbreak.userservice.domain;


import ru.digitbreak.library.entity.IGenericEntity;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity<T> implements IGenericEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public T id(Long id){
        this.id = id;
        return (T)this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity element = (AbstractEntity) o;
        return element.getId() != null && getId() != null && Objects.equals(getId(), element.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
