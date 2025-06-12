package model;

import java.util.Objects;

/**
 * Класс model Discipline .
 */
public class Discipline  {
    private String name;               // not null, not empty
    private Long lectureHours;    // not null

    public Discipline () {
    }

    public Discipline (String name, Long lectureHours) {
        setName(name);
        setLectureHours(lectureHours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name не может быть null или пустым.");
        }
        this.name = name;
    }

    public Long getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Long lectureHours) {
        if (lectureHours == null) {
            throw new IllegalArgumentException("lectureHours не может быть null.");
        }
        this.lectureHours = lectureHours;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", lectureHours=" + lectureHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discipline)) return false;
        Discipline discipline = (Discipline) o;
        return Objects.equals(name, discipline.name)
                && Objects.equals(lectureHours, discipline.lectureHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lectureHours);
    }
}
