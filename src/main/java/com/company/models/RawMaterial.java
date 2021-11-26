package com.company.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "raw_material")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raw_id", nullable = false)
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
    @JoinColumn(name = "scenario_id")
    @NonNull
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @NonNull
    private TechTask task;

    public Integer getScenarioId(){
        return scenario.getId();
    }

    public Integer getTechTaskId(){
        return task.getId();
    }
}
