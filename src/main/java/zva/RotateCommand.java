package zva;

public class RotateCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Поворачиваем!");
    }
}
