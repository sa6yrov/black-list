package kg.weare.blacklist.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "bad_guys")
public class BadGuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "inclusion_reason")
    private String inclusionReason;

    @Column(name = "bad_guy_lvl")
    private String badGuyLvl;

    @Column(name = "document_type")
    private String documentType;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    private void setCreatedDate(){
        this.createdDate = LocalDateTime.now();
    }

}
