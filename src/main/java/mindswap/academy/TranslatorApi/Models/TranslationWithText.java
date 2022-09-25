package mindswap.academy.TranslatorApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationWithText {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private String text;
    @JsonIgnore
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TranslationWithText that)) return false;
        return getSourceLanguage() == that.getSourceLanguage() && getFinalLanguage() == that.getFinalLanguage() && getText().equals(that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceLanguage(), getFinalLanguage(), getText());
    }

    @Override
    public String toString() {
        return "TranslationWithText{" +
                "sourceLanguage=" + sourceLanguage +
                ", finalLanguage=" + finalLanguage +
                ", text='" + text + '\'' +
                ", client=" + client +
                '}';
    }
}
