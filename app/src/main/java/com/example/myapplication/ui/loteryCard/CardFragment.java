package com.example.myapplication.ui.loteryCard;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import com.example.myapplication.clases.SingleRequestQueue;
import com.example.myapplication.clases.LoteryCard;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.clases.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<LoteryCard> myCard = new ArrayList<>();
    private ArrayList<LoteryCard> cartas = new ArrayList<>();
    private ArrayList<LoteryCard> cheked = new ArrayList<>();
    private RecyclerView recyclerView;
    private Random aleatory = new Random(System.currentTimeMillis());
    ImageView card;
    int cnum;
    CardAdapter cardAdapter;
    LoteryCard edcard;
    //    Drawable check = getActivity().getDrawable(R.drawable.ic_checked_foreground);
    int array[] = new int[16];
    Button btnr;
    int count = 0;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFragment newInstance(String param1, String param2) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card = view.findViewById(R.id.imageView2);
        btnr = view.findViewById(R.id.reset);
        btnr.setEnabled(false);
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCard();
                card.setEnabled(true);
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnum = (int) (Math.random() * (55 - 1) + 1);
                card.setImageResource(cartas.get(cnum - 1).getPicture());
                for (int i = 0; i < myCard.size(); i++) {
                    if (myCard.get(i).getNumber() == cnum) {
                        CardAdapter.ViewHolderCard holder = ((CardAdapter) recyclerView.getAdapter()).getViewByPosition(i);
                        View v = holder.itemView;
                        ImageView imgcard = v.findViewById(R.id.imgcard);
                        imgcard.setImageResource(R.drawable.ic_1elgallo);
                        if (cheked.get(i).getNumber() == cnum) {
                            cheked.remove(i);
                            count++;
                        }
                    }
                }
                if (count == 16) {
                    winer();
                }
            }
        });
        recyclerView = view.findViewById(R.id.rvcard);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        cartas.add(new LoteryCard(1, R.drawable.ic_1elgallo));
        cartas.add(new LoteryCard(2, R.drawable.ic_2eldiablito));
        cartas.add(new LoteryCard(3, R.drawable.ic_3ladama));
        cartas.add(new LoteryCard(4, R.drawable.ic_4elcatrin));
        cartas.add(new LoteryCard(5, R.drawable.ic_5elparaguas));
        cartas.add(new LoteryCard(6, R.drawable.ic_6lasirena));
        cartas.add(new LoteryCard(7, R.drawable.ic_7laescalera));
        cartas.add(new LoteryCard(8, R.drawable.ic_8labotella));
        cartas.add(new LoteryCard(9, R.drawable.ic_9barril));
        cartas.add(new LoteryCard(10, R.drawable.ic_10arbol));
        cartas.add(new LoteryCard(11, R.drawable.ic_11melon));
        cartas.add(new LoteryCard(12, R.drawable.ic_12elvaliente));
        cartas.add(new LoteryCard(13, R.drawable.ic_13elgorrito));
        cartas.add(new LoteryCard(14, R.drawable.ic_14lamuerte));
        cartas.add(new LoteryCard(15, R.drawable.ic_15lapera));
        cartas.add(new LoteryCard(16, R.drawable.ic_16labandera));
        cartas.add(new LoteryCard(17, R.drawable.ic_17elbandolon));
        cartas.add(new LoteryCard(18, R.drawable.ic_18elvioloncello));
        cartas.add(new LoteryCard(19, R.drawable.ic_19lagarza));
        cartas.add(new LoteryCard(20, R.drawable.ic_20elpajaro));
        cartas.add(new LoteryCard(21, R.drawable.ic_21lamano));
        cartas.add(new LoteryCard(22, R.drawable.ic_22labota));
        cartas.add(new LoteryCard(23, R.drawable.ic_23laluna));
        cartas.add(new LoteryCard(24, R.drawable.ic_24elcotorro));
        cartas.add(new LoteryCard(25, R.drawable.ic_25elborracho));
        cartas.add(new LoteryCard(26, R.drawable.ic_26elnegrito));
        cartas.add(new LoteryCard(27, R.drawable.ic_27elcorazon));
        cartas.add(new LoteryCard(28, R.drawable.ic_28lasandia));
        cartas.add(new LoteryCard(29, R.drawable.ic_29eltambor));
        cartas.add(new LoteryCard(30, R.drawable.ic_30elcamaron));
        cartas.add(new LoteryCard(31, R.drawable.ic_31lasjaras));
        cartas.add(new LoteryCard(32, R.drawable.ic_32elmusico));
        cartas.add(new LoteryCard(33, R.drawable.ic_33laara));
        cartas.add(new LoteryCard(34, R.drawable.ic_34elsoldado));
        cartas.add(new LoteryCard(35, R.drawable.ic_35laestrella));
        cartas.add(new LoteryCard(36, R.drawable.ic_36elcazo));
        cartas.add(new LoteryCard(37, R.drawable.ic_37elmundo));
        cartas.add(new LoteryCard(38, R.drawable.ic_38elapache));
        cartas.add(new LoteryCard(39, R.drawable.ic_39elnopal));
        cartas.add(new LoteryCard(40, R.drawable.ic_40elalacran));
        cartas.add(new LoteryCard(41, R.drawable.ic_41larosa));
        cartas.add(new LoteryCard(42, R.drawable.ic_42lacalavera));
        cartas.add(new LoteryCard(43, R.drawable.ic_43lacampana));
        cartas.add(new LoteryCard(44, R.drawable.ic_44elcantarito));
        cartas.add(new LoteryCard(45, R.drawable.ic_45elvenado));
        cartas.add(new LoteryCard(46, R.drawable.ic_46elsol));
        cartas.add(new LoteryCard(47, R.drawable.ic_47lacorona));
        cartas.add(new LoteryCard(48, R.drawable.ic_48lachalupa));
        cartas.add(new LoteryCard(49, R.drawable.ic_49elpino));
        cartas.add(new LoteryCard(50, R.drawable.ic_50elpescado));
        cartas.add(new LoteryCard(51, R.drawable.ic_51lapalma));
        cartas.add(new LoteryCard(52, R.drawable.ic_52lamaceta));
        cartas.add(new LoteryCard(53, R.drawable.ic_53elarpa));
        cartas.add(new LoteryCard(54, R.drawable.ic_54larana));

        generateCard();
    }

    private void generateCard() {
        array[0] = (int) (Math.random() * (54));
        for (int x = 1; x < 16; x++) {
            array[x] = (int) (Math.random() * (54));
            for (int j = 0; j < x; j++) {
                if (array[x] == array[j]) {
                    x--;
                }
            }
        }
        for (int i = 0; i < 16; i++) {
            LoteryCard card = cartas.get(array[i]);
            myCard.add(card);
            cheked.add(card);
            if (myCard.size() == 16) {
                cardAdapter = new CardAdapter(myCard);
                recyclerView.setAdapter(cardAdapter);
            }
        }
    }

    private void winer() {
        Toast.makeText(getContext(), "Completed", Toast.LENGTH_SHORT).show();
        card.setEnabled(false);
        btnr.setEnabled(true);
    }

    private void getCard(final View v) {
        String url = "http://ramiro174.com/api/cartaloteria";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    cnum = response.getInt("numero");
                    card.setImageResource(cartas.get(cnum - 1).getPicture());
                    for (int i = 0; i < myCard.size(); i++) {
                        if (myCard.get(i).getNumber() == cnum) {
                            CardAdapter.ViewHolderCard holder = ((CardAdapter) recyclerView.getAdapter()).getViewByPosition(i);
                            View v = holder.itemView;
                            ImageView imgcard = v.findViewById(R.id.imgcard);
                            imgcard.setImageResource(R.drawable.ic_1elgallo);
                            if (cheked.get(i).getNumber() == cnum) {
                                cheked.remove(i);
                                count++;
                            }
                        }
                    }
                    if (count == 16) {
                        winer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), String.valueOf(error.networkResponse.statusCode), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                String token = "Bearer " + Token.getToken();
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", token);
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        SingleRequestQueue.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
//                new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//
//
////                final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
////                String token = pref.getString("token", null);
//
//                params.put("Content-Type", "application/json; charset=UTF-8");
//                params.put("Authorization", "Bearer " + Token.getToken());
//                return params;
//
//
//            }
//        };
    }
}