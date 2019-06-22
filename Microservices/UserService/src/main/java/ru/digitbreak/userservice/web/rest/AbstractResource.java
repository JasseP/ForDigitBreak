package ru.digitbreak.userservice.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitbreak.userservice.domain.AbstractEntity;
import ru.digitbreak.userservice.service.AbstractService;
import ru.digitbreak.userservice.web.rest.errors.BadRequestAlertException;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractResource<T extends AbstractEntity<T>,ID extends Serializable> {

    @Value("${jhipster.clientApp.name}")
    protected String applicationName;

    protected Logger log;
    protected Class<T> clazz;
    protected String ENTITY_NAME;

    protected final AbstractService<T,ID> service;

    public AbstractResource(AbstractService<T,ID> service){
        this.service = service;
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
        log.debug("REST request to save {} : {}",clazz.getCanonicalName(), entity);
        if (entity.getId() != null) {
            throw new BadRequestAlertException("A new entity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        T result = service.save(entity);
        return ResponseEntity.created(new URI("/api/"+clazz.getSimpleName().toLowerCase() + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
        log.debug("REST request to update {} : {}",clazz.getCanonicalName(), entity);
        if (entity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        T result = service.save(entity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /} : get all the entity.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simples in body.
     */
    @GetMapping
    public List<T> getAllEntities() {
        log.debug("REST request to get all {}",clazz.getCanonicalName());
        return service.findAll();
    }

    /**
     * {@code GET  /:id} : get the "id" entity.
     *
     * @param id the id of the simple to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<T> getEntity(@PathVariable ID id) {
        log.debug("REST request to get {} : {}",clazz.getCanonicalName(), id);
        Optional<T> entity = service.findOne(id);
        return ResponseUtil.wrapOrNotFound(entity);
    }

    /**
     * {@code DELETE  /simples/:id} : delete the "id" entity.
     *
     * @param id the id of the entity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimple(@PathVariable ID id) {
        log.debug("REST request to delete {} : {}",clazz.getCanonicalName(), id);
        service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
