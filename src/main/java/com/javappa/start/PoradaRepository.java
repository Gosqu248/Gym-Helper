package com.javappa.start;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PoradaRepository implements JpaRepository<Porada, Long> {
    @Override
    public List<Porada> findAll() {
        return null;
    }

    @Override
    public List<Porada> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Porada> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Porada> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends Porada> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Porada> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Porada> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Porada getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Porada> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Porada> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Porada> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Porada> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Porada> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Porada> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Porada, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
