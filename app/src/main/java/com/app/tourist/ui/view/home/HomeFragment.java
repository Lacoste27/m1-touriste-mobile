package com.app.tourist.ui.view.home;

import com.app.tourist.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.databinding.FragmentHomeBinding;
import com.app.tourist.ui.adapter.ItemsAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView.Adapter adapterPopular, adapterNew;
    private RecyclerView recyclerViewPopular, recyclerViewNew;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View views = inflater.inflate(R.layout.fragment_home, container, false);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.view = views;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ItemsModel> itemsArrayList = new ArrayList<>();

        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));
        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));
        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));

        recyclerViewPopular = (RecyclerView) view.findViewById(R.id.viewPopular);
        recyclerViewNew = (RecyclerView) view.findViewById(R.id.viewNews);

        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterNew = new ItemsAdapter(itemsArrayList);
        adapterPopular = new ItemsAdapter(itemsArrayList);

        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewNew.setAdapter(adapterNew);
    }

    public void setRecyclerView(){
        ArrayList<ItemsModel> itemsArrayList = new ArrayList<>();

        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));
        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));
        itemsArrayList.add(new ItemsModel("House with a great view", "San Francisco, CA 94110"," This is 2 bed /1 bath home boasts an enormous, \n", 2,1,840353, "pic1", true));

        recyclerViewPopular = (RecyclerView) this.view.findViewById(R.id.viewPopular);
        recyclerViewNew = (RecyclerView) this.view.findViewById(R.id.viewNews);

        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterNew = new ItemsAdapter(itemsArrayList);
        adapterPopular = new ItemsAdapter(itemsArrayList);

        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewNew.setAdapter(adapterNew);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}