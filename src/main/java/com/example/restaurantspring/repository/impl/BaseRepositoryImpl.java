package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.AbstractEntity;
import com.example.restaurant.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class BaseRepositoryImpl<T extends AbstractEntity> implements BaseRepository<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> entityType;

    public BaseRepositoryImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    protected Session getCurrentSession() {
        return sessionFactory.openSession(); // Открываем новую сессию для каждого метода
    }

    @Override
    public Optional<T> findById(Long id) {
        try (Session session = getCurrentSession()) {
            return Optional.ofNullable(session.get(entityType, id));
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = getCurrentSession()) {
            Query<T> query = session.createQuery("FROM " + entityType.getSimpleName(), entityType);
            return query.getResultList();
        }
    }

    @Override
    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity; // Возвращаем сохраненный объект
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // Бросаем исключение дальше для обработки
        }
    }

    @Override
    public T update(Long id, T entity) {
        entity.setId(id); // Устанавливаем id для обновления
        return save(entity); // Используем метод save для обновления
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            if (session.contains(entity)) {
                session.delete(entity);
            } else {
                session.delete(session.merge(entity)); // Если объект не в сессии, объединяем и удаляем
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // Бросаем исключение дальше для обработки
        }
    }

    @Override
    public boolean existsById(Long id) {
        try (Session session = getCurrentSession()) {
            Long count = (Long) session.createQuery("SELECT COUNT(e) FROM " + entityType.getSimpleName() + " e WHERE e.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<T> entityOptional = findById(id);
        entityOptional.ifPresent(this::delete);
    }
}
