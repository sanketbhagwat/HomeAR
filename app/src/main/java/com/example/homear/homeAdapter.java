package com.example.homear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder> {
    List<homeClass> homeClassList;
    Context context;

    public homeAdapter(List<homeClass> homeClassList, Context context) {
        this.homeClassList = homeClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public homeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_rv_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeAdapter.ViewHolder holder, int position) {
        holder.home_titles.setText(homeClassList.get(position).title);

        CategoriesAdapter categoriesAdapter;
        categoriesAdapter=new CategoriesAdapter(homeClassList.get(position).catgoriesList,context);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.rv_child.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return homeClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       RecyclerView rv_child;
       TextView home_titles;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_child=itemView.findViewById(R.id.rv_child);
            home_titles=itemView.findViewById(R.id.home_titles);

        }
    }
}
