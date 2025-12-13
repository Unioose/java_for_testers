package manager.hmb;
import jakarta.persistence.*;



@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;

    public String firstname;

    public String lastname;

    public String address;

    public String email;

    public String email2;

    public String email3;

    public String home;

    public String mobile;

    public String work;

    public String phone2;

    public ContactRecord(){}

    public ContactRecord (int id, String firstname, String lastname, String address, String email, String email2, String email3)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }
}
