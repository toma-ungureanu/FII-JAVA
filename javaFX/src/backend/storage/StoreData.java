package backend.storage;

import backend.database.DatabaseConnection;
import backend.object_oriented_model.MovieAppEmail;
import backend.object_oriented_model.MovieAppUser;
import backend.object_oriented_model.UserAndEmail;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Toma-Florin Ungureanu
 */
public class StoreData
{
    public static String savePath = "storedData/user.xml";
    public StoreData() {}

    public String getAccountInfo(String email)
    {
        try
        {
            CallableStatement callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{? = call INFO_UTILIZATOR(?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, email);
            callableStatement.executeUpdate();
            DatabaseConnection.commit();
            return callableStatement.getString(1);
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public List<UserAndEmail> parseAccountInfo(String accountData)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(accountData, ";");
        MovieAppUser movieAppUser = new MovieAppUser();
        MovieAppEmail email = new MovieAppEmail();
        List<UserAndEmail> accountInfo = new ArrayList<>();

        String header, receivedString;
        int column = 0;
        while (stringTokenizer.hasMoreTokens())
        {
            column++;
            switch (column)
            {
                case 1:
                {
                    header = "id=";
                    receivedString = stringTokenizer.nextToken();
                    movieAppUser.setId(Integer.parseInt(receivedString.
                            substring(header.length())));
                    break;
                }
                case 2:
                {
                    header = "nume=";
                    receivedString = stringTokenizer.nextToken();
                    movieAppUser.setFirstName(receivedString.
                            substring(header.length()));
                    break;
                }
                case 3:
                {
                    header = "prenume=";
                    receivedString = stringTokenizer.nextToken();
                    movieAppUser.setLastName(receivedString.
                            substring(header.length()));
                    break;
                }
                case 4:
                {
                    header = "parola=";
                    receivedString = stringTokenizer.nextToken();
                    movieAppUser.setPassword(receivedString.
                            substring(header.length()));
                    break;
                }
                case 5:
                {
                    header = "email=";
                    receivedString = stringTokenizer.nextToken();
                    email.setId(movieAppUser.getId());
                    email.setEmail(receivedString.
                            substring(header.length()));
                    break;
                }
            }
        }
        accountInfo.add(movieAppUser);
        accountInfo.add(email);
        return accountInfo;
    }

    public void storeAccountData(List<UserAndEmail> userAndEmail)
            throws ParserConfigurationException, TransformerException, IOException
    {
        if (userAndEmail.size() > 2)
        {
            System.out.println("Incorrect info to parse, too few info provided");
            return;
        }

        if (!(userAndEmail.get(0) instanceof MovieAppUser))
        {
            System.out.println("Incorrect order of info");
            return;
        }

        if (!(userAndEmail.get(1) instanceof MovieAppEmail))
        {
            System.out.println("Incorrect info to parse, no email object found");
            return;
        }
        MovieAppUser movieAppUser = (MovieAppUser) userAndEmail.get(0);
        MovieAppEmail movieAppEmail = (MovieAppEmail) userAndEmail.get(1);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

        //User
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("user");
        document.appendChild(root);

        //id
        Attr id = document.createAttribute("userId");
        id.setValue(String.valueOf(movieAppUser.getId()));
        root.setAttributeNode(id);

        //First Name
        Element firstName = document.createElement("firstname");
        firstName.appendChild(document.createTextNode(movieAppUser.getFirstName()));
        root.appendChild(firstName);

        //Last Name
        Element lastName = document.createElement("lastname");
        lastName.appendChild(document.createTextNode(movieAppUser.getLastName()));
        root.appendChild(lastName);

        //Password
        Element password = document.createElement("password");

        //Encode Password
        String encodedPass = Base64.getEncoder().encodeToString(movieAppUser.getPassword()
                .getBytes());
        password.appendChild(document.createTextNode(encodedPass));
        root.appendChild(password);

        //Email
        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(movieAppEmail.getEmail()));
        root.appendChild(email);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        File userDataFile = new File(savePath);
        if (userDataFile.createNewFile())
        {
            System.out.println("File was created!");
        } else
        {
            System.out.println("File already exists.");
        }

        StreamResult result = new StreamResult(userDataFile);

        transformer.transform(source, result);
        System.out.println("File saved!");
    }

    public List<UserAndEmail> readStoredData()
    {
        List<UserAndEmail> userAndEmail = new ArrayList<>();
        MovieAppUser movieAppUser = new MovieAppUser();
        MovieAppEmail movieAppEmail = new MovieAppEmail();
        try
        {
            File userFile = new File(savePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(userFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;

            //First Name
            movieAppUser.setFirstName(eElement.getElementsByTagName("firstname")
                    .item(0).getTextContent());

            //Last Name
            movieAppUser.setLastName(eElement.getElementsByTagName("lastname")
                    .item(0).getTextContent());

            //Id
            movieAppUser.setId(Integer.parseInt(eElement.getAttribute("userId")));

            //Password
            String encryptedPass = eElement.getElementsByTagName("password")
                    .item(0).getTextContent();

            //Decrypted Password
            byte[] decryptedPass = Base64.getDecoder().decode(encryptedPass);
            movieAppUser.setPassword(new String(decryptedPass));

            movieAppEmail.setEmail(eElement.getElementsByTagName("email")
                    .item(0).getTextContent());

            movieAppEmail.setId(movieAppUser.getId());
        } catch (ParserConfigurationException | IOException | SAXException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }

        userAndEmail.add(movieAppUser);
        userAndEmail.add(movieAppEmail);
        return userAndEmail;
    }

}