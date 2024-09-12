import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestTodos {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты задач
    @Test
    public void testMatchesSimpeTaskValid() { // Валидные значения, в методе поиска, SimpleTask.

        SimpleTask simpleTask = new SimpleTask(5, "Тест успешный.");
        Todos todos = new Todos();

        todos.add( simpleTask );

        Boolean exp = true;
        Boolean act = simpleTask.matches( simpleTask.getTitle() );

        Assertions.assertEquals( exp, act );

    }

    @Test
    public void testMatchesSimpeTaskInValid() { // Невалидные значения, в методе поиска, SimpleTask.

        SimpleTask simpleTask = new SimpleTask(5, "Тест успешный.");
        Todos todos = new Todos();

        todos.add( simpleTask );
        String invalidTestValue = "Нет такого заголовка задачи";

        Boolean exp = false;
        Boolean act = simpleTask.matches( invalidTestValue );

        Assertions.assertEquals( exp, act );

    }

    @Test
    public void testMatchesEpicValid() { // Валидные значения, в методе поиска, Epic.

        String[] testSubtasks = { "Молоко", "Яйца", "Хлеб" };
        String validSubtask = "Хлеб";
        String validSubtask1 = "Молоко";
        String validSubtask2 = "Яйца";
//        String validSubtask = {"Нет", "таких", "задач."};
        Epic epic = new Epic(5, testSubtasks );
        Todos todos = new Todos();

        todos.add( epic );


        Boolean exp = true;
        Boolean act = epic.matches( validSubtask );
        Boolean act1 = epic.matches( validSubtask1 );
        Boolean act2 = epic.matches( validSubtask2 );

        Assertions.assertEquals( exp, act );
        Assertions.assertEquals( exp, act1 );
        Assertions.assertEquals( exp, act2 );

    }

    @Test
    public void testMatchesEpicInvalid() { // Невалидные значения, в методе поиска, Epic.

        String[] testSubtasks = { "Молоко", "Яйца", "Хлеб" };
        String inValidSubtask = "задач.";
        Epic epic = new Epic(5, testSubtasks );
        Todos todos = new Todos();

        todos.add( epic );


        Boolean exp = false;
        Boolean act = epic.matches( inValidSubtask );

        Assertions.assertEquals( exp, act );

    }

    @Test
    public void testMatchesMeetingValid() { // Валидные значения, в методе поиска, Epic.

        String testTopicValue =  "Тема обсуждения.";
        String testProjectValue = "Название проекта";
        String testStartValue = "Дата начала 17 декабря сего года";
//        String invalidTestValueTopic = "Неправильная тема обсуждения";
        Meeting meeting = new Meeting(175, testTopicValue, testProjectValue, testStartValue );

        Todos todos = new Todos();

        todos.add( meeting );

        Boolean exp = true;
//        Boolean act = meeting.matches( meeting.getTopic() ); // Поиск Темы обсуждения.
//        Boolean act = meeting.matches( meeting.getProject() ); // Поиск Названия проекта.
        Boolean act = meeting.matches( meeting.getStart() ); // Поиск даты обсуждения.
        Assertions.assertEquals( exp, act );

    }

    @Test
    public void testMatchesMeetingInValid() { // Невалидные значения, в методе поиска, Epic.

        String testTopicValue =  "Тема обсуждения.";
        String testProjectValue = "Название проекта";
        String testStartValue = "Дата начала 17 декабря сего года";
        String invalidTestValueTopic = "Неправильная тема обсуждения";
        String invalidTestValueProject = "Неправильное название проекта";
        String invalidTestValueStart = "Неправильная дата обсуждения.";
        Meeting meeting = new Meeting(175, testTopicValue, testProjectValue, testStartValue );

        Todos todos = new Todos();

        todos.add( meeting );

        Boolean exp = false;
//        Boolean act = meeting.matches( invalidTestValueTopic ); // Поиск Темы обсуждения.
//        Boolean act = meeting.matches( invalidTestValueProject ); // Поиск Названия проекта.
        Boolean act = meeting.matches( invalidTestValueStart ); // Поиск даты обсуждения.
        Assertions.assertEquals( exp, act );

    }

    @Test
    public void testSearchValidSimpleTask() { // Валидные значения, в методе search(), задача из SimpleTask.

        SimpleTask simpleTask = new SimpleTask(175, "Сходить в магазин за хлебом." );

        Todos todos = new Todos();

        todos.add( simpleTask );

        String testSimpleTaskValue = "в магазин";

        Task[] exp = todos.findAll();
        Task[] act = todos.search(testSimpleTaskValue);
        Assertions.assertArrayEquals( exp, act );
    }

    @Test
    public void testSearchInValidSimpleTask() { // Невалидные значения, в методе search(), задача из SimpleTask.

        SimpleTask simpleTask = new SimpleTask(175, "Сходить в магазин за хлебом." );

        Todos todos = new Todos();

        todos.add( simpleTask );

        String testSimpleTaskValue = "Невалидность";

        Task[] exp = new Task[0];
        Task[] act = todos.search(testSimpleTaskValue);
        Assertions.assertArrayEquals( exp, act );
    }

    @Test
    public void testSearchValidEpic() { // Валидные значения, в методе search(), задача из Epic.

        String[] subtasks = { "Мир", "Труд", "Май" };
        Epic epic = new Epic(175, subtasks );

        Todos todos = new Todos();

        todos.add( epic );

        String testEpicValue = "Май";
        String testEpicValue1 = "Май";
        String testEpicValue2 = "Май";

        Task[] exp = todos.findAll();
        Task[] act = todos.search(testEpicValue);
        Task[] act1 = todos.search(testEpicValue1);
        Task[] act2 = todos.search(testEpicValue2);
        Assertions.assertArrayEquals( exp, act );
        Assertions.assertArrayEquals( exp, act1 );
        Assertions.assertArrayEquals( exp, act2 );
    }

    @Test
    public void testSearchInValidEpic() { // Невалидные значения, в методе search(), задача из Epic.

        String[] subtasks = { "Мир", "Труд", "Май" };
        Epic epic = new Epic(175, subtasks );

        Todos todos = new Todos();

        todos.add( epic );

        String testEpicValue = "Невалидность";

        Task[] exp = new Task[0];
        Task[] act = todos.search(testEpicValue);

        Assertions.assertArrayEquals( exp, act );

    }

    @Test
    public void testSearchValidMeeting() { // Валидные значения, в методе search(), задача из Meeting.

        Meeting meeting = new Meeting(
                321,
                "Обсуждение обсуждения.",
                "Тот самый проект.",
                "Когда-нибудь, наверное."
        );
        Todos todos = new Todos();

        todos.add(meeting);

        String testMeetingValueTopic = "Обсуждение";
        String testMeetingValueProject = "Тот самый";
        String testMeetingValueStart = "нибудь";

        Task[] exp = todos.findAll();
        Task[] act = todos.search(testMeetingValueTopic);
        Task[] act1 = todos.search(testMeetingValueProject);
        Task[] act2 = todos.search(testMeetingValueStart);
        Assertions.assertArrayEquals( exp, act );
        Assertions.assertArrayEquals( exp, act1 );
        Assertions.assertArrayEquals( exp, act2 );

    }

    @Test
    public void testSearchInValidMeeting() { // Невалидные значения, в методе search(), задача из Meeting.

        Meeting meeting = new Meeting(
                321,
                "Обсуждение обсуждения.",
                "Тот самый проект.",
                "Когда-нибудь, наверное."
        );
        Todos todos = new Todos();

        todos.add(meeting);

        String testMeetingValueTopic = "Невалидность";

        Task[] exp = new Task[0];
        Task[] act = todos.search(testMeetingValueTopic);

        Assertions.assertArrayEquals( exp, act );

    }

}
