package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

/**
 * Created by quentin on 14/10/2016.
 */

public class SignUpSuccessEvent {

    private String uid;
    private String pseudo;

    public SignUpSuccessEvent() {
        super();
    }

    public SignUpSuccessEvent(String uid, String pseudo) {
        this.setUid(uid);
        this.setPseudo(pseudo);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
