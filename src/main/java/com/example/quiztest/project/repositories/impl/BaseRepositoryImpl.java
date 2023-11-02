package com.example.quiztest.project.repositories.impl;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.repositories.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public class BaseRepositoryImpl <T extends BaseEntity> extends SimpleJpaRepository<T, Long> implements BaseRepository<T> {
    private final Specification<T> isNotDeletedSpecification = (root, query, cb) -> cb.equal(root.get("deleted"), false);

    public BaseRepositoryImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public T findByIdAndDeletedFalse(Long id) {
        T entity = findById(id).orElse(null);
        if (entity != null && !entity.getDeleted()) {
            return entity;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public T trash(Long id) {
        T entity = findById(id).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            return save(entity);
        }
        return null;
    }

    @Override
    public List<T> trashList(List<Long> ids) {
        return findAll(isNotDeletedSpecification);
    }

    @Override
    public List<T> findAllNotDeleted() {
        return findAll(isNotDeletedSpecification);
    }

    @Override
    public Page<T> findAllNotDeleted(Pageable pageable) {
        return findAll(isNotDeletedSpecification,pageable);
    }
}
