package akumanomi.Entity.Enum;

public enum FruitTypeEnum {
    MERA_MERA(1, "&a&lMera Mera no Mi", "meramera");

    private int fruitTypeID;
    private String fruitName;
    private String commandAlias;

    public int getValue() {
        return fruitTypeID;
    }

    public String getName() {
        return fruitName;
    }

    public String getCommandAlias() {
        return commandAlias;
    }

    public static FruitTypeEnum fromCommandAlias(String commandAlias) {
       FruitTypeEnum[] fruitTypes = FruitTypeEnum.values();
       for (FruitTypeEnum fruitTypeEnum : fruitTypes) {
        if(fruitTypeEnum.getCommandAlias() == commandAlias) {
            return fruitTypeEnum;
        }
       }
       return null;
    }

    FruitTypeEnum(int fruitTypeID, String fruitName, String commandAlias) {
        this.fruitTypeID = fruitTypeID;
        this.fruitName = fruitName;
        this.commandAlias = commandAlias;
    }


}