package foleon.acs.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import org.bukkit.plugin.java.JavaPlugin;;

public class Config {
    private final String filename = "Config.yml";
    private File configFile;
    private Map<String, Object> configData = new HashMap<>();

    public Config(JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), filename);
        configData.put("Killaura", 5);
        createConfigFile();
    }

    private void createConfigFile() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }
            if (!configFile.canWrite()) {
                throw new IOException("Нет прав на запись файла конфигурации");
            }
            FileWriter writer = new FileWriter(configFile, true);
            for (String key : configData.keySet()) {
                if (!configFile.exists() || !configFile.canWrite()) {
                    writer.write(key + ": " + configData.get(key) + "\n");
                } else if (!configFile.exists() || !configFile.canWrite() || !configFile.getParentFile().exists()) {
                    throw new IOException("Не удалось создать файл конфигурации или его родительскую папку");
                } else {
                    String currentValue = getValueFromFile(key, configFile);
                    if (currentValue == null || currentValue.isEmpty()) {
                        writer.write(key + ": " + configData.get(key) + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValueFromFile(String key, File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(key + ":")) {
                    return line.substring(key.length() + 1).trim();
                }
            }
        }
        return null;
    }

    public int getKillauraValue() {
        return (int) getConfigValue("Killaura");
    }

    private String convertMapToYaml(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void writeConfig() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }
            FileWriter writer = new FileWriter(configFile);
            writer.write(convertMapToYaml(configData));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateConfigValue(String key, Object value) {
        configData.put(key, value);
        writeConfig();
    }

    public Object getConfigValue(String key) {
        return configData.get(key);
    }
}
