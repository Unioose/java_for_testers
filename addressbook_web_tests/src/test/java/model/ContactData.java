package model;

public record ContactData(String firstname, String lastname, String address, String email, String email2, String email3) {

    public ContactData(){
        this("","","","","","");
    }

    public ContactData withFirstName(String firstname) {
        return  new ContactData(firstname, this.lastname, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withLastName(String lastname) {
        return  new ContactData(this.firstname, lastname, this.address, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return  new ContactData(this.firstname, this.lastname, address, this.email, this.email2, this.email3);
    }

    public ContactData withEmail(String email, String email2, String email3) {
        return  new ContactData(this.firstname, this.lastname, this.address, email, email2, email3);
    }
}