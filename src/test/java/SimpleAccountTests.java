import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleAccountTests {
    private static Account accountDebit;
    private static long currentBalanceDebit;

    @BeforeAll
    public static void initializeBeforeBalance() {
        accountDebit = new SimpleAccount();
        currentBalanceDebit = accountDebit.getBalance();
    }
    public static Stream<Arguments> getParametrizedTestAddParameters() {
        return Stream.of(Arguments.of(0),
                Arguments.of(500000),
                Arguments.of(3_000_000));
    }

    public static Stream<Arguments> getParametrizedTestPayParameters() {
        return Stream.of(Arguments.of( 1000),
                Arguments.of( 4_000_000),
                Arguments.of( 500000));
    }


    @Order(1)
    @ParameterizedTest
    @MethodSource("getParametrizedTestAddParameters")
    public void parametrizedTestAddSimpleAccount(long amount) {
        long expendedBalance = currentBalanceDebit + amount;
        // act
        accountDebit.add(amount);
        var actualBalance = accountDebit.getBalance();
        System.out.println("Баланс дебетого счета после пополнения: " + actualBalance);
        // assert
        Assertions.assertEquals(expendedBalance, actualBalance);
        System.out.println("Баланс пополнен успешно!");
        currentBalanceDebit = actualBalance;
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("getParametrizedTestPayParameters")
    public void parametrizedTestPaySimpleAccount(long amount) {
        long expendedBalance = currentBalanceDebit - amount;
        // act
        if (currentBalanceDebit > amount) {
            accountDebit.pay(amount);
            var actualBalance = accountDebit.getBalance();
            System.out.println("Списание по счету выполнено успешно. Баланс по счету составляет: " + actualBalance);
            // assert
            Assertions.assertEquals(expendedBalance, actualBalance);
            currentBalanceDebit = actualBalance;
        }
        if (currentBalanceDebit <= amount) {
            var actualBalance = accountDebit.getBalance();
            System.out.println("Недостаточно средств для совершения операции. Баланс по счету составляет: " + actualBalance);
            // assert
            Assertions.assertEquals(expendedBalance, actualBalance);
            currentBalanceDebit = actualBalance;
        }
    }
}