package com.XYZBank.Listner;

import io.qameta.allure.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.XYZBank.base.BasePage.getDriver;

public class AllureAttachments {

    @Attachment(value = "Entirepage Screenshot of {0}", type = "image/png")
    public static byte[] saveFullPageScreenshot(String name, String folderPath) {
        try {
            BufferedImage image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getDriver()).getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            // Create the folder if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Specify the file path including the folder, name, and extension
            String filePath = folderPath + name + ".png";

            // Save the screenshot to the specified file path
            ImageIO.write(image, "png", new File(filePath));

            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to capture screenshot for " + name, e);
        }
    }
}
