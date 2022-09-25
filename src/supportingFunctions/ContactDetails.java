package supportingFunctions;

public interface ContactDetails{
    String helpline = "180-678";
    String address = "no.34 X street y-00057";
    String email = "bankservice@db.com";

    String contactUS(String str) throws SecurityAlert;
}