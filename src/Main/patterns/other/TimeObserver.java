package Main.patterns.other;

import Main.model.enums.Season;

/**Патерн Observer: Інтерфейс для об'єктів, що реагують на зміну часу.**/
public interface TimeObserver {
    void onDayPass(Season season);
}