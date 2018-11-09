package com.example.mohamedraslan.hossamexams.Dialog;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

import com.example.mohamedraslan.hossamexams.R;


/**
 * Created by microprocess on 2018-09-29.
 */

public class AnimatedDialog  {
    Dialog dialog;
    ImageView image_Dialog;
    final Context context ;
    public  AnimatedDialog(final Context context){
        this.context = context ;


 }
    public void ShowDialog(){

    dialog = new Dialog(context);
    dialog.getWindow().setBackgroundDrawable(
            new ColorDrawable(android.graphics.Color.TRANSPARENT));
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog);
    image_Dialog = (ImageView) dialog.findViewById(R.id.image_loading);
    image_Dialog.animate().rotationYBy(1800f).setDuration(5000).setListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {

            image_Dialog.setRotation(0);
            image_Dialog.animate().rotation(1800f).setDuration(4000);

        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    });

    dialog.setCancelable(false);
    dialog.show();
}
    public void Close_Dialog(){

     if (dialog != null){

         image_Dialog.animate().cancel();
         dialog.dismiss();
     }
 }


}
