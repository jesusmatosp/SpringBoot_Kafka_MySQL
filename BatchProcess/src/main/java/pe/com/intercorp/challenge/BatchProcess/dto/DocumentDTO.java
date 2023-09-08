package pe.com.intercorp.challenge.BatchProcess.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentDTO {

    @XmlElement( name = "person" )
    private PersonDTO person;
    private Integer random;

    @XmlElement( name = "random_float" )
    private Float randomFloat;
    private Boolean bool;
    private String date;
    private String regEx;

    @XmlElement( name = "enum" )
    private String vEnum;
    private List<String> elt;


}
