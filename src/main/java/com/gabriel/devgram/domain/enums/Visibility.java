package com.gabriel.devgram.domain.enums;

public enum Visibility {

    PUBLIC(0, "VISIBILITY_PUBLIC"),PRIVATE(1,"VISIBILITY_PRIVATE");

    private Integer code;
    private String description;

    Visibility(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    public static Visibility toEnum(String optionOfEnum){
        if(optionOfEnum == null){
            return null;
        }

        for (Visibility optionVisibility : Visibility.values()){
            if(optionOfEnum.equals(optionVisibility.getDescription())){
                return optionVisibility;
            }
        }

        throw new IllegalArgumentException("Opção inválida");
    }

}
