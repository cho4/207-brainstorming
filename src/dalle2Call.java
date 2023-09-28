import java.io.IOException;
import java.util.List;
import com.intellijava.core.controller.RemoteImageModel;
import com.intellijava.core.model.input.ImageModelInput;

// Testing OpenAI's DALL-E API endpoint, which returns an image given a prompt
public class dalle2Call {

    public static void main(String[] args) {

        System.out.println("Start calling the API!");

        try {

            // get the api key from https://openai.com/api/
            String apiKey = System.getenv("openai_key");
            /******************************/
            /** NOTE: For the output image from this dalle-2 api call, please check the outputFiles/teddy.png file**/
            /******************************/
            tryTheImageModel(apiKey, "teddy writing a blog in times square");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void tryTheImageModel(String apiKey, String prompt) throws IOException {
        // initiate the remote image model wrapper
        RemoteImageModel imageModel = new RemoteImageModel(apiKey, "openai");

        // prepare the input parameters
        ImageModelInput imageInput = new ImageModelInput.Builder(prompt)
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



