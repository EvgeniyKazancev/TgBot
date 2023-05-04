package ru.skillfacrtorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "GetCursOnDateXML",namespace = "http://web.cbr.ru/")
@Data
public class GetCursOnDateXml {
    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")//Указание на то, в каком теге XML должно быть данное поле
    protected XMLGregorianCalendar onDate;

}
