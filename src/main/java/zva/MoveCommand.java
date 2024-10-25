package zva;

public class MoveCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Летим вперед");
    }
}
