package ru.kpfu.itis.arifulina.db.generator;

import ru.kpfu.itis.arifulina.db.generator.constants.ValuesConstants;
import ru.kpfu.itis.arifulina.db.generator.exc.ValuesGeneratorException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ValuesGenerator extends Random {

    private Random rand;

    public ValuesGenerator(Random rand) {
        this.rand = rand;
    }

    private final Map<Integer, Integer> daysInMonths = new HashMap<>();

    ValuesGenerator() {
        daysInMonths.put(1, 31);
        daysInMonths.put(2, 28);
        daysInMonths.put(3, 31);
        daysInMonths.put(4, 30);
        daysInMonths.put(5, 31);
        daysInMonths.put(6, 30);
        daysInMonths.put(7, 31);
        daysInMonths.put(8, 31);
        daysInMonths.put(9, 30);
        daysInMonths.put(10, 31);
        daysInMonths.put(11, 30);
        daysInMonths.put(12, 31);
    }

    public Timestamp getTimestamp() {
        int year = rand.nextInt(ValuesConstants.MIN_YEAR, ValuesConstants.MAX_YEAR);
        int month = rand.nextInt(1, ValuesConstants.MAX_MONTH + 1);
        int day = rand.nextInt(1, daysInMonths.get(month) + 1);
        int hour = rand.nextInt(ValuesConstants.MAX_HOURS);
        int minute = rand.nextInt(ValuesConstants.MAX_MINUTES);
        int second = rand.nextInt(ValuesConstants.MAX_SECONDS);

        return Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    public Timestamp getTimestamp(LocalDateTime bound) {
        if (bound.getYear() < ValuesConstants.MIN_YEAR || bound.getYear() > ValuesConstants.MAX_YEAR) {
            throw new ValuesGeneratorException("Not supported values of year.");
        }
        int year = rand.nextInt(ValuesConstants.MIN_YEAR, bound.getYear() + 1);
        int month = year != bound.getYear() ? rand.nextInt(1, ValuesConstants.MAX_MONTH) : rand.nextInt(1, bound.getMonthValue() + 1);
        int day = month != bound.getMonthValue() ? rand.nextInt(1, daysInMonths.get(month)) + 1 : rand.nextInt(1, bound.getDayOfMonth() + 1);
        int hour = day != bound.getDayOfMonth() ? rand.nextInt(1, ValuesConstants.MAX_HOURS) : rand.nextInt(1, bound.getHour() + 1);
        int minute = hour != bound.getHour() ? rand.nextInt(1, ValuesConstants.MAX_MINUTES) : rand.nextInt(1, bound.getMinute() + 1);
        int second = minute != bound.getMinute() ? rand.nextInt(1, ValuesConstants.MAX_SECONDS) : rand.nextInt(1, bound.getSecond());

        return Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    // in case if you don't want to use gpt to gen phone numbers
    // you can reformat it
    public String getPhone() {
        int countryCode = Double.valueOf(rand.nextDouble() * 1000).intValue();
        int operatorCode = Double.valueOf(rand.nextDouble() * 1000).intValue();
        int subscriberNumber = Double.valueOf(rand.nextDouble() * 10000000).intValue();
        return countryCode + " (" + operatorCode + ") " + subscriberNumber;
    }

    // don't forget to prepare file with GPT Client
    public String getString(StringsGenerator generator) {
        return generator.getString();
    }

    public static Random from(RandomGenerator generator) {
        return Random.from(generator);
    }

    @Override
    public void nextBytes(byte[] bytes) {
        rand.nextBytes(bytes);
    }

    @Override
    public int nextInt() {
        return rand.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return rand.nextInt(bound);
    }

    @Override
    public int nextInt(int origin, int bound) {
        return rand.nextInt(origin, bound);
    }

    @Override
    public long nextLong() {
        return rand.nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return rand.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return rand.nextFloat();
    }

    @Override
    public double nextDouble() {
        return rand.nextDouble();
    }

    @Override
    public double nextGaussian() {
        return rand.nextGaussian();
    }
}
