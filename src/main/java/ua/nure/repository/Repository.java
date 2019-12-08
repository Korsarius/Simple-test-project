package ua.nure.repository;

import java.util.Collection;

/**
 * Interface for the repository pattern. Offers a way to apply CRUD operations
 * into a collection of instances taken from a hidden source.
 *
 * @param <T> the type stored on the repository
 */
public interface Repository<T> {

    /**
     * Adds  an entity to  the repository.
     *
     * @param entity the entity to add
     */
    void add(final T entity);

    /**
     * Removes an entity  from repository.
     *
     * @param entity the entity to remove
     */
    void remove(final T entity);

    /**
     * Updates an entity on the repository.
     *
     * @param entity the entity to update.
     */
    void update(T entity);

    /**
     * Returns all the entities contained in the repository.
     *
     * @return all the entities contained in the repository
     */
    Collection<T> findAll();

}
