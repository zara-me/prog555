package xml;

import manager.CollectionManager;
import model.LabWork;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlStorage {
    public static void saveToFile(String fileName, CollectionManager manager) throws IOException {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("Не удалось создать директорию.");
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            StringBuilder sb = new StringBuilder();
            sb.append("<Collection>\n");
            for (LabWork labWork : manager.getCollection()) {
                sb.append(XmlParser.toXml(labWork)).append("\n");
            }
            sb.append("</Collection>");
            fos.write(sb.toString().getBytes());
            fos.flush();
        }
    }

    public static void loadFromFile(String fileName, CollectionManager manager) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            System.out.println("Файл не существует или недоступен для чтения.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder xmlBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                xmlBuilder.append(line.trim());
            }
            String xmlContent = xmlBuilder.toString();
            List<String> labWorkXmls = splitByTag(xmlContent, "LabWork");

            for (String xml : labWorkXmls) {
                LabWork labWork = XmlParser.parseLabWork(xml);
                if (labWork != null) {
                    manager.add(labWork);
                }
            }
        }
    }

    private static List<String> splitByTag(String xml, String tag) {
        List<String> result = new ArrayList<>();
        String openTag = "<" + tag;
        String closeTag = "</" + tag + ">";
        int startIndex = 0;
        while (true) {
            int idxOpen = xml.indexOf(openTag, startIndex);
            if (idxOpen < 0) break;
            int idxClose = xml.indexOf(closeTag, idxOpen);
            if (idxClose < 0) break;
            String block = xml.substring(idxOpen, idxClose + closeTag.length());
            result.add(block);
            startIndex = idxClose + closeTag.length();
        }
        return result;
    }
}