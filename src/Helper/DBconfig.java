package Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for database connection details.
 * Loads database credentials from .env file for security.
 */
public class DBconfig {

    private static final Map<String, String> envConfig = new HashMap<>();

    static {
        loadEnvFile();
    }

    /**
     * Loads environment variables from .env file
     */
    private static void loadEnvFile() {
        String envFilePath = ".env";
        try (BufferedReader reader = new BufferedReader(new FileReader(envFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envConfig.put(parts[0].trim(), parts[1].trim());
                }
            }
            System.out.println("Environment configuration loaded successfully");
        } catch (IOException e) {
            System.out.println("Warning: .env file not found. Using system environment variables instead.");
            System.out.println("Create a .env file based on .env.example");
        }
    }

    /**
     * Get database URL from environment
     * @return database URL
     */
    public static String getDbUrl() {
        return getEnvVariable("DB_URL", "jdbc:mysql://localhost:3310/java_college_db");
    }

    /**
     * Get database user from environment
     * @return database user
     */
    public static String getDbUser() {
        return getEnvVariable("DB_USER", "root");
    }

    /**
     * Get database password from environment
     * @return database password
     */
    public static String getDbPassword() {
        return getEnvVariable("DB_PASSWORD", "");
    }

    /**
     * Get database driver from environment
     * @return database driver class name
     */
    public static String getDbDriver() {
        return getEnvVariable("DB_DRIVER", "com.mysql.cj.jdbc.Driver");
    }

    /**
     * Get environment variable with fallback values
     * @param key environment variable key
     * @param defaultValue default value if not found
     * @return environment variable value or default
     */
    private static String getEnvVariable(String key, String defaultValue) {
        // First check .env file
        if (envConfig.containsKey(key)) {
            return envConfig.get(key);
        }
        // Then check system environment variables
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Return default value
        return defaultValue;
    }
}
