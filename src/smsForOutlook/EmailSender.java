package smsForOutlook;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * EmailSender is a class used to send emails through outlook.  This class will allow you to login,
 * authenticate, and send custom messages remotely through outlook using JavaMail API.
 */
public class EmailSender {

	private String username;
	private String password;
	private String from;
	private String recipient;
	private String subjectText;
	private String messageText;

	/**
	 * Constructor - Creates a new EmailSender object that can send email through connected outlook/office account
	 */
	public EmailSender() {
		this.username = "";
		this.password = "";
		this.from = "";
		this.recipient = "";
		this.subjectText = "";
		this.messageText = "No message sent";
	}


	/**
	 * Constructor - Creates a new EmailSender object that can send email through connected outlook/office account
	 * 
	 * @param username - Your office 365 username (almost always your email)
	 * @param password - Your office 365 password
	 * @param from - Your email address
	 * @param recipient - Who the email will be sent to;
	 */
	public EmailSender(String username, String password, String from, String recipient) {
		this.username = username;
		this.password = password;
		this.from = from;
		this.recipient = recipient;
		
		this.subjectText = "";
		this.messageText = "No message sent";
	}


    /**
     * Sends a new email.  It is necessary that signIn is called before this method.
     */
    public void sendEmail()  {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // smtp-mail.outlook.com
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.office365.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        try {
            Message message = createMessage(session);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private Message createMessage(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(this.subjectText);
        message.setText(this.messageText);

        return message;
    }

	/**
	 * This method will sign into office 365.  Throws exception if authentication fails.
	 *
	 * @param username - Your office 365 username (almost always your email)
     * @param password - Your office 365 password
	 * @throws Exception
	 */
	public void signIn(String username, String password) throws Exception {
        setUsername(username);
        setPassword(password);
        setFrom(username);


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // smtp-mail.outlook.com
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.office365.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailSender.this.username, password);
            }
        });

        session.setDebug(true);

        Transport transport;
        transport = session.getTransport("smtp");
        transport.connect("smtp.office365.com", this.username, password);
        transport.close();

    }





    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setFrom(String sender) {
		this.from = sender;
	}


	public void setRecipient(String receiver) {
		this.recipient = receiver;
	}


	public void setMessageText(String message) {
		this.messageText = message;
	}


	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}
}