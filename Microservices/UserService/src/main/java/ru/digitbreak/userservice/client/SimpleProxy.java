package ru.digitbreak.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitbreak.library.entity.Simple;
import ru.digitbreak.library.entity.api.IGenericProxyAPI;

import java.util.List;

import static ru.digitbreak.library.entity.GenericAbstractEntity.AUTHORIZATION_HEADER;

@FeignClient(name = "dataservice")
public interface SimpleProxy extends IGenericProxyAPI<Simple>
{

    @Override
    @PostMapping("/api/simples")
    ResponseEntity<Simple> createEntity(
        @RequestBody Simple entity);

    @Override
    @PutMapping("/api/simples")
    ResponseEntity<Simple> updateEntity(
        @RequestBody Simple entity);

    @Override
    @GetMapping("/api/simples/{id}")
    ResponseEntity<Simple> getEntity(
        @PathVariable("id") Long id);

    @Override
    @GetMapping("/api/simples")
    List<Simple> getAllEntites();

    @Override
    @DeleteMapping("/api/simples/{id}")
    ResponseEntity<Void> deleteEntity(
        @PathVariable("id") Long id);
}

