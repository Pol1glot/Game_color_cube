import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.input.Mouse;

public class Main {
    static int h = 900;
    static int w = 700;
    static int point = 0; //Очки для победы или проигрыша
    static int[][] massi; //Центральный массив разных цветов

    //repeatGame
    public static void repeatgame() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                massi[i][j] = (int) (Math.random() * 6);
            }
        }
    }

    public static Color colr(int x) {
        Color col = null;
        switch (x) {
            case 0:
                col = Color.RED;
                break;
            case 1:
                col = Color.ORANGE;
                break;
            case 2:
                col = Color.PURPLE;
                break;
            case 3:
                col = Color.BLUE;
                break;
            case 4:
                col = Color.GREEN;
                break;
            case 5:
                col = Color.YELLOW;
                break;
        }
        return col;
    }

    public static void main(String[] args) {
        int gameton = 1; //Игровое состояние
        int winpoint = 25; //Очки для победы
        int xx = 80; // Расстояние между игровыми кнопками
        massi = new int[12][12]; //Размерность массива
        repeatgame();
        Raylib rl = new Raylib();
        rl.core.InitWindow(w, h, "Raylib-J Example");
        while (!rl.core.WindowShouldClose()) {
            {//LOGIC
                //Рестарт
                if (rl.core.GetMouseX() >= 540 && rl.core.GetMouseX() <= 690 && rl.core.GetMouseY() >= 10 && rl.core.GetMouseY() <= 80 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                    point = 0;
                    gameton = 1;
                    repeatgame();
                }
                if (gameton == 1) {
                    //Игровые кнопки
                    //RED
                    if (rl.core.GetMouseX() >= xx && rl.core.GetMouseX() <= xx + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 0);
                    }
                    //ORANGE
                    if (rl.core.GetMouseX() >= xx + (35 + 60) && rl.core.GetMouseX() <= xx + (35 + 60) + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 1);
                    }
                    //PURPLE
                    if (rl.core.GetMouseX() >= xx + 2 * (35 + 60) && rl.core.GetMouseX() <= xx + 2 * (35 + 60) + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 2);
                    }
                    //BLUE
                    if (rl.core.GetMouseX() >= xx + 3 * (35 + 60) && rl.core.GetMouseX() <= xx + 3 * (35 + 60) + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 3);
                    }
                    //GREEN
                    if (rl.core.GetMouseX() >= xx + 4 * (35 + 60) && rl.core.GetMouseX() <= xx + 4 * (35 + 60) + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 4);
                    }
                    //GOLD
                    if (rl.core.GetMouseX() >= xx + 5 * (35 + 60) && rl.core.GetMouseX() <= xx + 5 * (35 + 60) + 60 && rl.core.GetMouseY() >= 780 && rl.core.GetMouseY() <= 840 && rl.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT.ordinal())) {
                        point++;
                        MainAlgoritm.floodfill(massi, 0, 0, 5);
                    }
                }
                //ИГРА
                //ПОБЕДА
                boolean win = true;
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {
                        if (massi[i][j] != massi[0][0]){
                            win = false;
                        }
                    }
                }
                if(win){
                    rl.text.DrawText("YOU WIN!", 280, 730, 25, Color.RED);
                    gameton = 0;
                }
                //ПРОИГРЫШ
                if (point == winpoint) {
                    rl.text.DrawText("YOU LOST!", 280, 730, 25, Color.RED);
                    gameton = 0;
                }
            }
            {//DRAWING
                rl.core.BeginDrawing();
                rl.core.ClearBackground(Color.WHITE);
                //Координаты
                rl.text.DrawText(String.format(" Cordinates X:%d, Y:%d", rl.core.GetMouseX(), rl.core.GetMouseY()), 10, 10, 20, Color.BLACK);
                //Центральные значения
                rl.text.DrawText(String.format("%d / %d", point, winpoint), 300, 90, 20, Color.BLACK);
                //RESTART
                rl.shapes.DrawRectangle(540, 10, 150, 70, Color.ORANGE);
                rl.text.DrawText("RESTART", 555, 30, 25, Color.WHITE);
                //Игровая сетка
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {
                        rl.shapes.DrawRectangle(i * 40 + 110, j * 40 + 170, 40, 40, colr(massi[i][j]));
                    }
                }
                //Игровы кнопки
                rl.shapes.DrawRectangle(xx, 780, 60, 60, Color.RED);
                rl.shapes.DrawRectangle(xx + 95, 780, 60, 60, Color.ORANGE);
                rl.shapes.DrawRectangle(xx + 2 * 95, 780, 60, 60, Color.PURPLE);
                rl.shapes.DrawRectangle(xx + 3 * 95, 780, 60, 60, Color.BLUE);
                rl.shapes.DrawRectangle(xx + 4 * 95, 780, 60, 60, Color.GREEN);
                rl.shapes.DrawRectangle(xx + 5 * 95, 780, 60, 60, Color.YELLOW);
            }
            rl.core.EndDrawing();
        }
    }
}