package com.developer.mohamedraslan.hossamexams.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Adapter.AdapterShowUnits;
import com.developer.mohamedraslan.hossamexams.Adapter.Department_Refrence_Adapter;
import com.developer.mohamedraslan.hossamexams.Adapter.RecyclerItemTouchHelper;
import com.developer.mohamedraslan.hossamexams.Adapter.RecyclerItemTouchHelperUnit;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.Unites_Contract;
import com.developer.mohamedraslan.hossamexams.Dialog.AddUniteDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.NotificationDialog;
import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.UnitesPresnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.developer.mohamedraslan.hossamexams.Views.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unites_inDepartmentQ extends Fragment implements Unites_Contract.ParentUnitesUI,View.OnClickListener , RecyclerItemTouchHelperUnit.RecyclerItemTouchHelperListener{


    RecyclerView yearsindepquestionunite;
    FloatingActionButton add_unite;
    TextView     numofYearsquestionunite;
    ImageView    noUniteimage;
    UnitesPresnter unitesPresnter;
    String depName,yearName;
    String adminOruser = "";
    FirebaseAuth auth;
    AddUniteDialog addUniteDialog;
    AdapterShowUnits department_refrence_adapter;
    AnimatedDialog animatedDialog;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animatedDialog = new AnimatedDialog(getActivity());
        auth           = FirebaseAuth.getInstance();


        //   حنشوف لو هوا مستخدم حنخفي ال floatActionbutton لو ادمن حنخليه زي ما هوا


        Bundle b = getArguments();

        if (b!=null){

            depName         = b.getString("depName","");
            yearName        = b.getString("yearName","");
            unitesPresnter  = new UnitesPresnter(this);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v                   = inflater.inflate(R.layout.unites_question,container,false);

        String uID               = auth.getCurrentUser().getUid();
        ControlPanel.Title.setText("Unites");
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        unitesPresnter.tellModeltoChecking(uID);
        ControlPanel.SetNavChecked(1);
        yearsindepquestionunite  = v.findViewById(R.id.yearsindepquestionunite);
        add_unite                = v.findViewById(R.id.add_unite);
        numofYearsquestionunite  = v.findViewById(R.id.numofYearsquestionunite);
        noUniteimage             = v.findViewById(R.id.noUniteimage);
        add_unite.setOnClickListener(this);
        return v;
    }



    @Override
    public void detailsforUnits(String depName, String yearName, String unitName) {


        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){


            controlUI.showunitBankQuestion(depName,yearName,unitName);


        }
        // حنروح controlpanel عشان نعرض بقا الفراج بتاع الاسئله


    }

    @Override
    public void UniteTrueadded() {

        if (addUniteDialog!=null){
            addUniteDialog.getButtom().setEnabled(true);
            addUniteDialog.getProgress().setVisibility(View.INVISIBLE);
            ControlPanel.progressBar.setVisibility(View.INVISIBLE);
            addUniteDialog.dismiss();
            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showfragUnites(depName,yearName,1);

            }

        }




    }

    @Override
    public void UniteFalseadded() {

        if (addUniteDialog!=null){
            addUniteDialog.getButtom().setEnabled(true);
            addUniteDialog.getProgress().setVisibility(View.INVISIBLE);
            ControlPanel.progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Connection Poor.", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void showUnites(List<Unites_Model_Json> units) {



        Collections.sort(units, new MySalaryCompunit());
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        department_refrence_adapter = new AdapterShowUnits(units,getActivity(),this,depName,yearName);
        yearsindepquestionunite.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        yearsindepquestionunite.setLayoutManager(linearLayoutManager);
        //**\\
        yearsindepquestionunite.setAdapter(department_refrence_adapter);


        if (!adminOruser.equals("") && adminOruser.equals("Admin") ){

            ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelperUnit(0, ItemTouchHelper.LEFT, this,units);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(yearsindepquestionunite);

        }

        if (noUniteimage.isShown()){

            noUniteimage.setVisibility(View.GONE);
        }



    }

    @Override
    public void noUnitestoShow() {
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        noUniteimage.setVisibility(View.VISIBLE);


    }

    @Override
    public void whatproblemthatOcurr(String problem) {
        Toast.makeText(getActivity(), "لقد حدثت مشكله", Toast.LENGTH_SHORT).show();
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        noUniteimage.setVisibility(View.VISIBLE);

    }

    @Override
    public void nameUnite(String unitName) {

        unitesPresnter.tellmodeltocheckaboutUnit(depName,yearName,unitName);


    }

    @Override
    public void unitNotExistingofyouwanttoaddAdd(String unitName) {


        unitesPresnter.tellModeltoAddUnite(depName,yearName,unitName);

    }

    @Override
    public void unitalreadyheresorryCantaddthis() {

        if (addUniteDialog!=null){
            addUniteDialog.getButtom().setEnabled(true);
            addUniteDialog.getProgress().setVisibility(View.INVISIBLE);
            ControlPanel.progressBar.setVisibility(View.INVISIBLE);
            if (getActivity()!=null){

                com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                        = new com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog(getActivity(), "هذه الوحدة متواجده بالفعل لا يمكنك أضافتها مرة أخري.");
                alertDialog.show();

            }


        }



    }

    @Override
    public void poorConnection(String error) {



        if (addUniteDialog!=null){
            addUniteDialog.getButtom().setEnabled(true);
            addUniteDialog.getProgress().setVisibility(View.INVISIBLE);
            ControlPanel.progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "مشكله....", Toast.LENGTH_SHORT).show();
        }



    }




    @Override
    public void onClick(View view) {


        if (view == add_unite){

            //  حنضيف اليونيت في المكان بتاعها

            if (getActivity()!=null){

                addUniteDialog = new AddUniteDialog(getActivity(),R.style.PauseDialog,this);
                addUniteDialog.show();
            }



        }

    }




    class MySalaryCompunit implements Comparator<Unites_Model_Json> {

        @Override
        public int compare(Unites_Model_Json e1, Unites_Model_Json e2) {

            if(e1.getUnitTime() > e2.getUnitTime()){

                return 1;
            } else {
                return -1;
            }

        }
    }



    //   دول الي خاصين بالادمن لو هوا ادمن حنظهرله ال floatActionButton
    @Override
    public void imAdminShowMeButton() {

        adminOruser = "Admin";
        unitesPresnter.tellModeltogetUnits(depName,yearName);

        if (!add_unite.isShown()){

            add_unite.setVisibility(View.VISIBLE);
        }
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }

    @Override
    public void imUserHideButton() {

        adminOruser = "User";

        unitesPresnter.tellModeltogetUnits(depName,yearName);

        if (add_unite.isShown()){
            add_unite.setVisibility(View.GONE);
        }
        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

    }

    @Override
    public void setNumberOfunit(int uum) {

        numofYearsquestionunite.setText(uum + "\n" + "Unit");

    }












    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position, final List<Unites_Model_Json> units) {
        if (viewHolder instanceof AdapterShowUnits.UnitHolder) {
            // get the removed item name to display it in snack bar

            final Unites_Model_Json deletedItem = units.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove unitFrom database and refresh fragment (::)

            final String name = units.get(viewHolder.getAdapterPosition()).getUnitName();


            if (getActivity()!=null){


                final AlertDialog aleartDialog = new AlertDialog(getActivity(),getString(R.string.title),"اذا قمت بحذف الوحدة الدراسيه فسوف يتم حذف جميع محتويات الوحدة (الأختبارات - الأسئله - الطلبات - النتائج ) هل تريد الحذف؟");
                aleartDialog.show();
                aleartDialog.setCancelable(false);
                aleartDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        aleartDialog.dismiss();
                        animatedDialog.ShowDialog();
                        //  Removing all Details for Unit //
                        unitesPresnter.tellModeltoDeleteUnit(depName,yearName,name);


                    }
                });
                aleartDialog.btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (department_refrence_adapter!=null){

                            aleartDialog.dismiss();
                            department_refrence_adapter.removeItem(deletedIndex);
                            department_refrence_adapter.restoreItem(deletedItem, deletedIndex);

                        }





                    }
                });


            }







//        }
        }


    }

    @Override
    public void unitsAndDetailsofHisAreDeleted() {

        animatedDialog.Close_Dialog();

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.showfragUnites(depName,yearName,2);

        }

        // refresh fragment


        //  showDialog


    }

    @Override
    public void probelmunitNotDeleted(String p) {
        animatedDialog.Close_Dialog();
        /// showDialog
    }

}
