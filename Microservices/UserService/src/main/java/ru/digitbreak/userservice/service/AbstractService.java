package ru.digitbreak.userservice.service;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.digitbreak.library.specification.GenericSpecification;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T,ID> {

    protected Logger log;
    protected Class<T> clazz;

    protected final JpaRepository<T,ID> repository;

    protected final GenericSpecification<T,ID> specification;

    public AbstractService(JpaRepository<T, ID> repository,
                           GenericSpecification<T, ID> specification) {
        this.repository = repository;
        this.specification = specification;
    }

    public T save(T entity) {
        log.debug("Request to save {} : {}",clazz.getCanonicalName(), entity);
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        log.debug("Request to get all {}",clazz.getCanonicalName());
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<T> findOne(ID id) {
        log.debug("Request to get {} : {}",clazz.getCanonicalName(), id);
        return repository.findById(id);
    }

    public void delete(ID id) {
        log.debug("Request to delete {} : {}",clazz.getCanonicalName(), id);
        repository.deleteById(id);
    }
}
