package pe.com.intercorp.challenge.Microservice01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Metadata {

    private Long id;
    private Integer random;
    private Float randomFloat;
    private Boolean bool;
    private String date;
    private String regEx;
    private String vEnum;
    //private List<String> elt;
    private Person person;
    private Date lastUpdate;
    private Date lastModified;

}
