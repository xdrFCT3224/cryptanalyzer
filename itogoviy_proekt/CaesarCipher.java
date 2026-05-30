public class CaesarCipher {

    public static final String ALPHABET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдежзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            " .,!?\"':;-()";

    public static String encrypt(String text, int key) {

        StringBuilder result = new StringBuilder();//сюда будем собирать результат

        for (char ch : text.toCharArray()) {//перебираем каждый символ текста
            int index = ALPHABET.indexOf(ch);//ишем позицию символа в алфавите

            if (index != -1) {//если символ найден

                int newIndex = (index + key) % ALPHABET.length();//шифровка , вычисляем новую позицию.

                result.append(ALPHABET.charAt(newIndex));

            } else {//Если символа нет в ALPHABET оставляем его без изменений
                result.append(ch);//если символа нет в алфовите записываем как есть
            }
        }

        return result.toString();//возывращаем готовый текст
    }

    public static String decrypt(String text, int key) {

        return encrypt(text, ALPHABET.length() - (key % ALPHABET.length()));//расшифровка
    }
}