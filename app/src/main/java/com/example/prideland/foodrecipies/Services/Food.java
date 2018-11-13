package com.example.prideland.foodrecipies.Services;

import com.example.prideland.foodrecipies.Constants;
import com.example.prideland.foodrecipies.models.Recipies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Food {
    public  static  void getFood(String food, Callback callback){
        OkHttpClient client =new OkHttpClient();

        HttpUrl.Builder urlBuilder=HttpUrl.parse(Constants.FOOD2FORK_BASE_URL).newBuilder();
         urlBuilder.addQueryParameter("key",Constants.FOOD2FORK_API);
         urlBuilder.addQueryParameter(Constants.FOOD2FORK_FOOD_QUERY_PARAMETER,food);

         String url=urlBuilder.build().toString();

        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call=client.newCall(request);
        call.enqueue(callback);
    }

    public  static ArrayList<Recipies> ProcessResults(Response response) throws IOException,JSONException {
        ArrayList<Recipies> recipies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject food2forkJSon = new JSONObject(jsonData);
            JSONArray businessJSon = food2forkJSon.getJSONArray("business");
            for (int i = 0; i < businessJSon.length(); i++) {
                JSONObject recipiesJSON = businessJSon.getJSONObject(i);
                String title = recipiesJSON.getString("title");
                String website = food2forkJSon.getString("url");
                double socialrank = recipiesJSON.getDouble("socialRanks");

                String imageUrl = food2forkJSon.getString("image_url");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return recipies;
    }
}
