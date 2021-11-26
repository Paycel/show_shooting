package com.company.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "tech_task")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TechTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Integer id;

    @Column(name = "task_link")
    @NonNull
    private String taskLink;
}
