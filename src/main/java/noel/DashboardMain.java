package noel;

public class DashboardMain {

    private final static String REPOSITORY = "sanhee/baseball";

    public static void main(String[] args) {

        Study study = new Study(REPOSITORY);

        study.searchUserOfPostBy(1, 15);
        study.printOfRate(15);

    }
}
