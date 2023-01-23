package strategyPattern;

public class FirstStrategy extends Strategy {
    @Override
    public void execute() {
        System.out.println("FirstStrategy");
    }
}
