package cn.itcast.travel.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailInfo {
    private String mailServerHost;
    private String mailServerPort;
    private String fromAddress;
    private String userName;
    private String password;
    private String[] toAddress;
    private String[] ccAddress;
    private String subject;
    private String content;
    private String[] attachFileNames;
    private boolean validate = false;

    public MailInfo() {
    }

    public Properties getProperties() {
        Properties p = System.getProperties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", this.validate ? "true" : "false");
        p.put("mail.smtp.timeout", '\ufde8');
        p.put("mail.smtp.connectiontimeout", '\ufde8');
        return p;
    }

    public String getMailServerHost() {
        return this.mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return this.mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getToAddress() {
        return this.toAddress;
    }

    public String[] getValidateEmails(String[] toAddress) {
        List<String> emailList = new ArrayList();

        for(int i = 0; i < toAddress.length; ++i) {
            String tempEmail = toAddress[i];
            if (this.isEmail(tempEmail)) {
                emailList.add(tempEmail);
            }
        }

        String[] ultimateAddress = (String[])emailList.toArray(new String[emailList.size()]);
        return ultimateAddress;
    }

    public boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public boolean isEmail(String str) {
        String regex = "^\\s*\\w+(?:\\.{0,1}\\s{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return this.match(regex, str);
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = this.getValidateEmails(toAddress);
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return this.attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public boolean isValidate() {
        return this.validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getCcAddress() {
        return this.ccAddress;
    }

    public void setCcAddress(String[] ccAddress) {
        this.ccAddress = this.getValidateEmails(ccAddress);
    }
}
