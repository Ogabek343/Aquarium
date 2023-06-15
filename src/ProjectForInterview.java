import service.MainService;

public class ProjectForInterview {
    private static final MainService mainService = MainService.getInstance();

    public static void main(String[] args){
        System.out.println("Program started");
        mainService.start();
    }
}
