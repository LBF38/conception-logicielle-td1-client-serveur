package strategyPattern;

public class SecondStrategy extends Strategy {
    @Override
    public void execute() {
        System.out.println("SecondStrategy");
    }
}
