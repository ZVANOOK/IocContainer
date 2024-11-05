package zva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IocContainerThreadTest {
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
        command.execute();
    }

    @Test
    public void testResolveRotateCommand() {
        Command command = container.resolve("rotate");
        command.execute();
    }

    @Test
    public void testResolveNonExistentCommand() {
        assertThrows(IllegalArgumentException.class, () -> container.resolve("unknown"));
    }

    @Test
    public void testResolveCommandsInMultipleThreads() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Создаем пул из 10 потоков
        Future<Void>[] futures = new Future[10];

        for (int i = 0; i < 10; i++) {
            futures[i] = executor.submit(() -> {
                Command command = container.resolve("move");
                assertDoesNotThrow(command::execute);
                return null;
            });
        }

        // Ожидаем завершения всех потоков
        for (Future<Void> future : futures) {
            future.get();
        }

        executor.shutdown(); // Завершаем работу пула
    }
}