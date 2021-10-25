package app.repo;

import app.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {
    @Autowired
    EntityManager em;

    @Override
    public List<User> findUsersBy(Long id, String email, String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> User = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(User.get("name"), "%" + name + "%"));
        }
        if (email != null) {
            predicates.add(cb.like(User.get("email"), "%" + email + "%"));
        }

        if (id != null) {
            predicates.add(cb.equal(User.get("id"), id));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
