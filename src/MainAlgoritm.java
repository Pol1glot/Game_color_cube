import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class MainAlgoritm {
    // Четыре возможных движения
    private static final int[] ROW = {-1, 0, 0, 1};
    private static final int[] COL = {0, -1, 1, 0};


    // проверяем, можно ли перейти к пикселю (x, y) из
    // текущий пиксель. Функция возвращает false, если пиксель
    // имеет другой цвет или недопустимый пиксель
    public static boolean isSafe(int[][] mat, int x, int y, int target) {
        return x >= 0 && x < mat.length && y >= 0 && y < mat[0].length && mat[x][y] == target;
    }

    // Заливка с использованием BFS
    public static void floodfill(int[][] mat, int x, int y, int replacement) {
        // базовый вариант
        if (mat == null || mat.length == 0) {
            return;
        }

        // создаем queue и ставим в queue начальный пиксель
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x, y));

        // получаем целевой цвет
        int target = mat[x][y];

        // целевой цвет такой же, как и замена
        if (target == replacement) {
            return;
        }

        // прерываем, когда queue становится пустой
        while (!q.isEmpty()) {
            // удалить передний узел из очереди и обработать его
            Pair node = q.poll();

            // (x, y) представляет текущий пиксель
            x = node.x;
            y = node.y;

            // заменить текущий цвет пикселя цветом замены
            mat[x][y] = replacement;

            // обрабатываем все восемь соседних пикселей текущего пикселя и
            // поставить в queue каждый допустимый пиксель
            for (int k = 0; k < ROW.length; k++) {
                // если соседний пиксель в позиции (x + row[k], y + col[k])
                // действителен и имеет тот же цвет, что и текущий пиксель
                if (isSafe(mat, x + ROW[k], y + COL[k], target)) {
                    // поставить в queue соседний пиксель
                    q.add(new Pair(x + ROW[k], y + COL[k]));
                }
            }
        }
    }
}