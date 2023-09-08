package pe.com.intercorp.challenge.BatchProcess.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {

    @XmlAttribute( name = "firstname")
    private String firstName;
    @XmlAttribute( name = "lastname" )
    private String lastName;
    @XmlAttribute( name = "city" )
    private String city;
    @XmlAttribute( name = "country" )
    private String country;
    @XmlAttribute( name = "firstname2" )
    private String firstName2;
    @XmlAttribute( name = "lastname2" )
    private String lastName2;
    @XmlAttribute( name = "email" )
    private String email;

}
