package org.mypetclinic.visitsservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visits")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "pet_id")
    @Column(name = "pet_id")
    private Long petId;


}
