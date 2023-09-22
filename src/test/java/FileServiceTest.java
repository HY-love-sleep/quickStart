import com.hy.springbootquickstart.Application;
import com.hy.springbootquickstart.entity.AlarmRule;
import com.hy.springbootquickstart.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Description: 文件服务测试类
 * Author: yhong
 * Date: 2023/9/22
 */
@SpringBootTest(classes = Application.class)
@Slf4j
public class FileServiceTest {
    @Value("${file.baseUrl}")
    String baseUrl;
    @Value("${file.excelPath}")
    String excelPath;

    @Autowired
    private FileService<AlarmRule> fileService;

    @Test
    public void pageToExcelTest() throws IOException {
        fileService.pageToExcel(baseUrl, excelPath);
        log.info("inserted to excel");
    }
}
