package ru.digitbreak.userservice.web.proxy;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitbreak.library.entity.GenericAbstractEntity;
import ru.digitbreak.library.entity.api.IGenericProxyAPI;
import ru.digitbreak.userservice.security.SecurityUtils;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

public abstract class AbstractProxyResource<T extends GenericAbstractEntity<T>> {

    protected Logger log;
    protected Class<T> clazz;

    protected final IGenericProxyAPI<T> proxyAPI;

    public AbstractProxyResource(IGenericProxyAPI<T> proxyAPI) {
        this.proxyAPI = proxyAPI;
    }

    /**
     * {@code POST  /} : Create a new entity.
     *
     * @param entity the entity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entity, or with status {@code 400 (Bad Request)} if the entity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<T> createEntity(@RequestBody T entity) throws URISyntaxException {
        log.debug("REST request to proxy save {} : {}",clazz.getCanonicalName(), entity);
        return proxyAPI.createEntity(entity);
    }

    /**
     * {@code PUT  /} : Updates an existing entity.
     *
     * @param entity the entity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entity,
     * or with status {@code 400 (Bad Request)} if the entity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping
    public ResponseEntity<T> updateEntity(@RequestBody T entity) throws URISyntaxException {
        log.debug("REST request to proxy update {} : {}",clazz.getCanonicalName(), entity);
        return proxyAPI.updateEntity(entity);
    }

    /**
     * {@code GET  /} : get all the entity.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simples in body.
     */
    @GetMapping
    public List<T> getAllEntities() {
        log.debug("REST request to proxy get all {}",clazz.getCanonicalName());
        return proxyAPI.getAllEntites();
    }

    /**
     * {@code GET  /:id} : get the "id" entity.
     *
     * @param id the id of the simple to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<T> getEntity(@PathVariable Long id) {
        log.debug("REST request to proxy get {} : {}",clazz.getCanonicalName(), id);
        return proxyAPI.getEntity(id);
    }

    /**
     * {@code DELETE  /simples/:id} : delete the "id" entity.
     *
     * @param id the id of the entity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimple(@PathVariable Long id) {
        log.debug("REST request to proxy delete {} : {}",clazz.getCanonicalName(), id);
        return proxyAPI.deleteEntity(id);
    }
}
