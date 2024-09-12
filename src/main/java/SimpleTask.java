public class SimpleTask extends Task {

    protected String title;

    // Конструктор
    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    // Действия
    public String getTitle() {
        return title;
    }

    @Override
    public boolean matches(String query) {
        if (title.contains(query)) {
            return true;
        }

        return false;
    }

}
