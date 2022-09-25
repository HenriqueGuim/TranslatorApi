package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Translation {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private Long count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Translation that)) return false;
        return getSourceLanguage() == that.getSourceLanguage() && getFinalLanguage() == that.getFinalLanguage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceLanguage(), getFinalLanguage());
    }

    @Override
    public String toString() {
        return "Translation{" +
                "sourceLanguage=" + sourceLanguage +
                ", finalLanguage=" + finalLanguage +
                ", count=" + count +
                '}';
    }
}
