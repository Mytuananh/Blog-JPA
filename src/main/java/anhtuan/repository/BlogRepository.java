package anhtuan.repository;

import anhtuan.model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Blog> findAll() {
        String sql = "select b from Blog b";
        TypedQuery<Blog> query = em.createQuery(sql,Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        String sql = "select b from Blog b where b.id = :id";
        TypedQuery<Blog> query = em.createQuery(sql,Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        }


    @Override
    public void save(Blog blog) {
        if (blog.getId() != null) {
            em.merge(blog);
        } else {
            em.persist(blog);
        }
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            em.remove(blog);
        }
    }
}
