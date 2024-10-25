package zva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IocContainerTest {
    private IocContainer container;

    @BeforeEach
    public void setUp() {
        container = new IocContainer();
        container.register("move", args -> new MoveCommand());
        container.register("rotate", args -> new RotateCommand());
    }

    @Test
    public void testResolveMoveCommand() {
        Command command = container.resolve("move");
        command.execute(); // Should print "Moving forward"
    }

    @Test
    public void testResolveRotateCommand() {
        Command command = container.resolve("rotate");
        command.execute(); // Should print "Rotating"
    }

    @Test
    public void testResolveNonExistentCommand() {
        assertThrows(IllegalArgumentException.class, () -> container.resolve("unknown"));
    }
}
