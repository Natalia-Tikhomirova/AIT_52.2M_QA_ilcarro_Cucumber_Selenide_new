package ilcarro.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.selenide.videorecorder.core.VideoRecorder;

public class Hooks {
    private final VideoRecorder videoRecorder = new VideoRecorder();

    /**
     * 🎥 Начинает запись видео перед сценарием.
     *
     * @param scenario Текущий сценарий теста
     */

    @Before
    public void beforeScenario(Scenario scenario) {
        scenario.log("🎥 Let's start recording video: " + scenario.getName());
        videoRecorder.start();
    }

    @After
    public void afterScenario(Scenario scenario) {
        String videoPath = String.valueOf(videoRecorder.videoUrl());
        videoRecorder.finish();
        scenario.log("🎥 The video of the test has been saved: " + videoPath);
    }
}
