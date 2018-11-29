package link.yangxin.multidatasource.dal.s;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangxin
 * @date 2018/11/29
 */
@Repository
public interface MessageDao extends JpaRepository<Message, Long> {
}