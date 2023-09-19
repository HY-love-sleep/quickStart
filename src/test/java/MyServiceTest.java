/**
 * Description: 单元测试
 * Author: yhong
 * Date: 2023/9/18
 */
import com.hy.springbootquickstart.Application;
import com.hy.springbootquickstart.service.LoopCallService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest(classes = Application.class)
public class MyServiceTest {

    @Autowired
    LoopCallService loopCallService;

    @Test
    public void testMyService() {
        List<Long> cal = loopCallService.cal(100);
    }
}

