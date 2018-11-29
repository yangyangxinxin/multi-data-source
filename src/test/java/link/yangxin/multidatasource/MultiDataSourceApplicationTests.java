package link.yangxin.multidatasource;

import link.yangxin.multidatasource.dal.p.User;
import link.yangxin.multidatasource.dal.p.UserDao;
import link.yangxin.multidatasource.dal.s.Message;
import link.yangxin.multidatasource.dal.s.MessageDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDataSourceApplicationTests {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    @Resource
    private UserDao userDao;

    @Resource
    private MessageDao messageDao;

    @Test
    public void contextLoads() {
        primaryJdbcTemplate.execute("delete from user");
        secondJdbcTemplate.execute("delete from user");
    }

    @Test
    @Transactional(rollbackFor = Exception.class,value = "transactionManagerPrimary")
    public void testJpa(){
        User user = new User();
        user.setName("yangxin");
        userDao.save(user);

        Message message = new Message();
        message.setContent("hello world !");
        messageDao.save(message);
        if (1 == 1) {
            throw new RuntimeException();
        }

    }

}
