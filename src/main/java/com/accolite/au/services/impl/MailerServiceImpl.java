package com.accolite.au.services.impl;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.mappers.BusinessUnitMapper;
import com.accolite.au.mappers.SessionMapper;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.services.MailerService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class MailerServiceImpl implements MailerService {

    private final BusinessUnitRepository businessUnitRepository;

    public MailerServiceImpl(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    private final Properties getPropertyProps() throws IOException{

        //      Get properties object
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "578");
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        FileInputStream mailProps = new FileInputStream("/home/abhi/Desktop/Accolite/AU Management System/AuManagementSystemFinal/src/main/resources/application.properties");

        props.load(mailProps);
        return props;
    }

    private final void composeAndSendMail(TrainerDTO trainerDTO, SessionDTO sessionDTO, Session session){
        //      compose message
        try {
            MimeMessage message = new MimeMessage(session);

            List<String> ccList = new ArrayList<>();
            ccList.add(trainerDTO.getReportingManagerEmailId());
            //ccList.add(businessUnitRepository.getOne(trainerDTO.getBusinessUnitId()).getBuHeadEmail());

            for(String mail : ccList) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail));
            }

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainerDTO.getEmailId()));
            message.setSubject(sessionDTO.getSessionName());

            //    MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();

            contentPart.setContent("<p>Hello " + trainerDTO.getTrainerName() + "<br><br>" +
                            "You have been invited to take up the " + sessionDTO.getSessionName() + " session and thank you for your participation!!<br><br>" +
                            "Please refer to the attachments for the guidelines, topics to be covered and presentation template<br><br></p>"+
                            "<p>Some highlights that are required to follow:</p>\r\n" +
                            "<ol>\r\n" +
                            "  <li>Start the Training on time.</li>" +
                            "  <li>Make it Interactive.</li>" +
                            "  <li>Have a Q & A session.</li>" +
                            "<li>Ensure to have hands-on for the sessions wherever applicable.</li>"+
                            "<li>Provide an assignment to the AU�rs and share the evaluations with the AU team.</li>"+
                            "</ol>"+"<p>Please send us the presentation that would be used. "
                            + "Prepare atleast 10 MCQs from the topics to-be-covered as part of the session and share it with AU team by the end of the session. </p>"
                    ,"text/html");

            multipart.addBodyPart(contentPart);

            BodyPart tableContentPart = new MimeBodyPart();
            String tableContent ="<table style='border-collapse:collapse; border:1px solid black'>\r\n" +
                    "  <tr style='text-align:center;padding:10px;border:1px solid black'>\r\n" +
                    "    <th height='50' style='text-align:center;padding:10px;border:1px solid black'>Start Date</th>\r\n" +
                    "    <th height='50' style='text-align:center;padding:10px;border:1px solid black'>End Date</th>\r\n" +
                    "    <th height='50' style='text-align:center;padding:10px;border:1px solid black'>Time</th>\r\n" +
                    "    <th height='50' style='text-align:center;padding:10px;border:1px solid black'>Topic</th>\r\n" +
                    "    <th height='50' style='text-align:center;padding:10px;border:1px solid black'>Classroom Id</th>\r\n" +
                    "  </tr>";

            String slot = sessionDTO.getDaySlot().compareToIgnoreCase("M") == 0 ? "Morning" : "Afternoon";
            tableContent = tableContent.concat(
                    "  <tr style='text-align:center;padding:10px;border:1px solid black'>\r\n" +
                            "    <td height='50' style='text-align:center;padding:10px;border:1px solid black'>"+sessionDTO.getStartDate().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+"</th>\r\n" +
                            "    <td height='50' style='text-align:center;padding:10px;border:1px solid black'>"+sessionDTO.getEndDate().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+"</th>\r\n" +
                            "    <td height='50' style='text-align:center;padding:10px;border:1px solid black'>"+slot+"</th>\r\n" +
                            "    <td height='50' style='text-align:center;padding:10px;border:1px solid black'>"+sessionDTO.getSessionName()+"</th>\r\n" +
                            "    <td height='50' style='text-align:center;padding:10px;border:1px solid black'>"+sessionDTO.getClassroomTopicId()+"</th>\r\n" +
                            "  </tr>");

            //style="border-collapse:collapse; border:1px solid black"
            tableContent = tableContent.concat("</table>" +
                    "<p><b>Note:</b>  This mail contains all the presentations for a given topic as some topics are "
                    + "distributed across different presentations in previous years."
                    + " Please take whatever is necessary based on the topic assigned and ignore the rest.</p>"
                    + "<p>Please reach out to AU core team in case of any queries</p>");
            tableContentPart.setContent(tableContent,"text/html");
            multipart.addBodyPart(tableContentPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}
    }

    @Override
    public void SendMail(TrainerDTO trainerDTO, SessionDTO sessionDTO) throws IOException{

        Properties props = this.getPropertyProps();

        System.out.println(props.getProperty("mailId"));
        System.out.println(props.getProperty("mailPassword"));
        System.out.println(props);

        //get Session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mailId"),props.getProperty("mailPassword"));
                    }
                });

        //        Composing message
        this.composeAndSendMail(trainerDTO, sessionDTO, session);
    }

    @Override
    public void SendMockMail() throws IOException{
        //TrainerDTO trainerDTO = new TrainerDTO(2, 1, "Tarun", "tarun.k", "abhinav.sharma@accolitedigital.com", "abhinavsharma629@gmail.com");
        SessionDTO sessionDTO = new SessionDTO("Spring Boot", "123", Date.valueOf("1998-08-08"), Date.valueOf("1998-08-08"), "M");

        Properties props = this.getPropertyProps();

        System.out.println(props.getProperty("mailId"));
        System.out.println(props.getProperty("mailPassword"));
        System.out.println(props);

        //get Session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mailId"),props.getProperty("mailPassword"));
                    }
                });

        //this.composeAndSendMail(trainerDTO, sessionDTO, session);
    }

    private void addAttachments(Multipart multipart, File dir) throws MessagingException {
        String[] dirList = dir.list();
        for(String name : dirList){
            File file = new File(dir.getAbsolutePath().concat("//").concat(name));
            addAttachment(multipart, file, name);
        }
    }

    //         add attachments
    private static void addAttachment(Multipart multipart, File fileLoc, String fname) throws MessagingException
    {
        System.out.println(fileLoc);
        DataSource source = new FileDataSource(fileLoc);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fname);
        multipart.addBodyPart(messageBodyPart);
    }
}