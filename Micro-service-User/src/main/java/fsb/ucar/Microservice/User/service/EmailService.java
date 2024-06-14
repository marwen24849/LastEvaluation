package fsb.ucar.Microservice.User.service;

public interface EmailService  {
    public String sendMail(String to, String[] cc, String subject, String body);
}