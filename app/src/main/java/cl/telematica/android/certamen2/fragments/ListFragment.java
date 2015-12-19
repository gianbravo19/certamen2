package cl.telematica.android.certamen2.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen2.HttpServerConnection;
import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.UIAdapter;
import cl.telematica.android.certamen2.value;

import static android.support.v7.widget.LinearLayoutManager.*;

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context thiscontext;
    /**
     * New instance of ListFragment
     *
     * @return new instance of ListFragment
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_list, null);
        thiscontext = container.getContext();

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //Ver por que marca error
        mLayoutManager = new LinearLayoutManager(thiscontext, VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnection().connectToServer("http://api.icndb.com/jokes/random/20", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    //System.out.println(result);

                    // specify an adapter (see also next example)
                    mAdapter = new UIAdapter(getLista(result));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_list,
         * y donde prácticamente se debe hacer el desarrollo
        */

        return mainView;
    }

    private List<value> getLista(String result){

        List<value> listavalores = new ArrayList<value>();

        try {

            JSONObject objeto = new JSONObject(result);
            JSONArray valuesArray = objeto.getJSONArray("value");

            int size = valuesArray.length();

            for(int i = 0; i < size; i++){

                JSONObject objetoValues = valuesArray.getJSONObject(i);

                value valor = new value();

                valor.setId(objetoValues.getInt("id"));
                valor.setJoke(objetoValues.getString("joke"));

                listavalores.add(valor);
            }

            return listavalores;

        } catch (JSONException e) {
            e.printStackTrace();
            return listavalores;
        }
    }
}
