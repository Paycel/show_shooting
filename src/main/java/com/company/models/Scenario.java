package com.company.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "scenario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Scenario {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id", nullable = false)
    private Integer id;

    @Column(name = "approval")
    @NonNull
    private Boolean approval;

    @Column(name = "version")
    @NonNull
    private Integer version;

    @Column(name = "link", length = 50)
    @NonNull
    private String link;
}
