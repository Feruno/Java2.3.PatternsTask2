package test;

import dataTest.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class AppIbank {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void appSuccessfulPath() {

    }
}
