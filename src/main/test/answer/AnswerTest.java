package answer;

import com.alibaba.fastjson.JSON;
import com.computer.network.QuestionnaireApplication;
import com.computer.network.service.AnswerService;
import com.computer.network.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class})
public class AnswerTest {
    @Autowired
    AnswerService answerService;

    @Test
    public void testRetrieveAnswers(){
        ResponseVO responseVO = answerService.reviewAnswers(2);
        Object object = responseVO.getContent();
        String json = JSON.toJSONString(object);
        System.out.println(json);
    }
}
