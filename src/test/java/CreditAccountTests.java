import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreditAccountTests {
    private static Account accountCredit;
    private static long currentBalanceCredit;
    static final long CREDIT_LIMIT = 1_000_000; // Кредитный лемит

    @BeforeAll
    public static void setUpDebitAccount() {

        accountCredit = new CreditAccount(CREDIT_LIMIT);
        currentBalanceCredit = accountCredit.getBalance();
        System.out.println("Первоначальный кредитный баланс: " + currentBalanceCredit);
    }

//    public static Stream<Arguments> getParametrizedTestCreditAddParameters() {
//        return Stream.of(Arguments.of(1000),
//                Arguments.of(1_000_000),
//                Arguments.of(500000));
//    }

    public static Stream<Arguments> getParametrizedTestCreditPayParameters() {
        return Stream.of(Arguments.of(1000),
                Arguments.of(1_000_000),
                Arguments.of(500000));
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("getParametrizedTestCreditPayParameters")
    public void parametrizedTestPayCreditAccount(long amount) {
        long expendedBalance = currentBalanceCredit - amount;
        // act
        if (expendedBalance < (-CREDIT_LIMIT)) {
            var actualBalance = accountCredit.getBalance();
            System.out.println("Превышен лимит. \n " +
                    "Остаток по лимиту: " + (actualBalance + CREDIT_LIMIT));
            // assert
            Assertions.assertEquals(expendedBalance, actualBalance);
            currentBalanceCredit = actualBalance;
        } else {
            accountCredit.pay(amount);
            var actualBalance = accountCredit.getBalance();
            System.out.println("Операция выполнена успешно. \n" +
                    "Остаток по лимиту: " + (actualBalance + CREDIT_LIMIT));
            // assert
            Assertions.assertEquals(expendedBalance, actualBalance);
            currentBalanceCredit = actualBalance;
        }
    }

//    @Order(2)
//    @ParameterizedTest
//    @MethodSource("getParametrizedTestCreditAddParameters")
//    public void parametrizedTestAddCreditAccount(long amount) {
//        long expendedBalance = -CREDIT_LIMIT + currentBalanceCredit + amount;
//        // act
//        if (expendedBalance > 0) {
//            var actualBalance = accountCredit.getBalance();
//            System.out.println("Счет не может быть пополнен на такую сумму.\n" +
//                    "Максимальная сумма пополнения: " + (-CREDIT_LIMIT - actualBalance));
//        } else {
//            accountCredit.add(amount);
//            var actualBalance = accountCredit.getBalance();
//            System.out.println("Баланс пополнен успешно! Баланс по счету: " + (-actualBalance));
//            // assert
//            Assertions.assertEquals(expendedBalance, actualBalance);
//            currentBalanceCredit = actualBalance;
//        }
//    }
}
