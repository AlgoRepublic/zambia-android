package com.algorelpublic.zambia.utils;

/**
 * Created by Aljuma on 5/9/2016.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * General utilities class.
 */
public final class Util {
    private static final String TAG = Util.class.getName();

    /**
     * disable it at release mode
     */
    public static final boolean LOG_ENABLED = true;


//    public static void showSnackBarWithAction(final Context context, View view, String msg,final int res){
//        Snackbar snackbar = Snackbar
//                .make(view, msg, Snackbar.LENGTH_LONG)
//                .setAction("INVITE", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ((ActivityBase) context).callFragment(res, FragmentSocialInvites.newInstance(0),"FragmentSocialInvites" );
//                    }
//                });
//
//        snackbar.show();
//    }

    /**
     * Deletes the fileOrDirectory passed and all its children.
     *
     * @param fileOrDirectory
     * @return
     */
    public static boolean deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        boolean success = deleteFile(fileOrDirectory);

        if (Util.LOG_ENABLED) {
            Log.v(TAG, fileOrDirectory.getPath() + ".delete=" + success);
        }
        return success;
    }

    /**
     * @param file
     * @return success
     */
    public static boolean deleteFile(File file) {
        // because the android 'open failed: EBUSY'
        // http://stackoverflow.com/questions/11539657/open-failed-ebusy-device-or-resource-busy
        final File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
        file.renameTo(to);
        boolean success = to.delete();
        return success;
    }


    /**
     * @param dir
     * @return the complete size of the directory or the file passed in Bytes.
     */
    public static long dirSize(File dir) {
        if (dir.exists()) {
            long result = 0;
            File[] fileList = dir.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // Recursive call if it's a directory
                if (fileList[i].isDirectory()) {
                    result += dirSize(fileList[i]);
                } else {
                    // Sum the file size in bytes
                    result += fileList[i].length();
                }
            }
            return result; // return the file size
        }
        return 0;
    }


    public static boolean equals(Object objOne, Object objTwo) {
        if (objOne == objTwo) {
            return true;
        }

        return objOne == null ? false : objOne.equals(objTwo);
    }

    /**
     * @param activity
     * @param title
     * @param message
     */
    public static void showToastTitleMessage(Activity activity, String title, String message) {
        int numberSpacesToAdd = (message.length() - title.length()) / 2 + 6;
        String blankSpaces = "";
        blankSpaces = blankSpaces.substring(0, numberSpacesToAdd);

        String stringToShow = blankSpaces + title + "\n" + message;

        Toast.makeText(activity.getApplicationContext(), stringToShow, Toast.LENGTH_LONG).show();
    }


    /**
     * Prints the string to the log verbose, avoiding the logcat maxlength
     * restriction. The string is not truncated.
     *
     * @param tag
     * @param string
     */
    public static void logv(String tag, String string) {
        if (!Util.LOG_ENABLED) {
            return;
        }
        if (TextUtils.isEmpty(string) || string.length() <= 4000) {
            Log.v(tag, string);
        } else {
            int chunkCount = string.length() / 4000; // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= string.length()) {
                    Log.v(tag, "chunk " + i + " of " + chunkCount + ":" + string.substring(4000 * i));
                } else {
                    Log.v(tag, "chunk " + i + " of " + chunkCount + ":" + string.substring(4000 * i, max));
                }
            }
        }
    }


    /**
     * @param context
     */
    public static void restartApplication(Context context) {
        // free resources
//        FFMContext.shutdown();
//
//        // recreate context
//        FFMContext.start(context);
//
//        // call the main activity
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setClass(context, LoginToLoadingActivity.class);
//        context.startActivity(intent);
    }

    /**
     * Goes to the activity indicated with 'clazz', from the activity
     * 'fromActiviy' and erases navigation history.
     *
     * @param fromActivity
     * @param clazz
     */
    public static void gotoActivityClearTop(Activity fromActivity, Class clazz, Bundle extras) {
        Intent intent = new Intent();
        if (extras != null)
            intent.putExtras(extras);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(fromActivity, clazz);
        fromActivity.startActivity(intent);
        fromActivity.finish();
    }

    /**
     * Move file from one path to another path
     *
     * @param path_source
     * @param path_destination
     * @throws IOException
     */

    public static void moveFile(String path_source, String path_destination) throws IOException {
        File file_Source = new File(path_source);
        File file_Destination = new File(path_destination);

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(file_Source).getChannel();
            destination = new FileOutputStream(file_Destination).getChannel();

            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size - count)) < size) ;
            file_Source.delete();
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }


    public static void removeFragmentFromStack(Context context, Fragment fragment) {
        if (fragment != null) {
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove(fragment);
            trans.commit();
            manager.popBackStack();
        }
    }

    public static void removeAllFragmentFromStack(Context context) {
        {
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            int count = manager.getBackStackEntryCount();
            for (int index = 0; index < count; index++)
                manager.popBackStack();
        }
    }

    /**
     * Goes to the activity indicated with 'clazz', from the activity
     * 'fromActiviy' . and puts bundle in intent if not null
     *
     * @param fromActivity
     * @param clazz
     * @param extras
     */
    public static void gotoActivity(Activity fromActivity, Class clazz, Bundle extras) {
        Intent intent = new Intent();
        if (extras != null)
            intent.putExtras(extras);
        intent.setClass(fromActivity, clazz);
        fromActivity.startActivity(intent);

    }

    /**
     * Restore the saved application state if needed.
     *
     * @param context
     * @param savedInstanceState
     */
    public static void restoreSavedApplicationState(Context context, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Restore value of members from saved state
//            FFMContext.restoreIfNeeded(context, savedInstanceState);
        }
    }

    /**
     * Show loading with shimmer effect like facebook
     * @param shimmerView
     */
  /*  public static void applyShimmerEffectOnView(ShimmerFrameLayout shimmerView){
        shimmerView.setAngle(ShimmerFrameLayout.MaskAngle.CW_90);
        shimmerView.setDuration(1000);
        shimmerView.startShimmerAnimation();
    }*/


    /**
     * Encapsulated behavior for dismissing Dialogs, because of several android problems
     * related.
     */
    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (IllegalArgumentException e) // even sometimes happens?: http://stackoverflow.com/questions/12533677/illegalargumentexception-when-dismissing-dialog-in-async-task
            {
                Log.i(TAG, "Error when attempting to dismiss dialog, it is an android problem.", e);
            }
        }
    }

    public static void showkeyPad(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Pass a view to method and it will hide the keyboard if opened.
     *
     * @param view
     */
    public static void hidekeyPad(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public static Fragment getCurrentFragment(Context context) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        Fragment currentFragment = null;
        if (fragmentManager.getBackStackEntryCount() > 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            currentFragment = fragmentManager
                    .findFragmentByTag(fragmentTag);
        }
        return currentFragment;
    }

    //    /**
//     * Creates a controller for drawee Image view and returns back with customization
//     * @param uri
//     * @param view
//     * @return
//     */
//    public static DraweeController returnController(String uri, SimpleDraweeView view){
//        if(uri==null)
//            uri = "";
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
//                .build();
//        return Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
//                .setOldController(view.getController())
//               // .setTapToRetryEnabled(true)
//                .build();
//
//    }
    public static int getDeviceWidth(Context context) {
        int _widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        return _widthPixels;
    }

    public static int getDeviceHeight(Context context) {
        int _heigthPixels = 0;
        try {
            _heigthPixels = context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {

        }
        return _heigthPixels;
    }

    public static void getKeyHash(Context context) {
        /* HASH KEY CALCULATOR */

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.lizergroup.pricelizer", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64
                        .encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);

            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MY KEY HASH:", "NAME NOT FOUND");
        } catch (NoSuchAlgorithmException e) {
            Log.e("MY KEY HASH:", "NO SUCH ALGORITHM");
        }

    }

    public static void hideAView(final View layout, int afterMillisecs) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.animate()
                        .alpha(0.0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                layout.setVisibility(View.GONE);
                            }
                        });
            }
        }, 4000);
    }

    //    public static void makeMylinkClickable(final String text, final TextView tv, Integer color,
