import java.io.IOException;
import java.util.List;
import com.intellijava.core.controller.RemoteImageModel;
import com.intellijava.core.model.input.ImageModelInput;


public class dalle2Call {

    public static void main(String[] args) {

        System.out.println("Start calling the API!");

        try {

            // get the api key from https://openai.com/api/
            String apiKey = "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";
            /******************************/
            /** NOTE: For the output image from this dalle-2 api call, please check the outputFiles/teddy.png file**/
            /******************************/
            tryTheImageModel(apiKey);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void tryTheImageModel(String apiKey) throws IOException {
        // initiate the remote image model wrapper
        RemoteImageModel imageModel = new RemoteImageModel(apiKey, "openai");

        // prepare the input parameters
        ImageModelInput imageInput = new ImageModelInput.Builder("teddy writing a blog in times square")
                .setNumberOfImages(2).setImageSize("1024x1024").build();

        // call the model
        List<String> images = imageModel.generateImages(imageInput);

        // print images links
        System.out.println("Images links:");
        for (String image : images) {
            System.out.println(image);
        }
    }
}



