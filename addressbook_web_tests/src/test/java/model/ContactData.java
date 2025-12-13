package model;

public record ContactData(
        String id,
        String firstname,
        String lastname,
        String address,
        String email,
        String email2,
        String email3,
        String photo,
        String home,
        String mobile,
        String work,
        String secondary) {

    public ContactData(){
        this("", "","","","","","","", "", "", "", "");
    }

    public ContactData withId(String id) {
        return  new ContactData(id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withFirstName(String firstname) {
        return  new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastName(String lastname) {
        return  new ContactData(this.id, this.firstname, lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withAddress(String address) {
        return  new ContactData(this.id, this.firstname, this.lastname, address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withEmail(String email) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withEmail2(String email2) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, email2, this.email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withEmail3(String email3) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, email3, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withHome(String home) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, home, this.mobile, this.work, this.secondary);
    }

    public ContactData withMobile(String mobile) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, mobile, this.work, this.secondary);
    }

    public ContactData withWork(String work) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, work, this.secondary);
    }

    public ContactData withSecondary(String secondary) {
        return  new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, secondary);
    }
}