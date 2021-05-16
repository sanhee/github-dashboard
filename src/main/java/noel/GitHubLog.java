package noel;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GitHubLog {

    private GHRepository ghRepository;

    public GitHubLog(GitHub gitHub, String repository) throws IOException {
        ghRepository = gitHub.getRepository(repository).getSource();
    }

    public String getIssue(int number) {
        try {
            GHIssue issue = ghRepository.getIssue(number);
            return issue.getBody();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findAllIssueCommenter(int issueNum){
        List<GHIssueComment> comments = null;

        try {
            comments = ghRepository.getIssue(issueNum).getComments();
            Set<String> users = new HashSet<>();
            for(GHIssueComment comment : comments){
                users.add(comment.getUser().getLogin());
            }
            String[] ret = new String[users.size()];

            int i = 0;
            for(String user : users){
                ret[i++] = user;
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private GHRepository getRepo(String repositoryName) {
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connect();
            return gitHub.getRepository(repositoryName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
