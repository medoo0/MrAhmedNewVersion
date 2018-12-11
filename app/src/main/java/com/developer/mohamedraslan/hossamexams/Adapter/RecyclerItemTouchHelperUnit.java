package com.developer.mohamedraslan.hossamexams.Adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;

import java.util.List;

public class RecyclerItemTouchHelperUnit extends ItemTouchHelper.SimpleCallback{









    private RecyclerItemTouchHelperListener listener;
    List<Unites_Model_Json> units ;

    public RecyclerItemTouchHelperUnit(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener,List<Unites_Model_Json> units) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
        this.units    = units;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((AdapterShowUnits.UnitHolder) viewHolder).cardforGround;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((AdapterShowUnits.UnitHolder) viewHolder).cardforGround;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((AdapterShowUnits.UnitHolder) viewHolder).presstoshowdetailsofYearsUnit;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((AdapterShowUnits.UnitHolder) viewHolder).presstoshowdetailsofYearsUnit;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition(),units);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }







    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position,List<Unites_Model_Json> units);
    }
}
