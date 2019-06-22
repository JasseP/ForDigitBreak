package ru.digitbreak.library.entity.api.dataservice;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import static ru.digitbreak.library.entity.GenericAbstractEntity.AUTHORIZATION_HEADER;
import ru.digitbreak.library.entity.Simple;
import ru.digitbreak.library.entity.api.IGenericProxyAPI;


@Component
@FeignClient(name = "DataService")
public interface SimpleProxy extends IGenericProxyAPI<Simple>{
    
    @Override    
    @PostMapping("/api/simples")
    public ResponseEntity<Simple> createEntity(
            @RequestBody Simple entity);
    
    @Override
    @PutMapping("/api/simples")
    public ResponseEntity<Simple> updateEntity(
            @RequestBody Simple entity);
    
    @Override
    @GetMapping("/api/simples")
    public List<Simple> getAllEntites();
    
    @Override
    @GetMapping("/api/simples/{id}")
    public ResponseEntity<Simple> getEntity(
            @PathVariable("id") Long id);
    
    @Override
    @DeleteMapping("/api/simples/{id}")
    public ResponseEntity<Void> deleteEntity(
            @PathVariable("id") Long id);
}
