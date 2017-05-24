package cn.kosh.framework.jpa;

import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by kosh on 2017/4/30.
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager em;
    private final JpaEntityInformation<T, ?> entityInfo;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityInfo = entityInformation;
    }

    public void deleteInBatch(ID... ids) {
        delete("delete from %s", ids);
    }

    public void deleteByFlag(ID... ids) {
        delete("update %s set deleted = 1", ids);
    }

    private void delete(String template, ID... ids) {
        if (ids == null) {
            return;
        }
        String queryString = QueryUtils.getQueryString(template, entityInfo.getEntityName());
        if (ids.length == 0) {
            return;
        }
        StringBuilder builder = new StringBuilder(queryString);
        builder.append(" where");
        for (int i = 1; i <= ids.length; i++) {
            builder.append(String.format(" %s = ?%d", entityInfo.getIdAttribute().getName(), i));
            if (i < ids.length) {
                builder.append(" or");
            }
        }
        Query query = em.createQuery(builder.toString());
        int i = 1;
        for (ID id : ids) {
            query.setParameter(i++, id);
        }
        query.executeUpdate();
    }

}
