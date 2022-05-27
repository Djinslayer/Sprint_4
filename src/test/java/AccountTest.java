import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String errorMessage;
    private final String name;
    private final boolean expected;

    public AccountTest(String errorMessage, String name, boolean expected) {
        this.errorMessage = errorMessage;
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Строка должна быть корректной (кириллица, 3 символа 1 пробел)", "Н У", true}, //граничное значение (3 символа)
                {"Строка должна быть корректной (кириллица, 4 символа 1 пробел)", "На У", true}, //проверка ближайших значений к границе (4 символа)
                {"Строка должна быть корректной (кириллица, 19 символов 1 пробел)", "Наруто Узумакииииии", true}, //граничное значение (19 символов)
                {"Строка должна быть корректной (кириллица, 18 символов 1 пробел)", "Наруто Узумакиииии", true}, //проверка ближайших значений к границе (18 символов)
                {"Строка должна быть корректной (кириллица, 11 символов 1 пробел)", "Саске Учиха", true}, //значение в диапазоне границ (11 символов)
                {"Строка должна быть корректной (латиница, 14 символов 1 пробел)", "Naruto Uzumaki", true}, // проверка значения с латиницей
                {"Строка должна быть корректной (латиница + кириллица, 14 символов 1 пробел)", "Наруто Uzumaki", true}, // проверка значения с латиницей + кириллица

                {"Строка должна быть не корректной (кириллица, 2 символа)", "На", false}, //проверка ближайших значений к границе (2 символа)
                {"Строка должна быть не корректной (кириллица, 20 символов 1 пробел)", "Наруто Узумакиииииий", false}, //проверка ближайших значений к границе (20 символов)
                {"Строка должна быть не корректной (null строка)", null, false}, // не переданное значение
                {"Строка должна быть не корректной (пустая строка)", "", false}, // пустая строка
                {"Строка должна быть не корректной (строка из 1 пробела)", " ", false}, // строка из пробела
                {"Строка должна быть не корректной (пробел вначале строки)", " СаскеУчиха", false}, //проверка пробела вначале
                {"Строка должна быть не корректной (пробел в конце строки)", "СаскеУчиха ", false}, //проверка пробела в конце
                {"Строка должна быть не корректной (2 пробела подряд в середине)", "Саске  Учиха ", false}, //проверка 2-х подряд пробелов
                {"Строка должна быть не корректной (2 пробела в середине)", "Саске Уч иха ", false}, //проверка 2-х пробелов
                {"Строка должна быть не корректной (отсутствие пробела)", "СаскеУчиха", false}, //проверка отсутствия пробела

//                {"Строка должна быть не корректной (использование спецсимволов)", "&!'#$% *()^@", false}, // проверка использования спецсимволов
                {"Строка должна быть не корректной (использование дефиса)", "Чики-Брики", false}, // проверка использования дефиса
//                {"Строка должна быть не корректной (использование цифр)", "Петр 1", false} // проверка использования цифр
        };
    }

    @Test
    @DisplayName("Проверка валидации имени и фамилии")
    public void checkNameToEmbossTest() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss(name);
        assertEquals(errorMessage, actual, expected);
    }
}