package pe.com.intercorp.challenge.Microservice01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String firstName2;
    private String lastName2;
    private String email;
}
