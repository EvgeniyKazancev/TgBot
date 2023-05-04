package ru.skillfacrtorydemo.tgbot.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "GetCursOnDateXMLResponse", namespace = "http://web.cbr.ru/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCursOnDateXmlResponse {
@XmlElement(name = "GetCursOnDateXMLResult", namespace = "http://web.cbr.ru/")
    private GetCursOnDateXmlResult getCursOnDateXmlResult;
}
