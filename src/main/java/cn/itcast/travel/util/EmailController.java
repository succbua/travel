package cn.itcast.travel.util;



import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;



public class EmailController {

    //账号
    private  String sendto = "";
    //密码
    private  String subject = "";
    //地址
    private  String message = "";

    public EmailController(String sendto, String subject, String message) {
        this.sendto = sendto;
        this.subject = subject;
        this.message = message;
    }

    public EmailController() {
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  Boolean sendEmail(){ ;
        System.out.println("Sending");
        ExchangeClient client = new ExchangeClient.ExchangeClientBuilder()
                .hostname("ex.mychery.com")
                .exchangeVersion(ExchangeVersion.Exchange2010)
                .domain("chery")
                .username("it000230")
                .password("1q2w3e4r.")
                .recipientTo(sendto)
                .subject(subject)
                .message(message)
                .build();
        client.sendExchange();
        System.out.println("Dones");
        return Boolean.TRUE;
    }


}
