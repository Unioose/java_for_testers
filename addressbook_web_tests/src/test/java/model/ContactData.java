package model;

public record ContactData(String firstname, String lastname, String address, String email, String email2, String email3) {

    public ContactData(){
        this("","","","","","");
    }

    public ContactData withName(String name) {
        return  new ContactData(firstname, this.lastname, this.address, this.email, this.email2, this.email3);
    }
}