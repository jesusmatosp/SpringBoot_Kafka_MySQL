package pe.com.intercorp.challenge.BatchProcess.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Metadata {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer random;
    private Float randomFloat;
    private Boolean bool;
    private String date;
    private String regEx;
    private String vEnum;
    //private List<String> elt;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person person;
    private Date lastUpdate;
    private Date lastModified;
}
