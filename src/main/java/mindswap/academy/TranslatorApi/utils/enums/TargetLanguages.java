package mindswap.academy.TranslatorApi.utils.enums;

public enum TargetLanguages {

    BG("Bulgarian"),
    CS("Czech"),
    DA("Danish"),
    DE("German"),
    EL("Greek"),
    EN("English"),
    ES("Spanish"),
    ET("Estonian"),
    FI("Finnish"),
    FR("French"),
    HU("Hungarian"),
    ID("Indonesian"),
    IT("Italian"),
    JA("Japanese"),
    LT("Lithuanian"),
    LV("Latvian"),
    NL("Dutch"),
    PL("Polish"),
    PT("Portuguese"),
    RO("Romanian"),
    RU("Russian"),
    SK("Slovak"),
    SL("Slovenian"),
    SV("Swedish"),
    TR("Turkish"),
    UK("Ukrainian"),
    ZH("Chinese");

    private String description;

    TargetLanguages(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
