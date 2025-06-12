package manager;
import model.*;
import java.time.LocalDate;
import java.util.Scanner;
import model.Discipline;

/**
 * Класс для ввода и валидации полей model.StudyGroup/model.Person и т.п.
 */
public class InputManager {
    private static Long autoId = 1L; // для генерации уникального id

    public static LabWork readLabWork(Scanner scanner) {
        LabWork labWork = new LabWork();

        labWork.setId(autoId++);
        labWork.setCreationDate(LocalDate.now());

        // name
        System.out.print("Введите название лабораторной работы (name): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                labWork.setName(line);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Повторите ввод:");
            }
        }

        // coordinates
        System.out.println("Введите координаты (Coordinates):");
        labWork.setCoordinates(readCoordinates(scanner));

        // --- minimalPoint ---
        System.out.print("Введите минимальный балл (minimalPoint) (должен быть > 0): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                double val = Double.parseDouble(line);
                if (val <= 0) {
                    System.out.println("Ошибка: минимальный балл должен быть больше 0.");
                    continue;  // Ask again if invalid
                }
                labWork.setMinimalPoint(val);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число. Повторите ввод:");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Повторите ввод:");
            }
        }

        // --- averagePoint ---
        System.out.print("Введите средний балл (averagePoint) (должен быть > 0): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                long val = Long.parseLong(line);
                labWork.setAveragePoint(val);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число. Повторите ввод:");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Повторите ввод:");
            }
        }

        // --- difficulty ---
        // EASY, INSANE, TERRIBLE
        System.out.print("Введите уровень сложности (difficulty) (EASY, INSANE, TERRIBLE): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                Difficulty diff = Difficulty.valueOf(line);
                labWork.setDifficulty(diff);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректное значение. Попробуйте ещё раз (EASY, INSANE, TERRIBLE):");
            }
        }

        // discipline
        System.out.print("Хотите ввести discipline? (y/n): ");
        while (true) {
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("y")) {
                labWork.setDiscipline(readDiscipline(scanner));
                break;
            } else if (line.equals("n")) {
                labWork.setDiscipline(null);
                break;
            } else {
                System.out.println("Введите 'y' или 'n'.");
            }
        }

        return labWork;
    }
    /**
     * Считывает объект model.Coordinates из консоли.
     */
    public static Coordinates readCoordinates(Scanner scanner) {
        Coordinates coords = new Coordinates();

        // -- x --
        System.out.print("Введите координату x (Integer, не может быть null): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                Integer xVal = Integer.valueOf(line);
                coords.setX(xVal);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число (Integer). Повторите ввод:");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // -- y --
        System.out.print("Введите координату y (Double, не может быть null): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                Double yVal = Double.valueOf(line);
                coords.setY(yVal);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число (Double). Повторите ввод:");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        return coords;
    }
    /**
     * Считывает объект model.Discipline из консоли.
     */
    public static Discipline readDiscipline(Scanner scanner) {
        Discipline discipline = new Discipline();

        // name
        System.out.print("Введите название дисциплины (name): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                discipline.setName(line);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Повторите ввод:");
            }
        }
        // --- lectureHours ---
        System.out.print("Введите количество часов лекций (lectureHours) (long): ");
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                long hours = Long.parseLong(line);
                discipline.setLectureHours(hours);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число (long). Повторите ввод:");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Повторите ввод:");
            }
        }

        return discipline;
    }
}
