package model;

import java.time.LocalDate;

/**
 * Класс model.StudyGroup.
 */
public class LabWork implements Comparable<LabWork> {

    /**
     * Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    private Long id;

    /**
     * Не null, не пустая строка
     */
    private String name;

    /**
     * Не null
     */
    private Coordinates coordinates;

    /**
     * Не null, генерируется автоматически
     */
    private LocalDate creationDate;

    /**
     * Значение поля должно быть больше 0
     */
    private double minimalPoint;

    /**
     * > Поле не может быть null, Значение поля должно быть больше 0
     */
    private Long averagePoint;

    /**
     * Может быть null
     */
    private Difficulty difficulty;

    /**
     * Не может быть null
     */
    private Discipline discipline;


    @Override
    public int compareTo(LabWork o) {
        int result = Double.compare(this.minimalPoint, o.minimalPoint);
        if (result == 0) {
            result = Long.compare(this.averagePoint, o.averagePoint);
        }
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }

    // region Getters/Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("id должен быть > 0 и не null.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле name не может быть пустым или null.");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Поле coordinates не может быть null.");
        }
        this.coordinates = coordinates;
    }

    public void setMinimalPoint(double val) {
        this.minimalPoint = val;
    }
    public double getMinimalPoint() {
        return minimalPoint;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("creationDate не может быть null.");
        }
        this.creationDate = creationDate;
    }

    public Long getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(Long averagePoint) {
        if (averagePoint != null && averagePoint < 0) {
            throw new IllegalArgumentException("averagePoint должно быть > 0.");
        }
        this.averagePoint = averagePoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == null ) {
            throw new IllegalArgumentException("difficulty не может быть null.");
        }
        this.difficulty = difficulty;
    }

    public Discipline  getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        if (discipline == null) {
            throw new IllegalArgumentException("discipline не может быть null.");
        }
        this.discipline = discipline;
    }

    // endregion

    @Override
    public String toString() {
        return "model.LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", averagePoint=" + averagePoint +
                ", difficulty=" + difficulty +
                ", discipline=" + discipline +
                '}';
    }
}

