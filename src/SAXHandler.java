import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {

        List<Person> personList;
        Person person;
        public SAXHandler(){
            personList = new ArrayList<Person>();
        }
        boolean bid = false; boolean bname = false; boolean bgender = false; boolean bphone = false; boolean bemail= false;
        boolean baddress = false;
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (localName.equalsIgnoreCase("person")){
                person = new Person();
                person.setID(attributes.getValue("id"));
            }

            if (localName.equalsIgnoreCase("name")){bname = true;}
            if (localName.equalsIgnoreCase("gender")){bgender = true;}
            if (localName.equalsIgnoreCase("phone")){bphone = true;}
            if (localName.equalsIgnoreCase("email")){bemail = true;}
            if (localName.equalsIgnoreCase("address")){baddress = true;}
        }
        public void endElement(String uri, String localName, String qName) throws SAXException{
            if (localName.equalsIgnoreCase("person")){
                personList.add(person);
            }
        }
        public void characters(char[] ch, int start, int lenght) throws SAXException{
            if (bid){
                person.setID(new String(ch, start, lenght));
                bid=false;
            }
            if (bname){
                person.setName(new String(ch, start, lenght));
                bname=false;
            }
            if (bgender){
                person.setGender(new String(ch, start, lenght));
                bgender=false;
            }
            if (bphone){
                person.setPhone(new String(ch, start, lenght));
                bphone=false;
            }
            if (bemail){
                person.setEmail(new String(ch, start, lenght));
                bemail=false;
            }
            if (baddress){
                person.setAddress(new String(ch, start, lenght));
                baddress=false;
            }

        }


}
