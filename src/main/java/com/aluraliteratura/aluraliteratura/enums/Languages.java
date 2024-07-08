package com.aluraliteratura.aluraliteratura.enums;

public enum Languages {
    ES("es", "español"),
    EN("en", "inglés"),
    FR("fr", "frances"),
    PR("pr", "portugues");

    private final String texto;
    private final String abreviatura;

    Languages(String abreviatura, String texto) {
        this.texto = texto;
        this.abreviatura = abreviatura;
    }

    public static Languages fromTextoToLanguage(String text){
        for(Languages language : Languages.values()) {
            if (language.texto.equalsIgnoreCase(text))
                return language;
        }
        throw new IllegalArgumentException("No se encontró el idioma: " + text);
    }

    public static Languages fromAbreviaturaToLanguage(String abreviatura){
        for(Languages language : Languages.values()) {
            if (language.abreviatura.equalsIgnoreCase(abreviatura))
                return language;
        }
        throw new IllegalArgumentException("No se encontró el idioma: " + abreviatura);
    }


}
