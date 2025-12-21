package ru.stqa.mantis.model;

public record UserData(
        String username,
        String password,
        String realName,
        String email,
        String accessLevel,
        boolean enabled,
        boolean _protected) {

    public UserData(){
        this("", "", "Тест Тестович", "", "", true, false);
    }

    public UserData withName(String username){
        return new UserData(username,
                this.password,
                this.realName,
                String.format("%s@localhost", username),
                this.accessLevel,
                this.enabled,
                this._protected);
    }

    public UserData withPassword(String password){
        return new UserData(this.username,
                password,
                this.realName,
                this.email,
                this.accessLevel,
                this.enabled,
                this._protected);
    }
    public UserData withAccessLevel(String accessLevel){
        return new UserData(this.username,
                this.password,
                this.realName,
                this.email,
                accessLevel,
                this.enabled,
                this._protected);
    }
}
