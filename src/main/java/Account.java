import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;

public class Account {
    private final String name;

    public Account(String name) {
        this.name = name;
    }

    @Step("Проверка строки {name}")
    public boolean checkNameToEmboss(String name) {
        if(StringUtils.isEmpty(name))
            return false;
        else {
            boolean isLengthValid = name.length() >= 3 && name.length() <= 19;
            boolean isCountSpaceValid = StringUtils.countMatches(name, " ") == 1;
            boolean isSpaceNotStartOrNotEnd = !(name.startsWith(" ") || name.endsWith(" "));
            return  isLengthValid && isCountSpaceValid && isSpaceNotStartOrNotEnd;
        }
    }
}
