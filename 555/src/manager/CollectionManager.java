package manager;
import model.Difficulty;
import model.LabWork;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Класс, управляющий коллекцией объектов model.LabWork .
 * Хранит коллекцию (TreeSet), дату инициализации и методы для работы с ней.
 */
public class CollectionManager {
    private final TreeSet<LabWork> labWorks = new TreeSet<>();
    private final LocalDate creationDate = LocalDate.now();

    public TreeSet<LabWork> getCollection() {
        return labWorks;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Добавить новый элемент.
     */
    public void add(LabWork labWork) {
        labWorks.add(labWork);
    }

    /**
     * Обновить элемент по id.
     */
    public void update(long id, LabWork newLabWork) {
        LabWork oldLabWork = findById(id);
        if (oldLabWork != null) {
            labWorks.remove(oldLabWork);
            labWorks.add(newLabWork);
        }
    }

    /**
     * Удалить элемент по id.
     */
    public boolean removeById(long id) {
        LabWork labWork = findById(id);
        if (labWork != null) {
            labWorks.remove(labWork);
            System.out.println("Элемент с id " + id + " успешно удалён.");
            return true;
        }
        return false;
    }

    /**
     * Очистить коллекцию.
     */
    public void clear() {
        labWorks.clear();
    }

    /**
     * Добавить элемент, если он больше максимального.
     *
     * @return
     */
    public boolean addIfMax(LabWork labWork) {
        LabWork maxLabWork = labWorks.stream()
                .max(LabWork::compareTo)
                .orElse(null);
        if (maxLabWork == null || labWork.compareTo(maxLabWork) > 0) {
            labWorks.add(labWork);
        }
        return false;
    }

    /**
     * Удалить элементы, большие заданного.
     */
    public int removeGreater(LabWork labWork) {
        int before = labWorks.size();
        labWorks.removeIf(lw -> lw.compareTo(labWork) > 0);
        int removedCount = before - labWorks.size();
        return removedCount;
    }

    /**
     * Удалить элементы, меньшие заданного.
     */
    public int removeLower(LabWork labWork) {
        int before = labWorks.size();
        labWorks.removeIf(lw -> lw.compareTo(labWork) < 0);
        int removedCount = before - labWorks.size();
        return removedCount;
    }

    /**
     * Вывести среднее значение averagePoint.
     *
     * @return
     */
    public double averageOfAveragePoint() {
        double average = labWorks.stream()
                .mapToDouble(LabWork::getAveragePoint)
                .average()
                .orElse(0.0);
        return average;
    }

    /**
     * Вывести элементы, содержащие подстроку в name.
     */
    public void filterContainsName(String name) {
        labWorks.stream()
                .filter(lw -> lw.getName().contains(name))
                .forEach(System.out::println);
    }

    /**
     * Найти элемент по id.
     */
    public LabWork findById(long id) {
        for (LabWork sg : labWorks) {
            if (sg.getId() == id) {
                return sg;
            }
        }
        return null;
    }

    /**
     * Обновить элемент (заранее найдём старый, заменим на новый).
     */
    public void update(LabWork oldGroup, LabWork newGroup) {
        labWorks.remove(oldGroup);
        labWorks.add(newGroup);
    }

    public long countGreaterThanDifficulty(Difficulty difficulty) {
        return labWorks.stream()
                .filter(lw -> lw.getDifficulty() != null
                        && lw.getDifficulty().compareTo(difficulty) > 0)
                .count();
    }

}

