package ru.kpfu.itis.arifulina.db.generator;

import ru.kpfu.itis.arifulina.db.generator.exc.StringsGeneratorException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class StringsGenerator {

    private final List<String> strings;

    private final Random rand;

    public StringsGenerator (String path) throws StringsGeneratorException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            strings = br.lines().toList();
            this.rand = new Random();
        } catch (IOException e) {
            throw new StringsGeneratorException(e.getMessage());
        }
    }

    public StringsGenerator (String path, Random rand) throws StringsGeneratorException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            strings = br.lines().toList();
            this.rand = rand;
        } catch (IOException e) {
            throw new StringsGeneratorException(e.getMessage());
        }
    }

    public String getString() {
        return strings.get(rand.nextInt(strings.size()));
    }
}
