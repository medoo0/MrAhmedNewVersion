package com.developer.mohamedraslan.hossamexams.Adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;

import java.util.List;

public class ItemTouchHelperStudentMangment  extends ItemTouchHelper.SimpleCallback {


    private RecyclerItemTouchHelperListenerStudent listener;
    List<FullRegisterForm> students ;

    public ItemTouchHelperStudentMangment(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListenerStudent listener, List<FullRegisterForm> units) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
        this.students    = units;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((StudentManagementAdapter.ViewHolder) viewHolder).Press_on_CardView;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((StudentManagementAdapter.ViewHolder) viewHolder).Press_on_CardView;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((StudentManagementAdapter.ViewHolder) viewHolder).Press_on_CardView;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((StudentManagementAdapter.ViewHolder) viewHolder).Press_on_CardView;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition(),students);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }







    public interface RecyclerItemTouchHelperListenerStudent {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position,List<FullRegisterForm> units);
    }
}
