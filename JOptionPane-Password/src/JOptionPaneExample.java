import javax.swing.*;
import java.awt.event.*;

public class JOptionPaneExample extends JFrame {

    public JOptionPaneExample() {
        // Настройка фрейма
        setTitle("Registration Example");

        // Вызов диалогового окна для выбора разрешения экрана
        showResolutionDialog();

        // Запуск процесса регистрации
        if (showWelcomeDialog()) {
            String login = requestLogin();
            String password = requestPassword();
            if (confirmPassword(password)) {
                showSuccessDialog();
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Метод для отображения диалогового окна с выбором разрешения экрана
    private void showResolutionDialog() {
        String[] resolutions = {"800x600", "1024x768", "1280x1024", "1920x1080"};
        JComboBox<String> resolutionList = new JComboBox<>(resolutions);
        int result = JOptionPane.showConfirmDialog(this, resolutionList, "Выберите разрешение экрана", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedResolution = (String) resolutionList.getSelectedItem();
            if (selectedResolution != null) {
                String[] dimensions = selectedResolution.split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                setSize(width, height); // Установка размера фрейма в соответствии с выбранным разрешением
            }
        } else {
            System.exit(0); // Закрываем программу, если пользователь нажал "Cancel"
        }
    }

    // Метод для отображения приветственного окна
    private boolean showWelcomeDialog() {
        int result = JOptionPane.showConfirmDialog(this, "Добро пожаловать! Хотите зарегистрироваться в программе?", "Регистрация", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true; // Пользователь выбрал "Да"
        } else {
            System.exit(0); // Закрываем программу, если пользователь нажал "Нет"
            return false;
        }
    }

    // Метод для запроса логина пользователя
    private String requestLogin() {
        while (true) {
            String login = JOptionPane.showInputDialog(this, "Введите логин (больше 5 символов, без пробелов):");
            if (login != null && login.length() > 5 && !login.contains(" ")) {
                return login; // Возвращаем корректный логин
            } else {
                JOptionPane.showMessageDialog(this, "Некорректный логин! Пожалуйста, попробуйте снова.");
            }
        }
    }

    // Метод для запроса пароля пользователя
    private String requestPassword() {
        while (true) {
            JPasswordField passwordField = new JPasswordField();
            int result = JOptionPane.showConfirmDialog(this, passwordField, "Введите пароль (больше 8 символов, без пробелов, с цифрами и буквами):", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String password = new String(passwordField.getPassword());
                if (isValidPassword(password)) {
                    return password; // Возвращаем корректный пароль
                } else {
                    JOptionPane.showMessageDialog(this, "Некорректный пароль! Пожалуйста, попробуйте снова.");
                }
            }
        }
    }

    // Метод для проверки корректности пароля
    private boolean isValidPassword(String password) {
        if (password.length() <= 8 || password.contains(" ")) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasLetter = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (hasDigit && hasLetter) {
                return true;
            }
        }
        return false;
    }

    // Метод для подтверждения пароля
    private boolean confirmPassword(String originalPassword) {
        while (true) {
            JPasswordField passwordField = new JPasswordField();
            int result = JOptionPane.showConfirmDialog(this, passwordField, "Повторите пароль:", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String confirmPassword = new String(passwordField.getPassword());
                if (confirmPassword.equals(originalPassword)) {
                    return true; // Пароли совпадают
                } else {
                    JOptionPane.showMessageDialog(this, "Пароли не совпадают! Пожалуйста, попробуйте снова.");
                }
            }
        }
    }

    // Метод для отображения окна успешной регистрации
    private void showSuccessDialog() {
        JOptionPane.showMessageDialog(this, "Регистрация прошла успешно!");
    }

}

