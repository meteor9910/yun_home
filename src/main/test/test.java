import com.hopu.service.RoomService;
import com.hopu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.sendSMSCode("17683753387");
    }
}
