package com.rightmove.es.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rightmove.es.dao.GameDao;
import com.rightmove.es.domain.Game;

@Repository
@Transactional
public class GameDaoImpl implements GameDao {

	@PersistenceContext
	private EntityManager	em;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addGame(Game game) {
		em.persist(game);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Game> listAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Game> query = cb.createQuery(Game.class);
		Root<Game> gameRoot = query.from(Game.class);
		return em.createQuery(query.select(gameRoot)).getResultList();
	}

	@Override
	public Game findByPk(Long pk) {
		return em.find(Game.class, pk);
	}

	@Override
	public Collection<Game> findByName(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Game> query = cb.createQuery(Game.class);
		Root<Game> gameRoot = query.from(Game.class);
		query.select(gameRoot).where(cb.equal(gameRoot.get("name"), name));
		return em.createQuery(query).getResultList();
	}

}
