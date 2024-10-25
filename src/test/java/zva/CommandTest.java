package zva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Test
    public void testMoveCommandExecution() {
        Command command = new MoveCommand();
        assertDoesNotThrow(command::execute);
    }

    @Test
    public void testRotateCommandExecution() {
        Command command = new RotateCommand();
        assertDoesNotThrow(command::execute);
    }
}
