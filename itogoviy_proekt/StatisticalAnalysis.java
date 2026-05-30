import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalysis {

    public static int findKey(String text) {

        Map<Character, Integer> frequencyMap = new HashMap<>();//кодировка

        for (char ch : text.toCharArray()) {//цикл 

            if (Character.isLetter(ch)) {

                frequencyMap.put(ch,
                        frequencyMap.getOrDefault(ch, 0) + 1);
            }
        }

        char mostFrequent = ' ';

        int max = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) { //тут находятся самые часто повторяющ символы 

            if (entry.getValue() > max) {

                max = entry.getValue();

                mostFrequent = entry.getKey();
            }
        }

        char expected = 'о';// "о" встречается чаще всего в руско языыч текстах

        int encryptedIndex =
                CaesarCipher.ALPHABET.indexOf(mostFrequent);//код самого частого встречного символа

        int expectedIndex =
                CaesarCipher.ALPHABET.indexOf(expected);//код буквы "о"

        int key = encryptedIndex - expectedIndex; //вычисляем ключ

        if (key < 0) {
            key += CaesarCipher.ALPHABET.length();//обработка ОШИБКИ если ключ - то он прибавляет к нему длину алфавита 
        }

        return key;//возврат ключа
    }
}