package com.rightmove.es.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rightmove.es.domain.Player;

@Repository
@Transactional
public class PlayerDaoImpl implements com.rightmove.es.dao.PlayerDao {

	@PersistenceContext
	private EntityManager	em;

	@Override
	public List<Player> listAll(int totalPerPage, int pageNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> query = cb.createQuery(Player.class);
		Root<Player> root = query.from(Player.class);

		TypedQuery<Player> typedQuery = em.createQuery(query.select(root));
		typedQuery.setFirstResult(totalPerPage * (pageNumber - 1));
		typedQuery.setMaxResults(totalPerPage);
		return typedQuery.getResultList();
	}

	@Override
	public Player findByEmail(String email) {
		return em.find(Player.class, email);
	}

	@Override
	public Player createPlayer(Player player) {
		em.persist(player);
		return player;
	}
}
