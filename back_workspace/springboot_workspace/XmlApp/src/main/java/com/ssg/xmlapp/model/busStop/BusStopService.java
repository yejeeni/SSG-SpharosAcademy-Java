package com.ssg.xmlapp.model.busStop;

import com.ssg.xmlapp.domain.BusStop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusStopService {

    private final BusStopHandler busStopHandler;

    public List<BusStop> parse() throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();

        InputSource inputSource = new InputSource(new StringReader(""));

//        parser.parse();
        return null;
    }
}
