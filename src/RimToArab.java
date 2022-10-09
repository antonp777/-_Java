public enum RimToArab {
    I("1"), II("2"), III("3"), IV("4"), V("5"),
    VI("6"), VII("7"), VIII("8"), IX("9"), X("10");

    private String number_rim;
    RimToArab (String number_rim)
    {
        this.number_rim = number_rim;
    }
    public String Get_number()
    {
        return number_rim;
    }

    public static RimToArab get_number_rim(String number)
    {
        for (RimToArab number_ : values())
        {

            if (number_.Get_number().equals(number))
            {
                return number_;
            }
        }
        return null;
    }
}