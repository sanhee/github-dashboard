package noel;

import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Statistic {

    public final GitHubLog gitHubLog;

    public Map<String, Integer> participants = new HashMap<>();

    public Statistic(GitHub gitHub, String repository) throws IOException {
        gitHubLog = new GitHubLog(gitHub, repository);
    }

    public Map<String, Integer> searchUserOfPostBy(int issueStartNumber, int issuerEndNumber) {

        for (int i = issueStartNumber; i <= issuerEndNumber; i++) {

            String[] usersOfIssue = gitHubLog.findAllIssueCommenter(i);

            for (String userName : usersOfIssue) {
                if (participants.containsKey(userName)) {
                    int findCount = participants.get(userName);
                    participants.replace(userName, findCount + 1);
                } else {
                    participants.put(userName, 1);
                }
            }
        }
        return participants;
    }


    public void printOfRate(double max) {
        for (Map.Entry<String, Integer> entry : participants.entrySet()) {
            System.out.println("닉네임: " + entry.getKey() + "\t 참여율: " + entry.getValue() / max);
        }

    }

}
