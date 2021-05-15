package noel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GitHubLogTest {
    private final int ISSUE = 1;
    private final String REPOSITORY = "sanhee/github-dashboard";
    private GitHubLog gitHubLog;

    @BeforeEach
    void setup(){
        gitHubLog = new GitHubLog(REPOSITORY);
    }

    @Test
    void getIssue(){
        assertAll(
                ()->assertEquals(gitHubLog.getIssue(ISSUE),"참여율 계산")
        );

    }

    @Test
    void findAllIssueCommenter(){
        String user = "sanhee";
        assertThat(gitHubLog.findAllIssueCommenter(ISSUE)).contains(user).hasSize(1);
    }

}
