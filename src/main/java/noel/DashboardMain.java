package noel;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class DashboardMain {

    // 백기선님 스터디 포크 저장소, getSource()를 이용하면 원본 저장소에 접근할 수 있음.
    public final static String REPOSITORY = "sanhee/live-study";

    // 토큰발행: https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token
    public final static String TOKEN = "**********";

    public static void main(String[] args) throws IOException {

        GitHub gitHub = new GitHubBuilder().withOAuthToken(TOKEN).build();

        Statistic statistic = new Statistic(gitHub, REPOSITORY);

        statistic.searchUserOfPostBy(1, 15);
        statistic.printOfRate(15);

    }
}
