package com.rightmove.es.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rightmove.es.dao.ScoreDao;
import com.rightmove.es.domain.Game;
import com.rightmove.es.domain.Player;
import com.rightmove.es.domain.Score;

@Repository
public class ScoreDaoImpl implements ScoreDao {

	@PersistenceContext
	private EntityManager	em;

	@Override
	public List<Score> findTopScores(int maxResults, Game game) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Score> query = cb.createQuery(Score.class);
		Root<Score> root = query.from(Score.class);
		query.select(root);
		query.where(cb.equal(root.get("game"), game));
		//query.orderBy(Order.desc("scoreValue")); TODO
		TypedQuery<Score> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(maxResults);
		return typedQuery.getResultList();
	}

	@Override
	public Score createScore(Score score) {
		em.persist(score);
		return score;
	}

	@Override
	public List<Score> listAll(Game game, Player player) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Score> query = cb.createQuery(Score.class);
		Root<Score> root = query.from(Score.class);
		query.select(root);
		query.where(cb.equal(root.get("game"), game));
		query.where(cb.equal(root.get("player"), player));
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Score> listAll(Game game) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Score> query = cb.createQuery(Score.class);
		Root<Score> root = query.from(Score.class);
		query.select(root);
		query.where(cb.equal(root.get("game"), game));
		return em.createQuery(query).getResultList();
	}

}
