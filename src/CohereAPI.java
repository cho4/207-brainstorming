import okhttp3.*;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class CohereAPI {

    public static void main(String[] args) {
        String completion = getResponse("How are you doing today?");
        System.out.print(completion);

        // EXAMPLE completions: " As an AI language model, I don't have any emotions or feelings, so I can't say"
        // EXAMPLE completions: " As an artificial intelligence chatbot, I don't experience the emotions or physical sensations associated with the concept of"
    }

    private static final String COHERE_API_URL = "https://api.cohere.ai/v1/generate";
    private static final String API_TOKEN = System.getenv("cohere_key");

    public static String getResponse(String prompt) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = "{\n" +
                "  \"max_tokens\": 20,\n" +
                "  \"truncate\": \"END\",\n" +
                "  \"return_likelihoods\": \"NONE\",\n" +
                "  \"prompt\": \"" + prompt + "\"\n" +
                "}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url(COHERE_API_URL)
                .addHeader("authorization", "Bearer " + API_TOKEN)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            String completion = responseBody.getJSONArray("generations").getJSONObject(0).get("text").toString();

            return completion;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
