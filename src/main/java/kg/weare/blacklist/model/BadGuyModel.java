package kg.weare.blacklist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadGuyModel {
    private String fullName;
    private String birthDate;
    private String birthPlace;
    private String inclusionReason;
    private String badGuyLvl;
    private String documentType;

    @Override
    public String toString(){
        return "FullName: " + fullName + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Birth Place: " + birthPlace + "\n" +
                "Inclusion reason: " + inclusionReason + "\n" +
                "Bad guy level: " + badGuyLvl + "\n" +
                "Document Type: " + documentType + "\n" + "------------------------------------------";
    }
}
