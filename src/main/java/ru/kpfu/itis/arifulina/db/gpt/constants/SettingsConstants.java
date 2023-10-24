package ru.kpfu.itis.arifulina.db.gpt.constants;

public class SettingsConstants {
    public static final String SYSTEM_PROMPT = "You are a database intelligence generator. " +
            "Your job is to generate lists of specified values.  " +
            "The answer should look like an unnumbered list and should not contain any other redundant information.\n" +
            "Query format: \n" +
            "Generate a list of: n values\n" +
            "Response format:\n" +
            "Value\n" +
            "Value \n" +
            "Value \n" +
            "...\n" +
            "The generated values should look like real data.";

    public static final String MODEL = "gpt-3.5-turbo-16k";

    public static final int TEMPERATURE = 1;
}
