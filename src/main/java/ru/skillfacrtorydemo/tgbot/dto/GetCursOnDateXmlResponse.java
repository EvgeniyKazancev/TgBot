package ru.skillfacrtorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "GetCursOnDateXMLResponse", namespace = "http://web.cbr.ru/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetCursOnDateXmlResponse {
@XmlElement(name = "GetCursOnDateXMLResult", namespace = "http://web.cbr.ru/")
    private GetCursOnDateXmlResult getCursOnDateXmlResult;
}
