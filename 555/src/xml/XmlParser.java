package xml;

import model.*;
import java.time.LocalDate;

public class XmlParser {
    public static String toXml(LabWork labWork) {
        StringBuilder sb = new StringBuilder();
        sb.append("<LabWork>\n");
        tag(sb, "id", String.valueOf(labWork.getId()));
        tag(sb, "name", labWork.getName());
        sb.append("  <coordinates>\n");
        tag(sb, "x", String.valueOf(labWork.getCoordinates().getX()));
        tag(sb, "y", String.valueOf(labWork.getCoordinates().getY()));
        sb.append("  </coordinates>\n");
        tag(sb, "creationDate", labWork.getCreationDate().toString());
        tag(sb, "minimalPoint", String.valueOf(labWork.getMinimalPoint()));
        tag(sb, "averagePoint", String.valueOf(labWork.getAveragePoint()));
        tag(sb, "difficulty", labWork.getDifficulty().name());
        if (labWork.getDiscipline() != null) {
            sb.append("  <discipline>\n");
            disciplineToXml(sb, labWork.getDiscipline());
            sb.append("  </discipline>\n");
        } else {
            sb.append("  <discipline/>\n");
        }
        sb.append("</LabWork>\n");
        return sb.toString();
    }

    public static LabWork parseLabWork(String xml) {
        LabWork labWork = new LabWork();
        try {
            labWork.setId(Long.parseLong(parseValue(xml, "id")));
            labWork.setName(parseValue(xml, "name"));
            String coordsXml = subTag(xml, "coordinates");
            Coordinates coords = new Coordinates(
                    Integer.parseInt(parseValue(coordsXml, "x")),
                    Double.parseDouble(parseValue(coordsXml, "y"))
            );
            labWork.setCoordinates(coords);
            labWork.setCreationDate(LocalDate.parse(parseValue(xml, "creationDate")));
            labWork.setMinimalPoint(Double.parseDouble(parseValue(xml, "minimalPoint")));
            labWork.setAveragePoint(Long.parseLong(parseValue(xml, "averagePoint")));
            labWork.setDifficulty(Difficulty.valueOf(parseValue(xml, "difficulty")));
            String disciplineXml = subTag(xml, "discipline");
            if (!disciplineXml.isEmpty()) {
                labWork.setDiscipline(parseDiscipline(disciplineXml));
            }
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге LabWork: " + e.getMessage());
            return null;
        }
        return labWork;
    }

    private static void tag(StringBuilder sb, String tag, String value) {
        sb.append("  <").append(tag).append(">")
                .append(value)
                .append("</").append(tag).append(">\n");
    }

    private static String parseValue(String xml, String tag) {
        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int start = xml.indexOf(open);
        if (start < 0) return "";
        int end = xml.indexOf(close, start);
        if (end < 0) return "";
        return xml.substring(start + open.length(), end).trim();
    }

    private static String subTag(String xml, String tag) {
        String open = "<" + tag;
        String close = "</" + tag + ">";
        int start = xml.indexOf(open);
        if (start < 0) return "";
        int end = xml.indexOf(close, start);
        if (end < 0) return "";
        return xml.substring(start, end + close.length());
    }

    private static void disciplineToXml(StringBuilder sb, Discipline discipline) {
        tag(sb, "name", discipline.getName());
        tag(sb, "lectureHours", String.valueOf(discipline.getLectureHours()));
    }

    private static Discipline parseDiscipline(String xml) {
        Discipline discipline = new Discipline();
        discipline.setName(parseValue(xml, "name"));
        discipline.setLectureHours(Long.parseLong(parseValue(xml, "lectureHours")));
        return discipline;
    }
}