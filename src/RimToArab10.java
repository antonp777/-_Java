public enum RimToArab10 {
    X("1"),XX("2"), XXX("3"), XL("4"), L("5"), LX("6"),
    LXX("7"), LXXX("8"), XC("9");
    private String number_rim;
    RimToArab10 (String number_rim)
    {
        this.number_rim = number_rim;
    }
    public String Get_number()
    {
        return number_rim;
    }

    public static RimToArab10 get_number_rim(String number)
    {
        for (RimToArab10 number_ : values())
        {

            if (number_.Get_number().equals(number))
            {
                return number_;
            }
        }
        return null;
    }
}
