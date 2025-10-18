package Garden;

import java.util.List;

/**Інтерфейс для об'єктів з яких можна збирати врожай**/
public interface Harvestable {
    /**Збиратиме врожай з об'єкта, та повертатиме список зібраних плодів**/
    List<Fruit> harvest();
}
