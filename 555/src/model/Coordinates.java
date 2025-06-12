package model;

import java.util.Scanner;

/**
 * Координаты, не могут быть null, x > -659, y != null.
 */
public class Coordinates {
    private Integer x;   // not null
    private Double y;   // not null

    public Coordinates() {
    }

    public Coordinates(Integer x, Double y) {
        setX(x);
        setY(y);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        if (x == null) {
            throw new IllegalArgumentException("x не может быть null.");
        }
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        if (y == null) {
            throw new IllegalArgumentException("y не может быть null.");
        }
        this.y = y;
    }

    public static Coordinates readCoordinates(Scanner scanner) {
        Coordinates coords = new Coordinates();

        // Ввод x (Integer != null)
        while (true) {
            try {
                System.out.print("Введите x (Integer, не может быть null): ");
                String line = scanner.nextLine().trim();
                Integer xVal = Integer.valueOf(line); // может выбросить NumberFormatException
                coords.setX(xVal);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число. Повторите ввод:");
            } catch (IllegalArgumentException e) {
                // если в setX(...) своя проверка
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Ввод y (Double != null)
        while (true) {
            try {
                System.out.print("Введите y (Double, не может быть null): ");
                String line = scanner.nextLine().trim();
                Double yVal = Double.valueOf(line); // может выбросить NumberFormatException
                coords.setY(yVal);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число. Повторите ввод:");
            } catch (IllegalArgumentException e) {
                // если в setY(...) своя проверка
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        return coords;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

