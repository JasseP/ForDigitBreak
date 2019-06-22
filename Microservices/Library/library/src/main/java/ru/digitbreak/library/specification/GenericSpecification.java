package ru.digitbreak.library.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.Collection;

/**
 *
 * @author StFaustoff
 */
public abstract class GenericSpecification<T, ID> {
    
    private Predicate equalPredicate(Root<T> root, CriteriaBuilder builder, String field, Object value) {
        return builder.equal(root.get(field), value);
    }

    private Predicate differentPredicate(Root<T> root, CriteriaBuilder builder, String field, ZonedDateTime value, int sign) {
        if(sign==0)
            return builder.equal(root.get(field), value);
        else if(sign>0)
            return builder.greaterThan(root.get(field), value);
        else
            return builder.lessThan(root.get(field),value);
    }

    private Predicate differentPredicate(Root<T> root, CriteriaBuilder builder, String field, Object value, int sign) {
        if(sign==0)
            return builder.equal(root.get(field), value);
        else if(sign>0)
            return builder.greaterThan(root.get(field), String.valueOf(value));
        else
            return builder.lessThan(root.get(field), String.valueOf(value));
    }
    
    private Predicate betweenPredicate(Root<T> root, CriteriaBuilder builder, String field, Comparable min, Comparable max) {
        return builder.between(root.get(field), min, max);
    }

    private Predicate differentOrEqualToPredicate(Root<T> root, CriteriaBuilder builder, String field, Comparable value, boolean isGreater) {
        if (isGreater) {
            return builder.greaterThanOrEqualTo(root.get(field), value);
        } else {
            return builder.lessThanOrEqualTo(root.get(field), value);
        }
        
    }    
    
    private Predicate inPredicate(Root<T> root, CriteriaBuilder builder, String field, Collection values) {
        return root.get(field).in(values);
    }
    
    public Specification<T> defaultSpecification() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.conjunction();
    }

    public Specification<T> byId(ID id) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(equalPredicate(root, builder, "id", id));
    }

    public Specification<T> byIsActive(boolean isActive) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(equalPredicate(root, builder, "isActive", isActive));
    }

    public Specification<T> byLastModified(Long lastModified) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
            -> builder.and(differentPredicate(root, builder, "lastModified", lastModified,1));
    }

    public Specification<T> equalSpecification(String field, Object valueOfField) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(equalPredicate(root, builder, field, valueOfField));
    }

    public Specification<T> unequalSpecification(String field, Object valueOfField, boolean isGreater) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(differentPredicate(root, builder, field, valueOfField,isGreater?1:-1));
    }
     //TODO: убрать перегруженный метод и сделать по нормальному
     public Specification<T> unequalSpecification(String field, ZonedDateTime valueOfField, boolean isGreater) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(differentPredicate(root, builder, field, valueOfField,isGreater?1:-1));
    }
    
    public Specification<T> greaterThanOrEqualToSpecification(String field, Comparable valueOfField) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(differentOrEqualToPredicate(root, builder, field, valueOfField, true));
    }
    
    public Specification<T> lessThanOrEqualToSpecification(String field, Comparable valueOfField) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(differentOrEqualToPredicate(root, builder, field, valueOfField, false));
    }     
     
    public Specification<T> betweenSpecification(String field, Comparable min, Comparable max) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(betweenPredicate(root, builder, field, min, max));
    }
    public Specification<T> inSpecification(String field, Collection values) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.and(inPredicate(root, builder, field, values));
    }    
}
