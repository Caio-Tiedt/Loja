public class Formatar {
    public static String firstCharactes(String input, int quantidade){
        if (input.length() > quantidade)
        {
            return input.substring(0, quantidade);
        }
        else
        {
            while (input.length() != quantidade){
                input = input+" ";
            }
            return input;
        }
    }
}
