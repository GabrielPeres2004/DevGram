package com.gabriel.devgram.domain.enums;

public enum Role {

    USER(0, "ROLE_USER"),
    ADMIN(1, "ROLE_ADMIN");

    private Integer code;
    private String description;

    private Role(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Role toEnum(String optionOfEnum){
        if(optionOfEnum == null){
            return null;
        }

        for(Role optionRole : Role.values()){
            if(optionOfEnum.equals(optionRole.getDescription())){
                return optionRole;
            }
        }

        throw new IllegalArgumentException("Opção inválida");
    }








}
