package com.smolenskyi.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.*;

import com.smolenskyi.springmvc.entity.Part;
import com.smolenskyi.springmvc.service.FilterEntity;
import com.smolenskyi.springmvc.service.SortEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PartDAOImpl implements PartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Part> getParts() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Part> cq = cb.createQuery(Part.class);
		Root<Part> root = cq.from(Part.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

    @Override
    public List<Part> getParts(String search, String sort, String filter) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Part> cq = cb.createQuery(Part.class);
        Root<Part> root = cq.from(Part.class);

        List<Predicate> predicatesWhere = new ArrayList<Predicate>();
        if(search != null) {
			predicatesWhere.add(cb.like(root.get("namePart"), "%" + search + "%"));
		}
        if(filter != null && filter.equals(FilterEntity.NeedParts.name())) {
			predicatesWhere.add(cb.equal(root.get("requirement"), true));
		}
        if(filter != null && filter.equals(FilterEntity.OptionalParts.name())) {
			predicatesWhere.add(cb.equal(root.get("requirement"), false));
		}

		Order OrderBy;
		OrderBy = cb.asc(root.get("namePart"));
		if(sort != null && sort.equals(SortEntity.SortNameAZ.name())) {
			OrderBy = cb.asc(root.get("namePart"));
		}
		if(sort != null && sort.equals(SortEntity.SortNameZA.name())) {
			OrderBy = cb.desc(root.get("namePart"));
		}
		if(sort != null && sort.equals(SortEntity.SortCountToMax.name())) {
			OrderBy = cb.asc(root.get("countInStock"));
		}
		if(sort != null && sort.equals(SortEntity.SortCountToMin.name())) {
			OrderBy = cb.desc(root.get("countInStock"));
		}

        cq.select(root).where(predicatesWhere.toArray(new Predicate[predicatesWhere.size()])).orderBy(OrderBy);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
	public void deletePart(int id) {
		Session session = sessionFactory.getCurrentSession();
		Part book = session.byId(Part.class).load(id);
		session.delete(book);
	}

	@Override
	public void savePart(Part thePart) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(thePart);
	}

	@Override
	public Part getPart(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Part thePart = currentSession.get(Part.class, theId);
		return thePart;
	}
}
