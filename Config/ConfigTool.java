package Config;

import java.io.*;
import java.util.Properties;

public class ConfigTool {
    protected static final String CONFIG_FILE = "TankConfig.properties";
    protected static Properties props = new Properties();

    static {
        loadConfig();
    }

    //加载配置文件
    protected static void loadConfig() {
        try (InputStream input = ConfigTool.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
            } else {
                initDefaultConfig();
            }
        } catch (IOException ex) {
            System.err.println("无法加载配置文件，使用默认值");
            initDefaultConfig();
        }
    }

    protected static void initDefaultConfig() {
        props.setProperty("level", "1");  //关卡数
        props.setProperty("ourScore", "0"); //我方分数
        props.setProperty("enemyScore", "0"); //敌方分数
        props.setProperty("beatNum","0");   //击败数
        props.setProperty("tankWhetherSelected","false"); //是否已经选择坦克
        props.setProperty("selectedTank", "null"); //选择的坦克
    }

    //保存配置文件
    public static void saveConfig() {
        File configDir = new File("Config");
        if (!configDir.exists()) {
            configDir.mkdirs(); // 创建目录
        }

        try (OutputStream output = new FileOutputStream(
                "Config/" + CONFIG_FILE)) {
            props.store(output, "tank Configuration");
        } catch (IOException ex) {
            System.err.println("无法保存配置文件");
            ex.printStackTrace();
        }
    }

    // Get方法
    public static int getOurScore() {
        return Integer.parseInt(props.getProperty("ourScore"));
    }
    public static int getEnemyScore() {
        return Integer.parseInt(props.getProperty("enemyScore"));
    }
    public static int getLevel() {
        return Integer.parseInt(props.getProperty("level"));
    }
    public static int getBeatNum() {
        return Integer.parseInt(props.getProperty("beatNum"));
    }

    public static boolean isTankSelected() {
        return Boolean.parseBoolean(props.getProperty("tankWhetherSelected"));
    }
    public static int getSelectedTank() {
        switch (props.getProperty("selectedTank")) {
            case "红坦克" -> {return 1;}
            case "蓝坦克" -> {return 2;}
            case "绿坦克" -> {return 3;}
            case "黄坦克" -> {return 4;}
            default -> {return 1;}  //默认为红
        }
    }

    //set方法
    public static void setLevel(String level) {
        props.setProperty("level", level);
        saveConfig();
    }
    public static void setOurScore(String score) {
        props.setProperty("ourScore", score);
        saveConfig();
    }
    public static void setEnemyScore(String score) {
        props.setProperty("enemyScore", score);
        saveConfig();
    }
    public static void setBeatNum(String num) {
        props.setProperty("beatNum", num);
        saveConfig();
    }

    public static void setTankSelection(String tankType) {
        props.setProperty("tankWhetherSelected", "true");
        props.setProperty("selectedTank", tankType);
        saveConfig();
    }

    public static void resetTankSelection() {
        props.setProperty("tankWhetherSelected", "false");
        props.setProperty("selectedTank", "null");
        saveConfig();
    }
}
