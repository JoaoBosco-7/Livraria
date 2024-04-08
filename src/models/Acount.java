package models;

public abstract class Acount {
    private String acountId;
    private String passWord;

    public Acount(String acountId, String passWord) {
        this.acountId = acountId;
        this.passWord = passWord;
    }

    public String getAcountId() {
        return acountId;
    }

    public void setAcountId(String acountId) {
        this.acountId = acountId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
