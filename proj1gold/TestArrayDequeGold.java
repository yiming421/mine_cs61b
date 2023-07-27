import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    /* test StudentArrayDeque.java **/
    public void testStudentCode() {
        StudentArrayDeque<Integer> dq1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> dq2 = new ArrayDequeSolution<>();
        String message = new String();
        for (int i = 0; i < 1000; ++i) {
            double randNum = StdRandom.uniform();
            if (randNum < 0.25) {
                dq1.addFirst(i);
                dq2.addFirst(i);
                message += ("addFirst(" + i + ")\n");
            } else if (randNum < 0.5) {
                dq1.addLast(i);
                dq2.addLast(i);
                message += ("addLast(" + i + ")\n");
            } else if (randNum < 0.75 && dq1.size() > 0 && dq2.size() > 0) {
                Integer ret1 = dq1.removeFirst();
                Integer ret2 = dq2.removeFirst();
                message += ("removeFirst(): " + ret1 + "\n");
                assertEquals(message, ret1, ret2);
            } else if (randNum < 1 && dq1.size() > 0 && dq2.size() > 0) {
                Integer ret1 = dq1.removeLast();
                Integer ret2 = dq2.removeLast();
                message += ("removeLast(): " + ret1 + "\n");
                assertEquals(message, ret1, ret2);
            }
        }
    }
}
