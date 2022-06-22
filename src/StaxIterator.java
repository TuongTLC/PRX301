import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.*;
import java.io.FileOutputStream;
import java.util.List;

public class StaxIterator {
    public void writeFileByStaxIterator(List<Person> listPerson, String newFile) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            XMLEventWriter eventWriter = factory.createXMLEventWriter(new FileOutputStream(newFile));
            XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

            StartDocument s_document = xmlEventFactory.createStartDocument("UTF-8", "1.0");
            EndDocument e_document = xmlEventFactory.createEndDocument();
            StartElement s_personList = xmlEventFactory.createStartElement("", "", "person_list");
            EndElement e_person = xmlEventFactory.createEndElement("", "", "person_list");
            StartElement s_person = xmlEventFactory.createStartElement("", "", "person");
            EndElement e_personList = xmlEventFactory.createEndElement("", "", "person");
            StartElement s_name = xmlEventFactory.createStartElement("", "", "name");
            EndElement e_name = xmlEventFactory.createEndElement("", "", "name");
            StartElement s_gender = xmlEventFactory.createStartElement("", "", "gender");
            EndElement e_gender = xmlEventFactory.createEndElement("", "", "gender");
            StartElement s_phone = xmlEventFactory.createStartElement("", "", "phone");
            EndElement e_phone = xmlEventFactory.createEndElement("", "", "phone");
            StartElement s_email = xmlEventFactory.createStartElement("", "", "email");
            EndElement e_email = xmlEventFactory.createEndElement("", "", "email");
            StartElement s_address = xmlEventFactory.createStartElement("", "", "address");
            EndElement e_address = xmlEventFactory.createEndElement("", "", "address");

            eventWriter.add(s_document);
            eventWriter.add(s_personList);

            for (Person person : listPerson) {
                eventWriter.add(s_person);

                Attribute id = xmlEventFactory.createAttribute("id", person.getID());
                eventWriter.add(id);

                eventWriter.add(s_name);
                eventWriter.add(xmlEventFactory.createCharacters(person.getName()));
                eventWriter.add(e_name);

                eventWriter.add(s_gender);
                eventWriter.add(xmlEventFactory.createCharacters(person.getGender()));
                eventWriter.add(e_gender);

                eventWriter.add(s_phone);
                eventWriter.add(xmlEventFactory.createCharacters(person.getPhone()));
                eventWriter.add(e_phone);

                eventWriter.add(s_email);
                eventWriter.add(xmlEventFactory.createCharacters(person.getEmail()));
                eventWriter.add(e_email);

                eventWriter.add(s_address);
                eventWriter.add(xmlEventFactory.createCharacters(person.getAddress()));
                eventWriter.add(e_address);

                eventWriter.add(e_person);

            }

            eventWriter.add(e_personList);
            eventWriter.add(e_document);

            eventWriter.flush();
            eventWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
