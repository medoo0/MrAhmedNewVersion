package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.Unites_Contract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class AdapterShowUnits extends RecyclerView.Adapter<AdapterShowUnits.UnitHolder>{


    List<Unites_Model_Json>unites;
    Context context;
    Unites_Contract.ParentUnitesUI parentUnitesUI;
    String depName ;
    String yearName;

    public AdapterShowUnits(List<Unites_Model_Json> unites, Context context, Unites_Contract.ParentUnitesUI parentUnitesUI,String depName , String yearName) {
        this.unites   = unites;
        this.context  = context;
        this.yearName = yearName;
        this.depName  = depName;
        this.parentUnitesUI = parentUnitesUI;
    }

    @NonNull
    @Override
    public UnitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(context).inflate(R.layout.unitrec_layout,parent,false);

        return new UnitHolder(row,context,unites,parentUnitesUI,depName,yearName);

    }

    @Override
    public void onBindViewHolder(@NonNull UnitHolder holder, int position) {
        holder.setTextinTextView(unites.get(position).getUnitName());
      //  holder.animateCard();
        parentUnitesUI.setNumberOfunit(getItemCount());
        holder.presstoshowdetailsofYearsUnit.setScaleX(.9f);
        holder.presstoshowdetailsofYearsUnit.setScaleY(.9f);
        holder.presstoshowdetailsofYearsUnit.animate().scaleX(1f).scaleY(1f).setDuration(900);

    }

    @Override
    public int getItemCount() {
        return unites.size();
    }

    public static class UnitHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView presstoshowdetailsofYearsUnit,cardforGround;
        TextView name_of_yeartogetUnites;
        Context context;
        List<Unites_Model_Json>unotes;
        String depName,yearName;
        Unites_Contract.ParentUnitesUI parentUnitesUI;

        public UnitHolder(View itemView,Context context , List<Unites_Model_Json>unites, Unites_Contract.ParentUnitesUI parentUnitesUI,String depName , String yearName) {
            super(itemView);
            this.context = context;
            this.parentUnitesUI = parentUnitesUI;
            this.depName  = depName;
            this.yearName = yearName;
            this.unotes = unites;
            cardforGround                 = itemView.findViewById(R.id.cardforGround);
            presstoshowdetailsofYearsUnit = itemView.findViewById(R.id.presstoshowdetailsofYearsUnit);
            name_of_yeartogetUnites       = itemView.findViewById(R.id.name_of_yeartogetUnites);
            presstoshowdetailsofYearsUnit.setOnClickListener(this);
        }

        public void animateCard(){
            presstoshowdetailsofYearsUnit.setScaleX(.9f);
            presstoshowdetailsofYearsUnit.setScaleY(.9f);
            presstoshowdetailsofYearsUnit.animate().scaleX(1f).scaleY(1f).setDuration(900);

        }
        public void setTextinTextView(String text){

            name_of_yeartogetUnites.setText(text);
        }

        @Override
        public void onClick(View view) {
            if (view == presstoshowdetailsofYearsUnit){


                // هنا حنفتح الفراج بتاع الاسئله بقاااااااا ودا مهم جدا

                parentUnitesUI.detailsforUnits(depName,yearName,unotes.get(getAdapterPosition()).getUnitName());


//                Toast.makeText(context, unotes.get(getAdapterPosition()).getUnitName(), Toast.LENGTH_SHORT).show();

            }
        }
    }







    public void restoreItem(Unites_Model_Json item, int position) {
        unites.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
    public void removeItem(int position) {
        unites.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
}
