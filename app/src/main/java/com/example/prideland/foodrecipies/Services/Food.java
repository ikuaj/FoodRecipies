package com.example.prideland.foodrecipies.Services;

import com.example.prideland.foodrecipies.Constants;
import com.example.prideland.foodrecipies.models.Recipies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    public  static ArrayList<Recipies> processResults(Response response) {
        ArrayList<Recipies> recipies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()){
            JSONObject food2forkJSon = new JSONObject(jsonData);
            JSONArray recipiesJson = food2forkJSon.getJSONArray("recipes");
            Type type = new TypeToken<List<Recipies>>() {}.getType();
            Gson gson = new GsonBuilder().create();
            recipies = gson.fromJson(recipiesJson.toString(), type);
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
