package noel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GitHubLogTest {
    private final static int FIRST_ISSUE = 1;
    private GitHubLog gitHubLog;

    @BeforeEach
    void setup() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(DashboardMain.TOKEN).build();
        gitHubLog = new GitHubLog(gitHub, DashboardMain.REPOSITORY);
    }

    @Test
    @DisplayName("이슈의 본문을 탐색한다.")
    void getIssue() {
        assertThat(gitHubLog.getIssue(FIRST_ISSUE)).isEqualTo("## 목표" + System.lineSeparator() +
                "자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기." + System.lineSeparator() + System.lineSeparator() +
                "## 학습할 것" + System.lineSeparator() +
                "- JVM이란 무엇인가" + System.lineSeparator() +
                "- 컴파일 하는 방법" + System.lineSeparator() +
                "- 실행하는 방법" + System.lineSeparator() +
                "- 바이트코드란 무엇인가" + System.lineSeparator() +
                "- JIT 컴파일러란 무엇이며 어떻게 동작하는지" + System.lineSeparator() +
                "- JVM 구성 요소" + System.lineSeparator() +
                "- JDK와 JRE의 차이" + System.lineSeparator() + System.lineSeparator() +
                "## 마감 일시 (한국 시간 기준)" + System.lineSeparator() +
                "2020년 11월 21일 오후 10시" + System.lineSeparator());
    }

    @Test
    @DisplayName("첫번째 이슈의 댓글 작성자가 373명인지 확인한다.(중복제거)")
    void findAllIssueCommenter() {
        assertThat(gitHubLog.findAllIssueCommenter(FIRST_ISSUE)).hasSize(373);
    }

}
