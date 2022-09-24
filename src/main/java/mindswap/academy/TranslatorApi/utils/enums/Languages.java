package mindswap.academy.TranslatorApi.utils.enums;

public enum Languages {
    BG( "Bulgarian", "BG"),
    CS( "Czech", "CS"),
    DA( "Danish", "DA"),
    DE( "German", "DE"),
    EL( "Greek", "EL"),
    EN( "English", "EN"),
    ES( "Spanish", "ES"),
    ET( "Estonian", "ET"),
    FI( "Finnish", "FI"),
    FR( "French", "FR"),
    HU( "Hungarian", "HU"),
    ID( "Indonesian", "ID"),
    IT( "Italian", "IT"),
    JA( "Japanese", "JA"),
    LT( "Lithuanian", "LT"),
    LV( "Latvian", "LV"),
    NL( "Dutch", "NL"),
    PL( "Polish", "PL"),
    PT( "Portuguese", "PT"),
    RO( "Romanian", "RO"),
    RU( "Russian", "RU"),
    SK( "Slovak", "SK"),
    SL( "Slovenian", "SL"),
    SV( "Swedish", "SV"),
    TR( "Turkish", "TR"),
    UK( "Ukrainian", "UK"),
    ZH( "Chinese", "ZH");
    ;
    private final String language;
    private final String languageCode;

    Languages(String language, String languageCode) {
        this.language = language;
        this.languageCode = languageCode;
    }

    public String getLanguage() {
        return language;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String toString() {
        return languageCode;
    }
}