//                                           String userId,String name) {
//        if (text == null || tv == null)
//            return;
//        int start = 0, end;
//        final List<String> items = Arrays.asList(text.split("\\s"));
//        final List<String> nameList = Arrays.asList(name.trim().split("\\s"));
//        final SpannableString ss = new SpannableString(text);
//        for (final String item : items) {
//            for (final String nameStr : nameList) {
//                if (userId != null && item.trim().contains(nameStr)) {
//                    end = start + item.length();
//                    if (start < end) {
//                        ss.setSpan(new MyClickableSpan(userId, item), start,
//                                end, 0);
//                        if (color != null && color == 0xcc00d0de)
//                            ss.setSpan(
//                                    new ForegroundColorSpan(Util.getHexColor(new int[]{153, 153, 153
//                                    })), start, end,
//                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                }
//            }
//            start += item.length() + 1;
//        }
//
//        tv.setMovementMethod(LinkMovementMethod.getInstance());
//        tv.setText((ss), TextView.BufferType.SPANNABLE);
//    }
//    public static void followProductLinkClickable(final String text, final TextView tv, Integer color,
//                                                 String userId,String name,String pId,String pName) {
//        if (text == null || tv == null)
//            return;
//        int start = 0, end;
//        final List<String> items = Arrays.asList(text.split("\\s"));
//        final List<String> nameList = Arrays.asList(name.trim().split("\\s"));
//        final SpannableString ss = new SpannableString(text);
//        for (final String item : items) {
//            for (final String nameStr : nameList) {
//                if (userId != null && item.trim().contains(nameStr)) {
//                    end = start + item.length();
//                    if (start < end) {
//                        ss.setSpan(new MyClickableSpan(userId, item), start,
//                                end, 0);
//                        if (color != null && color == 0xcc00d0de)
//                            ss.setSpan(
//                                    new ForegroundColorSpan(Util.getHexColor(new int[]{153, 153, 153
//                                    })), start, end,
//                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                }
//            }
//            start += item.length() + 1;
//        }
//
//        tv.setMovementMethod(LinkMovementMethod.getInstance());
//        tv.setText((ss), TextView.BufferType.SPANNABLE);
//    }
//    public static void commentProductLinkClickable(final String text, final TextView tv, Integer color,
//                                                  String userId,String name,String pId,String pName) {
//        if (text == null || tv == null)
//            return;
//        int start = 0, end;
//        final List<String> items = Arrays.asList(text.split("\\s"));
//        final List<String> nameList = Arrays.asList(name.trim().split("\\s"));
//        final SpannableString ss = new SpannableString(text);
//        for (final String item : items) {
//            for (final String nameStr : nameList) {
//            if (userId != null &&  item.trim().contains(nameStr)) {
//                end = start + item.length();
//                if (start < end) {
//                    ss.setSpan(new MyClickableSpan(userId, item), start,
//                            end, 0);
//                    if (color != null && color == 0xcc00d0de)
//                        ss.setSpan(
//                                new ForegroundColorSpan(Util.getHexColor(new int[]{153, 153, 153
//                                })), start, end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//            }
//            if (pId != null && pName.trim().trim().contains(item.trim())) {
//                end = start + item.length();
//                if (start < end) {
//                    ss.setSpan(new MyClickableSpan(pId, pName,3), start,
//                            end, 0);
//                    if (color != null && color == 0xcc00d0de)
//                        ss.setSpan(
//                                new ForegroundColorSpan(Util.getHexColor(new int[]{153, 153, 153
//                                })), start, end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//            start += item.length() + 1;
//        }
//
//        tv.setMovementMethod(LinkMovementMethod.getInstance());
//        tv.setText((ss), TextView.BufferType.SPANNABLE);
//    }
    public static int getHexColor(int[] color) {
        return Color.rgb(color[0], color[1], color[2]);

    }


    public static int getHexArgbColor(int[] color) {
        return Color.argb(color[0], color[1], color[2],
                color[3]);

    }

    // method to check whether an app exists or not
    public static void checkIfAppExists(Context context, Intent appIntent, String appName) {
        if (appIntent.resolveActivity(context.getPackageManager()) != null) {
// start the activity if the app exists in the system
            context.startActivity(appIntent);

        } else {
// tell the user the app does not exist
            Toast.makeText(context, appName + " app does not exist!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

   /* public static float parseToFloat(float val){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(val));


    }*/

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static void setDrawableSelectedRound(final String startColor,
                                                final String endColor, View view, Integer left, Integer top,
                                                Integer right, Integer bottom, String borderColor) {
        GradientDrawable p = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor(startColor),
                        Color.parseColor(endColor)});
        if (borderColor != null)
            p.setStroke(1, Color.parseColor(borderColor));
        if (left != null || top != null || right != null || bottom != null)
            p.setCornerRadii(new float[]{left, left, top, top, right, right,
                    bottom, bottom});
        try {
            view.setBackground(p);
        } catch (Exception e) {
            view.setBackgroundDrawable(p);
        }
    }

}

