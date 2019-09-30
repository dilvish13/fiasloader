package ru.progmatik.main.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ru.fias.Object;
import ru.fias.House;
import ru.fias.ObjectGuid;
import ru.fias.Socr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * ридер для больших XML-файлов.
 */

public class XMLFileReader implements AutoCloseable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BufferedInputStream bis;
    private XMLStreamReader xmlStreamReader;
    private Unmarshaller jaxbUnmarshaller;

    public XMLFileReader(File inFile, Class objClass) throws IOException, XMLStreamException, JAXBException {

        // подготовка к чтению файла
        bis = new BufferedInputStream(new FileInputStream(inFile));

        XMLInputFactory factory = XMLInputFactory.newInstance();

        xmlStreamReader = factory.createXMLStreamReader(bis);

        JAXBContext jaxbContext = JAXBContext.newInstance(objClass);

        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

    }

    /**
     * метод читает из открытого в конструкторе потока заданное число объектов класса AddrObj
     * @param arraySize
     * @return
     * @throws XMLStreamException
     * @throws JAXBException
     */
    public List<Object> readAddrObjFromStream(int arraySize) throws XMLStreamException, JAXBException {

        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }

        // лист для результата
        List<Object> addrObjList = new ArrayList<>();
        // указатель на текущую ноду
        Object addrObj = null;
        // счетчик чтений
        int countR = 0;
        // счетчик записей
        int countW = 0;

        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // если элемент стартовый и его имя node
                if (!(xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("Object"))) {
                    // читаем следующий
                    xmlStreamReader.next();
                }else{
                    // создаем объект тип LocalNode
                    addrObj = (Object) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    // добавляем в лист ноду
                    // Только с ACTSTATUS=1 и AOLEVEL <= 6
                    if (Optional.ofNullable(addrObj)
                            .filter(o -> "1".equals(String.valueOf(o.getACTSTATUS())))
                            .filter(o -> o.getAOLEVEL().intValue() <= 6)
                            .isPresent()) {
                        addrObjList.add(addrObj);
                        // инкрементируем счетчик
                        countW++;
                    }
                    countR++;
                    if(countW > 0 && countW%arraySize == 0){
                        logger.info(String.format("Parced %s, imported %s records.", countR, countW));
                        return addrObjList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                logger.error("Read addrObj from XMLstream error", e1);
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return addrObjList;
    }


    /**
     * метод читает из открытого в конструкторе потока заданное число объектов класса ObjectGuid
     * @param arraySize
     * @param elementName имя элемента в XML
     * @return
     * @throws XMLStreamException
     * @throws JAXBException
     */
    public List<ObjectGuid> readObjectGuidFromStream(int arraySize, String elementName) throws XMLStreamException, JAXBException {
        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }
        // лист для результата
        List<ObjectGuid> guidList = new ArrayList<>();
        // указатель на текущую ноду
        ObjectGuid guid = null;
        // счетчик чтений
        int countR = 0;
        // счетчик записей
        int countW = 0;
        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // если элемент стартовый и его имя node
                if (!(xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase(elementName))) {
                    // читаем следующий
                    xmlStreamReader.next();
                }else{
                    // создаем объект тип LocalNode
                    guid = (ObjectGuid) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    // добавляем в лист ноду
                    // Только с AOID, GUID
                    if(guid != null && !isEmpty(guid.getAOID()) && !isEmpty(guid.getAOGUID())) {
                        guidList.add(guid);
                        // инкрементируем счетчик
                        countW++;
                    }
                    countR++;
                    if(countW > 0 && countW%arraySize == 0){
                        logger.info(String.format("Parced %s, imported %s records.", countR, countW));
                        return guidList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                logger.error("Read object GUID list from XMLstream error", e1);
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return guidList;
    }

    /**
     * метод читает из открытого в конструкторе потока заданное число объектов класса House
     * @param arraySize
     * @return
     * @throws XMLStreamException
     * @throws JAXBException
     */
    public List<House> readHousesFromStream(int arraySize, final String logFIAS) throws XMLStreamException, JAXBException {
        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }
        // лист для результата
        List<House> houseList = new ArrayList<>();
        // указатель на текущую ноду
        House house = null;
        // счетчик чтений
        int count = 0;
        BigInteger zeroBigInt = BigInteger.valueOf(0);
        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // если элемент стартовый и его имя node
                if (!(xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("House"))) {
                    // читаем следующий
                    xmlStreamReader.next();
                }else{
                    // создаем объект тип LocalNode
                    house = (House) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    count++;
                    if(logFIAS == null || logFIAS.isEmpty()) {
                        if (house.getSTRSTATUS().equals(zeroBigInt)) {
                            // добавляем в лист ноду
                            houseList.add(house);
                        }
                    }
                    else{
                        if(house.getHOUSEGUID().equalsIgnoreCase(logFIAS))
                            houseList.add(house);
                    }

                    if (count % arraySize == 0) {
                        return houseList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                logger.error("Read house from XMLstream error", e1);
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return houseList;
    }

    @Override
    public void close() throws Exception {
        bis.close();
        xmlStreamReader.close();
    }

    /**
     * вспомогательный метод определения снаружи, что в ридере еще есть данные
     * @return
     */
    public boolean hasNext() {
        try {
            return xmlStreamReader.hasNext();
        } catch (XMLStreamException e) {
            logger.error("StreamReader hasNext error", e);
            e.printStackTrace();
            return false;
        }
    }

    public List<House> readHousesFromStream(int batch_size) throws JAXBException, XMLStreamException {
        return readHousesFromStream(batch_size, null);
    }

    /**
     * метод читает из открытого в конструкторе потока заданное число объектов класса Socr
     * @param arraySize
     * @return
     * @throws XMLStreamException
     * @throws JAXBException
     */
    public List<Socr> readSocrFromStream(int arraySize, final String logFIAS) throws XMLStreamException, JAXBException {

        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }
        // лист для результата
        List<Socr> socrList = new ArrayList<>();
        // указатель на текущую ноду
        Socr socr = null;
        // счетчик чтений
        int count = 0;
        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // если элемент стартовый и его имя node
                if (!(xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("AddressObjectType"))) {
                    // читаем следующий
                    xmlStreamReader.next();
                }else{
                    // создаем объект тип LocalNode
                    socr = (Socr) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    count++;
                    if(logFIAS == null || logFIAS.isEmpty()) {
                        if (!"".equals(socr.getSCNAME())) {
                            // добавляем в лист ноду
                            socrList.add(socr);
                        }
                    }

                    if (count % arraySize == 0) {
                        return socrList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                logger.error("Read socr from XMLstream error", e1);
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return socrList;
    }

    public List<Socr> readSocrFromStream(int batch_size) throws JAXBException, XMLStreamException {
        return readSocrFromStream(batch_size, null);
    }
}