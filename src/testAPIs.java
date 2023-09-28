import java.io.IOException;

public class testAPIs {

    public static void main(String[] args) {
        // Testing Cohere API
        System.out.println(CohereAPI.getResponse("hello there"));

        // Testing DALL-E API
        try {
            dalle2Call.tryTheImageModel(System.getenv("openai_key"), "person staring into the distance");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
