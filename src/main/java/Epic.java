public class Epic extends Task {

    protected String[] subtasks;

    // Конструктор
    public Epic( int id, String[] subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    // Действия
    public String[] getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean matches(String query) {

        for ( String sub : subtasks ) {
            if ( query.contains( sub ) ) {
                return true;
            }
        }

        return false;
    }

}
