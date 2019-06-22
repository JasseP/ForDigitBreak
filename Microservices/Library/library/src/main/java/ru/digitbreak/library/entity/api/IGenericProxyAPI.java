package ru.digitbreak.library.entity.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import static ru.digitbreak.library.entity.GenericAbstractEntity.AUTHORIZATION_HEADER;

public interface IGenericProxyAPI<T> {
    
    public ResponseEntity<T> createEntity(
            @RequestBody T entity);
    
    public ResponseEntity<T> updateEntity(
            @RequestBody T entity);
    
    public List<T> getAllEntites();
    
    public ResponseEntity<T> getEntity(
            @PathVariable("id") Long id);
    
    public ResponseEntity<Void> deleteEntity(
            @PathVariable("id") Long id);
}
