package com.company.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "actors")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Integer id;

    @Column(name = "full_name", length = 80)
    @NonNull
    private String fullName;

    @Column(name = "tel_number", length = 15)
    @NonNull
    private String telNumber;

    @Column(name = "role", length = 30)
    @NonNull
    private String role;

    @Column(name = "age")
    @NonNull
    private Integer age;

    @Column(name = "deny_scenario")
    @NonNull
    private Boolean denyScenario;

    @Column(name = "deny_movie_shooting")
    @NonNull
    private Boolean denyMovieShooting;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    @NonNull
    private Scenario scenario;

    public Integer getScenarioId(){
        return scenario.getId();
    }
}
