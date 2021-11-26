package com.company.models;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;

@Table(name = "edited_material")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class EditedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edited_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    @NonNull
    private String name;

    @Column(name = "duration")
    @NonNull
    private Integer duration;

    @Column(name = "status", length = 30)
    @NonNull
    private String status;

    @ManyToOne
    @JoinColumn(name = "raw_id")
    @NonNull
    private RawMaterial raw;

    public Integer getRawMaterialId(){
        return raw.getId();
    }
}
