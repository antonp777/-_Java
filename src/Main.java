import java.util.Scanner;

public class Main {
    private static final String[] NUMBER_ARAB = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] NUMBER_RIM = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) throws Exception {
        System.out.println("-----------\nКалькулятор умеет выполнять операции сложения(+), вычитания(-), умножения(*) и деления(/) с " +
                "двумя числами\nЧисла могут быть от 1 до 10 включительно, как арабские, так и римские\n" +
                "Пример необходимо вводить в одну строку, например, 10*2 или IV+III\n-----------\nВведите пример");
        Scanner scaner = new Scanner(System.in);
        String input = scaner.nextLine();

        System.out.println(calc(input));
    }


    public static String calc(String input) throws Exception {
        String[] sign_arr = {"+", "-", "*", "/"};
        String sing_math = "";
        String result_math = "";
        int x1 = 0;
        int x2 = 0;
        int result = 0;

        input = input.replaceAll("\\s+","");

        for (String i: sign_arr)
        {
            int y = input.indexOf(i);
            if (y > 0 && y <= 4)
            {
                sing_math = i;
                break;
            }
        }

        if (sing_math == "") {
        throw new Exception("строка не является математической операцией");
        }

        String[] numbers = input.split("\\".concat(sing_math));
        String number1 = numbers[0];
        String number2 = numbers[1];
        if (numbers.length > 2)
        {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        String number1_type = number_type (number1);
        String number2_type = number_type (number2);

        if (number1_type == "arab" && number2_type == "arab")
        {
            x1 = Integer.parseInt(number1);
            x2 = Integer.parseInt(number2);
        }
        else if (number1_type == "rim" && number2_type == "rim")
        {
            RimToArab number_1 = RimToArab.valueOf(number1);
            x1 = Integer.parseInt(number_1.Get_number());
            RimToArab number_2 = RimToArab.valueOf(number2);
            x2 = Integer.parseInt(number_2.Get_number());
        }
        else if (number1_type == "no_type" || number2_type == "no_type")
        {
            throw new Exception("строка не является математической операцией");
        }
        else if ((number1_type == "arab" && number2_type == "rim") || (number1_type == "rim" && number2_type == "arab"))
        {
            throw new Exception("используются одновременно разные системы исчисления");
        }

        switch (sing_math)
        {
            case "+":
                result = x1+x2;
                break;

            case "-":
                result = x1-x2;
                break;
            case "*":
                result = x1*x2;
                break;
            case "/":
                result = x1/x2;
                break;
        }
        result_math = Integer.toString(result);

        if (number1_type == "rim" && number2_type == "rim")
        {
            if (result > 0)
            {
                //result_math = Integer.toString(result);
                int length_result = result_math.length();
                switch (length_result)
                {
                    case 1:
                        RimToArab number_rim = RimToArab.get_number_rim(result_math);
                        result_math = number_rim.toString();
                        break;
                    case 2:
                        RimToArab10 number_rim10 = RimToArab10.get_number_rim(result_math.substring(0,1));
                        String Fist_number = number_rim10.toString();

                        RimToArab number_rim_ = RimToArab.get_number_rim(result_math.substring(1));
                        String Second_number = number_rim_.toString();

                        result_math = Fist_number.concat(Second_number);
                        break;
                    case 3:
                        result_math = "C";
                        break;
                }
            }
            else
            {
                throw new Exception("в римской системе исчесления отсутвуют отрицательные числа и 0");
            }
        }
        //else {result_math = Integer.toString(result);}

        return result_math;
    }
    public static String number_type(String number) throws Exception
    {
        String number_type = "";
        for (String i: NUMBER_ARAB)
        {
            boolean type = number.equals(i);
            if (type)
            {
                number_type = "arab";
                break;
            }
            number_type = "no_type";
        }

        if (number_type == "no_type")
        {
            for (String i: NUMBER_RIM)
            {
                boolean type = number.equals(i);
                if (type)
                {
                    number_type = "rim";
                    break;
                }
                number_type = "no_type";
            }
        }
        return number_type;
    }

}