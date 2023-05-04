package ru.skillfacrtorydemo.tgbot.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.skillfacrtorydemo.tgbot.dto.GetCursOnDateXmlResponse;
import ru.skillfacrtorydemo.tgbot.dto.GetCursOnDateXml;
import ru.skillfacrtorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfacrtorydemo.tgbot.dto.GetCursOnDateXmlResponse;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CentralRussianBankService extends WebServiceTemplate { //Тут случается некоторая магия Spring и в момент запуска вашего приложения, сюда поставляется значение из application.properties или application.yml
    @Value("${cbr.api.url}")
    private String cbrApiUrl;
    public List<ValuteCursOnDate> getCurrenciesFromCbr() throws DatatypeConfigurationException{
        final GetCursOnDateXml getCursOnDateXml = new GetCursOnDateXml();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        GetCursOnDateXmlResponse response = (GetCursOnDateXmlResponse) marshalSendAndReceive(cbrApiUrl,getCursOnDateXml);
        if (response == null){
            throw new IllegalStateException("Could not get response from CBR Service");
        }
        final List<ValuteCursOnDate> courses = response.getGetCursOnDateXmlResult().getValuteData();;
        courses.forEach(course -> course.setName(course.getName().trim()));
        return courses;
    }
}
