package model;

public record ContactData(String id, String firstname, String lastname, String address, String email, String email2, String email3, String photo) {

    public ContactData(){
        this("", "","","","","","","");
    }

    public ContactData withId(String id) {
        return  new ContactData(id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return  new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return  new ContactData(this.id, this.firstname, lastname, this.address, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withAddress(String photo) {
        return  new ContactData(this.id, this.firstname, this.lastname, address, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withEmail(String email, String email2, String email3) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, email, email2, email3, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, photo);
    }
}